CREATE DATABASE IF NOT EXISTS databasets;
USE databasets;

CREATE TABLE persona (
    codigo_persona BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(200) NOT NULL,
    genero VARCHAR(1) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(20) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE cliente (
    codigo_cliente BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_persona BIGINT NOT NULL,
    clave VARCHAR(50) NOT NULL,
    estado VARCHAR(1) NOT NULL,
    FOREIGN KEY (codigo_persona) REFERENCES persona(codigo_persona)
);

CREATE TABLE cuenta (
    codigo_cuenta BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(30) NOT NULL,
    tipo_cuenta VARCHAR(1) NOT NULL, -- A Ahorro, C Corriente
    saldo_inicial DECIMAL(19, 2) NOT NULL,
    estado VARCHAR(1) NOT NULL, -- A Activo, I Inactivo
    codigo_cliente BIGINT NOT NULL,
    FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo_cliente)
);

CREATE TABLE movimientos (
    codigo_movimiento BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    tipo_movimiento VARCHAR(1) NOT NULL, -- D Deposito, R Retiro
    valor DECIMAL(19, 2) NOT NULL,
    saldo DECIMAL(19, 2) NOT NULL,
    codigo_cuenta BIGINT NOT NULL,
    FOREIGN KEY (codigo_cuenta) REFERENCES cuenta(codigo_cuenta)
);