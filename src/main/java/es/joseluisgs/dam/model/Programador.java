package es.joseluisgs.dam.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Programador {
    // Con serialized name indicamos que se va a serializar con el nombre que le hemos puesto
    // con expose es que loe exponemos
    // Si no ponemos nada es por defecto
    private String id;
    private String nombre;
    private int experiencia;
    private String departamento;
    private double salario;
    private String perfil;
    private LocalDate fechaAlta;
    private List<String> lenguajes;

    @Override
    public String toString() {
        return "Programador{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", experiencia=" + experiencia +
                ", departamento='" + departamento + '\'' +
                ", salario=" + salario +
                ", perfil='" + perfil + '\'' +
                ", fechaAlta=" + fechaAlta +
                /// IMPORTANTE, muestrale que pasa y como pudes estar muy entretenido :):)
                ", lenguajes=(" + String.join(", ", lenguajes) +
                ")}";
    }
}

