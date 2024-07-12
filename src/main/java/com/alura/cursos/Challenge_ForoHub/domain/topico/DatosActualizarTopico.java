package com.alura.cursos.Challenge_ForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(

        @NotNull
        Long id,

        String curso,

        String titulo,

        String mensaje


) {
}
