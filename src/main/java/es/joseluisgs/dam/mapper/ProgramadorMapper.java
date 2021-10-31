package es.joseluisgs.dam.mapper;

import es.joseluisgs.dam.dto.ProgramadorDTO;
import es.joseluisgs.dam.model.Programador;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramadorMapper extends BaseMapper<Programador, ProgramadorDTO> {

    @Override
    public Programador fromDTO(ProgramadorDTO item) {
        return new Programador(item.getId(), item.getNombre(), item.getExperiencia(), item.getDepartamento(),
                item.getSalario(), item.getPerfil(),
                // Pasamos de date a LocalDate, hecho a posta para ver la importancia de los Mapper y diferencias de datos entre DTO y Modelo
                item.getFechaAlta().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                //item.getFechaAlta(),
                item.getLenguajes());
    }

    @Override
    public ProgramadorDTO toDTO(Programador item) {
        return new ProgramadorDTO(item.getId(), item.getNombre(), item.getExperiencia(), item.getDepartamento(),
                item.getSalario(), item.getPerfil(),
                Date.from(item.getFechaAlta().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                //item.getFechaAlta(),
                item.getLenguajes());
    }

    // OJO aquí podría usar librerías de alto nivel como GSON o Jackson, pero he decidido hacer el mapeo manual
    // Esto en el fondo es lo que esta haciendo Retrofit por nosotros...
    public List<ProgramadorDTO> fromJSONArray(JSONArray items) {
        // Se puede hacer como  List<ProgramadorDTO> list = IntStream.range(0, items.length())
        // .mapToObj(items::getJSONObject).map(this::fromJSON).collect(Collectors.toList());
        List<ProgramadorDTO> list = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            ProgramadorDTO dto = fromJSON(item);
            list.add(dto);
        }
        return list;
    }

    public ProgramadorDTO fromJSON(JSONObject item) {
        ProgramadorDTO dto = new ProgramadorDTO(
                item.getString("id"),
                item.getString("nombre"),
                item.getInt("experiencia"),
                item.getString("departamento"),
                item.getInt("salario"),
                item.getString("perfil"),
                Date.from(Instant.parse(item.getString("fechaAlta"))),
                item.getJSONArray("lenguajes").toList().stream().map(Object::toString).collect(Collectors.toList()));

        return dto;
    }

}