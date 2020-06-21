USE DATABASE yhtSaleSystem

CREATE TABLE store
(
    storeID       INT          NOT NULL AUTO_INCREMENT,
    address       VARCHAR(100) NOT NULL,
    dayTurnover   FLOAT        NULL,
    monthTurnover FLOAT        NULL,
    CONSTRAINT STORE_PK PRIMARY KEY (storeID)
);

CREATE TABLE user
(
    userID        INT          NOT NULL AUTO_INCREMENT,
    workNumber    INT          NULL,
    name          VARCHAR(30)  NOT NULL,
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
    productID        INT          NOT NULL AUTO_INCREMENT,
    productName      VARCHAR(50)  NOT NULL UNIQUE ,
    productPrice     FLOAT        NOT NULL,
    CONSTRAINT PRODUCT_PK PRIMARY KEY (productID)
);

CREATE TABLE material
(
    materialID   INT         NOT NULL AUTO_INCREMENT,
    materialName VARCHAR(30) NOT NULL,
    CONSTRAINT MATERIAL_PK PRIMARY KEY (materialID)
);

CREATE TABLE purchaseRecord
(
    purchaseRecordID INT         NOT NULL AUTO_INCREMENT,
    totalPrice       FLOAT       NOT NULL,
    date             DATE NOT NULL,
    CONSTRAINT PURCHASERECORD_PK PRIMARY KEY (purchaseRecordID)
);

CREATE TABLE subPurchaseRecord
(
    recordID         INT   NOT NULL AUTO_INCREMENT,
    purchaseRecordID INT   NOT NULL,
    materialID       INT   NOT NULL,
    number           INT   NOT NULL,
    price            FLOAT Not Null,
    CONSTRAINT SUBPURCHASERECORD_PK PRIMARY KEY (recordID),
    CONSTRAINT PURCHASERECORD_FK FOREIGN KEY (purchaseRecordID) REFERENCES purchaseRecord (purchaseRecordID),
    CONSTRAINT MATERIAL_FK FOREIGN KEY (materialID) REFERENCES material (materialID)
);

CREATE TABLE saleRecord
(
    saleRecordID INT      NOT NULL AUTO_INCREMENT,
    totalPrice   FLOAT    NOT NULL,
    date         DATETIME NOT NULL,
    CONSTRAINT SALERECORD_PK PRIMARY KEY (saleRecordID)
);

CREATE TABLE subSaleRecord
(
    subSaleRecordID INT   NOT NULL AUTO_INCREMENT,
    saleRecordID    INT   NOT NULL,
    productID       INT   NOT NULL,
    productNum      INT   NOT NULL,
    price           FLOAT NOT NULL,
    CONSTRAINT SUBSALERECPRD_PK PRIMARY KEY (subSaleRecordID),
    CONSTRAINT SALERECORD_FK FOREIGN KEY (saleRecordID) REFERENCES saleRecord (saleRecordID),
    CONSTRAINT PRODUCT_FK FOREIGN KEY (productID) REFERENCES product (productID)
);

CREATE TABLE dailySaleAmount
(
    dailySaleAmountID INT         NOT NULL AUTO_INCREMENT,
    date              DATE NOT NULL,
    amount            FLOAT       NOT NULL,
    CONSTRAINT DAILYSALEAMOUNT_PK PRIMARY KEY (dailySaleAmountID)
);

CREATE TABLE monthlySaleAmount
(
    monthlySaleAmountID INT         NOT NULL AUTO_INCREMENT,
    yearMonth           VARCHAR(30) NOT NULL,
    amount              FLOAT       NOT NULL,
    CONSTRAINT MONTHLYSALEAMOUNT_PK PRIMARY KEY (monthlySaleAmountID)
);

SELECT password
FROM user
WHERE username =;

INSERT INTO user(workNumber, name, username, password) VALUE (NULL, 'Kenway', 'Kenway', '123456');

INSERT INTO user(workNumber, name, storeID, username, password) VALUE ( NULL, '张三', NULL, 'zhangsan@hotmail.com', '123456' );

SELECT workNumber, name, position, monthWorkTime, monthSalary FROM user WHERE storeID = 123;

SELECT productID, productName, productPrice FROM product;

INSERT INTO product(productName, productPrice) VALUE (product.getProductName, product.getProductPrice);

DELETE FROM product WHERE productID = product.productID;