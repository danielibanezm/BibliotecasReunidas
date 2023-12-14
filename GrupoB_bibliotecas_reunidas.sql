-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-12-2023 a las 17:22:48
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bibliotecas_reunidas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autores`
--

CREATE TABLE `autores` (
  `id_autor` int(11) NOT NULL,
  `nombre_autor` varchar(20) DEFAULT NULL,
  `apellido_autor` varchar(20) DEFAULT NULL,
  `nacionalidad_autor` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `autores`
--

INSERT INTO `autores` (`id_autor`, `nombre_autor`, `apellido_autor`, `nacionalidad_autor`) VALUES
(1, 'John', 'Smith', 'Estadounidense'),
(2, 'Ana', 'García', 'Española'),
(3, 'María', 'García', 'Española'),
(4, 'Carlos', 'Ruiz', 'Italiano'),
(5, 'F. Scott', 'Fitzgerald', 'Estadounidense'),
(6, 'Ana', 'López', 'Española'),
(7, 'Pablo', 'Martínez', 'Italiano'),
(8, 'Antoine de', 'Saint-Exupéry', 'Francés'),
(9, 'Daniel', 'García', 'Español'),
(10, 'Jane', 'Austen', 'Británica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bibliotecas`
--

CREATE TABLE `bibliotecas` (
  `id_biblioteca` int(11) NOT NULL,
  `calle_biblioteca` varchar(50) DEFAULT NULL,
  `provincia_biblioteca` varchar(20) DEFAULT NULL,
  `tlf_biblioteca` varchar(9) DEFAULT NULL,
  `email_biblioteca` varchar(50) DEFAULT NULL,
  `codigoPostal_biblioteca` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `bibliotecas`
--

INSERT INTO `bibliotecas` (`id_biblioteca`, `calle_biblioteca`, `provincia_biblioteca`, `tlf_biblioteca`, `email_biblioteca`, `codigoPostal_biblioteca`) VALUES
(1, 'Calle de las Letras', 'Madrid', '555111222', 'biblioteca1@gmail.com', '28001'),
(2, 'Avenida de los Libros', 'Barcelona', '555222333', 'biblioteca2@gmail.com', '08002'),
(3, 'Calle de la Ciencia', 'Valencia', '555333666', 'biblioteca3@gmail.com', '46001'),
(4, 'Avenida de los Libros', 'Sevilla', '555444777', 'biblioteca4@gmail.com', '41002'),
(5, 'Calle del Conocimiento', 'Barcelona', '555555000', 'biblioteca5@gmail.com', '08003'),
(6, 'Avenida de la Información', 'Madrid', '555666111', 'biblioteca6@gmail.com', '28004'),
(7, 'Calle de la Cultura', 'Bilbao', '555777222', 'biblioteca7@gmail.com', '48005'),
(8, 'Avenida de las Letras', 'Málaga', '555888333', 'biblioteca8@gmail.com', '29006'),
(9, 'Calle del Saber', 'Zaragoza', '555999444', 'biblioteca9@gmail.com', '50007'),
(10, 'Avenida de la Imaginación', 'Murcia', '555000555', 'biblioteca10@gmail.com', '30008');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `id_libro` int(11) NOT NULL,
  `id_biblioteca` int(11) NOT NULL,
  `titulo_libro` varchar(50) DEFAULT NULL,
  `autores_libro` varchar(100) DEFAULT NULL,
  `isbn_libro` varchar(17) DEFAULT NULL,
  `editorial_libro` varchar(20) DEFAULT NULL,
  `genero_libro` varchar(20) DEFAULT NULL,
  `idioma_libro` varchar(20) DEFAULT NULL,
  `edicion_libro` varchar(20) DEFAULT NULL,
  `ubicacion_libro` varchar(20) DEFAULT NULL,
  `publicacion_libro` date DEFAULT NULL,
  `pais_libro` varchar(20) DEFAULT NULL,
  `numPaginas_libro` varchar(4) DEFAULT NULL,
  `stock_total` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`id_libro`, `id_biblioteca`, `titulo_libro`, `autores_libro`, `isbn_libro`, `editorial_libro`, `genero_libro`, `idioma_libro`, `edicion_libro`, `ubicacion_libro`, `publicacion_libro`, `pais_libro`, `numPaginas_libro`, `stock_total`) VALUES
(1, 1, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(1, 2, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(1, 3, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(1, 4, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(1, 5, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(1, 6, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(1, 7, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(1, 8, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(1, 9, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(1, 10, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(2, 1, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(2, 2, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(2, 3, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(2, 4, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(2, 5, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(2, 6, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(2, 7, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(2, 8, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(2, 9, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(2, 10, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(3, 1, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(3, 2, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(3, 3, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(3, 4, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(3, 5, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(3, 6, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(3, 7, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(3, 8, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(3, 9, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(3, 10, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(4, 1, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(4, 2, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(4, 7, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(5, 1, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(5, 2, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(5, 3, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(5, 4, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(5, 5, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(5, 6, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(5, 7, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(5, 8, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(5, 9, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(5, 10, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(6, 1, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(6, 2, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(6, 3, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(6, 4, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(6, 5, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(6, 6, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(6, 7, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(6, 8, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(6, 9, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(6, 10, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(7, 1, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(7, 2, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(7, 3, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(7, 4, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(7, 5, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(7, 6, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(7, 7, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(7, 8, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(7, 9, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(7, 10, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(8, 1, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(8, 2, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(8, 3, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(8, 4, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(8, 5, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(8, 6, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(8, 7, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(8, 8, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(8, 9, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(8, 10, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(9, 1, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(9, 2, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(9, 3, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(9, 4, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(9, 5, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(9, 6, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(9, 7, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(9, 8, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(9, 9, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(9, 10, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(10, 1, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(10, 2, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(10, 3, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(10, 4, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(10, 5, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(10, 6, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(10, 7, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(10, 8, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(10, 9, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(10, 10, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(11, 1, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(11, 2, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(11, 3, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(11, 4, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(11, 5, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(11, 6, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(11, 7, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(11, 8, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(11, 9, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(11, 10, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(12, 1, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(12, 2, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(12, 3, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(12, 4, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(12, 5, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(12, 6, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(12, 7, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(12, 8, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(12, 9, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(12, 10, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(13, 1, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(13, 2, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(13, 3, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(13, 4, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(13, 5, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(13, 6, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(13, 7, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(13, 8, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(13, 9, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(13, 10, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(14, 1, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(14, 3, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(14, 4, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(14, 5, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(14, 6, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(14, 7, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(15, 1, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(15, 2, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(15, 3, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(15, 4, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(15, 5, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(15, 6, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(15, 7, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(15, 8, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(15, 9, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(15, 10, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(16, 1, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(16, 2, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(16, 3, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(16, 4, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(16, 5, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(16, 6, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(16, 7, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(16, 8, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(16, 9, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(16, 10, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(17, 1, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(17, 2, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(17, 3, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(17, 4, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(17, 5, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(17, 6, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(17, 7, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(17, 8, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(17, 9, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(17, 10, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(18, 1, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(18, 2, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(18, 3, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(18, 4, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(18, 5, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(18, 6, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(18, 7, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(18, 8, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(18, 9, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(18, 10, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(19, 1, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(19, 2, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(19, 3, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(19, 4, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(19, 5, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(19, 6, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(19, 7, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(19, 8, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(19, 9, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(19, 10, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(20, 1, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(20, 2, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(20, 3, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(20, 4, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(20, 5, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(20, 6, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(20, 7, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(20, 8, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(20, 9, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(20, 10, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(21, 1, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(21, 2, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(21, 3, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(21, 4, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(21, 5, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(21, 6, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(21, 7, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(21, 8, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(21, 9, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(21, 10, 'La Familia', 'John Smith', '978-0-13-468599-1', 'Tech', 'Informática', 'Español', 'Primera', 'Pasillo 1, Estante 1', '2020-01-01', 'EEUU', '400', 1),
(22, 1, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(22, 2, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(22, 3, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(22, 4, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(22, 5, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(22, 6, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(22, 7, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(22, 8, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(22, 9, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(22, 10, 'Historia del Arte', 'Ana García', '978-1-23-456789-2', 'Planeta', 'Arte', 'Español', 'Segunda', 'Pasillo 2, Estante 2', '2018-05-15', 'España', '300', 1),
(23, 1, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(23, 2, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(23, 3, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(23, 4, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(23, 5, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(23, 6, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(23, 7, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(23, 8, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(23, 9, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(23, 10, 'Ciencia de Datos', 'María García', '978-2-34-567890-3', 'Data', 'Informática', 'Español', 'Tercera', 'Pasillo 3, Estante 3', '2021-03-03', 'España', '350', 1),
(24, 2, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(24, 6, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(24, 7, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(24, 8, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(24, 9, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(24, 10, 'Cocina Mediterránea', 'Carlos Ruiz', '978-3-45-678901-4', 'Globo', 'Cocina', 'Español', 'Cuarta', 'Pasillo 4, Estante 4', '2019-06-20', 'Italia', '200', 1),
(25, 1, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(25, 2, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(25, 3, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(25, 4, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(25, 5, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(25, 6, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(25, 7, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(25, 8, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(25, 9, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(25, 10, 'El Gran Gatsby', 'F. Scott Fitzgerald', '978-4-56-789012-5', 'Fantum', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 5', '1925-04-10', 'EEUU', '250', 1),
(26, 1, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(26, 2, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(26, 3, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(26, 4, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(26, 5, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(26, 6, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(26, 7, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(26, 8, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(26, 9, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(26, 10, 'Inteligencia Artificial', 'Ana López', '978-5-67-890123-6', 'Tech', 'Informática', 'Español', 'Segunda', 'Pasillo 1, Estante 2', '2022-02-02', 'España', '400', 1),
(27, 1, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(27, 2, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(27, 3, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(27, 4, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(27, 5, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(27, 6, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(27, 7, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(27, 8, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(27, 9, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(27, 10, 'Pintura Renacentista', 'Pablo Martínez', '978-6-78-901234-7', 'Arte', 'Arte', 'Español', 'Primera', 'Pasillo 2, Estante 3', '2017-12-12', 'Italia', '300', 1),
(28, 1, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(28, 2, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(28, 3, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(28, 4, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(28, 5, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(28, 6, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(28, 7, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(28, 8, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(28, 9, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(28, 10, 'El Principito', 'Antoine de Saint-Exupéry', '978-7-89-012345-8', 'Literaria', 'Infantil', 'Francés', 'Cuarta', 'Pasillo 3, Estante 4', '1943-04-06', 'Francia', '100', 1),
(29, 1, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(29, 2, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(29, 3, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(29, 4, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(29, 5, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(29, 6, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(29, 7, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(29, 8, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(29, 9, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(29, 10, 'Economía para Todos', 'Daniel García', '978-8-90-123456-9', 'Mundum', 'Economía', 'Español', 'Tercera', 'Pasillo 4, Estante 5', '2020-08-08', 'España', '280', 1),
(30, 1, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(30, 2, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(30, 3, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(30, 4, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(30, 5, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(30, 6, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(30, 7, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(30, 8, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(30, 9, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1),
(30, 10, 'Orgullo y Prejuicio', 'Jane Austen', '978-9-01-234567-0', 'Penguin', 'Ficción', 'Inglés', 'Quinta', 'Pasillo 5, Estante 1', '1813-01-28', 'Reino Unido', '320', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multas`
--

CREATE TABLE `multas` (
  `id_multa` int(11) NOT NULL,
  `id_socio` int(11) DEFAULT NULL,
  `id_biblioteca` int(11) NOT NULL,
  `multa_obtenida` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `multas`
--

INSERT INTO `multas` (`id_multa`, `id_socio`, `id_biblioteca`, `multa_obtenida`) VALUES
(1, 1, 1, 0),
(1, 1, 2, 0),
(1, 1, 3, 0),
(1, 1, 4, 0),
(1, 1, 5, 0),
(1, 1, 6, 0),
(1, 1, 7, 0),
(1, 1, 8, 0),
(1, 1, 9, 0),
(1, 1, 10, 0),
(2, 2, 1, 0),
(2, 2, 2, 0),
(2, 2, 3, 0),
(2, 2, 4, 0),
(2, 2, 5, 0),
(2, 2, 6, 0),
(2, 2, 7, 0),
(2, 2, 8, 0),
(2, 2, 9, 0),
(2, 2, 10, 0),
(3, 3, 1, 0),
(3, 3, 2, 0),
(3, 3, 3, 0),
(3, 3, 4, 0),
(3, 3, 5, 0),
(3, 3, 6, 0),
(3, 3, 7, 0),
(3, 3, 8, 0),
(3, 3, 9, 0),
(3, 3, 10, 0),
(4, 4, 1, 0),
(4, 4, 2, 0),
(4, 4, 3, 0),
(4, 4, 4, 0),
(4, 4, 5, 0),
(4, 4, 6, 0),
(4, 4, 7, 0),
(4, 4, 8, 0),
(4, 4, 9, 0),
(4, 4, 10, 0),
(5, 5, 1, 0),
(5, 5, 2, 0),
(5, 5, 3, 0),
(5, 5, 4, 0),
(5, 5, 5, 0),
(5, 5, 6, 0),
(5, 5, 7, 0),
(5, 5, 8, 0),
(5, 5, 9, 0),
(5, 5, 10, 0),
(6, 6, 1, 0),
(6, 6, 2, 0),
(6, 6, 3, 0),
(6, 6, 4, 0),
(6, 6, 5, 0),
(6, 6, 6, 0),
(6, 6, 7, 0),
(6, 6, 8, 0),
(6, 6, 9, 0),
(6, 6, 10, 0),
(7, 7, 1, 1),
(7, 7, 2, 0),
(7, 7, 3, 1),
(7, 7, 4, 1),
(7, 7, 5, 1),
(7, 7, 6, 1),
(7, 7, 7, 1),
(7, 7, 8, 1),
(7, 7, 9, 1),
(7, 7, 10, 1),
(8, 8, 1, 0),
(8, 8, 2, 0),
(8, 8, 3, 0),
(8, 8, 4, 0),
(8, 8, 5, 0),
(8, 8, 6, 0),
(8, 8, 7, 0),
(8, 8, 8, 0),
(8, 8, 9, 0),
(8, 8, 10, 0),
(9, 9, 1, 0),
(9, 9, 2, 0),
(9, 9, 3, 0),
(9, 9, 4, 0),
(9, 9, 5, 0),
(9, 9, 6, 0),
(9, 9, 7, 0),
(9, 9, 8, 0),
(9, 9, 9, 0),
(9, 9, 10, 0),
(10, 10, 1, 0),
(10, 10, 2, 0),
(10, 10, 3, 0),
(10, 10, 4, 0),
(10, 10, 5, 0),
(10, 10, 6, 0),
(10, 10, 7, 0),
(10, 10, 8, 0),
(10, 10, 9, 0),
(10, 10, 10, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `otros`
--

CREATE TABLE `otros` (
  `id_otro` int(11) NOT NULL,
  `id_biblioteca` int(11) NOT NULL,
  `material` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `otros`
--

INSERT INTO `otros` (`id_otro`, `id_biblioteca`, `material`) VALUES
(1, 1, 'Emple1'),
(1, 2, 'Emple1'),
(1, 3, 'Emple1'),
(1, 4, 'Emple1'),
(1, 5, 'Emple1'),
(1, 6, 'Emple1'),
(1, 7, 'Emple1'),
(1, 8, 'Emple1'),
(1, 9, 'Emple1'),
(1, 10, 'Emple1'),
(2, 1, 'Emple2'),
(2, 2, 'Emple2'),
(2, 3, 'Emple2'),
(2, 4, 'Emple2'),
(2, 5, 'Emple2'),
(2, 6, 'Emple2'),
(2, 7, 'Emple2'),
(2, 8, 'Emple2'),
(2, 9, 'Emple2'),
(2, 10, 'Emple2'),
(3, 1, 'Admin1'),
(3, 2, 'Admin1'),
(3, 3, 'Admin1'),
(3, 4, 'Admin1'),
(3, 5, 'Admin1'),
(3, 6, 'Admin1'),
(3, 7, 'Admin1'),
(3, 8, 'Admin1'),
(3, 9, 'Admin1'),
(3, 10, 'Admin1'),
(4, 1, 'Admin2'),
(4, 2, 'Admin2'),
(4, 3, 'Admin2'),
(4, 4, 'Admin2'),
(4, 5, 'Admin2'),
(4, 6, 'Admin2'),
(4, 7, 'Admin2'),
(4, 8, 'Admin2'),
(4, 9, 'Admin2'),
(4, 10, 'Admin2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `id_prestamo` int(11) NOT NULL,
  `id_biblioteca` int(11) NOT NULL,
  `id_socio` int(11) DEFAULT NULL,
  `id_libro` int(11) DEFAULT NULL,
  `fecha_prestamo` date DEFAULT NULL,
  `fecha_entrega_prevista` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `prestamos`
--

INSERT INTO `prestamos` (`id_prestamo`, `id_biblioteca`, `id_socio`, `id_libro`, `fecha_prestamo`, `fecha_entrega_prevista`) VALUES
(1, 1, 1, 1, '2023-11-14', '2023-11-28'),
(2, 2, 1, 1, '2023-11-14', '2023-11-28'),
(3, 3, 1, 1, '2023-11-14', '2023-11-28'),
(4, 4, 1, 1, '2023-11-14', '2023-11-28'),
(5, 5, 1, 1, '2023-11-14', '2023-11-28'),
(6, 6, 1, 1, '2023-11-14', '2023-11-28'),
(7, 7, 1, 1, '2023-11-14', '2023-11-28'),
(8, 8, 1, 1, '2023-11-14', '2023-11-28'),
(8, 9, 1, 1, '2023-11-14', '2023-11-28'),
(10, 10, 1, 1, '2023-11-14', '2023-11-28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recibos`
--

CREATE TABLE `recibos` (
  `id_recibo` int(11) NOT NULL,
  `id_biblioteca` int(11) NOT NULL,
  `id_socio` int(11) DEFAULT NULL,
  `pagado` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `recibos`
--

INSERT INTO `recibos` (`id_recibo`, `id_biblioteca`, `id_socio`, `pagado`) VALUES
(1, 1, 1, 0),
(1, 2, 1, 0),
(1, 3, 1, 0),
(1, 4, 1, 0),
(1, 5, 1, 0),
(1, 6, 1, 0),
(1, 7, 1, 0),
(1, 8, 1, 0),
(1, 9, 1, 0),
(1, 10, 1, 0),
(2, 1, 2, 0),
(2, 2, 2, 0),
(2, 3, 2, 0),
(2, 4, 2, 0),
(2, 5, 2, 0),
(2, 6, 2, 0),
(2, 7, 2, 0),
(2, 8, 2, 0),
(2, 9, 2, 0),
(2, 10, 2, 0),
(3, 1, 3, 1),
(3, 2, 3, 1),
(3, 3, 3, 1),
(3, 4, 3, 1),
(3, 5, 3, 1),
(3, 6, 3, 1),
(3, 7, 3, 1),
(3, 8, 3, 1),
(3, 9, 3, 1),
(3, 10, 3, 1),
(4, 1, 4, 1),
(4, 2, 4, 1),
(4, 3, 4, 1),
(4, 4, 4, 1),
(4, 5, 4, 1),
(4, 6, 4, 1),
(4, 7, 4, 1),
(4, 8, 4, 1),
(4, 9, 4, 1),
(4, 10, 4, 1),
(5, 1, 5, 0),
(5, 2, 5, 0),
(5, 3, 5, 0),
(5, 4, 5, 0),
(5, 5, 5, 0),
(5, 6, 5, 0),
(5, 7, 5, 0),
(5, 8, 5, 0),
(5, 9, 5, 0),
(5, 10, 5, 0),
(6, 1, 6, 0),
(6, 2, 6, 0),
(6, 3, 6, 0),
(6, 4, 6, 0),
(6, 5, 6, 0),
(6, 6, 6, 0),
(6, 7, 6, 0),
(6, 8, 6, 0),
(6, 9, 6, 0),
(6, 10, 6, 0),
(7, 1, 7, 1),
(7, 2, 7, 1),
(7, 3, 7, 1),
(7, 4, 7, 1),
(7, 5, 7, 1),
(7, 6, 7, 1),
(7, 7, 7, 1),
(7, 8, 7, 1),
(7, 9, 7, 1),
(7, 10, 7, 1),
(8, 1, 8, 0),
(8, 2, 8, 0),
(8, 3, 8, 0),
(8, 4, 8, 0),
(8, 5, 8, 0),
(8, 6, 8, 0),
(8, 7, 8, 0),
(8, 8, 8, 0),
(8, 9, 8, 0),
(8, 10, 8, 0),
(9, 1, 9, 0),
(9, 2, 9, 0),
(9, 3, 9, 0),
(9, 4, 9, 0),
(9, 5, 9, 0),
(9, 6, 9, 0),
(9, 7, 9, 0),
(9, 8, 9, 0),
(9, 9, 9, 0),
(9, 10, 9, 0),
(10, 1, 10, 1),
(10, 2, 10, 1),
(10, 3, 10, 1),
(10, 4, 10, 1),
(10, 5, 10, 1),
(10, 6, 10, 1),
(10, 7, 10, 1),
(10, 8, 10, 1),
(10, 9, 10, 1),
(10, 10, 10, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socios`
--

CREATE TABLE `socios` (
  `id_socio` int(11) NOT NULL,
  `id_biblioteca` int(11) NOT NULL,
  `nombre_socio` varchar(20) DEFAULT NULL,
  `apellido_socio` varchar(20) DEFAULT NULL,
  `dni_socio` varchar(9) DEFAULT NULL,
  `direccion_socio` varchar(50) DEFAULT NULL,
  `tlf_socio` varchar(9) DEFAULT NULL,
  `email_socio` varchar(50) DEFAULT NULL,
  `lista_negra` tinyint(1) DEFAULT 0,
  `fechaNad_socio` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `socios`
--

INSERT INTO `socios` (`id_socio`, `id_biblioteca`, `nombre_socio`, `apellido_socio`, `dni_socio`, `direccion_socio`, `tlf_socio`, `email_socio`, `lista_negra`, `fechaNad_socio`) VALUES
(1, 1, 'Juan', 'González', '12345678A', 'Calle 123', '555123456', 'juan@gmail.com', 0, '1980-05-15'),
(1, 2, 'Juan', 'González', '12345678K', 'Calle 123', '555123456', 'juan@gmail.com', 0, '1980-05-15'),
(1, 3, 'Juan', 'González', '12345678U', 'Calle 123', '555123456', 'juan@gmail.com', 0, '1980-05-15'),
(1, 4, 'Juan', 'González', '12345678Z', 'Calle 123', '555123456', 'juan@gmail.com', 0, '1980-05-15'),
(1, 5, 'Pedro', 'López', '12345678E', 'Calle 123', '555123456', 'pedro@gmail.com', 0, '1980-05-15'),
(1, 6, 'Pedro', 'López', '12345678M', 'Calle 123', '555123456', 'pedro@gmail.com', 0, '1980-05-15'),
(1, 7, 'Juan', 'González', '12345678U', 'Calle 123', '555123456', 'juan@gmail.com', 0, '1980-05-15'),
(1, 8, 'Juan', 'González', '12345677E', 'Calle 123', '555123456', 'juan@gmail.com', 0, '1980-05-15'),
(1, 9, 'Juan', 'González', '12345678O', 'Calle 123', '555123456', 'juan@gmail.com', 0, '1980-05-15'),
(1, 10, 'Juan', 'González', '12345678Y', 'Calle 123', '555123456', 'juan@gmail.com', 0, '1980-05-15'),
(2, 1, 'María', 'López', '23456789B', 'Avenida 456', '555234567', 'maria@gmail.com', 0, '1992-08-21'),
(2, 2, 'María', 'López', '23456789L', 'Avenida 456', '555234567', 'maria@gmail.com', 0, '1992-08-21'),
(2, 3, 'María', 'López', '23456789V', 'Avenida 456', '555234567', 'maria@gmail.com', 0, '1992-08-21'),
(2, 4, 'María', 'López', '23456789A', 'Avenida 456', '555234567', 'maria@gmail.com', 0, '1992-08-21'),
(2, 5, 'Isabel', 'Martínez', '23456789F', 'Avenida 456', '555234567', 'isabel@gmail.com', 0, '1992-08-21'),
(2, 6, 'Isabel', 'Martínez', '23456789N', 'Avenida 456', '555234567', 'isabel@gmail.com', 0, '1992-08-21'),
(2, 7, 'María', 'López', '23456789V', 'Avenida 456', '555234567', 'maria@gmail.com', 0, '1992-08-21'),
(2, 8, 'María', 'López', '23456789F', 'Avenida 456', '555234567', 'maria@gmail.com', 0, '1992-08-21'),
(2, 9, 'María', 'López', '23456789P', 'Avenida 456', '555234567', 'maria@gmail.com', 0, '1992-08-21'),
(2, 10, 'María', 'López', '23456789Z', 'Avenida 456', '555234567', 'maria@gmail.com', 0, '1992-08-21'),
(3, 1, 'Luis', 'Martínez', '34567890C', 'Calle 789', '555345678', 'luis@gmail.com', 0, '1985-10-10'),
(3, 2, 'Luis', 'Martínez', '34567890M', 'Calle 789', '555345678', 'luis@gmail.com', 0, '1985-10-10'),
(3, 3, 'Luis', 'Martínez', '34567890W', 'Calle 789', '555345678', 'luis@gmail.com', 0, '1985-10-10'),
(3, 4, 'Luis', 'Martínez', '34567890B', 'Calle 789', '555345678', 'luis@gmail.com', 0, '1985-10-10'),
(3, 5, 'Luis', 'Martínez', '34567890G', 'Calle 789', '555345678', 'luis@gmail.com', 0, '1985-10-10'),
(3, 6, 'Luis', 'Martínez', '34567890O', 'Calle 789', '555345678', 'luis@gmail.com', 0, '1985-10-10'),
(3, 7, 'Luis', 'Martínez', '34567890W', 'Calle 789', '555345678', 'luis@gmail.com', 0, '1985-10-10'),
(3, 8, 'Luis', 'Martínez', '34567890G', 'Calle 789', '555345678', 'luis@gmail.com', 0, '1985-10-10'),
(3, 9, 'Luis', 'Martínez', '34567890Q', 'Calle 789', '555345678', 'luis@gmail.com', 0, '1985-10-10'),
(3, 10, 'Luis', 'Martínez', '34567890A', 'Calle 789', '555345678', 'luis@gmail.com', 0, '1985-10-10'),
(4, 1, 'Elena', 'Rodríguez', '45678901D', 'Avenida 987', '555456789', 'elena@gmail.com', 0, '1988-03-25'),
(4, 2, 'Elena', 'Rodríguez', '45678901N', 'Avenida 987', '555456789', 'elena@gmail.com', 0, '1988-03-25'),
(4, 3, 'Elena', 'Rodríguez', '45678901X', 'Avenida 987', '555456789', 'elena@gmail.com', 0, '1988-03-25'),
(4, 4, 'Elena', 'Rodríguez', '45678901C', 'Avenida 987', '555456789', 'elena@gmail.com', 0, '1988-03-25'),
(4, 5, 'Elena', 'Rodríguez', '45678901H', 'Avenida 987', '555456789', 'elena@gmail.com', 0, '1988-03-25'),
(4, 6, 'Elena', 'Rodríguez', '45678901P', 'Avenida 987', '555456789', 'elena@gmail.com', 0, '1988-03-25'),
(4, 7, 'Elena', 'Rodríguez', '45678901X', 'Avenida 987', '555456789', 'elena@gmail.com', 0, '1988-03-25'),
(4, 8, 'Elena', 'Rodríguez', '45678901H', 'Avenida 987', '555456789', 'elena@gmail.com', 0, '1988-03-25'),
(4, 9, 'Elena', 'Rodríguez', '45678901R', 'Avenida 987', '555456789', 'elena@gmail.com', 0, '1988-03-25'),
(4, 10, 'Elena', 'Rodríguez', '45678901B', 'Avenida 987', '555456789', 'elena@gmail.com', 0, '1988-03-25'),
(5, 1, 'Carlos', 'Fernández', '56789012E', 'Calle 654', '555567890', 'carlos@gmail.com', 0, '1977-07-07'),
(5, 2, 'Carlos', 'Fernández', '56789012O', 'Calle 654', '555567890', 'carlos@gmail.com', 0, '1977-07-07'),
(5, 3, 'Carlos', 'Fernández', '56789012Y', 'Calle 654', '555567890', 'carlos@gmail.com', 0, '1977-07-07'),
(5, 4, 'Carlos', 'Fernández', '56789012D', 'Calle 654', '555567890', 'carlos@gmail.com', 0, '1977-07-07'),
(5, 5, 'Carlos', 'Fernández', '56789012I', 'Calle 654', '555567890', 'carlos@gmail.com', 0, '1977-07-07'),
(5, 6, 'Carlos', 'Fernández', '56789012Q', 'Calle 654', '555567890', 'carlos@gmail.com', 0, '1977-07-07'),
(5, 7, 'Carlos', 'Fernández', '56789012Y', 'Calle 654', '555567890', 'carlos@gmail.com', 0, '1977-07-07'),
(5, 8, 'Carlos', 'Fernández', '56789012I', 'Calle 654', '555567890', 'carlos@gmail.com', 0, '1977-07-07'),
(5, 9, 'Carlos', 'Fernández', '56789012S', 'Calle 654', '555567890', 'carlos@gmail.com', 0, '1977-07-07'),
(5, 10, 'Carlos', 'Fernández', '56789012C', 'Calle 654', '555567890', 'carlos@gmail.com', 0, '1977-07-07'),
(6, 1, 'Sofía', 'Hernández', '67890123F', 'Avenida 321', '555678901', 'sofia@gmail.com', 0, '1990-12-12'),
(6, 2, 'Sofía', 'Hernández', '67890123P', 'Avenida 321', '555678901', 'sofia@gmail.com', 0, '1990-12-12'),
(6, 5, 'Sofía', 'Hernández', '67890123J', 'Avenida 321', '555678901', 'sofia@gmail.com', 0, '1990-12-12'),
(6, 6, 'Sofía', 'Hernández', '67890123R', 'Avenida 321', '555678901', 'sofia@gmail.com', 0, '1990-12-12'),
(6, 7, 'Sofía', 'Hernández', '67890123Z', 'Avenida 321', '555678901', 'sofia@gmail.com', 0, '1990-12-12'),
(6, 8, 'Sofía', 'Hernández', '67890123J', 'Avenida 321', '555678901', 'sofia@gmail.com', 0, '1990-12-12'),
(6, 9, 'Sofía', 'Hernández', '67890123T', 'Avenida 321', '555678901', 'sofia@gmail.com', 0, '1990-12-12'),
(6, 10, 'Sofía', 'Hernández', '67890123D', 'Avenida 321', '555678901', 'sofia@gmail.com', 0, '1990-12-12'),
(7, 1, 'David', 'Díaz', '78901234G', 'Calle 135', '555789012', 'david@gmail.com', 0, '1982-04-18'),
(7, 2, 'David', 'Díaz', '78901234Q', 'Calle 135', '555789012', 'david@gmail.com', 0, '1982-04-18'),
(7, 5, 'David', 'Díaz', '78901234K', 'Calle 135', '555789012', 'david@gmail.com', 0, '1982-04-18'),
(7, 6, 'David', 'Díaz', '78901234S', 'Calle 135', '555789012', 'david@gmail.com', 0, '1982-04-18'),
(7, 7, 'David', 'Díaz', '78901234A', 'Calle 135', '555789012', 'david@gmail.com', 0, '1982-04-18'),
(7, 8, 'David', 'Díaz', '78901234K', 'Calle 135', '555789012', 'david@gmail.com', 0, '1982-04-18'),
(7, 9, 'David', 'Díaz', '78901234U', 'Calle 135', '555789012', 'david@gmail.com', 0, '1982-04-18'),
(7, 10, 'David', 'Díaz', '78901234E', 'Calle 135', '555789012', 'david@gmail.com', 0, '1982-04-18'),
(8, 1, 'Laura', 'Gómez', '89012345H', 'Avenida 246', '555890123', 'laura@gmail.com', 0, '1989-09-09'),
(8, 2, 'Laura', 'Gómez', '89012345R', 'Avenida 246', '555890123', 'laura@gmail.com', 0, '1989-09-09'),
(8, 5, 'Laura', 'Gómez', '89012345L', 'Avenida 246', '555890123', 'laura@gmail.com', 0, '1989-09-09'),
(8, 6, 'Laura', 'Gómez', '89012345T', 'Avenida 246', '555890123', 'laura@gmail.com', 0, '1989-09-09'),
(8, 7, 'Laura', 'Gómez', '89012345B', 'Avenida 246', '555890123', 'laura@gmail.com', 0, '1989-09-09'),
(8, 8, 'Laura', 'Gómez', '89012345L', 'Avenida 246', '555890123', 'laura@gmail.com', 0, '1989-09-09'),
(8, 9, 'Laura', 'Gómez', '89012345V', 'Avenida 246', '555890123', 'laura@gmail.com', 0, '1989-09-09'),
(8, 10, 'Laura', 'Gómez', '89012345F', 'Avenida 246', '555890123', 'laura@gmail.com', 0, '1989-09-09'),
(9, 1, 'Pablo', 'Ruiz', '90123456I', 'Calle 789', '555901234', 'pablo@gmail.com', 0, '1984-06-06'),
(9, 2, 'Pablo', 'Ruiz', '90123456S', 'Calle 789', '555901234', 'pablo@gmail.com', 0, '1984-06-06'),
(9, 7, 'Pablo', 'Ruiz', '90123456C', 'Calle 789', '555901234', 'pablo@gmail.com', 0, '1984-06-06'),
(9, 8, 'Pablo', 'Ruiz', '90123456M', 'Calle 789', '555901234', 'pablo@gmail.com', 0, '1984-06-06'),
(9, 9, 'Pablo', 'Ruiz', '90123456W', 'Calle 789', '555901234', 'pablo@gmail.com', 0, '1984-06-06'),
(9, 10, 'Pablo', 'Ruiz', '90123456G', 'Calle 789', '555901234', 'pablo@gmail.com', 0, '1984-06-06'),
(10, 1, 'Ana', 'Pérez', '01234567J', 'Avenida 012', '555012345', 'ana@gmail.com', 0, '1995-01-01'),
(10, 2, 'Ana', 'Pérez', '01234567T', 'Avenida 012', '555012345', 'ana@gmail.com', 0, '1995-01-01'),
(10, 7, 'Ana', 'Pérez', '01234567D', 'Avenida 012', '555012345', 'ana@gmail.com', 0, '1995-01-01'),
(10, 8, 'Ana', 'Pérez', '01234567N', 'Avenida 012', '555012345', 'ana@gmail.com', 0, '1995-01-01'),
(10, 9, 'Ana', 'Pérez', '01234567X', 'Avenida 012', '555012345', 'ana@gmail.com', 0, '1995-01-01'),
(10, 10, 'Ana', 'Pérez', '01234567H', 'Avenida 012', '555012345', 'ana@gmail.com', 0, '1995-01-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `id_biblioteca` int(11) NOT NULL,
  `email_usuario` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `id_biblioteca`, `email_usuario`) VALUES
(1, 1, 'emple1B1@gmail.com'),
(1, 2, 'emple1B2@gmail.com'),
(1, 3, 'emple1B3@gmail.com'),
(1, 4, 'emple1B4@gmail.com'),
(1, 5, 'emple1B5@gmail.com'),
(1, 6, 'emple1B6@gmail.com'),
(1, 7, 'emple1B7@gmail.com'),
(1, 8, 'emple1B8@gmail.com'),
(1, 9, 'emple1B9@gmail.com'),
(1, 10, 'emple1B10@gmail.com'),
(2, 1, 'emple2B1@gmail.com'),
(2, 2, 'emple2B2@gmail.com'),
(2, 3, 'emple2B3@gmail.com'),
(2, 4, 'emple2B4@gmail.com'),
(2, 5, 'emple2B5@gmail.com'),
(2, 6, 'emple2B6@gmail.com'),
(2, 7, 'emple2B7@gmail.com'),
(2, 8, 'emple2B8@gmail.com'),
(2, 9, 'emple2B9@gmail.com'),
(2, 10, 'emple2@B10gmail.com'),
(3, 1, 'admin1B1@gmail.com'),
(3, 2, 'admin1B2@gmail.com'),
(3, 3, 'admin1B3@gmail.com'),
(3, 4, 'admin1B4@gmail.com'),
(3, 5, 'admin1B5@gmail.com'),
(3, 6, 'admin1B6@gmail.com'),
(3, 7, 'admin1B7@gmail.com'),
(3, 8, 'admin1B8@gmail.com'),
(3, 9, 'admin1B9@gmail.com'),
(3, 10, 'admin1B10@gmail.com'),
(4, 1, 'admin2B1@gmail.com'),
(4, 2, 'admin2B2@gmail.com'),
(4, 3, 'admin2B2@gmail.com'),
(4, 4, 'admin2B4@gmail.com'),
(4, 5, 'admin2B5@gmail.com'),
(4, 6, 'admin2B6@gmail.com'),
(4, 7, 'admin2B7@gmail.com'),
(4, 8, 'admin2B8@gmail.com'),
(4, 9, 'admin2B9@gmail.com'),
(4, 10, 'admin2B10@gmail.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autores`
--
ALTER TABLE `autores`
  ADD PRIMARY KEY (`id_autor`);

--
-- Indices de la tabla `bibliotecas`
--
ALTER TABLE `bibliotecas`
  ADD PRIMARY KEY (`id_biblioteca`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id_libro`,`id_biblioteca`),
  ADD KEY `id_biblioteca` (`id_biblioteca`);

--
-- Indices de la tabla `multas`
--
ALTER TABLE `multas`
  ADD PRIMARY KEY (`id_multa`,`id_biblioteca`),
  ADD KEY `idSoc_mul_fk` (`id_socio`),
  ADD KEY `idBib_mul_fk` (`id_biblioteca`);

--
-- Indices de la tabla `otros`
--
ALTER TABLE `otros`
  ADD PRIMARY KEY (`id_otro`,`id_biblioteca`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`id_prestamo`,`id_biblioteca`),
  ADD KEY `idSoc_pres_fk` (`id_socio`),
  ADD KEY `idLib_pres_fk` (`id_libro`),
  ADD KEY `idBib_pres_fk` (`id_biblioteca`);

--
-- Indices de la tabla `recibos`
--
ALTER TABLE `recibos`
  ADD PRIMARY KEY (`id_recibo`,`id_biblioteca`),
  ADD KEY `idSoc_rec_fk` (`id_socio`),
  ADD KEY `idBib_rec_fk` (`id_biblioteca`);

--
-- Indices de la tabla `socios`
--
ALTER TABLE `socios`
  ADD PRIMARY KEY (`id_socio`,`id_biblioteca`),
  ADD KEY `idBi_soc_fk` (`id_biblioteca`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`,`id_biblioteca`),
  ADD KEY `idBi_usu_fk` (`id_biblioteca`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `autores`
--
ALTER TABLE `autores`
  MODIFY `id_autor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `bibliotecas`
--
ALTER TABLE `bibliotecas`
  MODIFY `id_biblioteca` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `id_libro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `multas`
--
ALTER TABLE `multas`
  MODIFY `id_multa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `otros`
--
ALTER TABLE `otros`
  MODIFY `id_otro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `id_prestamo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `recibos`
--
ALTER TABLE `recibos`
  MODIFY `id_recibo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `socios`
--
ALTER TABLE `socios`
  MODIFY `id_socio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `libros`
--
ALTER TABLE `libros`
  ADD CONSTRAINT `libros_ibfk_1` FOREIGN KEY (`id_biblioteca`) REFERENCES `bibliotecas` (`id_biblioteca`);

--
-- Filtros para la tabla `multas`
--
ALTER TABLE `multas`
  ADD CONSTRAINT `idBib_mul_fk` FOREIGN KEY (`id_biblioteca`) REFERENCES `bibliotecas` (`id_biblioteca`),
  ADD CONSTRAINT `idSoc_mul_fk` FOREIGN KEY (`id_socio`) REFERENCES `socios` (`id_socio`);

--
-- Filtros para la tabla `otros`
--
ALTER TABLE `otros`
  ADD CONSTRAINT `idOtro_otros_fk` FOREIGN KEY (`id_otro`,`id_biblioteca`) REFERENCES `usuarios` (`id_usuario`, `id_biblioteca`);

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `idBib_pres_fk` FOREIGN KEY (`id_biblioteca`) REFERENCES `bibliotecas` (`id_biblioteca`),
  ADD CONSTRAINT `idLib_pres_fk` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id_libro`),
  ADD CONSTRAINT `idSoc_pres_fk` FOREIGN KEY (`id_socio`) REFERENCES `socios` (`id_socio`);

--
-- Filtros para la tabla `recibos`
--
ALTER TABLE `recibos`
  ADD CONSTRAINT `idBib_rec_fk` FOREIGN KEY (`id_biblioteca`) REFERENCES `bibliotecas` (`id_biblioteca`),
  ADD CONSTRAINT `idSoc_rec_fk` FOREIGN KEY (`id_socio`) REFERENCES `socios` (`id_socio`);

--
-- Filtros para la tabla `socios`
--
ALTER TABLE `socios`
  ADD CONSTRAINT `idBi_soc_fk` FOREIGN KEY (`id_biblioteca`) REFERENCES `bibliotecas` (`id_biblioteca`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `idBi_usu_fk` FOREIGN KEY (`id_biblioteca`) REFERENCES `bibliotecas` (`id_biblioteca`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
