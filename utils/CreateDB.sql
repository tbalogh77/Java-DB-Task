DROP   DATABASE Orders ;

CREATE DATABASE Orders ;
CREATE USER 'Abigel'@'localhost' IDENTIFIED BY 'password'; 
GRANT  ALL PRIVILEGES ON * . * TO 'Abigel'@'localhost'; 
GRANT  ALL ON Orders.* TO 'Abigel'@'localhost' IDENTIFIED BY 'password' WITH GRANT OPTION; 
FLUSH  PRIVILEGES;

CREATE TABLE Orders.order( 
	OrderId INT NOT NULL AUTO_INCREMENT, 
	BuyerName VARCHAR(256),
	BuyerEmail VARCHAR(256),
	OrderTotalValue INT,
	Address VARCHAR(256),
	OrderDate DATE, 
	PRIMARY KEY ( OrderId ) 
);


CREATE TABLE Orders.order_item( 
	OrderItemId INT NOT NULL AUTO_INCREMENT, 
	OrderId INT,
	SalePrice INT,
	ShippingPrice INT,
	TotalItemPrice INT,
	SKU VARCHAR(256),
	Status ENUM('IN_STOCK', 'OUT_OF_STOCK'),
	PRIMARY KEY ( OrderItemId ),
	FOREIGN KEY (OrderId) REFERENCES Orders.order(OrderId)
);

show    databases ;
connect Orders ;
show    tables;



