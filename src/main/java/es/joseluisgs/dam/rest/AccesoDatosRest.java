package es.joseluisgs.dam.rest;

import es.joseluisgs.dam.dto.DepartamentoDTO;
import es.joseluisgs.dam.dto.DepartamentoModifyDTO;
import es.joseluisgs.dam.dto.ProgramadorDTO;
import es.joseluisgs.dam.dto.ProgramadorModifyDTO;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface AccesoDatosRest {
    // Creamos una llamada para cada cosa que queremos hacer

    // DEPARTAMENTOS

    // Departamentos, obtener todos
    @GET("departamento")
    Call<List<DepartamentoDTO>> departamentoGetAll();

    // Obtener uno producto por ID
    @GET("departamento/{id}")
    Call<DepartamentoDTO> departamentoGetById(@Path("id") String id);

    // Obtenemos todos los programadores
    @GET("departamento/{id}/programadores")
    Call<List<ProgramadorDTO>> departamentoGetProgramadores(@Path("id") String id);

    // Obtenemos el jefe
    @GET("departamento/{id}/jefe")
    Call<ProgramadorDTO> departamentoGetJefe(@Path("id") String id);

    // POST Departamento
    @POST("departamento")
    Call<DepartamentoDTO> departamentoCreate(@Body DepartamentoModifyDTO departamento);

    // PUT Departamento
    @PUT("departamento/{id}")
    Call<DepartamentoDTO> departamentoUpdate(@Path("id") String id, @Body DepartamentoModifyDTO departamento);

    // DELETE Departamento
    @DELETE("departamento/{id}")
    Call<DepartamentoDTO> departamentoDelete(@Path("id") String id);

    // PATCH añadir y quitar programador a departamento
    @PATCH("departamento/{id}/add_programador/{idProgramador}")
    Call<DepartamentoDTO> departamentoAddProgramador(@Path("id") String id, @Path("idProgramador") String idProgramador);

    @PATCH("departamento/{id}/remove_programador/{idProgramador}")
    Call<DepartamentoDTO> departamentoRemoveProgramador(@Path("id") String id, @Path("idProgramador") String idProgramador);

    // PROGRAMADORES

    // Obtenemos todos los programadores
    @GET("programador")
    Call<List<ProgramadorDTO>> programadorGetAll();

    // Obtenemos uno producto por ID
    @GET("programador/{id}")
    Call<ProgramadorDTO> programadorGetById(@Path("id") String id);

    // Obtiene programador por perfil
    @GET("programador/perfil/{perfil}")
    Call<List<ProgramadorDTO>> programadorGetByPerfil(@Path("perfil") String perfil);

    // Obtiene programador por lenguaje
    @GET("programador/lenguaje/{lenguaje}")
    Call<List<ProgramadorDTO>> programadorGetByLenguaje(@Path("lenguaje") String lenguaje);

    // POST Programador
    @POST("programador")
    Call<ProgramadorDTO> programadorCreate(@Body ProgramadorModifyDTO programador);

    // PUT Programador
    @PUT("programador/{id}")
    Call<ProgramadorDTO> programadorUpdate(@Path("id") String id, @Body ProgramadorModifyDTO programador);

    // DELETE Programador
    @DELETE("programador/{id}")
    Call<ProgramadorDTO> programadorDelete(@Path("id") String id);
}

