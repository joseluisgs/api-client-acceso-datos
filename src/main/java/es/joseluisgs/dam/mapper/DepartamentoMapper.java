package es.joseluisgs.dam.mapper;

import es.joseluisgs.dam.dto.DepartamentoDTO;
import es.joseluisgs.dam.dto.ProgramadorDTO;
import es.joseluisgs.dam.model.Departamento;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DepartamentoMapper extends BaseMapper<Departamento, DepartamentoDTO> {
    ProgramadorMapper programadorMapper = new ProgramadorMapper();

    @Override
    public Departamento fromDTO(DepartamentoDTO item) {
        return new Departamento(item.getId(), item.getNombre(), item.getPresupuesto(),
                programadorMapper.fromDTO(item.getJefe()), programadorMapper.fromDTO(item.getProgramadores()));
    }

    @Override
    public DepartamentoDTO toDTO(Departamento item) {
        return new DepartamentoDTO(item.getId(), item.getNombre(), item.getPresupuesto(),
                programadorMapper.toDTO(item.getJefe()), programadorMapper.toDTO(item.getProgramadores()));
    }

    // OJO aquí podría usar librerías de alto nivel como GSON o Jackson, pero he decidido hacer el mapeo manual
    // Esto en el fondo es lo que esta haciendo Retrofit por nosotros...
    public List<DepartamentoDTO> fromJSONArray(JSONArray items) {
        // Se puede hacer como  List<ProgramadorDTO> list = IntStream.range(0, items.length())
        // .mapToObj(items::getJSONObject).map(this::fromJSON).collect(Collectors.toList());
        List<DepartamentoDTO> list = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            DepartamentoDTO dto = fromJSON(item);
            list.add(dto);
        }
        return list;
    }

    public DepartamentoDTO fromJSON(JSONObject item) {
        DepartamentoDTO dto = new DepartamentoDTO(
                item.getString("id"),
                item.getString("nombre"),
                item.getInt("presupuesto"),
                programadorMapper.fromJSON(item.getJSONObject("jefe")),
                programadorMapper.fromJSONArray(item.getJSONArray("programadores")));
        return dto;
    }

}