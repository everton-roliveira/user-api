-- Cria a tabela person_tbl
CREATE TABLE IF NOT EXISTS person_tbl (
    id SERIAL NOT NULL,
    name VARCHAR(50) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    person_tax_id VARCHAR(14) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(11),
    gender CHAR(1),

    deleted_at TIMESTAMP,

    PRIMARY KEY (id),
    UNIQUE (email),
    UNIQUE (phone_number),
    UNIQUE (person_tax_id)
);

-- Adiciona comentários às colunas person_tbl
COMMENT ON COLUMN person_tbl.name IS 'nome da pessoa';
COMMENT ON COLUMN person_tbl.lastname IS 'nome do meio e último nome da pessoa';
COMMENT ON COLUMN person_tbl.person_tax_id IS 'CPF ou CNPJ da pessoa';
COMMENT ON COLUMN person_tbl.email IS 'email da pessoa';
COMMENT ON COLUMN person_tbl.phone_number IS 'celular da pessoa com o DDD';
COMMENT ON COLUMN person_tbl.gender IS 'gênero da pessoa (M = Masculino, F = Feminino)';

-- Tabela person_tbl_audit
CREATE TABLE IF NOT EXISTS person_tbl_audit (
    id SERIAL NOT NULL,
    person_id INT NOT NULL,
    operation VARCHAR(10) NOT NULL,
    name VARCHAR(50),
    lastname VARCHAR(255),
    person_tax_id VARCHAR(14),
    email VARCHAR(100),
    phone_number VARCHAR(11),
    gender CHAR(1),
    deleted_at TIMESTAMP,
    audit_timestamp TIMESTAMP,
    
    PRIMARY KEY (id)
);

-- trigger para auditar
-- Gatilho para inserção (INSERT) na tabela person_tbl
CREATE OR REPLACE FUNCTION person_insert_trigger()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO person_tbl_audit (person_id, operation, name, lastname, person_tax_id, email, phone_number, gender, audit_timestamp)
    VALUES (NEW.id, 'INSERT', NEW.name, NEW.lastname, NEW.person_tax_id, NEW.email, NEW.phone_number, NEW.gender, NOW());
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Gatilho para atualização (UPDATE) na tabela person_tbl
CREATE OR REPLACE FUNCTION person_update_trigger()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO person_tbl_audit (person_id, operation, name, lastname, person_tax_id, email, phone_number, gender, audit_timestamp)
    VALUES (NEW.id, 'UPDATE', NEW.name, NEW.lastname, NEW.person_tax_id, NEW.email, NEW.phone_number, NEW.gender, NOW());
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Associa os gatilhos à tabela person_tbl
CREATE TRIGGER person_insert
AFTER INSERT ON person_tbl
FOR EACH ROW
EXECUTE FUNCTION person_insert_trigger();

CREATE TRIGGER person_update
AFTER UPDATE ON person_tbl
FOR EACH ROW
EXECUTE FUNCTION person_update_trigger();