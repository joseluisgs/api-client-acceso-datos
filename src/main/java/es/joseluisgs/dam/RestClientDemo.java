package es.joseluisgs.dam;

import es.joseluisgs.dam.dto.DepartamentoDTO;
import es.joseluisgs.dam.dto.DepartamentoModifyDTO;
import es.joseluisgs.dam.dto.ProgramadorDTO;
import es.joseluisgs.dam.dto.ProgramadorModifyDTO;
import es.joseluisgs.dam.mapper.DepartamentoMapper;
import es.joseluisgs.dam.mapper.ProgramadorMapper;
import es.joseluisgs.dam.rest.APIRestConfig;
import es.joseluisgs.dam.rest.AccesoDatosRest;
import es.joseluisgs.dam.model.Departamento;
import es.joseluisgs.dam.model.Programador;
import retrofit2.Response;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RestClientDemo {
    AccesoDatosRest restService = APIRestConfig.getService();
    // Mapeadores
    private DepartamentoMapper mapperDep = new DepartamentoMapper();
    private ProgramadorMapper mapperProg = new ProgramadorMapper();

    public void runDepartamento() {
        System.out.println("Cliente REST EndPoint Departamento");
        //departamentGetAll();
        departamentGetById();
        departamentoGetProgramadores();
        departamentoGetJefe();
        //departamentoPost();
        departamentoAñadirProgramador();
        departamentoEliminarProgramador();
        //departamentoPut();
        //departamentoDelete();

    }

    public void runProgramador() {
        System.out.println("Cliente REST EndPoint Programador");
        programadorGetAll();
        programadorGetById();
        getProgramadorByPerfil();
        getProgramadorByLenguaje();
        // de la misma manera hacer por perfil y lenguaje
        //programadorPost();
        //programadorPut();
        //programadorDelete();
    }

    private void getProgramadorByLenguaje() {
        System.out.println("GET Programador por lenguaje");
        try {
            Response<List<ProgramadorDTO>> response = restService.programadorGetByLenguaje("Java").execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de GET Programador por lenguaje");
                List<Programador> result = mapperProg.fromDTO(response.body());
                result.forEach(System.out::println);
            }else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getProgramadorByPerfil() {
        System.out.println("GET Programador por perfil");
        try {
            Response<List<ProgramadorDTO>> response = restService.programadorGetByPerfil("FullStack").execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de GET Programador por perfil");
                List<Programador> result = mapperProg.fromDTO(response.body());
                result.forEach(System.out::println);
            }else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void programadorDelete() {
        System.out.println("DELETE Programador");
        try {
            Response<ProgramadorDTO> response = restService.programadorDelete("111").execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de DELETE Programador");
                Programador result = mapperProg.fromDTO(response.body());
                System.out.println(result);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void programadorPut() {
        System.out.println("PUT Programador");
        ProgramadorModifyDTO prog = new ProgramadorModifyDTO();
        prog.setNombre("Prueba Update");
        prog.setExperiencia(2);
        prog.setSalario(20000);
        prog.setPerfil("FullStack");
        prog.setDepartamento_id("111");
        prog.setFechaAlta(Date.from(Instant.now()));
        prog.setLenguajes(Collections.singletonList("Java, TypeScript"));
        try {
            Response<ProgramadorDTO> response = restService.programadorUpdate("111", prog).execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de PUT Programador");
                Programador result = mapperProg.fromDTO(response.body());
                System.out.println(result);
            }else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
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
        prog.setLenguajes(Collections.singletonList("Java, TypeScript"));
        try {
            Response<ProgramadorDTO> response = restService.programadorCreate(prog).execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de POST Programador");
                Programador result = mapperProg.fromDTO(response.body());
                System.out.println(result);
            }else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void programadorGetById() {
        System.out.println("GET Programador");
        try {
            Response<ProgramadorDTO> response = restService.programadorGetById("111").execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de GET Programador");
                Programador result = mapperProg.fromDTO(response.body());
                System.out.println(result);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void programadorGetAll() {
        System.out.println("GET ALL Programadores");
        try {
            Response<List<ProgramadorDTO>> response = restService.programadorGetAll().execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code() + " - Datos obtenidos: " + response.body().size());
                System.out.println("Resultado de GET ALL Programador");
                List<Programador> result = mapperProg.fromDTO(response.body());
                result.forEach(System.out::println);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void departamentoAñadirProgramador() {
        System.out.println("PATCH Departamento Añadir Programador");
        DepartamentoModifyDTO dto = new DepartamentoModifyDTO();
        try {
            Response<DepartamentoDTO> response = restService.departamentoAddProgramador("111", "777").execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de PATCH Add Programador");
                Departamento result = mapperDep.fromDTO(response.body());
                System.out.println(result);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void departamentoEliminarProgramador() {
        System.out.println("PATCH Departamento Eliminar Programador");
        DepartamentoModifyDTO dto = new DepartamentoModifyDTO();
        try {
            Response<DepartamentoDTO> response = restService.departamentoRemoveProgramador("111", "777").execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de PATCH Remove Programador");
                Departamento result = mapperDep.fromDTO(response.body());
                System.out.println(result);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void departamentoDelete() {
        System.out.println("DELETE Departamento");
        try {
            Response<DepartamentoDTO> response = restService.departamentoDelete("111").execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de DELETE");
                Departamento result = mapperDep.fromDTO(response.body());
                System.out.println(result);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void departamentoPut() {
        System.out.println("PUT Departamento");
        DepartamentoModifyDTO dto = new DepartamentoModifyDTO();
        dto.setNombre("Departamento Update");
        dto.setPresupuesto(1000.00);
        dto.setId_jefe("111");

        try {
            Response<DepartamentoDTO> response = restService.departamentoUpdate("111", dto).execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de PUT");
                Departamento result = mapperDep.fromDTO(response.body());
                System.out.println(result);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void departamentoPost() {
        System.out.println("POST Departamento");
        DepartamentoModifyDTO dto = new DepartamentoModifyDTO();
        dto.setNombre("Departamento Insert");
        dto.setPresupuesto(1000.00);
        dto.setId_jefe("111");

        try {
            Response<DepartamentoDTO> response = restService.departamentoCreate(dto).execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de POST");
                Departamento result = mapperDep.fromDTO(response.body());
                System.out.println(result);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void departamentGetAll() {
        System.out.println("GET ALL Departamentos");
        try {
            Response<List<DepartamentoDTO>> response = restService.departamentoGetAll().execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful()) {
                System.out.println("Request Done! - Código: " + response.code() + " - Datos obtenidos: " + response.body().size());
                // System.out.println(response.body());
                System.out.println("Resultado de GET ALL");
                List<Departamento> result = mapperDep.fromDTO(response.body());
                result.forEach(System.out::println);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void departamentGetById() {
        System.out.println("GET Departamentos By ID 111");
        try {
            Response<DepartamentoDTO> response = restService.departamentoGetById("111").execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de GET By Id");
                Departamento result = mapperDep.fromDTO(response.body());
                System.out.println(result);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void departamentoGetProgramadores() {
        System.out.println("GET Programadores por Departamentos ID 111");
        try {
            Response<List<ProgramadorDTO>> response = restService.departamentoGetProgramadores("111").execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful()) {
                System.out.println("Request Done! - Código: " + response.code() + " - Datos obtenidos: " + response.body().size());
                System.out.println("Resultado de GET Programadores By Departamento ID");
                List<Programador> result = mapperProg.fromDTO(response.body());
                result.forEach(System.out::println);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void departamentoGetJefe() {
        System.out.println("GET Departamentos Jefe By ID 111");
        try {
            Response<ProgramadorDTO> response = restService.departamentoGetJefe("111").execute();
            // La hemos obtenido correctamente
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Request Done! - Código: " + response.code());
                System.out.println("Resultado de GET Departamento Jefe ");
                System.out.println(response.body());
                Programador result = mapperProg.fromDTO(response.body());
                System.out.println("La salida es: " + result);
            }
            else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
