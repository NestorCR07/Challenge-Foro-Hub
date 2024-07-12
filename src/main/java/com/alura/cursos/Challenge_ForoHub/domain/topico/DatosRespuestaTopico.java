package com.alura.cursos.Challenge_ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(Long id, String titulo, String mensaje, LocalDateTime fecha,String curso,
                                   Long idAutor) {
}
