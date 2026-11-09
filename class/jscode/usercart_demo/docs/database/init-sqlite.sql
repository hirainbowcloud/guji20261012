-- ============================================================
-- 簡易購物車資料庫初始化腳本（SQLite）
-- 對應後端：shoppingusercart
--
-- 用法：
--   sqlite3 mydb.db ".read init-sqlite.sql"
--   或使用 DB Browser for SQLite → Execute SQL → 開啟本檔 → 執行
--
-- ⚠️ 注意：本腳本會先 DROP 既有資料表，適合「重建」示範資料庫。
--          若資料庫已有重要資料，請先備份 mydb.db。
-- ============================================================

-- PRAGMA foreign_keys = ON;

DROP TABLE IF EXISTS orderitems;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS ratingwithimage;
DROP TABLE IF EXISTS productswithimage;

-- ------------------------------------------------------------
-- 1. 產品表（JPA Entity: Product）
--    注意：picture 欄位存在於資料庫，但目前 Entity 沒有映射，
--          因此不會被 API 回傳。本腳本將 picture 設為 NULL。
-- ------------------------------------------------------------
CREATE TABLE productswithimage (
    id          int NOT NULL PRIMARY KEY,
    category    varchar(255),
    description varchar(2048),
    image       varchar(255),
    picture     longblob,
    price       double NOT NULL,
    title       varchar(255),
    rating_id   int
);

-- ------------------------------------------------------------
-- 2. 評分表（目前沒有 JPA Entity，只有 rating_id 外鍵欄位）
-- ------------------------------------------------------------
CREATE TABLE ratingwithimage (
    id    int NOT NULL PRIMARY KEY,
    count int NOT NULL,
    rate  double NOT NULL
);

-- ------------------------------------------------------------
-- 3. 訂單主檔（JPA Entity: Order）
--    orderTime 由 SQLite JDBC 以「epoch 毫秒」整數儲存
-- ------------------------------------------------------------
CREATE TABLE orders (
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    orderTime  TIMESTAMP,
    totalPrice INT NOT NULL,
    username   varchar(255)
);

-- ------------------------------------------------------------
-- 4. 訂單明細（JPA Entity: OrderItem）
-- ------------------------------------------------------------
CREATE TABLE orderitems (
    id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    pid          INT NOT NULL,
    productPrice INT NOT NULL,
    productTitle varchar(255),
    quantity     INT NOT NULL,
    order_id     bigint
);

CREATE INDEX idx_orders_username ON orders(username);
CREATE INDEX idx_orderitems_order_id ON orderitems(order_id);
CREATE INDEX idx_products_rating_id ON productswithimage(rating_id);


-- ------------------------------------------------------------
-- 5. 種子資料：20 筆產品
-- ------------------------------------------------------------

INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (1, 'men''s clothing', 'Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday', 'https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png', NULL, 109.95, 'Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops', 1);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (2, 'men''s clothing', 'Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.', 'https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_t.png', NULL, 22.3, 'Mens Casual Premium Slim Fit T-Shirts', 2);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (3, 'men''s clothing', 'great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.', 'https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_t.png', NULL, 55.99, 'Mens Cotton Jacket', 3);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (4, 'men''s clothing', 'The color could be slightly different between on the screen and in practice. / Please note that body builds vary by person, therefore, detailed size information should be reviewed below on the product description.', 'https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_t.png', NULL, 15.99, 'Mens Casual Slim Fit', 4);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (5, 'jewelery', 'From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean''s pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.', 'https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_t.png', NULL, 695.0, 'John Hardy Women''s Legends Naga Gold & Silver Dragon Station Chain Bracelet', 5);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (6, 'jewelery', 'Satisfaction Guaranteed. Return or exchange any order within 30 days.Designed and sold by Hafeez Center in the United States. Satisfaction Guaranteed. Return or exchange any order within 30 days.', 'https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_t.png', NULL, 168.0, 'Solid Gold Petite Micropave', 6);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (7, 'jewelery', 'Classic Created Wedding Engagement Solitaire Diamond Promise Ring for Her. Gifts to spoil your love more for Engagement, Wedding, Anniversary, Valentine''s Day...', 'https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_t.png', NULL, 9.99, 'White Gold Plated Princess', 7);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (8, 'jewelery', 'Rose Gold Plated Double Flared Tunnel Plug Earrings. Made of 316L Stainless Steel', 'https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_t.png', NULL, 10.99, 'Pierced Owl Rose Gold Plated Stainless Steel Double', 8);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (9, 'electronics', 'USB 3.0 and USB 2.0 Compatibility Fast data transfers Improve PC Performance High Capacity; Compatibility Formatted NTFS for Windows 10, Windows 8.1, Windows 7; Reformatting may be required for other operating systems; Compatibility may vary depending on users hardware configuration and operating system', 'https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_t.png', NULL, 64.0, 'WD 2TB Elements Portable External Hard Drive - USB 3.0', 9);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (10, 'electronics', 'Easy upgrade for faster boot up, shutdown, application load and response (As compared to 5400 RPM SATA 2.5 hard drive; Based on published specifications and internal benchmarking tests using PCMark vantage scores) Boosts burst write performance, making it ideal for typical PC workloads The perfect balance of performance and reliability Read/write speeds of up to 535MB/s/450MB/s (Based on internal testing; Performance may vary depending upon drive capacity, host device, OS and application.)', 'https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_t.png', NULL, 109.0, 'SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s', 10);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (11, 'electronics', '3D NAND flash are applied to deliver high transfer speeds Remarkable transfer speeds that enable faster bootup and improved overall system performance. The advanced SLC Cache Technology allows performance boost and longer lifespan 7mm slim design suitable for Ultrabooks and Ultra-slim notebooks. Supports TRIM command, Garbage Collection technology, RAID, and ECC (Error Checking & Correction) to provide the optimized performance and enhanced reliability.', 'https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_t.png', NULL, 109.0, 'Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5', 11);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (12, 'electronics', 'Expand your PS4 gaming experience, Play anywhere Fast and easy, setup Sleek design with high capacity, 3-year manufacturer''s limited warranty', 'https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_t.png', NULL, 114.0, 'WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive', 12);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (13, 'electronics', '21. 5 inches Full HD (1920 x 1080) widescreen IPS display And Radeon free Sync technology. No compatibility for VESA Mount Refresh Rate: 75Hz - Using HDMI port Zero-frame design | ultra-thin | 4ms response time | IPS panel Aspect ratio - 16: 9. Color Supported - 16. 7 million colors. Brightness - 250 nit Tilt angle -5 degree to 15 degree. Horizontal viewing angle-178 degree. Vertical viewing angle-178 degree 75 hertz', 'https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_t.png', NULL, 599.0, 'Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin', 13);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (14, 'electronics', '49 INCH SUPER ULTRAWIDE 32:9 CURVED GAMING MONITOR with dual 27 inch screen side by side QUANTUM DOT (QLED) TECHNOLOGY, HDR support and factory calibration provides stunningly realistic and accurate color and contrast 144HZ HIGH REFRESH RATE and 1ms ultra fast response time work to eliminate motion blur, ghosting, and reduce input lag', 'https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_t.png', NULL, 999.99, 'Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) Super Ultrawide Screen QLED', 14);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (15, 'women''s clothing', 'Note:The Jackets is US standard size, Please choose size as your usual wear Material: 100% Polyester; Detachable Liner Fabric: Warm Fleece. Detachable Functional Liner: Skin Friendly, Lightweigt and Warm.Stand Collar Liner jacket, keep you warm in cold weather. Zippered Pockets: 2 Zippered Hand Pockets, 2 Zippered Pockets on Chest (enough to keep cards or keys)and 1 Hidden Pocket Inside.Zippered Hand Pockets and Hidden Pocket keep your things secure. Humanized Design: Adjustable and Detachable Hood and Adjustable cuff to prevent the wind and water,for a comfortable fit. 3 in 1 Detachable Design provide more convenience, you can separate the coat and inner as needed, or wear it together. It is suitable for different season and help you adapt to different climates', 'https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_t.png', NULL, 56.99, 'BIYLACLESEN Women''s 3-in-1 Snowboard Jacket Winter Coats', 15);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (16, 'women''s clothing', '100% POLYURETHANE(shell) 100% POLYESTER(lining) 75% POLYESTER 25% COTTON (SWEATER), Faux leather material for style and comfort / 2 pockets of front, 2-For-One Hooded denim style faux leather jacket, Button detail on waist / Detail stitching at sides, HAND WASH ONLY / DO NOT BLEACH / LINE DRY / DO NOT IRON', 'https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_t.png', NULL, 29.95, 'Lock and Love Women''s Removable Hooded Faux Leather Moto Biker Jacket', 16);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (17, 'women''s clothing', 'Lightweight perfet for trip or casual wear---Long sleeve with hooded, adjustable drawstring waist design. Button and zipper front closure raincoat, fully stripes Lined and The Raincoat has 2 side pockets are a good size to hold all kinds of things, it covers the hips, and the hood is generous but doesn''t overdo it.Attached Cotton Lined Hood with Adjustable Drawstrings give it a real styled look.', 'https://fakestoreapi.com/img/71HblAHs5xL._AC_UY879_-2t.png', NULL, 39.99, 'Rain Jacket Women Windbreaker Striped Climbing Raincoats', 17);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (18, 'women''s clothing', '95% RAYON 5% SPANDEX, Made in USA or Imported, Do Not Bleach, Lightweight fabric with great stretch for comfort, Ribbed on sleeves and neckline / Double stitching on bottom hem', 'https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_t.png', NULL, 9.85, 'MBJ Women''s Solid Short Sleeve Boat Neck V', 18);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (19, 'women''s clothing', '100% Polyester, Machine wash, 100% cationic polyester interlock, Machine Wash & Pre Shrunk for a Great Fit, Lightweight, roomy and highly breathable with moisture wicking fabric which helps to keep moisture away, Soft Lightweight Fabric with comfortable V-neck collar and a slimmer fit, delivers a sleek, more feminine silhouette and Added Comfort', 'https://fakestoreapi.com/img/51eg55uWmdL._AC_UX679_t.png', NULL, 7.95, 'Opna Women''s Short Sleeve Moisture', 19);
INSERT INTO productswithimage (id, category, description, image, picture, price, title, rating_id) VALUES (20, 'women''s clothing', '95%Cotton,5%Spandex, Features: Casual, Short Sleeve, Letter Print,V-Neck,Fashion Tees, The fabric is soft and has some stretch., Occasion: Casual/Office/Beach/School/Home/Street. Season: Spring,Summer,Autumn,Winter.', 'https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_t.png', NULL, 12.99, 'DANVOUY Womens T Shirt Casual Cotton Short', 20);

-- ------------------------------------------------------------
-- 6. 種子資料：20 筆評分
-- ------------------------------------------------------------

INSERT INTO ratingwithimage (id, count, rate) VALUES (1, 120, 3.9);
INSERT INTO ratingwithimage (id, count, rate) VALUES (2, 259, 4.1);
INSERT INTO ratingwithimage (id, count, rate) VALUES (3, 500, 4.7);
INSERT INTO ratingwithimage (id, count, rate) VALUES (4, 430, 2.1);
INSERT INTO ratingwithimage (id, count, rate) VALUES (5, 400, 4.6);
INSERT INTO ratingwithimage (id, count, rate) VALUES (6, 70, 3.9);
INSERT INTO ratingwithimage (id, count, rate) VALUES (7, 400, 3.0);
INSERT INTO ratingwithimage (id, count, rate) VALUES (8, 100, 1.9);
INSERT INTO ratingwithimage (id, count, rate) VALUES (9, 203, 3.3);
INSERT INTO ratingwithimage (id, count, rate) VALUES (10, 470, 2.9);
INSERT INTO ratingwithimage (id, count, rate) VALUES (11, 319, 4.8);
INSERT INTO ratingwithimage (id, count, rate) VALUES (12, 400, 4.8);
INSERT INTO ratingwithimage (id, count, rate) VALUES (13, 250, 2.9);
INSERT INTO ratingwithimage (id, count, rate) VALUES (14, 140, 2.2);
INSERT INTO ratingwithimage (id, count, rate) VALUES (15, 235, 2.6);
INSERT INTO ratingwithimage (id, count, rate) VALUES (16, 340, 2.9);
INSERT INTO ratingwithimage (id, count, rate) VALUES (17, 679, 3.8);
INSERT INTO ratingwithimage (id, count, rate) VALUES (18, 130, 4.7);
INSERT INTO ratingwithimage (id, count, rate) VALUES (19, 146, 4.5);
INSERT INTO ratingwithimage (id, count, rate) VALUES (20, 145, 3.6);

-- ------------------------------------------------------------
-- 7. 種子資料：2 筆示範訂單（username = admin）
-- ------------------------------------------------------------

-- INSERT INTO orders (id, orderTime, totalPrice, username) VALUES (1, 1786849979186, 143, 'admin');
-- INSERT INTO orders (id, orderTime, totalPrice, username) VALUES (2, 1793588072252, 1220, 'admin');

-- ------------------------------------------------------------
-- 8. 種子資料：5 筆示範訂單明細
-- ------------------------------------------------------------

INSERT INTO orderitems (id, pid, productPrice, productTitle, quantity, order_id) VALUES (1, 1, 109, 'Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops', 1, 1);
INSERT INTO orderitems (id, pid, productPrice, productTitle, quantity, order_id) VALUES (2, 2, 22, 'Mens Casual Premium Slim Fit T-Shirts', 1, 1);
INSERT INTO orderitems (id, pid, productPrice, productTitle, quantity, order_id) VALUES (3, 8, 10, 'Pierced Owl Rose Gold Plated Stainless Steel Double', 1, 1);
INSERT INTO orderitems (id, pid, productPrice, productTitle, quantity, order_id) VALUES (4, 13, 599, 'Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin', 2, 2);
INSERT INTO orderitems (id, pid, productPrice, productTitle, quantity, order_id) VALUES (5, 2, 22, 'Mens Casual Premium Slim Fit T-Shirts', 1, 2);

-- 完成
