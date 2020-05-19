-- DATABASE

CREATE DATABASE tracker;


-- TABLES

CREATE TABLE Items (
	id serial primary key,
	name varchar(500)
);