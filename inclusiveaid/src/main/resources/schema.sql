DROP TABLE IF EXISTS user_disability_types, voice_commands, subtitles, suggestions, layout_analyses, sessions, feedbacks, user_adaptation_settings, layout_elements, adaptations, users, disability_types, roles;

CREATE TABLE roles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50)
);

CREATE TABLE disability_types (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  description VARCHAR(255)
);

CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(255),
  role_id BIGINT,
  FOREIGN KEY(role_id) REFERENCES roles(id)
);

CREATE TABLE user_disability_types (
  user_id BIGINT,
  disability_type_id BIGINT,
  PRIMARY KEY(user_id,disability_type_id),
  FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY(disability_type_id) REFERENCES disability_types(id)
);

CREATE TABLE adaptations (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  description VARCHAR(255),
  disability_type_id BIGINT,
  FOREIGN KEY(disability_type_id) REFERENCES disability_types(id)
);

CREATE TABLE user_adaptation_settings (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  adaptation_id BIGINT,
  enabled BOOLEAN,
  FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY(adaptation_id) REFERENCES adaptations(id)
);

CREATE TABLE layout_elements (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  element_type VARCHAR(50),
  description VARCHAR(255),
  xpath VARCHAR(255)
);

CREATE TABLE feedbacks (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  message VARCHAR(500),
  timestamp TIMESTAMP,
  FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE sessions (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  started_at TIMESTAMP,
  ended_at TIMESTAMP,
  FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE layout_analyses (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  session_id BIGINT,
  details TEXT,
  FOREIGN KEY(session_id) REFERENCES sessions(id)
);

CREATE TABLE suggestions (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  layout_analysis_id BIGINT,
  message VARCHAR(500),
  FOREIGN KEY(layout_analysis_id) REFERENCES layout_analyses(id)
);

CREATE TABLE subtitles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  session_id BIGINT,
  text VARCHAR(500),
  timestamp TIMESTAMP,
  FOREIGN KEY(session_id) REFERENCES sessions(id)
);

CREATE TABLE voice_commands (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  session_id BIGINT,
  command VARCHAR(255),
  result VARCHAR(255),
  timestamp TIMESTAMP,
  FOREIGN KEY(session_id) REFERENCES sessions(id)
);
