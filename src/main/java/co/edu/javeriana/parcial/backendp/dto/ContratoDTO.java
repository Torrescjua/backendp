package co.edu.javeriana.parcial.backendp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ContratoDTO {
    private Long id;
    private String identificador;
    private Double valor;
    private String nombreContratante;
    private String documentoContratante;
    private String nombreContratantista;
    private String documentoContratantista;
    private Date fechaInicial;
    private Date fechaFinal;
}
