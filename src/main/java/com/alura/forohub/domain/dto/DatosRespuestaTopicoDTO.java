package com.alura.forohub.domain.dto;

import com.alura.forohub.domain.entities.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion
) {
    public DatosRespuestaTopicoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion());
    }
}
