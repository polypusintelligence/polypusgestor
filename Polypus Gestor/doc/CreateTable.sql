CREATE TABLE perfil(
  per_id_perfil        INTEGER IDENTITY(1,1)      NOT NULL,
  per_tx_descricao     VARCHAR(255)               NOT NULL,
  CONSTRAINT pk__perfil  PRIMARY KEY  (per_id_perfil),
  CONSTRAINT uk__perfil__per_tx_descricao UNIQUE (per_tx_descricao)
);

INSERT INTO perfil (per_tx_descricao) VALUES ('Administrador');
INSERT INTO perfil (per_tx_descricao) VALUES ('Gerencial');

CREATE TABLE relatorio(
  rel_id_relatorio     INTEGER IDENTITY(1,1)      NOT NULL,
  rel_tx_descricao     VARCHAR(255)               NOT NULL,
  CONSTRAINT pk__relatorio PRIMARY KEY  (rel_id_relatorio),
  CONSTRAINT uk__relatorio UNIQUE (rel_tx_descricao)
);

CREATE TABLE perfil_relatorio(
  per_id_perfil        INTEGER           NOT NULL,
  rel_id_relatorio     INTEGER           NOT NULL,
  CONSTRAINT pk__perfil_relatorio PRIMARY KEY  (per_id_perfil, rel_id_relatorio),
  CONSTRAINT fk__perfil_relatorio__per_id_perfil FOREIGN KEY (per_id_perfil) REFERENCES perfil (per_id_perfil),
  CONSTRAINT fk__perfil_relatorio__rel_id_relatorio FOREIGN KEY (rel_id_relatorio) REFERENCES relatorio (rel_id_relatorio)
);

CREATE TABLE usuario(
  usu_id_usuario     INTEGER IDENTITY(1,1)      NOT NULL,
  per_id_perfil      INTEGER                    NOT NULL,
  usu_tx_nome        VARCHAR(100)               NOT NULL,
  usu_tx_email       VARCHAR(100)               NOT NULL,
  usu_tx_telefone    VARCHAR(30)                NOT NULL,
  usu_tx_senha       VARCHAR(255)               NOT NULL,
  CONSTRAINT pk__usuario  PRIMARY KEY  (per_id_perfil),
  CONSTRAINT uk__usuario__usu_tx_nome UNIQUE (usu_tx_nome),
  CONSTRAINT uk__usuario__usu_tx_email UNIQUE (usu_tx_email),
  CONSTRAINT fk__usuario__per_id_perfil FOREIGN KEY (per_id_perfil) REFERENCES perfil (per_id_perfil),
);


