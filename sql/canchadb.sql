-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-07-2025 a las 08:49:12
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `canchadb`
--
CREATE DATABASE IF NOT EXISTS `canchadb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `canchadb`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `dni` varchar(15) NOT NULL,
  `frecuente` tinyint(1) DEFAULT 0,
  `fecha_registro` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `nombre`, `telefono`, `email`, `dni`, `frecuente`, `fecha_registro`) VALUES
(1, 'Sergio Zegarra', '987654321', 'sergio.z@example.com', '42451791', 1, '2025-07-08 10:00:00'),
(2, 'Frank Mendoza', '912345678', 'frank.m@example.com', '41234567', 0, '2025-07-08 10:05:00'),
(3, 'Gretel López', '956789012', 'gretel.l@example.com', '40345678', 1, '2025-07-08 10:10:00'),
(4, 'Baruc Ramírez', '923456789', 'baruc.r@example.com', '42678901', 0, '2025-07-08 10:15:00'),
(5, 'Camila Torres', '965432187', 'camila.t@example.com', '43891234', 1, '2025-07-08 10:20:00'),
(6, 'Luis García', '987123456', 'luis.g@example.com', '40123456', 0, '2025-07-08 10:25:00'),
(7, 'María Sánchez', '954321876', 'maria.s@example.com', '42234567', 1, '2025-07-08 10:30:00'),
(8, 'Pedro Alvarado', '978654321', 'pedro.a@example.com', '41765432', 0, '2025-07-08 10:35:00'),
(9, 'Ana Castillo', '963258741', 'ana.c@example.com', '40987654', 1, '2025-07-08 10:40:00'),
(10, 'Jorge Rivera', '987456321', 'jorge.r@example.com', '41567890', 0, '2025-07-08 10:45:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `nombre_completo` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` enum('ADMIN','USUARIO') DEFAULT 'USUARIO',
  `fecha_alta` datetime DEFAULT current_timestamp(),
  `fecha_baja` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `username`, `nombre_completo`, `password`, `rol`, `fecha_alta`, `fecha_baja`) VALUES
(1, 'sergio', 'sergio zegarra corne', '$2a$10$ZxCpXiFVVRWx.TVImm5k2eHlsKM5Vi/VCnaGW8GxT71IeQjobC2N6', 'ADMIN', '2025-07-08 23:26:18', NULL);
(2, 'frankm', 'Frank Mendoza', '$2a$10$K7jTQO/uy8REuTv9nq3gA.nVJjPiOVKQokUuhB.Gov2Av8n2o7nbi', 'USUARIO', '2025-07-08 23:30:00', NULL),
(3, 'gretel', 'Gretel Muñoz', '$2a$10$yx8Kf6TL7UZhWa3V3wTw2eQ2wP/cc5VZ/9hEFSb8p/7oL/y9CPLVe', 'USUARIO', '2025-07-08 23:31:00', NULL),
(4, 'luisg', 'Luis García', '$2a$10$XJXx9SyGZnsbZfTh6uCbV.p/UHRB3bPEKhDPdu3.SsIh0zyDlfH6a', 'ADMIN', '2025-07-08 23:32:00', NULL),
(5, 'ana.c', 'Ana Castillo', '$2a$10$9Ku3/Tm.9HZkrv7C5Es09.J7hOv9N08UW7CzWxq8o1R6y4SLHhHaa', 'USUARIO', '2025-07-08 23:33:00', NULL),
(6, 'jorger', 'Jorge Rivera', '$2a$10$rOZbZ7IH1sUShP0HwTAd8.bKlKUzE8nICCBMuHjUEAvA97uY9fBo6', 'USUARIO', '2025-07-08 23:34:00', NULL),
(7, 'camila.t', 'Camila Torres', '$2a$10$ClBKysdXw7yvdzCPu4hVQObZydWCPR3fFZzL9RM8S4opHl/5k4Rme', 'USUARIO', '2025-07-08 23:35:00', NULL),
(8, 'pedro.a', 'Pedro Alvarado', '$2a$10$tVw2jXnAX0M4NjPynGNNKuOGHnpPce1MWMQ1B5IQDA0N0Erkp1BFe', 'USUARIO', '2025-07-08 23:36:00', NULL),
(9, 'marias', 'María Sánchez', '$2a$10$ZQn0eXfUw7f3X9NP7jXkpujfJYu8Rf3Wb02vDMB7TLO4JF4Q2VfpC', 'ADMIN', '2025-07-08 23:37:00', NULL),
(10, 'baruc', 'Baruc Ramírez', '$2a$10$HfCKNctyyhzM7KxYuF3LNeXyheTxwl8mdOlCk3Ryf2hp5GR86t8om', 'USUARIO', '2025-07-08 23:38:00', NULL),
(11, 'karla.m', 'Karla Muñoz', '$2a$10$ndD3MtOyl/v8KDCeVG7CKO6N5o41T3gi51tvWxNjC1YW0Xqjl0Z8a', 'USUARIO', '2025-07-08 23:39:00', NULL);


--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
