CREATE TABLE Afinidades (
    idAfinidad int  NOT NULL,
    tipoAfinidad varchar(20)  NOT NULL,
    CONSTRAINT Afinidades_pk PRIMARY KEY (idAfinidad)
);

-- Table: Comentarios
CREATE TABLE Comentarios (
    contenido varchar(300)  NOT NULL,
    fechaCreacion date  NOT NULL,
    Usuarios_documento int  NOT NULL,
    Iniciativas_id int  NOT NULL,
    CONSTRAINT Comentarios_pk PRIMARY KEY (Usuarios_documento,Iniciativas_id)
);

CREATE TABLE Iniciativas (
    id int  NOT NULL,
    estados_idEstado int  NOT NULL,
    Usuarios_documento int  NOT NULL,
    nombre varchar(50)  NOT NULL,
    descripcion varchar(500)  NOT NULL,
    fechaCreacion date  NOT NULL,
    CONSTRAINT Iniciativas_pk PRIMARY KEY (id)
);

CREATE TABLE Intereses (
    Iniciativa_documento int  NOT NULL,
    Usuarios_id int  NOT NULL,
    Afinidades_idAfinidad int  NOT NULL,
    voto boolean  NOT NULL,
    CONSTRAINT Intereses_pk PRIMARY KEY (Iniciativa_documento,Usuarios_id)
);

CREATE TABLE Usuarios (
    documento int  NOT NULL,
    nombre varchar(50)  NOT NULL,
    area varchar(50)  NOT NULL,
    telefono int  NOT NULL,
    correo varchar(50)  NOT NULL,
    rol varchar(50)  NOT NULL,
    CONSTRAINT documento PRIMARY KEY (documento)
);

-- Table: estados
CREATE TABLE estados (
    idEstado int  NOT NULL,
    tipoEstado varchar(20)  NOT NULL,
    CONSTRAINT estados_pk PRIMARY KEY (idEstado)
);