CREATE TABLE data (
   id BIGINT NOT NULL,
   name VARCHAR(255) NULL,
   gender VARCHAR(255) NULL,
   level VARCHAR(255) NULL,
   micro_area VARCHAR(255) NULL,
   care_info INT NULL,
   pa INT NULL,
   CONSTRAINT pk_data PRIMARY KEY (id)
);