package com.alura.forohub.domain.dto;

import com.alura.forohub.domain.entities.Status;
import com.alura.forohub.domain.entities.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaObtenerTopicoDTO(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        Integer autor,
        String curso
) {
    public DatosRespuestaObtenerTopicoDTO(Topico t) {
        this(t.getTitulo(), t.getMensaje(), t.getFechaCreacion(), t.getStatus(), t.getAutor(), t.getCurso());
    }
}
