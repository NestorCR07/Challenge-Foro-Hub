package com.alura.cursos.Challenge_ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String curso,
        Long idAutor
) {
    public DatosListadoTopicos(Topico topico)
    {
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),
                topico.getFecha(),topico.getCurso(),topico.getAutor().getId());
    }
}
