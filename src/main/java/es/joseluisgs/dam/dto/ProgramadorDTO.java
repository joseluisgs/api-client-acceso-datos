package es.joseluisgs.dam.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramadorDTO {
    // Con serialized name indicamos que se va a serializar con el nombre que le hemos puesto
    // con expose es que loe exponemos
    // Si no ponemos nada es por defecto
    private String id;
    private String nombre;
    private int experiencia;
    private String departamento;
    private double salario;
    private String perfil;
    private Date fechaAlta;
    private List<String> lenguajes;

}
