package com.alura.cursos.Challenge_ForoHub.domain.usuario;

import com.alura.cursos.Challenge_ForoHub.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
