-- SOBRE RELAÇÃO DE CHAVES
-- Todas as FOREIGN KEYS deste projeto não são unicas,
-- pois não ocorreá relação 1:1, somente 1:n

-- SOBRE ON DELETE CASCADE
-- Tabela configurada para "ON DELETE CASCADE", porém
-- é sabido que NÃO É UMA BOA PRATICA DELETAR VALORES
-- em termos de banco de dados

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
    valor_unit NUMERIC NOT NULL
);

-- >>>>=====================<<<<
-- >>> tabelas independentes <<<
-- >>>>=====================<<<<
-- A FOREIGN KEYS deve ser NOT NULL, pois hospede é (1,1) e
-- funcionario também é (1,1)
CREATE TABLE IF NOT EXISTS pedido (
    id SERIAL PRIMARY KEY,
    dt_pedido DATE NOT NULL,
    id_hosp_fk INT REFERENCES hospede(id) ON DELETE CASCADE NOT NULL,
    id_func_fk INT REFERENCES funcionario(id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS quarto (
    num SERIAL PRIMARY KEY,
    qtd_cama_solt INT NOT NULL,
    qtd_cama_cas INT NOT NULL,
    qtd_banh INT NOT NULL,
    info_adc VARCHAR(255) NOT NULL,
    vl_quarto NUMERIC NOT NULL,
    id_tipoquar_fk INT REFERENCES tipo_quarto(id) ON DELETE CASCADE NOT NULL,
    id_caracquar_fk INT REFERENCES carac_quarto(id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS reserva (
    id SERIAL PRIMARY KEY,
    dt_entrada DATE NOT NULL,
    dt_saida DATE NOT NULL,
    res_status VARCHAR(255) NOT NULL,
    id_pedido_fk INT REFERENCES pedido(id) ON DELETE CASCADE NOT NULL,
    num_quarto_fk INT REFERENCES quarto(num) ON DELETE CASCADE NOT NULL
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

-- >>>>=======================<<<<
-- >>>          views          <<<
-- >>>>=======================<<<<

CREATE VIEW valor_reserva AS
SELECT 
    r.id AS reserva_id,
    (r.data_saida - r.data_entrada) * t.valor_unitario AS valor_reserva
FROM 
    reserva AS r
JOIN 
    quarto AS q ON r.num_quarto_fk = q.num
JOIN 
    tipo_quarto AS t ON q.id_tipoquar_fk = t.id;

CREATE VIEW valor_total_pedido AS
SELECT 
    p.id AS pedido_id,
    SUM((r.data_saida - r.data_entrada) * t.valor_unitario) AS valor_total_pedido
FROM 
    pedido AS p
JOIN 
    reserva AS r ON p.id = r.id_pedido_fk
JOIN 
    -- Aqui, apesar de ter só um valor não precisando filtra-lo
    -- necessitamos específicar o ponto de junção com o ON.
    quarto AS q ON r.num_quarto_fk = q.num
JOIN 
    tipo_quarto AS t ON q.id_tipoquar_fk = t.id
GROUP BY 
    p.id;