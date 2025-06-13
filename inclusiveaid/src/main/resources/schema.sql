DROP TABLE IF EXISTS agent_active_users CASCADE;
DROP TABLE IF EXISTS agent_interactions CASCADE;
DROP TABLE IF EXISTS disability_specific_configs CASCADE;
DROP TABLE IF EXISTS user_disability_types CASCADE;
DROP TABLE IF EXISTS user_adaptation_settings CASCADE;
DROP TABLE IF EXISTS ai_agents CASCADE;
DROP TABLE IF EXISTS usuarios CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS disability_types CASCADE;
DROP TABLE IF EXISTS tags CASCADE;

CREATE TABLE roles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE usuarios (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role_id BIGINT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE disability_types (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE,
  description TEXT
);

CREATE TABLE ai_agents (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  version VARCHAR(20) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT true,
  last_update TIMESTAMP NOT NULL,
  nlp_config_language_model VARCHAR(100),
  nlp_config_confidence_threshold DOUBLE,
  nlp_config_enable_context_awareness BOOLEAN,
  image_config_object_detection_model VARCHAR(100),
  image_config_detection_threshold DOUBLE,
  image_config_enable_ocr BOOLEAN,
  voice_config_speech_recognition_model VARCHAR(100),
  voice_config_recognition_threshold DOUBLE,
  voice_config_enable_noise_reduction BOOLEAN
);

CREATE TABLE agent_active_users (
  agent_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  PRIMARY KEY (agent_id, user_id),
  FOREIGN KEY (agent_id) REFERENCES ai_agents(id),
  FOREIGN KEY (user_id) REFERENCES usuarios(id)
);

CREATE TABLE agent_interactions (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  agent_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  type VARCHAR(50) NOT NULL,
  action TEXT NOT NULL,
  response TEXT,
  timestamp TIMESTAMP NOT NULL,
  successful BOOLEAN NOT NULL DEFAULT true,
  FOREIGN KEY (agent_id) REFERENCES ai_agents(id),
  FOREIGN KEY (user_id) REFERENCES usuarios(id)
);

CREATE TABLE disability_specific_configs (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  agent_id BIGINT NOT NULL,
  disability_type_id BIGINT NOT NULL,
  enable_screen_reader BOOLEAN DEFAULT false,
  enable_high_contrast BOOLEAN DEFAULT false,
  enable_image_description BOOLEAN DEFAULT false,
  enable_subtitles BOOLEAN DEFAULT false,
  enable_visual_alerts BOOLEAN DEFAULT false,
  enable_sign_language BOOLEAN DEFAULT false,
  enable_voice_commands BOOLEAN DEFAULT false,
  enable_gesture_recognition BOOLEAN DEFAULT false,
  enable_task_automation BOOLEAN DEFAULT false,
  enable_simplified_mode BOOLEAN DEFAULT false,
  enable_consistent_feedback BOOLEAN DEFAULT false,
  enable_reduced_stimuli BOOLEAN DEFAULT false,
  enable_step_by_step_guide BOOLEAN DEFAULT false,
  enable_content_summarization BOOLEAN DEFAULT false,
  enable_visual_guidance BOOLEAN DEFAULT false,
  custom_settings TEXT,
  FOREIGN KEY (agent_id) REFERENCES ai_agents(id),
  FOREIGN KEY (disability_type_id) REFERENCES disability_types(id)
);

CREATE TABLE user_disability_types (
  user_id BIGINT NOT NULL,
  disability_type_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, disability_type_id),
  FOREIGN KEY (user_id) REFERENCES usuarios(id),
  FOREIGN KEY (disability_type_id) REFERENCES disability_types(id)
);

CREATE TABLE user_adaptation_settings (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  setting_key VARCHAR(100) NOT NULL,
  setting_value TEXT,
  FOREIGN KEY (user_id) REFERENCES usuarios(id)
);

CREATE TABLE tags (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);
