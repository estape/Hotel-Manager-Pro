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
CREATE TABLE IF NOT EXISTS reserva_diaria_log (
	id SERIAL PRIMARY KEY,
	nome_func VARCHAR(255),
	dt_acesso DATE
);

DELETE FROM reserva_diaria_log
WHERE dt_acesso != CURRENT_DATE;

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

CREATE TABLE IF NOT EXISTS res_status (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
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
    id SERIAL PRIMARY KEY,
    num_quarto INT NOT NULL UNIQUE,
    qtd_cama_solt INT NOT NULL,
    qtd_cama_cas INT NOT NULL,
    qtd_banh INT NOT NULL,
    info_adc VARCHAR(255) NOT NULL,
    id_tipoquar_fk INT REFERENCES tipo_quarto(id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS reserva (
    id SERIAL PRIMARY KEY,
    dt_entrada DATE NOT NULL,
    dt_saida DATE NOT NULL,
    id_res_status_fk INT REFERENCES res_status(id) ON DELETE CASCADE NOT NULL,
    id_pedido_fk INT REFERENCES pedido(id) ON DELETE CASCADE NOT NULL,
    id_quarto_fk INT REFERENCES quarto(id) ON DELETE CASCADE NOT NULL
);

-- >>>>========================<<<<
-- >>> populando banco de dados <<<
-- >>>>========================<<<<

-- ON CONFLICT: Se encontrar conflito no atributo nome (que precisa
-- ter clausula UNIQUE) DO NOTHING (Faça nada), ou seja, se tiver
-- conflito no valor a ser inserido em nome (for igual a um valor
-- existente), faça nada.
INSERT INTO res_status (nome)
VALUES 
    ('Reservado'), 
    ('Check-in feito'), 
    ('Atrasado'), 
    ('Cancelado')
ON CONFLICT (nome) DO NOTHING;

-- Processo parecido com o de cima, só que no campo cpf.
INSERT INTO funcionario (nome, cpf, cargo)
VALUES ('admin', 'admin', 'admin')
ON CONFLICT (cpf) DO NOTHING;

-- >>>>=======================<<<<
-- >>>          views          <<<
-- >>>>=======================<<<<

-- Dropa view, pois view não aceita IF NOT EXISTS
DROP VIEW IF EXISTS valor_reserva;
-- Cria nova view
CREATE VIEW valor_reserva AS
SELECT 
    r.id AS reserva_id,
    (r.dt_saida - r.dt_entrada) * t.valor_unit AS valor_reserva
FROM 
    reserva AS r
JOIN 
    quarto AS q ON r.id_quarto_fk = q.id
JOIN 
    tipo_quarto AS t ON q.id_tipoquar_fk = t.id;
-- Dropa view, pois view não aceita IF NOT EXISTS
DROP VIEW IF EXISTS valor_total_pedido;
-- Cria nova view
CREATE VIEW valor_total_pedido AS
SELECT 
    p.id AS pedido_id,
    SUM((r.dt_saida - r.dt_entrada) * t.valor_unit) AS valor_total_pedido
FROM 
    pedido AS p
JOIN 
    reserva AS r ON p.id = r.id_pedido_fk
JOIN 
    -- Aqui, apesar de ter só um valor não precisando filtra-lo
    -- necessitamos específicar o ponto de junção com o ON.
    quarto AS q ON r.id_quarto_fk = q.id
JOIN 
    tipo_quarto AS t ON q.id_tipoquar_fk = t.id
GROUP BY 
    p.id;