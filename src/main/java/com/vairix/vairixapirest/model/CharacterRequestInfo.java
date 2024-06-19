package com.vairix.vairixapirest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Date;

/**
 * Entity que guarda la info de los request relacionados con los personajes.
 */
@Entity
@Table(name = "CHARACTER_REQUEST_INFO")
@Data
public class CharacterRequestInfo {

    /**
     * ID del evento, es un codigo autogenerado
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Tiempo en que se registro el evento
     */
    private Date timestamp;
    /**
     * Nombre del request realizado
     */
    private String serviceNameRequested;

}
