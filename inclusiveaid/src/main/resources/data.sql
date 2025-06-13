-- Insert default roles
INSERT INTO roles (name) VALUES ('ROLE_USER') ON CONFLICT DO NOTHING;
INSERT INTO roles (name) VALUES ('ROLE_ADMIN') ON CONFLICT DO NOTHING;

-- Insert default admin user (password: admin123)
INSERT INTO usuarios (name, email, password, role_id)
VALUES ('Admin', 'admin@inclusiveaid.com', '$2a$10$rDkPvvAFV6GgJkKq8WU1UOQZQZQZQZQZQZQZQZQZQZQZQZQZQZQZQ', 
        (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'))
ON CONFLICT (email) DO NOTHING;

-- Insert default disability types
INSERT INTO disability_types (name, description) VALUES 
('Visual', 'Visual impairments including blindness and low vision'),
('Auditiva', 'Hearing impairments including deafness and hard of hearing'),
('Motora', 'Motor impairments affecting movement and coordination'),
('Cognitiva', 'Cognitive impairments affecting learning and understanding'),
('TEA', 'Transtorno do Espectro Autista')
ON CONFLICT DO NOTHING;

-- Insert default AI agent configuration
INSERT INTO ai_agents (name, version, is_active, last_update, nlp_config_language_model, nlp_config_confidence_threshold, nlp_config_enable_context_awareness,
                      image_config_object_detection_model, image_config_detection_threshold, image_config_enable_ocr,
                      voice_config_speech_recognition_model, voice_config_recognition_threshold, voice_config_enable_noise_reduction)
VALUES ('Default Agent', '1.0.0', true, CURRENT_TIMESTAMP,
        'gpt-3.5-turbo', 0.8, true,
        'yolov5', 0.7, true,
        'whisper', 0.75, true)
ON CONFLICT DO NOTHING;

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

-- Insert default tags
INSERT INTO tags (name) VALUES ('Accessibility') ON CONFLICT DO NOTHING;
INSERT INTO tags (name) VALUES ('UI') ON CONFLICT DO NOTHING;
