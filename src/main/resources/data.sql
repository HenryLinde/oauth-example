DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    userid VARCHAR(5) PRIMARY KEY,
    username VARCHAR(45),
    password VARCHAR(60),
    enabled TINYINT NOT NULL DEFAULT 1
);

CREATE TABLE user_roles (
    user_role_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username varchar(45) NOT NULL UNIQUE KEY,
    role varchar(45) NOT NULL
);

SELECT 
    *
FROM
    users;

INSERT INTO users(userid, username,password,enabled) VALUES ('001', 'test' ,'$2a$04$UeUNPvHapZ/d6/EU0pcEQe.jHQz.DUWBznMhVLEhoGOUrYK5LNxLW', true);

INSERT INTO user_roles (user_role_id, username, role) VALUES ('001', 'test', 'ROLE_USER');
