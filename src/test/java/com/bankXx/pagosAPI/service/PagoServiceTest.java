package com.bankXx.pagosAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;

import com.bankXx.pagosAPI.entity.Pago;
import com.bankXx.pagosAPI.repository.PagoRepository;

class PagoServiceTest {
	
	private PagoRepository pagoRepository;
    private AmqpTemplate rabbitTemplate;
    private PagoService pagoService;

    @BeforeEach
    public void setUp() {
        pagoRepository = mock(PagoRepository.class);
        rabbitTemplate = mock(AmqpTemplate.class);
        pagoService = new PagoService(pagoRepository, rabbitTemplate);
//        pagoService.setRabbitTemplate(rabbitTemplate);
    }

    @Test
    void testCrearPago() {
        Pago pago = new Pago();
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        Pago result = pagoService.crearPago(pago);

        assertEquals(pago, result);
    }

    @Test
    void testVerificarEstatusPago() {

        Long id = 1L;
        Pago pago = new Pago();
        pago.setEstatus("PENDIENTE");
        when(pagoRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(pago));


        String result = pagoService.verificarEstatusPago(id);


        assertEquals("PENDIENTE", result);
    }

    @Test
    void testCambiarEstatusPago() {

        Long id = 1L;
        Pago pago = new Pago();
        pago.setEstatus("PENDIENTE");
        when(pagoRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(pago));


        String result = pagoService.cambiarEstatusPago(id, "PAGADO");


        assertEquals("Estatus del pago actualizado correctamente", result);
    }

}
