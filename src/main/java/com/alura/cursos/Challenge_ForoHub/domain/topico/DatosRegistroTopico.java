package com.alura.cursos.Challenge_ForoHub.domain.topico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(

        @NotNull
        Long autorId,
        @NotBlank
        String curso,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje
) {
}
