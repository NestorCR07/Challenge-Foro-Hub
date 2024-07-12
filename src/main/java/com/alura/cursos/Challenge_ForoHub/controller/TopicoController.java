package com.alura.cursos.Challenge_ForoHub.controller;

import com.alura.cursos.Challenge_ForoHub.domain.topico.*;
import com.alura.cursos.Challenge_ForoHub.domain.usuario.Usuario;
import com.alura.cursos.Challenge_ForoHub.domain.usuario.UsuarioRepository;
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
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    //Agregar topico
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        Usuario autor = usuarioRepository.findById(datosRegistroTopico.autorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Topico topico = new Topico(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje(),
                LocalDateTime.now(), true,datosRegistroTopico.curso(), autor);

        topicoRepository.save(topico);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(),
                topico.getTitulo(), topico.getMensaje(), topico.getFecha(),topico.getCurso(),
                topico.getAutor().getId());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    //Listar todos los topicos
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listadoTopicos(@PageableDefault(size = 2) Pageable paginacion) {
        Page<Topico> topicosPage = topicoRepository.findByStatusTrue(paginacion);
        Page<DatosListadoTopicos> datosListadoTopicosPage = topicosPage.map(DatosListadoTopicos::new);
        return ResponseEntity.ok(datosListadoTopicosPage);
    }

    //Buscar topico por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado"));

        var datosTopico = new DatosRespuestaTopico(topico.getId(),topico.getTitulo(), topico.getMensaje(),
                topico.getFecha(), topico.getCurso(), topico.getAutor().getId());

        return ResponseEntity.ok(datosTopico);
    }

    //Actualizar Topico
    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(),
                topico.getTitulo(), topico.getMensaje(), topico.getFecha(),topico.getCurso(),
                topico.getAutor().getId()));
    }

    //Eliminar Topico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }


}