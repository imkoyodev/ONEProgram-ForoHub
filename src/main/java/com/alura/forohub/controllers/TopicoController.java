package com.alura.forohub.controllers;

import com.alura.forohub.domain.dto.DatosRegistroTopicoDTO;
import com.alura.forohub.domain.dto.DatosRespuestaObtenerTopicoDTO;
import com.alura.forohub.domain.dto.DatosRespuestaTopicoDTO;
import com.alura.forohub.domain.entities.Topico;
import com.alura.forohub.domain.repositories.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @PostMapping()
    public ResponseEntity<DatosRespuestaTopicoDTO> registrarTopico(@RequestBody @Valid DatosRegistroTopicoDTO datosRegistroTopicoDTO, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopicoDTO));
        DatosRespuestaTopicoDTO datosRespuestaTopicoDTO = new DatosRespuestaTopicoDTO(topico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopicoDTO);
    }

    @GetMapping()
    public ResponseEntity<Page<DatosRespuestaObtenerTopicoDTO>> obtenerTodosLosTopicos(@PageableDefault(size = 10) Pageable pageable){
        //return ResponseEntity.ok(topicoRepository.findAll().stream().map(t -> { new DatosRespuestaTodosTopicoDTO(t)}).toList());
        return ResponseEntity.ok(topicoRepository.findAll(pageable).map(DatosRespuestaObtenerTopicoDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaObtenerTopicoDTO> obtenerTopicoPorId(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico =new DatosRespuestaObtenerTopicoDTO(topico);
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @RequestBody DatosRegistroTopicoDTO datosRegistroTopicoDTO){
        var topicoEncontrado = topicoRepository.findById(id);
        if(topicoEncontrado.isPresent()){
            var topico = topicoEncontrado.get();
            topico.actualizar(datosRegistroTopicoDTO);
            return ResponseEntity.ok(new DatosRespuestaTopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        var topicoEncontrado = topicoRepository.findById(id);
        if(topicoEncontrado.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
