package com.alura.cursos.Challenge_ForoHub.domain.topico;

import com.alura.cursos.Challenge_ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private Boolean status;
    private String curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuario autor;

    public Topico(String titulo, String mensaje, LocalDateTime fecha, Boolean status,String curso, Usuario autor) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.status = status;
        this.curso = curso;
        this.autor = autor;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {

        if(datosActualizarTopico.titulo() != null)
        {
            this.titulo = datosActualizarTopico.titulo();
        }

        if(datosActualizarTopico.mensaje() != null)
        {
            this.mensaje = datosActualizarTopico.mensaje();
        }

        if(datosActualizarTopico.curso() != null)
        {
            this.curso = datosActualizarTopico.curso();
        }
    }

    public void desactivarTopico() {

        this.status=false;

    }


}
