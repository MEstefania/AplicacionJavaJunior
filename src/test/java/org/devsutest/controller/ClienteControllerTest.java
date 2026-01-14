package org.devsutest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.devsutest.dto.ClienteDTO;
import org.devsutest.dto.respuestaBase.BaseResponseDTO;
import org.devsutest.dto.respuestaBase.BaseResponseSimpleDTO;
import org.devsutest.dto.respuestaBase.ResponseBaseMapper;
import org.devsutest.service.impl.ClienteServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class ClienteControllerTest {

    @MockBean
    private ClienteServiceImpl service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testErrorNombreNullRequestCreateClient() throws Exception {
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponseCreateUpdate(null);
        given(service.crearCliente(any())).willReturn(responseDTO);
        mvc.perform(
                MockMvcRequestBuilders.post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"genero\":\"Femenino\",\n" +
                                "    \"edad\":24,\n" +
                                "    \"identificacion\":\"1234567897\", \n" +
                                "    \"direccion\":\"13 junio y Equinoccial\",\n" +
                                "    \"telefono\":\"098874587\",\n" +
                                "    \"contrasenia\":\"1245\",\n" +
                                "    \"estado\":\"True\"\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("nombre", Matchers.is("El nombre no puede ser nulo.")));
    }

    @Test
    public void testOkRequestCreateClient() throws Exception {
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponseCreateUpdate(1L);
        given(service.crearCliente(any())).willReturn(responseDTO);
        mvc.perform(
                        MockMvcRequestBuilders.post("/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"nombre\":\"Juan Osorio\",\n" +
                                        "    \"genero\":\"Femenino\",\n" +
                                        "    \"edad\":24,\n" +
                                        "    \"identificacion\":\"1234567897\", \n" +
                                        "    \"direccion\":\"13 junio y Equinoccial\",\n" +
                                        "    \"telefono\":\"098874587\",\n" +
                                        "    \"contrasenia\":\"1245\",\n" +
                                        "    \"estado\":\"True\"\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno[0].newId", Matchers.equalTo(1)));
    }
    @Test
    public void testOkGetClientAll() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId("1");
        clienteDTO.setNombre("Juan Osorio");
        clienteDTO.setGenero("Femenino");
        clienteDTO.setEdad(0);
        clienteDTO.setIdentificacion("1234567892");
        clienteDTO.setDireccion("13 junio y Equinoccial");
        clienteDTO.setTelefono("098874587");
        clienteDTO.setContrasenia("1245");
        clienteDTO.setEstado(true);

        List<Object> clientes = List.of(clienteDTO);
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponse(clientes);
        given(service.obtenerTodosLosClientes()).willReturn(responseDTO);
        mvc.perform(
                        MockMvcRequestBuilders.get("/clientes/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno").isArray())
                .andExpect(jsonPath("retorno[0].id").value("1"))
                .andExpect(jsonPath("retorno[0].nombre").value("Juan Osorio"))
                .andExpect(jsonPath("retorno[0].genero").value("Femenino"))
                .andExpect(jsonPath("retorno[0].estado").value(true));
    }

    @Test
    public void testOkIsEmptyGetClientAll() throws Exception {
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponse(null);
        given(service.obtenerTodosLosClientes()).willReturn(responseDTO);
        mvc.perform(
                        MockMvcRequestBuilders.get("/clientes/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno",Matchers.nullValue()));
    }

    @Test
    public void testOkGetClientById() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId("1");
        clienteDTO.setNombre("Juan Osorio");
        clienteDTO.setGenero("Femenino");
        clienteDTO.setEdad(0);
        clienteDTO.setIdentificacion("1234567892");
        clienteDTO.setDireccion("13 junio y Equinoccial");
        clienteDTO.setTelefono("098874587");
        clienteDTO.setContrasenia("1245");
        clienteDTO.setEstado(true);
        BaseResponseSimpleDTO responseDTO = ResponseBaseMapper.generateOkSimpleResponse((clienteDTO));
        given(service.obtenerCliente(any())).willReturn(responseDTO);
        mvc.perform(
                        MockMvcRequestBuilders.get("/clientes/{cliente_id}",1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno.id").value("1"))
                .andExpect(jsonPath("retorno.nombre").value("Juan Osorio"))
                .andExpect(jsonPath("retorno.genero").value("Femenino"))
                .andExpect(jsonPath("retorno.estado").value(true));
    }

    @Test
    public void testOkIsEmptyGetClientById() throws Exception {
        BaseResponseSimpleDTO responseDTO = ResponseBaseMapper.generateOkSimpleResponse((null));
        given(service.obtenerCliente(any())).willThrow(new EntityNotFoundException("No se encontró el cliente con id: 1"));
        mvc.perform(
                        MockMvcRequestBuilders.get("/clientes/{cliente_id}",5))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testOkDeleteClient() throws Exception {
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponseDelete(1L);
        given(service.eliminarCliente(1L)).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.delete("/clientes/{cliente_id}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno[0].oldId").value(1));
    }

    @Test
    public void testNotFoundDeleteClient() throws Exception {
        given(service.eliminarCliente(999L))
                .willThrow(new EntityNotFoundException("No se encontró el cliente con id: 999"));

        mvc.perform(
                        MockMvcRequestBuilders.delete("/clientes/{cliente_id}", 999))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(false)))
                .andExpect(jsonPath("error", Matchers.is(1)))
                .andExpect(jsonPath("mensaje", Matchers.containsString("No se encontró")))
                .andExpect(jsonPath("retorno", Matchers.nullValue()));
    }

    @Test
    public void testOkUpdateClient() throws Exception {
        ClienteDTO clienteActualizado = new ClienteDTO();
        clienteActualizado.setId("1");
        clienteActualizado.setNombre("Juan Osorio Actualizado");
        clienteActualizado.setGenero("Masculino");
        clienteActualizado.setEdad(25);
        clienteActualizado.setIdentificacion("1234567892");
        clienteActualizado.setDireccion("Nueva Dirección 123");
        clienteActualizado.setTelefono("099999999");
        clienteActualizado.setContrasenia("nuevaPass123");
        clienteActualizado.setEstado(true);

        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponseCreateUpdate(clienteActualizado);
        given(service.actualizarCliente(any(ClienteDTO.class), any())).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.put("/clientes/{cliente_id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"nombre\":\"Juan Osorio Actualizado\",\n" +
                                        "    \"genero\":\"Masculino\",\n" +
                                        "    \"edad\":25,\n" +
                                        "    \"identificacion\":\"1234567892\", \n" +
                                        "    \"direccion\":\"Nueva Dirección 123\",\n" +
                                        "    \"telefono\":\"099999999\",\n" +
                                        "    \"contrasenia\":\"nuevaPass123\",\n" +
                                        "    \"estado\":\"True\"\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno[0].id").value("1"))
                .andExpect(jsonPath("retorno[0].nombre").value("Juan Osorio Actualizado"))
                .andExpect(jsonPath("retorno[0].direccion").value("Nueva Dirección 123"))
                .andExpect(jsonPath("retorno[0].telefono").value("099999999"));
    }

    @Test
    public void testNotFoundUpdateClient() throws Exception {
        given(service.actualizarCliente(any(ClienteDTO.class), any()))
                .willThrow(new EntityNotFoundException("No se encontró el cliente con id: 999"));

        mvc.perform(
                        MockMvcRequestBuilders.put("/clientes/{cliente_id}", 999)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"nombre\":\"Juan Osorio\",\n" +
                                        "    \"genero\":\"Masculino\",\n" +
                                        "    \"edad\":25,\n" +
                                        "    \"identificacion\":\"1234567892\", \n" +
                                        "    \"direccion\":\"Nueva Dirección\",\n" +
                                        "    \"telefono\":\"099999999\",\n" +
                                        "    \"contrasenia\":\"pass123\",\n" +
                                        "    \"estado\":\"True\"\n" +
                                        "}"))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("$.procesoCorrecto", Matchers.is(false)))
                .andExpect(jsonPath("$.error", Matchers.is(1)))
                .andExpect(jsonPath("$.mensaje", Matchers.containsString("No se encontró")))
                .andExpect(jsonPath("$.retorno", Matchers.nullValue()));
    }

}
