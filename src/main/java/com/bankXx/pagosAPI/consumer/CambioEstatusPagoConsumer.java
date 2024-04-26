package com.bankXx.pagosAPI.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CambioEstatusPagoConsumer {

    @RabbitListener(queues = "cambio-estatus-pago")
    public void recibirNotificacionCambioEstatus(String mensaje) {
    	log.info(" ");
    	log.info("********************************************");
    	log.info("********************************************");
    	log.info("********************************************");
        log.info("Notificación de cambio de estatus RECIBIDA desde RabbitMQ: " + mensaje);
        log.info("********************************************");
    	log.info("********************************************");
    	log.info("********************************************");
    }
}
