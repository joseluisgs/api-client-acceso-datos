package es.joseluisgs.dam;

import es.joseluisgs.dam.graphql.GraphQLClient;
import okhttp3.Response;

import java.io.IOException;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "API CLIENT ACCESO A DATOS" );

        // RESTClientDemo restClient = new RESTClient();
        //restClient.runDepartamento();
        // restClient.runProgramador();

        GraphQLClientDemo graphQLClient= new GraphQLClientDemo();
        graphQLClient.runProgramador();






    }
}
