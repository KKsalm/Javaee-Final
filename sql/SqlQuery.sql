# CREATE DATABASE yhtSaleSystem  COLLATE 'utf8_general_ci';
#
# USE DATABASE yhtSaleSystem;

SET GLOBAL sql_mode = (SELECT REPLACE(@@sql_mode, 'ONLY_FULL_GROUP_BY', ''));

CREATE TABLE store
(
    storeID INT          NOT NULL AUTO_INCREMENT,
    address VARCHAR(100) NOT NULL,
    status  BOOLEAN      NOT NULL DEFAULT 0,
    CONSTRAINT STORE_PK PRIMARY KEY (storeID)
);

CREATE TABLE user
(
    userID        INT          NOT NULL AUTO_INCREMENT,
    name          VARCHAR(30)  NULL,
    position      VARCHAR(30)  NULL,
    CHECK (position IN ('boss', 'manager', 'staff', NULL)),
    storeID       INT          NULL,
    monthWorkTime FLOAT        NULL DEFAULT 0,
    monthSalary   FLOAT        NULL DEFAULT 0,
    username      VARCHAR(100) NOT NULL UNIQUE,
    password      VARCHAR(100) NOT NULL,
    CONSTRAINT USER_PK PRIMARY KEY (userid),
    CONSTRAINT STORE_FK FOREIGN KEY (storeID) REFERENCES store (storeID)
);

CREATE TABLE product
(
    productID    INT         NOT NULL AUTO_INCREMENT,
    productName  VARCHAR(50) NOT NULL,
    productPrice FLOAT       NOT NULL,
    status       BOOLEAN     NOT NULL DEFAULT 0,
    CONSTRAINT PRODUCT_PK PRIMARY KEY (productID)
);

CREATE TABLE material
(
    materialID   INT         NOT NULL AUTO_INCREMENT,
    materialName VARCHAR(30) NOT NULL UNIQUE,
    price        FLOAT       NOT NULL,
    CONSTRAINT MATERIAL_PK PRIMARY KEY (materialID)
);

CREATE TABLE purchaseRecord
(
    purchaseRecordID INT   NOT NULL AUTO_INCREMENT,
    materialID       INT   NOT NULL,
    number           INT   NOT NULL,
    totalPrice       FLOAT NOT NULL,
    date             DATE  NOT NULL,
    CONSTRAINT PURCHASERECORD_PK PRIMARY KEY (purchaseRecordID),
    CONSTRAINT PURCHASERECORD_FK FOREIGN KEY (materialID) REFERENCES material (materialID)
);

CREATE TABLE saleRecord
(
    saleRecordID  INT      NOT NULL AUTO_INCREMENT,
    productID     INT      NOT NULL,
    productNumber INT      NOT NULL,
    totalPrice    FLOAT    NOT NULL,
    storeID       INT      NOT NULL,
    date          DATETIME NOT NULL,
    CONSTRAINT SALERECORD_PK PRIMARY KEY (saleRecordID),
    CONSTRAINT SALERECORD_FK1 FOREIGN KEY (productID) REFERENCES product (productID),
    CONSTRAINT SALERECORD_FK2 FOREIGN KEY (storeID) REFERENCES store (storeID)
);

CREATE TABLE dailySaleAmount
(
    dailySaleAmountID INT   NOT NULL AUTO_INCREMENT,
    date              DATE  NOT NULL,
    amount            FLOAT NOT NULL,
    storeID           INT   NOT NULL,
    CONSTRAINT DAILYSALEAMOUNT_PK PRIMARY KEY (dailySaleAmountID)
);

CREATE TABLE monthlySaleAmount
(
    monthlySaleAmountID INT         NOT NULL AUTO_INCREMENT,
    yearMonth           VARCHAR(30) NOT NULL,
    amount              FLOAT       NOT NULL,
    storeID             INT         NOT NULL,
    CONSTRAINT MONTHLYSALEAMOUNT_PK PRIMARY KEY (monthlySaleAmountID)
);

INSERT INTO store (storeID, address) VALUE (1, 'zhongshan');
INSERT INTO store (storeID, address) VALUE (2, 'zhongshan');
INSERT INTO store (storeID, address) VALUE (3, 'zhongshan');


INSERT INTO user(name, storeID, position, username, password) VALUE (NULL, 1, 'boss', 'Kenway', '123456');
INSERT INTO user(name, storeID, position, username, password) VALUE (NULL, 1, 'boss', 'ZZR', '123456');
INSERT INTO user(name, storeID, position, username, password) VALUE (NULL, 1, 'boss', 'CHB', '123456');

INSERT INTO user(name, storeID, position, username, password) VALUE (NULL, 1, 'manager', 'test', 'test');
INSERT INTO user(name, storeID, position, username, password) VALUE (NULL, 1, 'staff', 'test1', 'test');
INSERT INTO user(name, storeID, position, username, password) VALUE (NULL, 1, 'staff', 'test2', 'test');
INSERT INTO user(name, storeID, position, username, password) VALUE (NULL, 2, 'manager', 'test3', 'test');
INSERT INTO user(name, storeID, position, username, password) VALUE (NULL, 2, 'staff', 'test4', 'test');
INSERT INTO user(name, storeID, position, username, password) VALUE (NULL, 2, 'staff', 'test5', 'test');

INSERT INTO product (productName, productPrice) VALUE ('product1', 20);
INSERT INTO product (productName, productPrice) VALUE ('product2', 20);
INSERT INTO product (productName, productPrice) VALUE ('product3', 20);
INSERT INTO product (productName, productPrice) VALUE ('product4', 20);

INSERT INTO material (materialName, price) VALUE ('masterial1', 20);
INSERT INTO material (materialName, price) VALUE ('masterial2', 20);
INSERT INTO material (materialName, price) VALUE ('masterial3', 20);
INSERT INTO material (materialName, price) VALUE ('masterial4', 20);

INSERT INTO saleRecord (productID, productNum, totalPrice, storeID, date) VALUE (1, 10, 200, 1, now());
INSERT INTO saleRecord (productID, productNum, totalPrice, storeID, date) VALUE (1, 10, 200, 1, now());
INSERT INTO saleRecord (productID, productNum, totalPrice, storeID, date) VALUE (1, 10, 200, 1, now());
INSERT INTO saleRecord (productID, productNum, totalPrice, storeID, date) VALUE (1, 10, 200, 1, now());
INSERT INTO saleRecord (productID, productNum, totalPrice, storeID, date) VALUE (1, 10, 200, 2, now());
INSERT INTO saleRecord (productID, productNum, totalPrice, storeID, date) VALUE (2, 10, 200, 2, now());
INSERT INTO saleRecord (productID, productNum, totalPrice, storeID, date) VALUE (2, 10, 200, 2, now());
INSERT INTO saleRecord (productID, productNum, totalPrice, storeID, date) VALUE (2, 10, 200, 2, now());

INSERT INTO purchaseRecord (materialID, number, totalPrice, date) VALUE (1, 10, 200, now());
INSERT INTO purchaseRecord (materialID, number, totalPrice, date) VALUE (1, 10, 200, now());
INSERT INTO purchaseRecord (materialID, number, totalPrice, date) VALUE (1, 10, 200, now());
INSERT INTO purchaseRecord (materialID, number, totalPrice, date) VALUE (1, 10, 200, now());

INSERT INTO dailySaleAmount (date, amount, storeID) VALUE (now(), 1000, 1);
INSERT INTO dailySaleAmount (date, amount, storeID) VALUE (now(), 1000, 2);
INSERT INTO dailySaleAmount (date, amount, storeID) VALUE (now(), 1000, 3);

INSERT INTO monthlySaleAmount (yearMonth, amount, storeID) VALUE ('2020-6', 20000, 1);
INSERT INTO monthlySaleAmount (yearMonth, amount, storeID) VALUE ('2020-6', 20000, 2);
INSERT INTO monthlySaleAmount (yearMonth, amount, storeID) VALUE ('2020-6', 20000, 3);

