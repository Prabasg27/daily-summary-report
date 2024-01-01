DROP TABLE IF EXISTS movement_spec;

CREATE TABLE `movement_spec` (
`id` int AUTO_INCREMENT PRIMARY KEY,
`field_name` VARCHAR(100) NOT NULL,
`field_length` int NOT NULL,
`field_position` int NOT NULL,
`field_type` VARCHAR(100) NOT NULL,
`field_mapper` VARCHAR(100) NOT NULL
);