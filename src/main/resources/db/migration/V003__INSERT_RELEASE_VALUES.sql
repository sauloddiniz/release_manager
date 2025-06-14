INSERT INTO MANAGER.RELEASE (system, version, commits, notes, "user", user_update, released_at, deleted_at)
VALUES
    ('ERP', '1.0.0',      '["a1b2c3d4", "e5f6g7h8"]', 'Primeira release',        'alice',   'alice',   '2024-06-01 12:00:00', NULL),
    ('ERP', '1.1.0',      '["i9j0k1l2"]',             'Correção de bugs',        'bob',     'bob',     '2024-06-15 09:30:00', NULL),
    ('CRM', '2.0.0-beta', '["m3n4o5p6"]',             'Release beta',            'carla',   'duda',    '2024-05-10 18:30:00', '2024-06-12 10:00:00'),
    ('Website', '0.9.5',  '["q7r8s9t0"]',             'Pré-lançamento',          'davi',    'davi',    '2024-05-22 08:45:00', NULL),
    ('App', '1.5.2',      '["u1v2w3x4"]',             'Melhorias de UX',         'erika',   'erika',   '2024-04-02 14:12:00', '2024-06-01 15:00:00'),
    ('ERP', '2.0.1',      '["y5z6a7b8", "c9d0e1f2"]', 'Atualização de segurança','alice',   'bob',     '2024-03-18 19:23:00', NULL),
    ('CRM', '2.0.0',      '["g3h4i5j6"]',             'Versão estável',          'carla',   'carla',   '2024-05-25 10:00:00', NULL),
    ('Website', '1.0.0',  '["k7l8m9n0"]',             'Lançamento oficial',      'davi',    'erika',   '2024-06-01 16:50:00', NULL),
    ('App', '1.6.0',      '["o1p2q3r4"]',             'Nova funcionalidade',     'erika',   'davi',    '2024-06-11 13:25:00', '2024-06-14 01:00:00'),
    ('ERP', '2.1.0',      '["s5t6u7v8"]',             'Integração API',          'bob',     'bob',     '2024-06-14 10:10:00', NULL);