package es.joseluisgs.dam;

import es.joseluisgs.dam.dto.ProgramadorDTO;
import es.joseluisgs.dam.dto.ProgramadorModifyDTO;
import es.joseluisgs.dam.graphql.DepartamentoService;
import es.joseluisgs.dam.graphql.GraphQLClient;
import es.joseluisgs.dam.graphql.ProgramadorService;
import es.joseluisgs.dam.mapper.DepartamentoMapper;
import es.joseluisgs.dam.mapper.ProgramadorMapper;
import es.joseluisgs.dam.model.Departamento;
import es.joseluisgs.dam.model.Programador;
import retrofit2.Response;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GraphQLClientDemo {
    private ProgramadorService programadorService = ProgramadorService.getInstance();
    private DepartamentoService departamentoService = DepartamentoService.getInstance();
    ProgramadorMapper mapperProg = new ProgramadorMapper();
    DepartamentoMapper mapperDep = new DepartamentoMapper();

    
    public void runProgramador() {
        System.out.println("Cliente GraphQL Programador");
        programadorGetAll();
        programadorGetById();
        programadorGetByLenguaje();
        programadorGetByPerfil();
        programadorPost();
        programadorPut();
        programadorDelete();
    }

    public void runDepartamento() {
        System.out.println("Cliente GraphQL Departamento");
        //departamentoGetAll();
        //departamentoGetById();
        //departamentoGetProgramadores();
        departamentoGetJefe();
    }

    private void departamentoGetAll() {
        System.out.println("GET ALL Departamentos");
        try {
            List<Departamento> result = mapperDep.fromDTO(departamentoService.getAll());
            result.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void departamentoGetById() {
        System.out.println("GET Departamento By Id");
        try {
            Departamento result = mapperDep.fromDTO(departamentoService.getByID("111"));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void departamentoGetProgramadores() {
        System.out.println("GET Programadores by Departamento");
        try {
            List<Programador> result = mapperProg.fromDTO(departamentoService.getProgramadores("111"));
            result.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void departamentoGetJefe() {
        System.out.println("GET Jefe by Departamento");
        try {
            Programador result = mapperProg.fromDTO(departamentoService.getJefe("111"));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void programadorPost() {
        System.out.println("POST Programador");
        ProgramadorModifyDTO prog = new ProgramadorModifyDTO();
        prog.setNombre("Prueba Insert");
        prog.setExperiencia(2);
        prog.setSalario(20000);
        prog.setPerfil("FullStack");
        prog.setDepartamento_id("111");
        prog.setFechaAlta(Date.from(Instant.now()));
        prog.setLenguajes(List.of("Java, TypeScript"));
        try {
            Programador result = mapperProg.fromDTO(programadorService.create(prog));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void programadorPut() {
        System.out.println("PUT Programador");
        ProgramadorModifyDTO prog = new ProgramadorModifyDTO();
        prog.setId("111");
        prog.setNombre("Prueba Update");
        prog.setExperiencia(2);
        prog.setSalario(20000);
        prog.setPerfil("FullStack");
        prog.setDepartamento_id("111");
        prog.setFechaAlta(Date.from(Instant.now()));
        prog.setLenguajes(List.of("Java, TypeScript"));
        try {
            Programador result = mapperProg.fromDTO(programadorService.update(prog));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void programadorDelete() {
        System.out.println("DELETE Programador");
        try {
            Programador result = mapperProg.fromDTO(programadorService.delete("111"));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
