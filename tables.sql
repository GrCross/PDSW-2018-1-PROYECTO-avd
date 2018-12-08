-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-11-20 21:07:43.597

-- tables

-- Table: Comentarios
CREATE TABLE Comentarios (
    contenido varchar(300)  NOT NULL,
    fechaCreacion date  NOT NULL,
    Usuarios_documento int  NOT NULL,
    Iniciativas_id int  NOT NULL,
    commentId int NOT NULL,
    CONSTRAINT Comentarios_pk PRIMARY KEY (contenido,Usuarios_documento,Iniciativas_id)
);

-- Table: Iniciativas
CREATE TABLE Iniciativas (
    id int  NOT NULL,
    Usuarios_documento int  NOT NULL,
    nombre varchar(50)  NOT NULL,
    descripcion varchar(500)  NOT NULL,
    fechaCreacion date  NOT NULL,
    palabrasClave varchar(300)  NOT NULL,
    area varchar(50)  NOT NULL,
    estados_idEstado varchar(50)  NOT NULL,
    CONSTRAINT Iniciativas_pk PRIMARY KEY (id)
);

-- Table: Intereses
CREATE TABLE Intereses (
    Iniciativa_documento int  NOT NULL,
    Usuarios_id int  NOT NULL,
    voto boolean  NOT NULL,
    CONSTRAINT Intereses_pk PRIMARY KEY (Iniciativa_documento,Usuarios_id)
);

-- Table: Usuarios
CREATE TABLE Usuarios (
    documento int  NOT NULL,
    nombre varchar(50)  NOT NULL,
    area varchar(50)  NOT NULL,
    telefono int  NOT NULL,
    correo varchar(50)  NOT NULL,
    rol varchar(50)  NOT NULL,
    CONSTRAINT Iniciativa_correo_unique UNIQUE (correo),
    CONSTRAINT documento PRIMARY KEY (documento)
);

-- foreign keys
-- Reference: Comentarios_Iniciativas (table: Comentarios)
ALTER TABLE Comentarios ADD CONSTRAINT Comentarios_Iniciativas
    FOREIGN KEY (Iniciativas_id)
    REFERENCES Iniciativas (id)
;

-- Reference: Comentarios_Usuarios (table: Comentarios)
ALTER TABLE Comentarios ADD CONSTRAINT Comentarios_Usuarios
    FOREIGN KEY (Usuarios_documento)
    REFERENCES Usuarios (documento)
;

-- Reference: Iniciativas_Usuarios (table: Iniciativas)
ALTER TABLE Iniciativas ADD CONSTRAINT Iniciativas_Usuarios
    FOREIGN KEY (Usuarios_documento)
    REFERENCES Usuarios (documento)
;


 --Reference: Interes_Iniciativa (table: Intereses)
ALTER TABLE Intereses ADD CONSTRAINT Interes_Iniciativa
    FOREIGN KEY (Iniciativa_documento)
    REFERENCES Iniciativas(id)  
;

-- Reference: Interes_Usuarios (table: Intereses)
ALTER TABLE Intereses ADD CONSTRAINT Interes_Usuarios
    FOREIGN KEY (Usuarios_id)
    REFERENCES Usuarios(documento)  
;

-- End of file.

