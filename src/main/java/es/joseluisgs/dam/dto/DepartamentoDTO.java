package es.joseluisgs.dam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoDTO {
    private String id;
    private String nombre;
    private double presupuesto;
    private ProgramadorDTO jefe;
    private List<ProgramadorDTO> programadores;
}
