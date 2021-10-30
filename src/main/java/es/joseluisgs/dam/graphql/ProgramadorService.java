package es.joseluisgs.dam.graphql;

import es.joseluisgs.dam.dto.ProgramadorDTO;
import es.joseluisgs.dam.mapper.ProgramadorMapper;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

// Realiza las llamadas de programador
public class ProgramadorService {
    private static ProgramadorService instance = null;
    ProgramadorMapper mapperProg = new ProgramadorMapper();

    private ProgramadorService() {
    }
    public static ProgramadorService getInstance() {
        if (instance == null) {
            instance = new ProgramadorService();
        }
        return instance;
    }

    public List<ProgramadorDTO> getAll() throws Exception {
        GraphQLClient client = GraphQLClient.getInstance();
        String query =  "{\"query\":\"query Query {\\n  getProgramadores {\\n    id\\n nombre\\n    experiencia\\n    " +
                    "salario\\n    perfil\\n    " +
                    "departamento\\n    fechaAlta\\n  lenguajes\\n }\\n}\"}";
        Response response = client.executeQuery(query);
        if (response.isSuccessful()) {
            // Aquí ya tenemos la respuesta., vamos a pasarla a JSON
            JSONObject json = new JSONObject(response.body().string());
            // Ahora tenemos que sacar el array de programadores
            JSONArray prog = json.getJSONObject("data").getJSONArray("getProgramadores");
            //System.out.println(prog);
            return mapperProg.fromJSONArray(prog);
        } else {
            throw new Exception("Error: " + response.code());
        }
    }

    public ProgramadorDTO getByID(String idProgramador) throws Exception {
        GraphQLClient client = GraphQLClient.getInstance();
        String query =   "{\"query\":\"query Query($getProgramadorByIdId: ID!) {\\n  getProgramadorByID(id: $getProgramadorByIdId) " +
                "{\\n    id\\n    nombre\\n    experiencia\\n    salario\\n    perfil\\n    departamento\\n    fechaAlta\\n    lenguajes\\n  }\\n}\"" +
                ",\"variables\":{\n  \"getProgramadorByIdId\": "+idProgramador+"\n}}";

        Response response = client.executeQuery(query);

        if (response.isSuccessful()) {
            // Aquí ya tenemos la respuesta., vamos a pasarla a JSON
            JSONObject json = new JSONObject(response.body().string());
            // Ahora tenemos que sacar el array de programadores
            JSONObject prog = json.getJSONObject("data").getJSONObject("getProgramadorByID");
            return mapperProg.fromJSON(prog);
        } else {
            throw new Exception("Error: " + response.code());
        }
    }

    public List<ProgramadorDTO> getByLenguaje(String lenguaje) throws Exception {
        GraphQLClient client = GraphQLClient.getInstance();

        String query = "{\"query\":\"query Query($lenguaje: String!) {\\n  getProgramadoresByLenguaje(lenguaje: $lenguaje) " +
                "{\\n    id\\n    nombre\\n    experiencia\\n    salario\\n    perfil\\n    departamento\\n    fechaAlta\\n    lenguajes\\n  }\\n}\"" +
                ",\"variables\":{\"lenguaje\":\""+lenguaje+"\"}" +
                "}";

        // System.out.println(query);
        Response response = client.executeQuery(query);
        if (response.isSuccessful()) {
            // Aquí ya tenemos la respuesta., vamos a pasarla a JSON
            JSONObject json = new JSONObject(response.body().string());
            // Ahora tenemos que sacar el array de programadores
            JSONArray prog = json.getJSONObject("data").getJSONArray("getProgramadoresByLenguaje");
            System.out.println(prog);
            return mapperProg.fromJSONArray(prog);
        } else {
            throw new Exception("Error: " + response.code());
        }
    }

    public List<ProgramadorDTO> getByPerfil(String perfil) throws Exception {
        GraphQLClient client = GraphQLClient.getInstance();
        String query = "{\"query\":\"query Query($perfil: String!) {\\n  getProgramadoresByPerfil(perfil: $perfil) " +
                "{\\n    id\\n    nombre\\n    experiencia\\n    salario\\n    perfil\\n    departamento\\n    fechaAlta\\n    lenguajes\\n  }\\n}\"" +
                ",\"variables\":{\"perfil\":\""+perfil+"\"}" +
                "}";


        Response response = client.executeQuery(query);
        if (response.isSuccessful()) {
            // Aquí ya tenemos la respuesta., vamos a pasarla a JSON
            JSONObject json = new JSONObject(response.body().string());
            // Ahora tenemos que sacar el array de programadores
            JSONArray prog = json.getJSONObject("data").getJSONArray("getProgramadoresByPerfil");
            //System.out.println(prog);
            return mapperProg.fromJSONArray(prog);
        } else {
            throw new Exception("Error: " + response.code());
        }
    }
}

