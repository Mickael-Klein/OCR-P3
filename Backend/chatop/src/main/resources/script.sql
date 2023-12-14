CREATE TABLE IF NOT EXISTS `USERS` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255) UNIQUE NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `RENTALS` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surface` int NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `picture` varchar(255) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `owner_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`owner_id`) REFERENCES `USERS` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `MESSAGES` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `rental_id` int NOT NULL,
  `user_id` int NOT NULL,
  `message` varchar(2000) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`rental_id`) REFERENCES `RENTALS` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`) ON DELETE CASCADE
);