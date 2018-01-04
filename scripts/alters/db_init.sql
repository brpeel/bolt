CREATE TABLE bolt_user
(
  id SERIAL NOT NULL,
  email varchar(100) NOT NULL,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  points bigint NOT NULL DEFAULT 0,
  deleted boolean NOT NULL DEFAULT False,
  CONSTRAINT "bolt_user_primary_key" PRIMARY KEY (id)
);

CREATE TABLE transfer (
  id SERIAL NOT NULL,
  user_id BIGINT NOT NULL references bolt_user(id),
  points BIGINT NOT NULL,
  CONSTRAINT "transfer_primary_key" PRIMARY KEY (id)
);