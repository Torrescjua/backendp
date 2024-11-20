package co.edu.javeriana.parcial.backendp.repository;

import co.edu.javeriana.parcial.backendp.entity.Contrato;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findByValorBetween(Double minValue, Double maxValue);
}