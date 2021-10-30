package es.joseluisgs.dam.mapper;

import es.joseluisgs.dam.dto.DepartamentoDTO;
import es.joseluisgs.dam.model.Departamento;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

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

}