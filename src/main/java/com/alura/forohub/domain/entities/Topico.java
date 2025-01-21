package com.alura.forohub.domain.entities;

import com.alura.forohub.domain.dto.DatosRegistroTopicoDTO;
import com.alura.forohub.domain.dto.DatosRespuestaTopicoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "topicos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;
    @Column(insertable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer autor;
    private String curso;

    public Topico(DatosRegistroTopicoDTO datosRegistroTopicoDTO) {
        this.titulo = datosRegistroTopicoDTO.titulo();
        this.mensaje = datosRegistroTopicoDTO.mensaje();
        this.autor = datosRegistroTopicoDTO.autor();
        this.curso = datosRegistroTopicoDTO.curso();
        this.fechaCreacion = LocalDateTime.now();
    }

    public Topico(DatosRegistroTopicoDTO datosRegistroTopicoDTO, Long id) {
        this.id = id;
        this.titulo = datosRegistroTopicoDTO.titulo();
        this.mensaje = datosRegistroTopicoDTO.mensaje();
        this.autor = datosRegistroTopicoDTO.autor();
        this.curso = datosRegistroTopicoDTO.curso();
        this.status = Status.ABIERTO;
    }

    public void actualizar(DatosRegistroTopicoDTO datosRegistroTopicoDTO){
        if(datosRegistroTopicoDTO.titulo() != null) this.titulo = datosRegistroTopicoDTO.titulo();
        if(datosRegistroTopicoDTO.mensaje() != null) this.mensaje = datosRegistroTopicoDTO.mensaje();
        if(datosRegistroTopicoDTO.autor() != null) this.autor = datosRegistroTopicoDTO.autor();
        if(datosRegistroTopicoDTO.curso() != null) this.curso = datosRegistroTopicoDTO.curso();
    }
}
