-- =========================================================================
-- InterviewPrep — Full Stack Interview Preparation & Mock Test Platform
-- MySQL 8.0 Database Schema & Initial Seed Script
-- =========================================================================

CREATE DATABASE IF NOT EXISTS interviewprep_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE interviewprep_db;

-- 1. Users Table (Students & Administrators)
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    college VARCHAR(255),
    degree VARCHAR(100),
    branch VARCHAR(100),
    graduation_year INT,
    role VARCHAR(50) NOT NULL DEFAULT 'ROLE_STUDENT',
    streak_days INT DEFAULT 0,
    last_practice_date DATETIME,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2. Subjects Table
CREATE TABLE IF NOT EXISTS subjects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    icon VARCHAR(100),
    color VARCHAR(50),
    active BOOLEAN DEFAULT TRUE
);

-- 3. Topics Table
CREATE TABLE IF NOT EXISTS topics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    subject_id BIGINT NOT NULL,
    CONSTRAINT fk_topic_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE
);

-- 4. Questions Table
CREATE TABLE IF NOT EXISTS questions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    subject_id BIGINT NOT NULL,
    topic_id BIGINT,
    difficulty VARCHAR(50) NOT NULL DEFAULT 'MEDIUM',
    question_text TEXT NOT NULL,
    code_snippet TEXT,
    optiona TEXT NOT NULL,
    optionb TEXT NOT NULL,
    optionc TEXT NOT NULL,
    optiond TEXT NOT NULL,
    correct_option VARCHAR(5) NOT NULL,
    explanation TEXT NOT NULL,
    marks DOUBLE NOT NULL DEFAULT 1.0,
    negative_marks DOUBLE NOT NULL DEFAULT 0.25,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_question_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE,
    CONSTRAINT fk_question_topic FOREIGN KEY (topic_id) REFERENCES topics(id) ON DELETE SET NULL
);

-- 5. Mock Tests Table
CREATE TABLE IF NOT EXISTS mock_tests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    topic_id BIGINT,
    difficulty VARCHAR(50) NOT NULL DEFAULT 'ALL',
    total_questions INT NOT NULL,
    duration_minutes INT NOT NULL,
    is_negative_marking BOOLEAN DEFAULT TRUE,
    negative_mark_value DOUBLE DEFAULT 0.25,
    score DOUBLE DEFAULT 0.0,
    max_score DOUBLE DEFAULT 0.0,
    percentage DOUBLE DEFAULT 0.0,
    accuracy DOUBLE DEFAULT 0.0,
    correct_count INT DEFAULT 0,
    wrong_count INT DEFAULT 0,
    skipped_count INT DEFAULT 0,
    time_taken_seconds INT DEFAULT 0,
    status VARCHAR(50) NOT NULL DEFAULT 'IN_PROGRESS',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    completed_at DATETIME,
    CONSTRAINT fk_mock_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_mock_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE,
    CONSTRAINT fk_mock_topic FOREIGN KEY (topic_id) REFERENCES topics(id) ON DELETE SET NULL
);

-- 6. Mock Questions (Instantiated Questions per Test Attempt)
CREATE TABLE IF NOT EXISTS mock_questions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mock_test_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    question_order INT NOT NULL,
    selected_option VARCHAR(5),
    is_correct BOOLEAN,
    is_marked_for_review BOOLEAN DEFAULT FALSE,
    is_skipped BOOLEAN DEFAULT TRUE,
    marks_obtained DOUBLE DEFAULT 0.0,
    CONSTRAINT fk_mq_mocktest FOREIGN KEY (mock_test_id) REFERENCES mock_tests(id) ON DELETE CASCADE,
    CONSTRAINT fk_mq_question FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

-- 7. Bookmarks Table
CREATE TABLE IF NOT EXISTS bookmarks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    notes TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_user_question (user_id, question_id),
    CONSTRAINT fk_bookmark_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_bookmark_question FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

-- 8. User Badges Table
CREATE TABLE IF NOT EXISTS user_badges (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    badge_key VARCHAR(100) NOT NULL,
    badge_name VARCHAR(255) NOT NULL,
    description TEXT,
    icon VARCHAR(50),
    earned_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_user_badge (user_id, badge_key),
    CONSTRAINT fk_badge_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 9. Daily Challenges Table
CREATE TABLE IF NOT EXISTS daily_challenges (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    challenge_date DATE NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    reward_points INT DEFAULT 50,
    subject_id BIGINT,
    CONSTRAINT fk_dc_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS daily_challenge_questions (
    challenge_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    PRIMARY KEY (challenge_id, question_id),
    CONSTRAINT fk_dcq_challenge FOREIGN KEY (challenge_id) REFERENCES daily_challenges(id) ON DELETE CASCADE,
    CONSTRAINT fk_dcq_question FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);
