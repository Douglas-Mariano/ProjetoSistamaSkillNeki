CREATE DATABASE sistemaskill;

CREATE SEQUENCE seq_usuario START 1;

CREATE TABLE usuario (
    idUsuario INT PRIMARY KEY DEFAULT nextval('seq_usuario'),
    nome VARCHAR(255) NOT NULL,
    login VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE SEQUENCE seq_skill START 1;

CREATE TABLE skill (
    idSkill INT PRIMARY KEY DEFAULT nextval('seq_skill'),
    nome VARCHAR(255) UNIQUE NOT NULL,
    descricao TEXT,
    imagem VARCHAR(255) NOT NULL
);

CREATE SEQUENCE seq_usuario_skill START 1;

CREATE TABLE usuario_skill (
    idUsuarioSkill INT PRIMARY KEY DEFAULT nextval('seq_usuario_skill'),
    level INT NOT NULL,
    idUsuario INT REFERENCES usuario(idUsuario),
    idSkill INT REFERENCES skill(idSkill)
);
