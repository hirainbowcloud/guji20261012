-- 僅供尚未建立 offices 資料表時使用；IF NOT EXISTS 不會修改已存在的資料表。
-- 注意：實際 classicmodels 資料表欄名為駝峰式（officeCode, addressLine1, postalCode...）
CREATE DATABASE IF NOT EXISTS classicmodels;
USE classicmodels;

CREATE TABLE IF NOT EXISTS offices (
    officeCode VARCHAR(10) NOT NULL PRIMARY KEY,
    city VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    addressLine1 VARCHAR(50) NOT NULL,
    addressLine2 VARCHAR(50) DEFAULT NULL,
    state VARCHAR(50) DEFAULT NULL,
    country VARCHAR(50) NOT NULL,
    postalCode VARCHAR(15) NOT NULL,
    territory VARCHAR(10) NOT NULL
);
