CREATE TABLE IF NOT EXISTS `users`(
    `id` CHAR(36) NOT NULL PRIMARY KEY,
    `name` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `role` ENUM('customer', 'store manager', 'marketing manager'),
    `mobile_number` varchar(16) NOT NULL,
    `image_url` varchar(255) NOT NULL,
    `address` text NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(255) NOT NULL,
    `updated_at` date NOT NULL,
    `updated_by` varchar(255) NOT NULL
);