CREATE TABLE bolt_user
(
  id SERIAL NOT NULL,
  email varchar(100) NOT NULL,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  points bigint NOT NULL DEFAULT 0,
  CONSTRAINT "PKey" PRIMARY KEY (id)
);
