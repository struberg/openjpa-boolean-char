-- first step is to delete all tables
DROP TABLE CUSTOMER IF EXISTS;
DROP TABLE OPENJPA_SEQUENCE_TABLE IF EXISTS;


-- now create all the tables again

CREATE TABLE CUSTOMER
(
    ID BIGINT PRIMARY KEY NOT NULL,
    ACTIVE VARCHAR(1), -- attention, default is BOOLEAN. This is exactly our special problem!
    NAME VARCHAR(255)
);

CREATE TABLE OPENJPA_SEQUENCE_TABLE
(
    ID TINYINT PRIMARY KEY NOT NULL,
    SEQUENCE_VALUE BIGINT
);


insert into CUSTOMER (ID, ACTIVE, NAME) VALUES (1, '1', 'Hans');
insert into CUSTOMER (ID, ACTIVE, NAME) VALUES (2, '1', 'Karl');
insert into CUSTOMER (ID, ACTIVE, NAME) VALUES (3, '1', 'John');

-- for keeping it easier all our inactive customers are women
insert into CUSTOMER (ID, ACTIVE, NAME) VALUES (10, '0', 'Lydia');
insert into CUSTOMER (ID, ACTIVE, NAME) VALUES (11, '0', 'Maria');
