-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-07-2025 a las 04:28:08
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `canchas`
--

CREATE TABLE `canchas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `gps` varchar(50) DEFAULT NULL,
  `estado` enum('DISPONIBLE','OCUPADA','MANTENIMIENTO') DEFAULT 'DISPONIBLE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `canchas`
--

INSERT INTO `canchas` (`id`, `nombre`, `direccion`, `gps`, `estado`) VALUES
(1, 'Plaza Hogar', 'Av. Los Álamos 123', '12.3456,-77.1234', 'DISPONIBLE'),
(2, 'Complejo San Miguel', 'Jr. Las Flores 456', '12.4567,-77.2345', 'DISPONIBLE'),
(3, 'Deportivo Villa Sur', 'Calle Sol N°789', '12.5678,-77.3456', 'DISPONIBLE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `canchas_deportes`
--

CREATE TABLE `canchas_deportes` (
  `id` int(11) NOT NULL,
  `cancha_id` int(11) NOT NULL,
  `deporte_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `canchas_deportes`
--

INSERT INTO `canchas_deportes` (`id`, `cancha_id`, `deporte_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 1),
(4, 2, 3),
(5, 3, 1),
(6, 3, 2),
(7, 3, 3);

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
-- Estructura de tabla para la tabla `deportes`
--

CREATE TABLE `deportes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `deportes`
--

INSERT INTO `deportes` (`id`, `nombre`) VALUES
(3, 'BASQUET'),
(1, 'FUTBOL 6'),
(2, 'VOLEY');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarios_canchas_deportes`
--

CREATE TABLE `horarios_canchas_deportes` (
  `id` int(11) NOT NULL,
  `cancha_deporte_id` int(11) NOT NULL,
  `dia_semana` enum('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO','DOMINGO') DEFAULT NULL,
  `hora_inicio` time NOT NULL,
  `variacion_precio` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `horarios_canchas_deportes`
--

INSERT INTO `horarios_canchas_deportes` (`id`, `cancha_deporte_id`, `dia_semana`, `hora_inicio`, `variacion_precio`) VALUES
(1, 1, 'LUNES', '08:00:00', 0),
(2, 1, 'LUNES', '18:00:00', 20),
(3, 2, 'MARTES', '10:00:00', -10),
(4, 3, 'VIERNES', '19:00:00', 30),
(5, 4, 'SABADO', '14:00:00', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `precios_canchas_deportes`
--

CREATE TABLE `precios_canchas_deportes` (
  `id` int(11) NOT NULL,
  `cancha_deporte_id` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `precios_canchas_deportes`
--

INSERT INTO `precios_canchas_deportes` (`id`, `cancha_deporte_id`, `precio`, `fecha_inicio`, `fecha_fin`) VALUES
(1, 1, '50.00', '2025-07-01', NULL),
(2, 2, '30.00', '2025-07-01', NULL),
(3, 3, '40.00', '2025-07-01', NULL),
(4, 4, '35.00', '2025-07-01', NULL),
(5, 5, '60.00', '2025-07-01', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id` int(11) NOT NULL,
  `cancha_id` int(11) NOT NULL,
  `deporte_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `precio` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(1, 'sergio', 'sergio zegarra corne', '$2a$10$ZxCpXiFVVRWx.TVImm5k2eHlsKM5Vi/VCnaGW8GxT71IeQjobC2N6', 'ADMIN', '2025-07-08 23:26:18', NULL),
(2, 'frankm', 'Frank Mendoza', '$2a$10$K7jTQO/uy8REuTv9nq3gA.nVJjPiOVKQokUuhB.Gov2Av8n2o7nbi', 'USUARIO', '2025-07-08 23:30:00', NULL),
(3, 'gretel', 'Gretel Muñoz Garcia', '$2a$10$yx8Kf6TL7UZhWa3V3wTw2eQ2wP/cc5VZ/9hEFSb8p/7oL/y9CPLVe', 'USUARIO', '2025-07-08 23:31:00', NULL),
(4, 'luisito', 'Luis Garcia Marquez', '$2a$10$XJXx9SyGZnsbZfTh6uCbV.p/UHRB3bPEKhDPdu3.SsIh0zyDlfH6a', 'USUARIO', '2025-07-08 23:32:00', '2025-07-09 02:04:40'),
(5, 'ana.c', 'Ana Castillo', '$2a$10$9Ku3/Tm.9HZkrv7C5Es09.J7hOv9N08UW7CzWxq8o1R6y4SLHhHaa', 'USUARIO', '2025-07-08 23:33:00', '2025-07-09 02:04:42'),
(6, 'jorger', 'Jorge Rivera', '$2a$10$rOZbZ7IH1sUShP0HwTAd8.bKlKUzE8nICCBMuHjUEAvA97uY9fBo6', 'USUARIO', '2025-07-08 23:34:00', NULL),
(7, 'camila.t', 'Camila Torres', '$2a$10$ClBKysdXw7yvdzCPu4hVQObZydWCPR3fFZzL9RM8S4opHl/5k4Rme', 'USUARIO', '2025-07-08 23:35:00', NULL),
(8, 'pedro.a', 'Pedro Alvarado', '$2a$10$tVw2jXnAX0M4NjPynGNNKuOGHnpPce1MWMQ1B5IQDA0N0Erkp1BFe', 'USUARIO', '2025-07-08 23:36:00', NULL),
(9, 'sergio3', 'Maria Sanchez Fernandez', '$2a$10$BbRvClu8/OVi62rhsBaVpOF8TgpMD37rH8veiRSEQCrM8XO0usW.m', 'USUARIO', '2025-07-08 23:37:00', '2025-07-09 02:01:57'),
(10, 'baruc', 'Baruc Ramírez', '$2a$10$7DgE7daaRszUWmTkxMut..4H92e/oXgfku71guJVkehmYZikUkJC6', 'USUARIO', '2025-07-08 23:38:00', NULL),
(11, 'karla.m', 'Karla Murio', '$2a$10$BcO1CRsQYnPrB27c6P7XgepcaQyubRq4hsiMafPy5sII/HzenZ1Ki', 'ADMIN', '2025-07-08 23:39:00', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `canchas`
--
ALTER TABLE `canchas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `canchas_deportes`
--
ALTER TABLE `canchas_deportes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cancha_id` (`cancha_id`),
  ADD KEY `deporte_id` (`deporte_id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `deportes`
--
ALTER TABLE `deportes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `horarios_canchas_deportes`
--
ALTER TABLE `horarios_canchas_deportes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cancha_deporte_id` (`cancha_deporte_id`);

--
-- Indices de la tabla `precios_canchas_deportes`
--
ALTER TABLE `precios_canchas_deportes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cancha_deporte_id` (`cancha_deporte_id`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cancha_id` (`cancha_id`),
  ADD KEY `deporte_id` (`deporte_id`),
  ADD KEY `cliente_id` (`cliente_id`),
  ADD KEY `usuario_id` (`usuario_id`);

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
-- AUTO_INCREMENT de la tabla `canchas`
--
ALTER TABLE `canchas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `canchas_deportes`
--
ALTER TABLE `canchas_deportes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `deportes`
--
ALTER TABLE `deportes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `horarios_canchas_deportes`
--
ALTER TABLE `horarios_canchas_deportes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `precios_canchas_deportes`
--
ALTER TABLE `precios_canchas_deportes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `canchas_deportes`
--
ALTER TABLE `canchas_deportes`
  ADD CONSTRAINT `canchas_deportes_ibfk_1` FOREIGN KEY (`cancha_id`) REFERENCES `canchas` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `canchas_deportes_ibfk_2` FOREIGN KEY (`deporte_id`) REFERENCES `deportes` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `horarios_canchas_deportes`
--
ALTER TABLE `horarios_canchas_deportes`
  ADD CONSTRAINT `horarios_canchas_deportes_ibfk_1` FOREIGN KEY (`cancha_deporte_id`) REFERENCES `canchas_deportes` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `precios_canchas_deportes`
--
ALTER TABLE `precios_canchas_deportes`
  ADD CONSTRAINT `precios_canchas_deportes_ibfk_1` FOREIGN KEY (`cancha_deporte_id`) REFERENCES `canchas_deportes` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`cancha_id`) REFERENCES `canchas` (`id`),
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`deporte_id`) REFERENCES `deportes` (`id`),
  ADD CONSTRAINT `reservas_ibfk_3` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `reservas_ibfk_4` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
