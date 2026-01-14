package org.devsutest.controller;

import org.devsutest.dto.MovimientoDTO;
import org.devsutest.dto.respuestaBase.BaseResponseDTO;
import org.devsutest.dto.respuestaBase.BaseResponseSimpleDTO;
import org.devsutest.dto.respuestaBase.ResponseBaseMapper;
import org.devsutest.service.impl.MovimientoServiceImpl;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
public class MovimientoControllerTest {

    @MockBean
    private MovimientoServiceImpl service;

    @Autowired
    private MockMvc mvc;
    @Test
    public void testOkRequestCreateMovimiento() throws Exception {
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponseCreateUpdate(1L);
        given(service.crearMovimiento(any())).willReturn(responseDTO);
        mvc.perform(
                        MockMvcRequestBuilders.post("/movimientos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"fecha\":\"2025-08-10T12:22:49.000Z\",\n" +
                                        "    \"tipo\":\"Ahorros\",\n" +
                                        "    \"valor_movimiento\":-540,\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cuenta\":4\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno[0].newId", Matchers.equalTo(1)));
    }

    @Test
    public void testErrorValorMovimientoNullRequestCreateMovimiento() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post("/movimientos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"fecha\":\"2025-08-10T12:22:49.000Z\",\n" +
                                        "    \"tipo\":\"Ahorros\",\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cuenta\":4\n" +
                                        "}"))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("valorMovimiento", Matchers.containsString("El valor no puede ser nulo.")));
    }

    @Test
    public void testErrorTipoNullRequestCreateMovimiento() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post("/movimientos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"fecha\":\"2025-08-10T12:22:49.000Z\",\n" +
                                        "    \"valor_movimiento\":-540,\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cuenta\":4\n" +
                                        "}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testErrorIdCuentaNullRequestCreateMovimiento() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post("/movimientos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"fecha\":\"2025-08-10T12:22:49.000Z\",\n" +
                                        "    \"tipo\":\"Ahorros\",\n" +
                                        "    \"valor_movimiento\":-540,\n" +
                                        "    \"estado\":\"True\"\n" +
                                        "}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testOkGetMovimientoAll() throws Exception {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setIdMovimiento(1L);
        movimientoDTO.setFecha(Timestamp.valueOf(LocalDateTime.parse("2025-08-10T12:22:49")));
        movimientoDTO.setTipo("Ahorros");
        movimientoDTO.setValorMovimiento(BigDecimal.valueOf(-540.0));
        movimientoDTO.setIdCuenta(4L);
        movimientoDTO.setSaldo(new BigDecimal("1000.0"));

        List<Object> movimientos = List.of(movimientoDTO);
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponse(movimientos);
        given(service.obtenerTodosLosMovimientos()).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.get("/movimientos/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno").isArray())
                .andExpect(jsonPath("retorno[0].id_movimiento").value(1L))
                .andExpect(jsonPath("retorno[0].tipo").value("Ahorros"))
                .andExpect(jsonPath("retorno[0].valor_movimiento").value(-540.0))
                .andExpect(jsonPath("retorno[0].id_cuenta").value(4));
    }

    @Test
    public void testOkIsEmptyGetMovimientoAll() throws Exception {
        List<Object> movimientos = Collections.emptyList();
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponse(movimientos);
        given(service.obtenerTodosLosMovimientos()).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.get("/movimientos/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno").isArray());
    }

    @Test
    public void testOkGetMovimientoById() throws Exception {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setIdMovimiento(1L);
        movimientoDTO.setFecha(Timestamp.valueOf(LocalDateTime.parse("2025-08-10T12:22:49")));
        movimientoDTO.setTipo("Ahorros");
        movimientoDTO.setValorMovimiento(BigDecimal.valueOf(-540.0));
        movimientoDTO.setIdCuenta(4L);
        movimientoDTO.setSaldo(new BigDecimal("1000.0"));

        BaseResponseSimpleDTO responseDTO = ResponseBaseMapper.generateOkSimpleResponse(movimientoDTO);
        given(service.obtenerMovimiento(1L)).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.get("/movimientos/{movimiento_id}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno.id_movimiento").value(1L))
                .andExpect(jsonPath("retorno.tipo").value("Ahorros"))
                .andExpect(jsonPath("retorno.valor_movimiento").value(-540.0))
                .andExpect(jsonPath("retorno.id_cuenta").value(4));
    }

    @Test
    public void testNotFoundGetMovimientoById() throws Exception {
        given(service.obtenerMovimiento(999L))
                .willThrow(new EntityNotFoundException("No se encontró el movimiento con id: 999"));

        mvc.perform(
                        MockMvcRequestBuilders.get("/movimientos/{movimiento_id}", 999))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(false)))
                .andExpect(jsonPath("error", Matchers.is(1)))
                .andExpect(jsonPath("mensaje", Matchers.containsString("No se encontró")))
                .andExpect(jsonPath("retorno", Matchers.nullValue()));
    }

    @Test
    public void testOkDeleteMovimiento() throws Exception {
        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponseDelete(1L);
        given(service.eliminarMovimiento(1L)).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.delete("/movimientos/{movimiento_id}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno[0].oldId").value(1));
    }

    @Test
    public void testNotFoundDeleteMovimiento() throws Exception {
        given(service.eliminarMovimiento(999L))
                .willThrow(new EntityNotFoundException("No se encontró el movimiento con id: 999"));

        mvc.perform(
                        MockMvcRequestBuilders.delete("/movimientos/{movimiento_id}", 999))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(false)))
                .andExpect(jsonPath("error", Matchers.is(1)))
                .andExpect(jsonPath("mensaje", Matchers.containsString("No se encontró")))
                .andExpect(jsonPath("retorno", Matchers.nullValue()));
    }

    @Test
    public void testOkUpdateMovimiento() throws Exception {
        MovimientoDTO movimientoActualizado = new MovimientoDTO();
        movimientoActualizado.setIdMovimiento(1L);
        movimientoActualizado.setFecha(Timestamp.valueOf(LocalDateTime.parse("2025-08-10T12:22:49")));
        movimientoActualizado.setTipo("Ahorros");
        movimientoActualizado.setValorMovimiento(BigDecimal.valueOf(-540.0));
        movimientoActualizado.setIdCuenta(4L);
        movimientoActualizado.setSaldo(new BigDecimal("1000.0"));

        BaseResponseDTO responseDTO = ResponseBaseMapper.generateOkResponseCreateUpdate(movimientoActualizado);
        given(service.actualizarMovimiento(any(MovimientoDTO.class), any())).willReturn(responseDTO);

        mvc.perform(
                        MockMvcRequestBuilders.put("/movimientos/{movimiento_id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"fecha\":\"2025-08-15T14:30:00.000Z\",\n" +
                                        "    \"tipo\":\"Corriente\",\n" +
                                        "    \"valor_movimiento\":1000,\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cuenta\":4\n" +
                                        "}"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(true)))
                .andExpect(jsonPath("error", Matchers.is(0)))
                .andExpect(jsonPath("mensaje", Matchers.nullValue()))
                .andExpect(jsonPath("retorno[0].id_movimiento").value(1L))
                .andExpect(jsonPath("retorno[0].saldo").value(1000.0));
    }

    @Test
    public void testNotFoundUpdateMovimiento() throws Exception {
        given(service.actualizarMovimiento(any(MovimientoDTO.class),any()))
                .willThrow(new EntityNotFoundException("No se encontró el movimiento con id: 999"));

        mvc.perform(
                        MockMvcRequestBuilders.put("/movimientos/{movimiento_id}", 999)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"fecha\":\"2025-08-15T14:30:00.000Z\",\n" +
                                        "    \"tipo\":\"Corriente\",\n" +
                                        "    \"valor_movimiento\":1000,\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cuenta\":4\n" +
                                        "}"))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(false)))
                .andExpect(jsonPath("error", Matchers.is(1)))
                .andExpect(jsonPath("mensaje", Matchers.containsString("No se encontró")))
                .andExpect(jsonPath("retorno", Matchers.nullValue()));
    }

    @Test
    public void testErrorCuentaNoExiste() throws Exception {
        given(service.crearMovimiento(any()))
                .willThrow(new EntityNotFoundException("No se encontró la cuenta con id: 999"));

        mvc.perform(
                        MockMvcRequestBuilders.post("/movimientos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"fecha\":\"2025-08-10T12:22:49.000Z\",\n" +
                                        "    \"tipo\":\"Ahorros\",\n" +
                                        "    \"valor_movimiento\":-540,\n" +
                                        "    \"estado\":\"True\",\n" +
                                        "    \"id_cuenta\":999\n" +
                                        "}"))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("procesoCorrecto", Matchers.is(false)))
                .andExpect(jsonPath("error", Matchers.is(1)))
                .andExpect(jsonPath("mensaje", Matchers.containsString("No se encontró la cuenta")));
    }

}
