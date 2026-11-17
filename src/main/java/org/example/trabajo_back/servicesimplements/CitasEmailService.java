package org.example.trabajo_back.servicesimplements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CitasEmailService {
    private static final Logger logger = LoggerFactory.getLogger(CitasEmailService.class);

    private final JavaMailSender mailSender;
    private final String remitente;

    public CitasEmailService(
            JavaMailSender mailSender,
            @Value("${spring.mail.username}") String remitente
    ) {
        this.mailSender = mailSender;
        this.remitente = remitente;
    }

    @Async
    public void enviarNotificacionCita(
            String destinatario,
            String nombrePaciente,
            String accion,
            LocalDate fechaCita,
            String estado,
            String motivoCita,
            String nombreOftalmologo
    ) {
        SimpleMailMessage correo = new SimpleMailMessage();
        correo.setFrom(remitente);
        correo.setTo(destinatario);
        correo.setSubject("OpticVision - Cita " + accion);
        correo.setText("""
                Hola %s:

                Tu cita ha sido %s.

                Fecha: %s
                Estado: %s
                Motivo: %s
                Oftalmólogo: %s

                OpticVision
                """.formatted(
                nombrePaciente,
                accion,
                fechaCita,
                estado,
                motivoCita,
                nombreOftalmologo
        ));

        try {
            mailSender.send(correo);
        } catch (MailException exception) {
            logger.error("No se pudo enviar la notificación de cita por correo", exception);
        }
    }
}
