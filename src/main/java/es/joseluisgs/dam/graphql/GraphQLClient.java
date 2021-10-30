package es.joseluisgs.dam.graphql;

import okhttp3.*;

import java.io.IOException;

public class GraphQLClient {
    private static final String server = "localhost";
    // Puerto del microservicio
    private static final String port = "4000";
    // endpint del microservicio
    private static final String endpoint = "/graphql/";
    // IP del servicio
    public static final String API_URL = "http://"+server+":"+port+endpoint;
    private static GraphQLClient client;

    OkHttpClient cliente = new OkHttpClient().newBuilder().build();
    MediaType mediaType = MediaType.parse("application/json");

    private GraphQLClient() {}

    public static GraphQLClient getInstance() {
        if (client == null) {
            client = new GraphQLClient();
        }
        return client;
    }

    public Response executeQuery(String query) throws IOException {
        RequestBody body = RequestBody.create(mediaType, query);
        Request request = new Request.Builder()
                .url(API_URL)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        return cliente.newCall(request).execute();
    }
}

