package es.joseluisgs.dam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {
    private String id;
    private String nombre;
    private double presupuesto;
    private Programador jefe;
    private List<Programador> programadores;

    @Override
    public String toString() {
        return "Departamento{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", presupuesto=" + presupuesto +
                ", jefe=" + jefe +
                ", programadores=" + programadores +
                '}';
    }
}
