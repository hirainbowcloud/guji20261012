-- src/main/resources/data.sql（練習 2-3 完成後替換為此版本）
-- categories 表：對應 Category entity 的 @Table(name = "categories")
INSERT INTO categories (id, name) VALUES (1, '電腦');
INSERT INTO categories (id, name) VALUES (2, '手機');
INSERT INTO categories (id, name) VALUES (3, '配件');
INSERT INTO categories (id, name) VALUES (4, '3C');

-- products 表：對應 Product entity 的 @Table(name = "products")
-- category_id 為外鍵，對應上方 categories.id
INSERT INTO products (name, price, stock, category_id) VALUES
  ('MacBook Pro 14', 69999.0, 5,  1),
  ('iPhone 15 Pro',  39999.0, 20, 2),
  ('iPad Air',       24999.0, 15, 1),
  ('AirPods Pro',    7999.0,  50, 3),
  ('Magic Keyboard', 3999.0,  30, 3),
  ('MacBook Pro 15', 69999.0, 5,  1),
  ('iPhone 17 Pro',  39999.0, 20, 2),
  ('iPad Air 2026',  24999.0, 15, 1),
  ('AirPods 5 Pro',    7999.0,  50, 3),
  ('Magic Keyboard 2026', 3999.0,  30, 3);

