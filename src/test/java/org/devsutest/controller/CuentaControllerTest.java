package org.devsutest.controller;

import org.devsutest.dto.CuentaDTO;
import org.devsutest.dto.respuestaBase.BaseResponseDTO;
import org.devsutest.dto.respuestaBase.BaseResponseSimpleDTO;
import org.devsutest.dto.respuestaBase.ResponseBaseMapper;
import org.devsutest.service.impl.CuentaServiceImpl;
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
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class CuentaControllerTest {
    @MockBean
    private CuentaServiceImpl service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testOkRequestCreateCuenta() throws Exception {
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponseCreateUpdate(1L);
        given(service.crearCuenta(any())).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.post("/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"numero_cuenta\":\"585545\",\n" +
                                        "    \"tipo\":\"Corriente\",\n" +
                                        "    \"saldo_inicial\":1000,\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cliente\":1\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno[0].newId", Matchers.equalTo(1)));
    }

    @Test
    public void testErrorNumeroCuentaNullRequestCreateCuenta() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post("/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"tipo\":\"Corriente\",\n" +
                                        "    \"saldo_inicial\":1000,\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cliente\":1\n" +
                                        "}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testErrorTipoNullRequestCreateCuenta() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post("/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"numero_cuenta\":\"585545\",\n" +
                                        "    \"saldo_inicial\":1000,\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cliente\":1\n" +
                                        "}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testErrorSaldoInicialNullRequestCreateCuenta() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post("/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"numero_cuenta\":\"585545\",\n" +
                                        "    \"tipo\":\"Corriente\",\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cliente\":1\n" +
                                        "}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testErrorIdClienteNullRequestCreateCuenta() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post("/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"numero_cuenta\":\"585545\",\n" +
                                        "    \"tipo\":\"Corriente\",\n" +
                                        "    \"saldo_inicial\":1000,\n" +
                                        "    \"estado\":\"True\"\n" +
                                        "}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }


    @Test
    public void testErrorClienteNoExiste() throws Exception {
        given(service.crearCuenta(any()))
                .willThrow(new EntityNotFoundException("No se encontró el cliente con id: 999"));

        mvc.perform(
                        MockMvcRequestBuilders.post("/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"numero_cuenta\":\"585545\",\n" +
                                        "    \"tipo\":\"Corriente\",\n" +
                                        "    \"saldo_inicial\":1000,\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cliente\":999\n" +
                                        "}"))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(false)))
                .andExpect(jsonPath("error", Matchers.is(1)))
                .andExpect(jsonPath("mensaje", Matchers.containsString("No se encontró el cliente")));
    }


    @Test
    public void testOkGetCuentaAll() throws Exception {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setIdCuenta("1");
        cuentaDTO.setNumeroCuenta("585545");
        cuentaDTO.setTipo("Corriente");
        cuentaDTO.setSaldoInicial(BigDecimal.valueOf(1000.0));
        cuentaDTO.setEstado(true);
        cuentaDTO.setIdCliente(1L);

        List<Object> cuentas = List.of(cuentaDTO);
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponse(cuentas);
        given(service.obtenerTodasLasCuentas()).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.get("/cuentas/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno").isArray())
                .andExpect(jsonPath("retorno[0].id_cuenta").value("1"))
                .andExpect(jsonPath("retorno[0].numero_cuenta").value("585545"))
                .andExpect(jsonPath("retorno[0].tipo").value("Corriente"))
                .andExpect(jsonPath("retorno[0].saldo_inicial").value(1000.0))
                .andExpect(jsonPath("retorno[0].estado").value(true))
                .andExpect(jsonPath("retorno[0].id_cliente").value(1));
    }

    @Test
    public void testOkIsEmptyGetCuentaAll() throws Exception {
        List<Object> cuentas = Collections.emptyList();
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponse(cuentas);
        given(service.obtenerTodasLasCuentas()).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.get("/cuentas/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno").isArray())
                .andExpect(jsonPath("retorno", Matchers.hasSize(0)));
    }

    @Test
    public void testOkGetCuentaAll_MultipleCuentas() throws Exception {
        CuentaDTO cuenta1 = new CuentaDTO();
        cuenta1.setIdCuenta("1");
        cuenta1.setNumeroCuenta("585545");
        cuenta1.setTipo("Corriente");
        cuenta1.setSaldoInicial(BigDecimal.valueOf(1000.0));
        cuenta1.setEstado(true);
        cuenta1.setIdCliente(1L);

        CuentaDTO cuenta2 = new CuentaDTO();
        cuenta2.setIdCuenta("2");
        cuenta2.setNumeroCuenta("123456");
        cuenta2.setTipo("Ahorros");
        cuenta2.setSaldoInicial(BigDecimal.valueOf(5000.0));
        cuenta2.setEstado(true);
        cuenta2.setIdCliente(1L);

        List<Object> cuentas = List.of(cuenta1, cuenta2);
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponse(cuentas);
        given(service.obtenerTodasLasCuentas()).willReturn(responseDTO);

        mvc.perform(MockMvcRequestBuilders.get("/cuentas/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("retorno").isArray())
                .andExpect(jsonPath("retorno", Matchers.hasSize(2)))
                .andExpect(jsonPath("retorno[0].numero_cuenta").value("585545"))
                .andExpect(jsonPath("retorno[1].numero_cuenta").value("123456"));
    }

    @Test
    public void testOkGetCuentaById() throws Exception {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setIdCuenta("1");
        cuentaDTO.setNumeroCuenta("585545");
        cuentaDTO.setTipo("Corriente");
        cuentaDTO.setSaldoInicial(BigDecimal.valueOf(1000.0));
        cuentaDTO.setEstado(true);
        cuentaDTO.setIdCliente(1L);

        BaseResponseSimpleDTO responseDTO = ResponseBaseMapper.generateOkSimpleResponse(cuentaDTO);
        given(service.obtenerCuenta(1L)).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.get("/cuentas/{cuenta_id}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno.id_cuenta").value("1"))
                .andExpect(jsonPath("retorno.numero_cuenta").value("585545"))
                .andExpect(jsonPath("retorno.tipo").value("Corriente"))
                .andExpect(jsonPath("retorno.saldo_inicial").value(1000.0))
                .andExpect(jsonPath("retorno.estado").value(true));
    }

    @Test
    public void testNotFoundGetCuentaById() throws Exception {
        given(service.obtenerCuenta(999L))
                .willThrow(new EntityNotFoundException("No se encontró la cuenta con id: 999"));

        mvc.perform(
                        MockMvcRequestBuilders.get("/cuentas/{cuenta_id}", 999))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(false)))
                .andExpect(jsonPath("error", Matchers.is(1)))
                .andExpect(jsonPath("mensaje", Matchers.containsString("No se encontró")))
                .andExpect(jsonPath("retorno", Matchers.nullValue()));
    }

    // ========== UPDATE TESTS ==========

    @Test
    public void testOkUpdateCuenta() throws Exception {
        CuentaDTO cuentaActualizada = new CuentaDTO();
        cuentaActualizada.setIdCuenta("1");
        cuentaActualizada.setNumeroCuenta("585545");
        cuentaActualizada.setTipo("Ahorros");
        cuentaActualizada.setSaldoInicial(BigDecimal.valueOf(2000.0));
        cuentaActualizada.setEstado(true);
        cuentaActualizada.setIdCliente(1L);

        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponseCreateUpdate(cuentaActualizada);
        given(service.actualizarCuenta(any(CuentaDTO.class),any())).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.put("/cuentas/{cuenta_id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"numero_cuenta\":\"585545\",\n" +
                                        "    \"tipo\":\"Ahorros\",\n" +
                                        "    \"saldo_inicial\":2000,\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cliente\":1\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno[0].id_cuenta").value("1"))
                .andExpect(jsonPath("retorno[0].tipo").value("Ahorros"))
                .andExpect(jsonPath("retorno[0].saldo_inicial").value(2000.0));
    }

    @Test
    public void testNotFoundUpdateCuenta() throws Exception {
        given(service.actualizarCuenta(any(CuentaDTO.class),any()))
                .willThrow(new EntityNotFoundException("No se encontró la cuenta con id: 999"));

        mvc.perform(
                        MockMvcRequestBuilders.put("/cuentas/{cuenta_id}", 999)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"numero_cuenta\":\"585545\",\n" +
                                        "    \"tipo\":\"Ahorros\",\n" +
                                        "    \"saldo_inicial\":2000,\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cliente\":1\n" +
                                        "}"))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(false)))
                .andExpect(jsonPath("error", Matchers.is(1)))
                .andExpect(jsonPath("mensaje", Matchers.containsString("No se encontró")))
                .andExpect(jsonPath("retorno", Matchers.nullValue()));
    }

    @Test
    public void testOkDeleteCuenta() throws Exception {
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponseDelete(1L);
        given(service.eliminarCuenta(1L)).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.delete("/cuentas/{cuenta_id}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno[0].oldId").value(1));
    }

    @Test
    public void testNotFoundDeleteCuenta() throws Exception {
        given(service.eliminarCuenta(999L))
                .willThrow(new EntityNotFoundException("No se encontró la cuenta con id: 999"));

        mvc.perform(
                        MockMvcRequestBuilders.delete("/cuentas/{cuenta_id}", 999))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(false)))
                .andExpect(jsonPath("error", Matchers.is(1)))
                .andExpect(jsonPath("mensaje", Matchers.containsString("No se encontró")))
                .andExpect(jsonPath("retorno", Matchers.nullValue()));
    }

    @Test
    public void testDeleteCuenta_InvalidId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/cuentas/{cuenta_id}", "invalid"))
                .andExpect(status().isBadRequest());
    }
}