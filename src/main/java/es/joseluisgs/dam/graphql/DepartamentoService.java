package es.joseluisgs.dam.graphql;

import es.joseluisgs.dam.dto.DepartamentoDTO;
import es.joseluisgs.dam.dto.ProgramadorDTO;
import es.joseluisgs.dam.mapper.DepartamentoMapper;
import es.joseluisgs.dam.mapper.ProgramadorMapper;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class DepartamentoService {
    private static DepartamentoService instance = null;
    DepartamentoMapper mapperDep = new DepartamentoMapper();

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
            JSONArray prog = json.getJSONObject("data").getJSONArray("getDepartamentos");
            //System.out.println(prog);
            return mapperDep.fromJSONArray(prog);
        } else {
            throw new Exception("Error: " + response.code());
        }
    }


}
