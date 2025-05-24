INSERT INTO roles (name) VALUES ('ROLE_USER'),('ROLE_ADMIN');

INSERT INTO disability_types (name,description) VALUES
  ('VISUAL','Deficiência visual'),
  ('AUDITIVA','Deficiência auditiva'),
  ('MOTORA','Deficiência motora'),
  ('COGNITIVA','Deficiência cognitiva'),
  ('TEA','Transtorno do Espectro Autista'),
  ('SENSORIAL','Deficiência sensorial');

INSERT INTO users (name,email,password,role_id) VALUES
  ('Maria','maria@ex.com','$2a$10$hash','1');

INSERT INTO user_disability_types (user_id,disability_type_id) VALUES
  (1,1),(1,3),(1,5);

INSERT INTO adaptations (name,description,disability_type_id) VALUES
  ('Leitura de Tela','Descrição','1');

INSERT INTO user_adaptation_settings (user_id,adaptation_id,enabled) VALUES
  (1,1,true);

INSERT INTO layout_elements (element_type,description,xpath) VALUES
  ('Button','Confirmar','//*[@id="confirm"]');

INSERT INTO feedbacks (user_id,message,timestamp) VALUES
  (1,'Ótimo sistema',NOW());

INSERT INTO sessions (user_id,started_at,ended_at) VALUES
  (1,NOW(),NULL);

INSERT INTO layout_analyses (session_id,details) VALUES
  (1,'{\"elements\":[{\"type\":\"button\",\"text\":\"Enviar\"}]}');

INSERT INTO suggestions (layout_analysis_id,message) VALUES
  (1,'Aumentar contraste');

INSERT INTO subtitles (session_id,text,timestamp) VALUES
  (1,'Bem-vindo ao sistema',NOW());

INSERT INTO voice_commands (session_id,command,result,timestamp) VALUES
  (1,'abrir menu','menu aberto',NOW());
