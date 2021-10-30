package es.joseluisgs.dam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramadorModifyDTO {
    private String id;
    private String nombre;
    private int experiencia;
    private String departamento_id;
    private double salario;
    private String perfil;
    private Date fechaAlta;
    private List<String> lenguajes;
}
