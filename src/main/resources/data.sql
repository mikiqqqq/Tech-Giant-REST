CREATE TABLE BRAND
(
    ID     NUMBER PRIMARY KEY AUTO_INCREMENT,
    TITLE  VARCHAR(255)
);

CREATE TABLE PRODUCT_TYPE
(
    ID     NUMBER(11) PRIMARY KEY AUTO_INCREMENT,
    TITLE  VARCHAR(41)
);

CREATE TABLE PRODUCT
(
    ID              NUMBER(11) PRIMARY KEY AUTO_INCREMENT,
    IMAGE           VARCHAR(255),
    TITLE           VARCHAR(41),
    DESCRIPTION     VARCHAR(255),
    PRICE           DECIMAL(11, 2),
    QUANTITY        NUMBER,
    BRAND_ID        NUMBER,
    TYPE_ID         NUMBER,
    PRODUCTION_YEAR NUMBER,
    FOREIGN KEY (BRAND_ID) REFERENCES BRAND(ID),
    FOREIGN KEY (TYPE_ID) REFERENCES PRODUCT_TYPE(ID)
);

CREATE TABLE AUTH_LEVEL
(
    ID     NUMBER PRIMARY KEY AUTO_INCREMENT,
    TITLE  VARCHAR(255)
);

CREATE TABLE USERS
(
    ID                  NUMBER(11) PRIMARY KEY AUTO_INCREMENT,
    NAME                VARCHAR(255),
    EMAIL               VARCHAR(255),
    HASHED_PASSWORD            VARCHAR(512),
    AUTH_LEVEL_ID       NUMBER(11),
    FOREIGN KEY (AUTH_LEVEL_ID) REFERENCES AUTH_LEVEL(ID)
);

CREATE TABLE PAYMENT_METHOD
(
    ID     NUMBER PRIMARY KEY AUTO_INCREMENT,
    TITLE  VARCHAR(255)
);

CREATE TABLE DISCOUNT_CODE
(
    ID        NUMBER PRIMARY KEY AUTO_INCREMENT,
    CODE      VARCHAR(255),
    AMOUNT    DECIMAL(3, 2),
    ACTIVE      BOOLEAN
);

CREATE TABLE ORDERS
(
    ID                    NUMBER(11) PRIMARY KEY AUTO_INCREMENT,
    STATUS                VARCHAR(40),
    DATE                  TIMESTAMP,
    TOTAL_PRICE_EXCL_VAT  DECIMAL(11, 2),
    TOTAL_PRICE           DECIMAL(11, 2),
    DISCOUNT_CODE_ID      NUMBER,
    PAYMENT_METHOD_ID     NUMBER,
    CARD_NUMBER           VARCHAR(19),
    EMAIL                 VARCHAR(255),
    PHONE_NUMBER          VARCHAR(20),
    DELIVERY_ADDRESS      VARCHAR(255),
    NOTE                  VARCHAR(255),
    FOREIGN KEY (DISCOUNT_CODE_ID) REFERENCES DISCOUNT_CODE(ID),
    FOREIGN KEY (PAYMENT_METHOD_ID) REFERENCES PAYMENT_METHOD(ID)
);

CREATE TABLE ORDER_ITEMS
(
    ID          NUMBER PRIMARY KEY AUTO_INCREMENT,
    QUANTITY    NUMBER,
    ORDER_ID    NUMBER,
    ITEM_ID     NUMBER,
    FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ID),
    FOREIGN KEY (ITEM_ID) REFERENCES PRODUCT(ID)
);

INSERT INTO BRAND (TITLE) VALUES ('Asus'), ('Razer'), ('Samsung'), ('Apple'), ('Xiaomi'), ('LG'), ('Speedlink'), ('Noa'), ('Logitech'), ('Fenix'), ('Lenovo'), ('Vivax');

INSERT INTO PRODUCT_TYPE (TITLE) VALUES ('Desktop'), ('Laptop'), ('Peripherals');

INSERT INTO PRODUCT (TITLE, DESCRIPTION, BRAND_ID, PRICE, QUANTITY, TYPE_ID, PRODUCTION_YEAR)
VALUES
    ('Speedlink Mouse S50', 'Specifications for Speedlink Mouse S50', 7, 250.00, 101, 1, 2023),
    ('Razer Mouse T350', 'Specifications for Razer Mouse T350', 2, 360.00, 10, 1, 2023),
    ('PC Fenix Ultima R9-5950X', 'Specifications for PC Fenix Ultima R9-5950X, 64GB, 2TB M.2, RTX2060 8GB, PWS 1000W', 10, 23560.99, 10, 1, 2023),
    ('Lenovo ThinkStation Xeon W-2133', 'Specifications for Lenovo ThinkStation Xeon W-2133/64GB/512GB/Quadro P4000/W10P', 11, 2350.96, 10, 1, 2023),
    ('Lenovo V50t Gen2','Specifications for Lenovo V50t Gen2 i3/8GB/256GB/IntHD/W10P/5 years', 11, 5665.29, 10, 1, 2023),
    ('Lenovo M70s SFF G6400','Specifications for Lenovo M70s SFF G6400/4GB/256GB/IntHD/W10P', 11, 4758.84, 10, 1, 2023),
    ('iPhone SE', 'Specifications for iPhone SE', 4, 8459.29, 10, 1, 2023),
    ('MacBook Air', 'Specifications for MacBook Air', 4, 11239.59, 10, 1, 2023),
    ('iPad Pro','Specifications for iPad Pro', 4, 3480, 10, 1, 2023),
    ('Vivax LED TV-49S60T2S2SM', 'New Smart TV with Android 7.1 allows internet connectivity or social media, all on a large screen.', 12, 2359.29, 10, 1, 2023),
    ('Vivax IMAGO LED TV-32LE95T2', 'Ergonomically designed TV with legs instead of a classic stand, adding to its overall elegance.', 12, 3380.79, 10, 1, 2023),
    ('Xiaomi 11 Lite 5G NE', 'Specifications for Xiaomi 11 Lite 5G NE', 5, 6679.59, 10, 1, 2023),
    ('Noa Element Mobile', 'Specifications for Noa Element Mobile', 8, 899.49, 10, 1, 2023),
    ('LOGITECH H820E HEADSET', 'Wireless headset for business calls', 9, 349.89, 10, 1, 2023),
    ('Galaxy Z Fold3 5G', 'Specifications for Galaxy Z Fold3 5G', 3, 8129.00, 10, 1, 2023),
    ('LG 75" (189 cm) 4K HDR Smart Nano Cell TV', 'Pure Colors in Real 4K resolution, NanoCell technology, 4K processor LG α5 Gen5 AI', 6, 5579.29, 10, 1, 2023),
    ('16" ProArt Studiobook Pro 16 OLED', 'Windows 11 Pro, Intel® Core™ i9-12900H processor, NVIDIA RTX™ A3000 12GB graphics', 1, 8888.88, 10, 1, 2023);

INSERT INTO PAYMENT_METHOD (TITLE) VALUES ('CARD'), ('CASH');

INSERT INTO AUTH_LEVEL (TITLE) VALUES ('USER'), ('ADMIN');

INSERT INTO DISCOUNT_CODE (CODE, AMOUNT, ACTIVE) VALUES ('12EY-18U3-FDO1-VR34', 0.25, true), ('1111-2222-3333-4444', 0.1, false);
