CREATE TABLE IF NOT EXISTS `inventory`(
    `sku` varchar(255) NOT NULL,
    `product_id` varchar(255) NOT NULL PRIMARY KEY,
    `stock` BIGINT NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(255) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(255) DEFAULT NULL
);