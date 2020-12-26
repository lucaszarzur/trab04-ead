#Criacao do banco de dados
create database biblioteca;

#Tabela USUARIO
CREATE TABLE IF NOT EXISTS BIBLIOTECA.USUARIO (
  ID_USUARIO INT NOT NULL,
  NOME VARCHAR(200) NOT NULL,
  EMAIL VARCHAR(60) NOT NULL,
  TIPO_USUARIO ENUM("Aluno", "Servidor") NOT NULL,
  RUA VARCHAR(100) NOT NULL,
  BAIRRO VARCHAR(100) NOT NULL,
  NUMERO INT NOT NULL,
  COMPLEMENTO VARCHAR(100) NOT NULL,
  ESTADO VARCHAR(100) NOT NULL,
  CIDADE VARCHAR(200) NOT NULL,
  DATA_NASCIMENTO DATE NOT NULL,
  STATUS ENUM("Ativo", "Inativo") NOT NULL,
  SENHA VARCHAR(50) NOT NULL,
  ADMIN TINYINT NOT NULL,
  PRIMARY KEY (ID_USUARIO))
ENGINE = InnoDB;
CREATE UNIQUE INDEX EMAIL_UNIQUE ON BIBLIOTECA.USUARIO (EMAIL ASC) VISIBLE;

#Tabela USUARIO_TELEFONE
CREATE TABLE IF NOT EXISTS BIBLIOTECA.USUARIO_TELEFONE (
  ID_USUARIO INT NOT NULL AUTO_INCREMENT,
  NUMERO_TELEFONE VARCHAR(11) NOT NULL,
  PRINCIPAL TINYINT NOT NULL,
  PRIMARY KEY (NUMERO_TELEFONE, ID_USUARIO),
  CONSTRAINT FK_TELEFONE_USUARIO
    FOREIGN KEY (ID_USUARIO)
    REFERENCES BIBLIOTECA.USUARIO (ID_USUARIO)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE INDEX FK_TELEFONE_USUARIO_idx ON BIBLIOTECA.USUARIO_TELEFONE (ID_USUARIO ASC) VISIBLE;

#Tabela OBRA
CREATE TABLE IF NOT EXISTS BIBLIOTECA.OBRA (
  ID_OBRA INT NOT NULL AUTO_INCREMENT,
  TITULO VARCHAR(300) NOT NULL,
  ANO_PUBLICACAO INT NOT NULL,
  TIPO_OBRA ENUM("Literatura", "Tese/Monografia", "Outros") NOT NULL,
  PRIMARY KEY (ID_OBRA))
ENGINE = InnoDB;

#Tabela AUTOR
CREATE TABLE IF NOT EXISTS BIBLIOTECA.AUTOR (
  ID_AUTOR INT NOT NULL AUTO_INCREMENT,
  NOME_AUTOR VARCHAR(200) NOT NULL,
  PRIMARY KEY (ID_AUTOR))
ENGINE = InnoDB;

#Tabela AUTOR_OBRA
CREATE TABLE IF NOT EXISTS BIBLIOTECA.AUTOR_OBRA (
  ID_OBRA INT NOT NULL,
  ID_AUTOR INT NOT NULL,
  PRIMARY KEY (ID_OBRA, ID_AUTOR),
  CONSTRAINT FK_AUTOR_OBRA
    FOREIGN KEY (ID_OBRA)
    REFERENCES BIBLIOTECA.OBRA (ID_OBRA)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_ID_AUTOR
    FOREIGN KEY (ID_AUTOR)
    REFERENCES BIBLIOTECA.AUTOR (ID_AUTOR)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE INDEX FK_ID_AUTOR_idx ON BIBLIOTECA.AUTOR_OBRA (ID_AUTOR ASC) VISIBLE;
CREATE INDEX FK_AUTOR_OBRA_idx ON BIBLIOTECA.AUTOR_OBRA (ID_OBRA ASC) VISIBLE;

#Tabela EXEMPLAR
CREATE TABLE IF NOT EXISTS BIBLIOTECA.EXEMPLAR (
  ID_EXEMPLAR INT NOT NULL AUTO_INCREMENT,
  DATA_AQUISICAO DATE NOT NULL,
  ID_OBRA INT NULL,
  SITUACAO ENUM("Disponível", "Emprestado", "Devolvido", "Reservado", "Extraviado") NOT NULL,
  PRIMARY KEY (ID_EXEMPLAR),
  CONSTRAINT FK_EXEMPLAR_OBRA
    FOREIGN KEY (ID_OBRA)
    REFERENCES BIBLIOTECA.OBRA (ID_OBRA)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE INDEX FK_EXEMPLAR_OBRA_idx ON BIBLIOTECA.EXEMPLAR (ID_OBRA ASC) VISIBLE;

#Tabela EMPRESTIMO
CREATE TABLE IF NOT EXISTS BIBLIOTECA.EMPRESTIMO (
  ID_EMPRESTIMO INT NOT NULL AUTO_INCREMENT,
  ID_USUARIO INT NOT NULL,
  ID_EXEMPLAR INT NOT NULL,
  DATA_EMPRESTIMO DATE NULL,
  DATA_PREVISTA_DEVOLUCAO DATE NULL,
  PRIMARY KEY (ID_EMPRESTIMO),
  CONSTRAINT FK_EMPRESTIMO_USUARIO
    FOREIGN KEY (ID_USUARIO)
    REFERENCES BIBLIOTECA.USUARIO (ID_USUARIO)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_EMPRESTIMO_EXEMPLAR
    FOREIGN KEY (ID_EXEMPLAR)
    REFERENCES BIBLIOTECA.EXEMPLAR (ID_EXEMPLAR)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE INDEX FK_EMPRESTIMO_USUARIO_idx ON BIBLIOTECA.EMPRESTIMO (ID_USUARIO ASC) VISIBLE;
CREATE INDEX FK_EMPRESTIMO_EXEMPLAR_idx ON BIBLIOTECA.EMPRESTIMO (ID_EXEMPLAR ASC) VISIBLE;

#Tabela DEVOLUCAO
CREATE TABLE IF NOT EXISTS BIBLIOTECA.DEVOLUCAO (
  ID_DEVOLUCAO INT NOT NULL AUTO_INCREMENT,
  DATA_DEVOLUCAO DATE NOT NULL,
  ID_EMPRESTIMO INT NOT NULL,
  PRIMARY KEY (ID_DEVOLUCAO),
  CONSTRAINT FK_DEVOLUCAO_EMPRESTIMO
    FOREIGN KEY (ID_EMPRESTIMO)
    REFERENCES BIBLIOTECA.EMPRESTIMO (ID_EMPRESTIMO)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE INDEX FK_DEVOLUCAO_EMPRESTIMO_idx ON BIBLIOTECA.DEVOLUCAO (ID_EMPRESTIMO ASC) VISIBLE;

#Tabela RESERVA
CREATE TABLE IF NOT EXISTS BIBLIOTECA.RESERVA (
  ID_RESERVA INT NOT NULL AUTO_INCREMENT,
  ID_USUARIO INT NOT NULL,
  ID_EXEMPLAR INT NOT NULL,
  DATA_RESERVA DATE NOT NULL,
  PRIMARY KEY (ID_RESERVA),
  CONSTRAINT FK_RESERVA_EXEMPLAR
    FOREIGN KEY (ID_EXEMPLAR)
    REFERENCES BIBLIOTECA.EXEMPLAR (ID_EXEMPLAR)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_RESERVA_USUARIO
    FOREIGN KEY (ID_USUARIO)
    REFERENCES BIBLIOTECA.USUARIO (ID_USUARIO)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE INDEX FK_RESERVA_EXEMPLAR_idx ON BIBLIOTECA.RESERVA (ID_EXEMPLAR ASC) VISIBLE;
CREATE INDEX FK_RESERVA_USUARIO_idx ON BIBLIOTECA.RESERVA (ID_USUARIO ASC) VISIBLE;