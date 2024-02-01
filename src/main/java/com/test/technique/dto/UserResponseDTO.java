package com.test.technique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DTO for representing user responses.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private String fathersLastname;
    private String mothersLastname;
    private String phoneNumber;
    private String prefixNumber;
    private String email;
    private Date birthdate;
    private Date createdAt;
    private Date updatedAt;
    private AddressDTO address;
    /**
     * Converte la fecha a una cadena en formato ISO 8601.
     *
     * @param date Fecha a convertir
     * @return Cadena en formato ISO 8601 o null si la fecha es null
     */
    private String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return dateFormat.format(date);
    }
}