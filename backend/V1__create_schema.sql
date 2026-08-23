CREATE TABLE address (
     id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     street     VARCHAR(255),
     number     VARCHAR(20),
     district   VARCHAR(255),
     city       VARCHAR(255),
     is_active    BOOLEAN      NOT NULL DEFAULT TRUE,
     created_at TIMESTAMP NOT NULL,
     updated_at TIMESTAMP
);

CREATE TABLE customer (
     id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     name         VARCHAR(255) NOT NULL ,
     cpf          VARCHAR(14)  NOT NULL,
     phone_number VARCHAR(20)  NOT NULL,
     email        VARCHAR(255) NOT NULL,
     address_id   BIGINT,
     created_at   TIMESTAMP    NOT NULL,
     updated_at   TIMESTAMP,
     is_active    BOOLEAN      NOT NULL DEFAULT TRUE,
     CONSTRAINT uk_customer_cpf UNIQUE (cpf),
     CONSTRAINT uk_customer_phone_number UNIQUE (phone_number),
     CONSTRAINT uk_customer_email UNIQUE (email),
     CONSTRAINT uk_customer_address_id UNIQUE (address_id),
     CONSTRAINT fk_customer_address FOREIGN KEY (address_id) REFERENCES address (id)
);


CREATE TABLE orders (
     id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     order_code  VARCHAR(36) 	NOT NULL,
     customer_id BIGINT 	    	NOT NULL,
     seller_id   BIGINT 	    	NOT NULL,
     amount      DECIMAL(19, 2)	NULL,
     status      VARCHAR(20)	NOT NULL,
     is_active    BOOLEAN      NOT NULL DEFAULT TRUE,
     created_at  TIMESTAMP 	NOT NULL,
     updated_at  TIMESTAMP,
     CONSTRAINT uk_orders_order_code UNIQUE (order_code),
     CONSTRAINT fk_orders_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE item_order (
     id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     product_id BIGINT         NOT NULL,
     quantity   INT            NOT NULL,
     sub_amount DECIMAL(19, 2) NOT NULL,
     order_id   BIGINT         NOT NULL,
     created_at TIMESTAMP      NOT NULL,
     updated_at TIMESTAMP,
     is_active    BOOLEAN      NOT NULL DEFAULT TRUE,
     CONSTRAINT fk_item_order_order FOREIGN KEY (order_id) REFERENCES orders (id)
);