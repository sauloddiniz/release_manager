CREATE TABLE IF NOT EXISTS release_manager."release" (
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

COMMENT ON COLUMN release_manager."release".id IS 'Chave primária, auto incremento';
COMMENT ON COLUMN release_manager."release".system IS 'Nome do sistema (trim automático, obrigatório)';
COMMENT ON COLUMN release_manager."release".version IS 'Versão do release (obrigatório)';
COMMENT ON COLUMN release_manager."release".commits IS 'Lista de commits em formato JSON (string array)';
COMMENT ON COLUMN release_manager."release".notes IS 'Notas do release (opcional)';
COMMENT ON COLUMN release_manager."release"."user" IS 'Usuário responsável pelo release (obrigatório)';
COMMENT ON COLUMN release_manager."release".user_update IS 'Usuário do update, extraído do token';
COMMENT ON COLUMN release_manager."release".released_at IS 'Data/hora da criação do release (preenchimento automático)';
COMMENT ON COLUMN release_manager."release".deleted_at IS 'Data/hora do soft delete (nulo quando ativo)';