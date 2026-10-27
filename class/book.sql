CREATE TABLE books (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(200) NOT NULL,
    author     VARCHAR(100) NOT NULL,
    isbn       VARCHAR(20)  NOT NULL,
    price      DECIMAL(10,2) NOT NULL,
    stock      INT NOT NULL DEFAULT 0,
    category   VARCHAR(50),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_books_isbn (isbn)
) ENGINE=InnoDB ;

CREATE INDEX idx_books_category ON books (category);
CREATE INDEX idx_books_title    ON books (title);