CREATE TABLE IF NOT EXISTS MANAGER.RELEASE (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    system VARCHAR(255) NOT NULL,
    version VARCHAR(30) NOT NULL,
    commits CLOB NOT NULL,
    notes TEXT,
    "user" VARCHAR(100) NOT NULL,
    user_update VARCHAR(100),
    released_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

COMMENT ON COLUMN MANAGER."RELEASE".id IS 'Chave primária, auto incremento';
COMMENT ON COLUMN MANAGER."RELEASE".system IS 'Nome do sistema (trim automático, obrigatório)';
COMMENT ON COLUMN MANAGER."RELEASE".version IS 'Versão do release (obrigatório)';
COMMENT ON COLUMN MANAGER."RELEASE".commits IS 'Lista de commits em formato JSON (string array)';
COMMENT ON COLUMN MANAGER."RELEASE".notes IS 'Notas do release (opcional)';
COMMENT ON COLUMN MANAGER."RELEASE"."user" IS 'Usuário responsável pelo release (obrigatório)';
COMMENT ON COLUMN MANAGER."RELEASE".user_update IS 'Usuário do update, extraído do token';
COMMENT ON COLUMN MANAGER."RELEASE".released_at IS 'Data/hora da criação do release (preenchimento automático)';
COMMENT ON COLUMN MANAGER."RELEASE".deleted_at IS 'Data/hora do soft delete (nulo quando ativo)';