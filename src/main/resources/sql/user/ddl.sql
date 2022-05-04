DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id varchar(10) primary key,
    name varchar(20) not null,
    password varchar(10) not null,
    level tinyint not null,
    login int not null,
    recommend int not null,
    email varchar(30) not null
)
