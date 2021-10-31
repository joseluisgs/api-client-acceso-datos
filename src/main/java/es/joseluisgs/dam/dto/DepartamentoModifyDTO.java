package es.joseluisgs.dam.dto;

import lombok.Data;

@Data
public class DepartamentoModifyDTO {
    private String id;
    private String nombre;
    private double presupuesto;
    private String id_jefe;
    private String id_programador;
}
