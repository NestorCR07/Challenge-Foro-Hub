CREATE TABLE respuestas(
    id bigint not null auto_increment,
    mensaje MEDIUMTEXT NOT NULL,
    tópico bigint,
    fechaCreacion DATETIME NOT NULL,
    autor bigint,

    primary key(id),

    FOREIGN KEY (tópico) REFERENCES topicos(id),
    FOREIGN KEY (autor) REFERENCES usuarios(id)
);