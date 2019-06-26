INSERT INTO `user` VALUES (1,'2019-04-03 09:07:42','admin','admin','$2a$04$LFBb3c3gJeXritB4VjZ4F.ak96N1kTWarCazM10JASQdiV1g5ZLNi','000000000','admin@gmail.com');

INSERT INTO `role` VALUES (2,'ADMIN');

INSERT INTO `privilege` VALUES
(1,'READ_ADMIN'),
(2,'CREATE_ADMIN'),
(3,'UPDATE_ADMIN'),
(4,'DELETE_ADMIN'),
(5,'READ_SALE'),
(6,'MAKE_SALE'),
(7,'READ_ITERM'),
(8,'CREATE_ITERM'),
(9,'UPDATE_ITERM'),
(10,'DELETE_ITERM'),
(11,'READ_ROLE'),
(12,'CREATE_ROLE'),
(13,'DELETE_ROLE'),
(14,'UPDATE_ROLE'),
(15,'READ_PRIVILEGE');


INSERT INTO `roles_privileges` VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15);

INSERT INTO `users_roles` VALUES (1,2);

INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1),(1),(1),(1);


