-- data.sql COMPLETO E CORRIGIDO

-- Insere papéis padrão (roles)
INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN') ON CONFLICT(name) DO NOTHING;

-- Insere usuário administrador com senha 'admin123'
INSERT INTO usuarios (name, email, password, role_id, active)
VALUES ('Admin', 'admin@inclusiveaid.com', '$2a$10$bO.g8.eJpLYL4n/j4L8x.OKP3sV1qmxzC335Gf8r3gJzD5m6jZgIe', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), true)
ON CONFLICT (email) DO NOTHING;

-- Insere usuário de exemplo 'Maria' com senha 'senha123'
INSERT INTO usuarios (name, email, password, role_id, active)
VALUES ('Maria', 'maria@ex.com', '$2a$10$bO.g8.eJpLYL4n/j4L8x.OKP3sV1qmxzC335Gf8r3gJzD5m6jZgIe', (SELECT id FROM roles WHERE name = 'ROLE_USER'), true)
ON CONFLICT (email) DO NOTHING;

-- Insere tipos de deficiência padrão
INSERT INTO disability_types (name, description) VALUES 
('Visual', 'Deficiências visuais, incluindo cegueira e baixa visão'),
('Auditiva', 'Deficiências auditivas, incluindo surdez e baixa audição'),
('Motora', 'Deficiências motoras que afetam movimento e coordenação'),
('Cognitiva', 'Deficiências cognitivas que afetam aprendizado e compreensão'),
('TEA', 'Transtorno do Espectro Autista')
ON CONFLICT(name) DO NOTHING;

-- Associa tipos de deficiência ao usuário 'Maria'
-- (Assumindo que o ID de Maria será 2 após o admin)
INSERT INTO user_disability_types (user_id, disability_type_id) VALUES
  ((SELECT id FROM usuarios WHERE email = 'maria@ex.com'), (SELECT id FROM disability_types WHERE name = 'Visual')),
  ((SELECT id FROM usuarios WHERE email = 'maria@ex.com'), (SELECT id FROM disability_types WHERE name = 'Motora')),
  ((SELECT id FROM usuarios WHERE email = 'maria@ex.com'), (SELECT id FROM disability_types WHERE name = 'TEA'))
ON CONFLICT DO NOTHING;

-- Insere adaptações
INSERT INTO adaptations (name, description, disability_type_id) VALUES
  ('Leitura de Tela', 'Usa IA para descrever elementos da interface.', (SELECT id FROM disability_types WHERE name = 'Visual'))
ON CONFLICT(name) DO NOTHING;

-- Define uma configuração de adaptação para 'Maria'
INSERT INTO user_adaptation_settings (user_id, adaptation_id, enabled) VALUES
  ((SELECT id FROM usuarios WHERE email = 'maria@ex.com'), (SELECT id FROM adaptations WHERE name = 'Leitura de Tela'), true)
ON CONFLICT DO NOTHING;

-- Insere dados de exemplo para uma sessão
INSERT INTO sessions (user_id, started_at, ended_at) VALUES
  ((SELECT id FROM usuarios WHERE email = 'maria@ex.com'), NOW(), NULL);

-- Insere outros dados de exemplo, assumindo que a sessão criada acima terá ID 1
INSERT INTO layout_elements (element_type, description, xpath) VALUES
  ('Button', 'Confirmar', '//*[@id=\"confirm\"]');

INSERT INTO feedbacks (user_id, message, timestamp) VALUES
  ((SELECT id FROM usuarios WHERE email = 'maria@ex.com'), 'Ótimo sistema', NOW());

INSERT INTO layout_analyses (session_id, details, timestamp) VALUES
  (1, '{\"elements\":[{\"type\":\"button\",\"text\":\"Enviar\"}]}', NOW());

INSERT INTO suggestions (layout_analysis_id, message) VALUES
  (1, 'Aumentar contraste');

INSERT INTO subtitles (session_id, text, timestamp) VALUES
  (1, 'Bem-vindo ao sistema', NOW());

INSERT INTO voice_commands (session_id, command, result, timestamp) VALUES
  (1, 'abrir menu', 'menu aberto', NOW());

-- Insere tags padrão
INSERT INTO tags (name) VALUES ('Accessibility'), ('UI') ON CONFLICT(name) DO NOTHING;