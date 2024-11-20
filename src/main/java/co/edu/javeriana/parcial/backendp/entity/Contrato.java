package co.edu.javeriana.parcial.backendp.entity;

import lombok.Data;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Data
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificador;
    private Double valor;

    private String nombreContratante;
    private String documentoContratante;

    private String nombreContratantista;
    private String documentoContratantista;

    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
}
