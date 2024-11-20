package co.edu.javeriana.parcial.backendp.service;

import co.edu.javeriana.parcial.backendp.dto.ContratoDTO;
import co.edu.javeriana.parcial.backendp.entity.Contrato;
import co.edu.javeriana.parcial.backendp.exceptions.ResourceNotFoundException;
import co.edu.javeriana.parcial.backendp.repository.ContratoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratoService {

    private static final String CONTRATO_NOT_FOUND = "Contrato no encontrado con ID: ";

    private final ContratoRepository contratoRepository;
    private final ModelMapper modelMapper;

    public ContratoService(ContratoRepository contratoRepository, ModelMapper modelMapper) {
        this.contratoRepository = contratoRepository;
        this.modelMapper = modelMapper;
    }

    // Obtener todos los contratos
    public List<ContratoDTO> getAll() {
        List<Contrato> contratos = contratoRepository.findAll();
        return contratos.stream()
                .map(contrato -> modelMapper.map(contrato, ContratoDTO.class))
                .collect(Collectors.toList());
    }

    // Obtener un contrato por ID
    public ContratoDTO getById(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CONTRATO_NOT_FOUND + id));
        return modelMapper.map(contrato, ContratoDTO.class);
    }

    // Crear un nuevo contrato
    public ContratoDTO create(ContratoDTO contratoDTO) {
        Contrato contrato = modelMapper.map(contratoDTO, Contrato.class);
        contrato = contratoRepository.save(contrato);
        return modelMapper.map(contrato, ContratoDTO.class);
    }

    // Actualizar un contrato existente
    public ContratoDTO update(Long id, ContratoDTO contratoDTO) {
        // Buscar el contrato existente
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con ID: " + id));
    
        // Mapear solo los campos presentes en el DTO a la entidad
        modelMapper.map(contratoDTO, contrato);
    
        // Guardar los cambios
        Contrato updatedContrato = contratoRepository.save(contrato);
    
        // Retornar el DTO actualizado
        return modelMapper.map(updatedContrato, ContratoDTO.class);
    }
    
    
    

    // Eliminar un contrato (eliminación lógica o física según el caso)
    public void delete(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CONTRATO_NOT_FOUND + id));

        contratoRepository.delete(contrato);
    }

    // Obtener contratos por rango de valor
    public List<ContratoDTO> getByValueRange(Double minValue, Double maxValue) {
        List<Contrato> contratos = contratoRepository.findByValorBetween(minValue, maxValue);
        return contratos.stream()
                .map(contrato -> modelMapper.map(contrato, ContratoDTO.class))
                .collect(Collectors.toList());
    }

    // Validar si un contrato está activo basándose en las fechas
    public boolean isContratoActivo(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CONTRATO_NOT_FOUND + id));
        return contrato.getFechaFinal() != null && contrato.getFechaFinal().after(new java.util.Date());
    }
}
