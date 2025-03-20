-- create database
CREATE DATA SOURCE IF NOT EXISTS lunch_app CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE lunch_app;

-- User table
CREATE TABLE IF NOT EXISTS user (
    uuid BINARY(16) NOT NULL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    surname VARCHAR(255),
    balance DECIMAL(19, 2) DEFAULT 0.0,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);
