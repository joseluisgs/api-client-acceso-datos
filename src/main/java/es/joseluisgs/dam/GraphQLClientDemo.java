package es.joseluisgs.dam;

import es.joseluisgs.dam.graphql.GraphQLClient;
import es.joseluisgs.dam.graphql.ProgramadorService;
import es.joseluisgs.dam.mapper.ProgramadorMapper;
import es.joseluisgs.dam.model.Programador;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class GraphQLClientDemo {
    private ProgramadorService programadorService = ProgramadorService.getInstance();
    ProgramadorMapper mapperProg = new ProgramadorMapper();

    
    public void runProgramador() {
        System.out.println("Cliente GraphQL Programador");
        //programadorGetAll();
        //programadorGetById();
        programadorGetByLenguaje();
        programadorGetByPerfil();
    }

    private void programadorGetAll() {
        System.out.println("GET ALL Programadores");
        try {
            List<Programador> result = mapperProg.fromDTO(programadorService.getAll());
            result.forEach(System.out::println);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }


    private void programadorGetById() {
        System.out.println("GET Programador By Id");
        try {
            Programador result = mapperProg.fromDTO(programadorService.getByID("111"));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void programadorGetByLenguaje() {
        System.out.println("GET Programador By Lenguaje");
        try {
            List<Programador> result  = mapperProg.fromDTO(programadorService.getByLenguaje("Java"));
            result.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void programadorGetByPerfil() {
        System.out.println("GET Programador By Perfil");
        try {
            List<Programador> result  = mapperProg.fromDTO(programadorService.getByPerfil("FullStack"));
            result.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
