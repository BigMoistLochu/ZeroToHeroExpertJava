-- Wstawianie rekordów do tabeli _user
INSERT INTO _user (email, lastname, password, permission, username) VALUES
                                                                        ('admin@example.com', 'Smith', 'adminpassword', 'ADMIN', 'admin'),
                                                                        ('mod1@example.com', 'Johnson', 'modpassword1', 'MODERATOR', 'moderator1'),
                                                                        ('mod2@example.com', 'Williams', 'modpassword2', 'MODERATOR', 'moderator2'),
                                                                        ('user1@example.com', 'Jones', 'userpassword1', 'USER', 'user1'),
                                                                        ('user2@example.com', 'Brown', 'userpassword2', 'USER', 'user2');

-- Wstawianie rekordów do tabeli token
INSERT INTO token (created_time, expired_time, token_cid, user_id) VALUES
                                                                       (NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 'token1', 1),
                                                                       (NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 'token2', 2),
                                                                       (NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 'token3', 3),
                                                                       (NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 'token4', 4),
                                                                       (NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 'token5', 5);