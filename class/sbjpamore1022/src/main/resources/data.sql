-- 步驟 1：先建立分類，因為 products 需要參照 category_id
INSERT INTO categories (id, name) VALUES
  (1, '電腦'),
  (2, '手機'),
  (3, '配件'),
  (4, '3C');

-- 步驟 2：欄位從 category 改為 category_id，填入對應的分類 ID
INSERT INTO products (name, price, stock, category_id) VALUES
  ('MacBook Pro 14', 69999.0, 5,  1),
  ('iPhone 15 Pro',  39999.0, 20, 2),
  ('iPad Air',       24999.0, 15, 1),
  ('AirPods Pro',    7999.0,  50, 3),
  ('Magic Keyboard', 3999.0,  30, 3),
  ('MacBook Pro 16', 89999.0, 55,  1),
  ('iPhone 17 Pro',  49999.0, 30, 2),
  ('iPad Air 2',     34999.0, 35, 1),
  ('AirPods ProX',   8999.0,  40, 3),
  ('Magic Keyboard I',1999.0, 10, 3);