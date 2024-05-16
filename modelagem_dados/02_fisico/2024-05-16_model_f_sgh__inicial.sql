CREATE TABLE funcionarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cargo VARCHAR(255) NOT NULL,
    login VARCHAR (255),
    senha VARCHAR(64) -- compatível com hashing SHA-256
);

CREATE TABLE hospedes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

 -- A FOREIGN KEYS pode ser NULL por ser 0,n
CREATE TABLE acompanhante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    id_hosp_fk INT REFERENCES hospedes(id)
);

CREATE TABLE status (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL
);

-- A FOREIGN KEYS não são unicas, pois a relação não é 1:1, é n:1
CREATE TABLE reservas (
    id SERIAL PRIMARY KEY,
    valor_total NUMERIC NOT NULL,
    dt_reserva DATE NOT NULL,
    id_func_fk INT REFERENCES funcionarios(id) NOT NULL, 
    id_status_fk INT REFERENCES status(id) NOT NULL,
    id_hosp_fk INT REFERENCES hospedes(id) NOT NULL
);

CREATE TABLE tipo_quarto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    valor NUMERIC NOT NULL
);

CREATE TABLE quartos (
    id SERIAL PRIMARY KEY,
    qtd_cama_solt INT NOT NULL,
    qtd_cama_cas INT NOT NULL,
    qtd_banh INT NOT NULL,
    info_adc VARCHAR(255) NOT NULL,
    id_tipoquar_fk INT REFERENCES tipo_quarto(id)
);

CREATE TABLE reserva_quartos (
    id SERIAL PRIMARY KEY,
    dt_entrada DATE NOT NULL,
    dt_saida DATE NOT NULL,
    id_reserv_fk INT REFERENCES reservas(id) NOT NULL,
    id_quarto_fk INT REFERENCES quartos(id) NOT NULL
);