CREATE TABLE topicos(
    id bigint not null auto_increment,
    titulo VARCHAR(255) NOT NULL,
    mensaje MEDIUMTEXT NOT NULL,
    fechaCreacion DATETIME NOT NULL,
    status BOOLEAN,
    autor bigint,

    primary key(id),

    FOREIGN KEY (autor) REFERENCES usuarios(id)
);