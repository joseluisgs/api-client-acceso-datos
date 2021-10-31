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

//        RestClientDemo restClient = new RestClientDemo();
//        restClient.runProgramador();
//        restClient.runDepartamento();


        GraphQLClientDemo graphQLClient= new GraphQLClientDemo();
//        graphQLClient.runProgramador();
        graphQLClient.runDepartamento();






    }
}
