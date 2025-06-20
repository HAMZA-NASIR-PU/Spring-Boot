
-- -- password is abc123
SELECT * FROM security_db.roles;
INSERT INTO security_db.roles(name)
VALUES("ROLE_USER");

-- password is abc123
SELECT * FROM security_db.users;
INSERT INTO security_db.users(email, name, password, username)
VALUES('doe@email.com', 'Doe', "$2a$12$qg5e.jCQlRTZcnO42sD.H.knz8SAZQ3lhGMV2QUQUeKspM2NiZDNG", "doe");

INSERT INTO security_db.users_roles
VALUES(2, 2);