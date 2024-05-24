-- Todas as FOREIGN KEYS deste projeto não são unicas,
-- pois não ocorreá relação 1:1, somente 1:n

-- >>>>=====================<<<<
-- >>> tabelas independentes <<<
-- >>>>=====================<<<<
CREATE TABLE IF NOT EXISTS funcionario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    cargo VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS hospede (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS tipo_quarto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    valor NUMERIC NOT NULL
);

CREATE TABLE IF NOT EXISTS carac_quarto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    valor NUMERIC NOT NULL
);

-- >>>>=====================<<<<
-- >>> tabelas independentes <<<
-- >>>>=====================<<<<
-- A FOREIGN KEYS deve ser NOT NULL, pois hospede é (1,1) e
-- funcionario também é (1,1)
CREATE TABLE IF NOT EXISTS pedido (
    id SERIAL PRIMARY KEY,
    dt_pedido DATE NOT NULL,
    vl_total NUMERIC,
    id_hosp_fk INT REFERENCES hospede(id) NOT NULL,
    id_func_fk INT REFERENCES funcionario(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS quarto (
    num SERIAL PRIMARY KEY,
    qtd_cama_solt INT NOT NULL,
    qtd_cama_cas INT NOT NULL,
    qtd_banh INT NOT NULL,
    info_adc VARCHAR(255) NOT NULL,
    vl_quarto NUMERIC NOT NULL,
    id_tipoquar_fk INT REFERENCES tipo_quarto(id) NOT NULL,
    id_caracquar_fk INT REFERENCES carac_quarto(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS reserva (
    id SERIAL PRIMARY KEY,
    vl_quarto NUMERIC NOT NULL,
    dt_entrada DATE NOT NULL,
    dt_saida DATE NOT NULL,
    res_status VARCHAR(255) NOT NULL,
    id_pedido_fk INT REFERENCES pedido(id) NOT NULL, 
    num_quarto_fk INT REFERENCES quarto(num) NOT NULL
);

-- A FOREIGN KEYS deve ser NOT NULL, pois hospede é (1,1)
CREATE TABLE IF NOT EXISTS acompanhante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    idade INT NOT NULL,
    id_reserva_fk INT REFERENCES reserva(id) NOT NULL
);

-- >>>>=======================<<<<
-- >>> criando usuario inicial <<<
-- >>>>=======================<<<<

-- Verifica se existe esse cpf, se não, fazer o insert.
-- INSERT com SELECT realiza a mesma coisa que INSERT com VALUES
-- A diferenã é que VALUES não aceita clausula WHERE.
-- Como precisamos validar essa subquery, usamos INSERT SELECT WHERE
-- A utilização do SELECT 1 é convenção para verificar existencia
INSERT INTO funcionario (nome, cpf, cargo)
SELECT 'admin', 'admin', 'admin'
WHERE NOT EXISTS (SELECT 1 FROM funcionario WHERE cpf = 'admin');