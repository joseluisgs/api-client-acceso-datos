package es.joseluisgs.dam.graphql;

import es.joseluisgs.dam.dto.DepartamentoDTO;
import es.joseluisgs.dam.dto.DepartamentoModifyDTO;
import es.joseluisgs.dam.dto.ProgramadorDTO;
import es.joseluisgs.dam.dto.ProgramadorModifyDTO;
import es.joseluisgs.dam.mapper.DepartamentoMapper;
import es.joseluisgs.dam.mapper.ProgramadorMapper;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class DepartamentoService {
    private static DepartamentoService instance = null;
    DepartamentoMapper mapperDep = new DepartamentoMapper();
    ProgramadorMapper mapperProg = new ProgramadorMapper();

    private DepartamentoService() {
    }
    public static DepartamentoService getInstance() {
        if (instance == null) {
            instance = new DepartamentoService();
        }
        return instance;
    }

    public List<DepartamentoDTO> getAll() throws Exception {
        GraphQLClient client = GraphQLClient.getInstance();

        String query =  "{\"query\":\"query Query {\\n  getDepartamentos {\\n    id\\n    nombre\\n    presupuesto\\n    " +
                "jefe {\\n      id\\n nombre\\n      experiencia\\n      salario\\n      perfil\\n      departamento\\n fechaAlta\\n    lenguajes\\n }\\n    " +
                "programadores {\\n      id\\n nombre\\n      experiencia\\n      salario\\n      perfil\\n      departamento\\n fechaAlta\\n      lenguajes\\n    }\\n  " +
                "}\\n}\"" +
                ",\"variables\":{}}";

        Response response = client.executeQuery(query);

        if (response.isSuccessful()) {
            // Aquí ya tenemos la respuesta., vamos a pasarla a JSON
            JSONObject json = new JSONObject(response.body().string());
            // Ahora tenemos que sacar el array de programadores
            JSONArray dep = json.getJSONObject("data").getJSONArray("getDepartamentos");
            //System.out.println(prog);
            return mapperDep.fromJSONArray(dep);
        } else {
            throw new Exception("Error: " + response.code());
        }
    }

    public DepartamentoDTO getByID(String idDepartamento) throws Exception {
        GraphQLClient client = GraphQLClient.getInstance();
        String query ="{\"query\":\"query Query($getDepartamentoByIdId: ID!) {\\n  getDepartamentoByID(id: $getDepartamentoByIdId) " +
                "{\\n    id\\n    nombre\\n    presupuesto\\n    " +
                "jefe {\\n     id\\n nombre\\n      experiencia\\n      salario\\n      perfil\\n      departamento\\n fechaAlta\\n    lenguajes\\n }\\n    " +
                "programadores {\\n      id\\n nombre\\n      experiencia\\n      salario\\n      perfil\\n      departamento\\n fechaAlta\\n      lenguajes\\n    }\\n  }\\n}\"" +
                ",\"variables\":{\n  \"getDepartamentoByIdId\": "+idDepartamento+"\n}}";

        Response response = client.executeQuery(query);

        if (response.isSuccessful()) {
            // Aquí ya tenemos la respuesta., vamos a pasarla a JSON
            JSONObject json = new JSONObject(response.body().string());
            // Ahora tenemos que sacar el array de programadores
            JSONObject dep = json.getJSONObject("data").getJSONObject("getDepartamentoByID");
            return mapperDep.fromJSON(dep);
        } else {
            throw new Exception("Error: " + response.code());
        }
    }

    public List<ProgramadorDTO> getProgramadores (String idDepartamento) throws Exception {
        GraphQLClient client = GraphQLClient.getInstance();
        String query ="{\"query\":\"query Query($getProgramadoresByIdId: ID!) {\\n  getProgramadoresByID(id: $getProgramadoresByIdId) " +
                "{\\n    id\\n nombre\\n      experiencia\\n      salario\\n      perfil\\n      departamento\\n fechaAlta\\n      lenguajes\\n  }\\n}\"" +
                ",\"variables\":{\n  \"getProgramadoresByIdId\": "+idDepartamento+"\n}}";

        Response response = client.executeQuery(query);

        if (response.isSuccessful()) {
            // Aquí ya tenemos la respuesta., vamos a pasarla a JSON
            JSONObject json = new JSONObject(response.body().string());
            // Ahora tenemos que sacar el array de programadores
            JSONArray dep = json.getJSONObject("data").getJSONArray("getProgramadoresByID");
            return mapperProg.fromJSONArray(dep);
        } else {
            throw new Exception("Error: " + response.code());
        }
    }

    public ProgramadorDTO getJefe(String idDepartamento) throws Exception {
        GraphQLClient client = GraphQLClient.getInstance();
        String query = "{\"query\":\"query Query($getJefeByIdId: ID!) {\\n  getJefeByID(id: $getJefeByIdId) " +
                "{\\n    id\\n nombre\\n      experiencia\\n      salario\\n      perfil\\n      departamento\\n fechaAlta\\n      lenguajes\\n   }\\n}\"" +
                ",\"variables\":{\n  \"getJefeByIdId\": "+idDepartamento+"\n}}";

        Response response = client.executeQuery(query);

        if (response.isSuccessful()) {
            // Aquí ya tenemos la respuesta., vamos a pasarla a JSON
            JSONObject json = new JSONObject(response.body().string());
            // Ahora tenemos que sacar el array de programadores
            JSONObject dep = json.getJSONObject("data").getJSONObject("getJefeByID");
            return mapperProg.fromJSON(dep);
        } else {
            throw new Exception("Error: " + response.code());
        }
    }

    public DepartamentoDTO create(DepartamentoModifyDTO depDTO) throws Exception {
        GraphQLClient client = GraphQLClient.getInstance();

        String query = "{\"query\":\"mutation Mutation($nombre: String!, $presupuesto: Float!, $idJefe: String!) {\\n  " +
                "addDepartamento(nombre: $nombre, presupuesto: $presupuesto, id_jefe: $idJefe) " +
                "{\\n    id\\n    nombre\\n    presupuesto\\n    " +
                "jefe {\\n     id\\n nombre\\n      experiencia\\n      salario\\n      perfil\\n      departamento\\n fechaAlta\\n    lenguajes\\n }\\n    " +
                "programadores {\\n      id\\n nombre\\n      experiencia\\n      salario\\n      perfil\\n      departamento\\n fechaAlta\\n      lenguajes\\n    }\\n  }\\n}\"" +
                ",\"variables\":{" +
                "\"nombre\":" + "\""+depDTO.getNombre()+"\"," +
                "\"presupuesto\":"+depDTO.getPresupuesto()+"," +
                "\"idJefe\":\""+depDTO.getId_jefe()+"\"" +
                "}}";

        Response response = client.executeQuery(query);

        if (response.isSuccessful()) {
            // Aquí ya tenemos la respuesta., vamos a pasarla a JSON
            JSONObject json = new JSONObject(response.body().string());
            // Ahora tenemos que sacar el array de programadores
            JSONObject prog = json.getJSONObject("data").getJSONObject("addDepartamento");
            return mapperDep.fromJSON(prog);
        } else {
            throw new Exception("Error: " + response.code());
        }
    }

    public DepartamentoDTO update(DepartamentoModifyDTO depDTO) throws Exception {
        GraphQLClient client = GraphQLClient.getInstance();

        String query = "{\"query\":\"mutation UpdateDepartamentoMutation($updateDepartamentoId: ID!, $nombre: String!, $presupuesto: Float!, $idJefe: String!) {\\n  " +
                "updateDepartamento(id: $updateDepartamentoId, nombre: $nombre, presupuesto: $presupuesto, id_jefe: $idJefe) " +
                "{\\n    id\\n    nombre\\n    presupuesto\\n    " +
                "jefe {\\n     id\\n nombre\\n      experiencia\\n      salario\\n      perfil\\n      departamento\\n fechaAlta\\n    lenguajes\\n }\\n    " +
                "programadores {\\n      id\\n nombre\\n      experiencia\\n      salario\\n      perfil\\n      departamento\\n fechaAlta\\n      lenguajes\\n    }\\n  }\\n}\"" +
                ",\"variables\":{" +
                "\"updateDepartamentoId\":" + "\""+depDTO.getId()+"\"," +
                "\"nombre\":" + "\""+depDTO.getNombre()+"\"," +
                "\"presupuesto\":"+depDTO.getPresupuesto()+"," +
                "\"idJefe\":\""+depDTO.getId_jefe()+"\"" +
                "}}";

        System.out.println(query);

        Response response = client.executeQuery(query);

        if (response.isSuccessful()) {
            // Aquí ya tenemos la respuesta., vamos a pasarla a JSON
            JSONObject json = new JSONObject(response.body().string());
            // Ahora tenemos que sacar el array de programadores
            JSONObject prog = json.getJSONObject("data").getJSONObject("updateDepartamento");
            return mapperDep.fromJSON(prog);
        } else {
            throw new Exception("Error: " + response.code());
        }
    }


}
