# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `posts` (
  `pno` int NOT NULL AUTO_INCREMENT,
  `bid` int NOT NULL,
  `uid` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `heart` int default 0,
  `img` varchar(1000),
  `create_date` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`pno`),
  KEY `post_to_user_fk` (`uid`),
  KEY `post_to_blog_fk` (`bid`),
  CONSTRAINT `post_to_user_fk` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) on delete cascade,
  CONSTRAINT `post_to_blog_fk` FOREIGN KEY (`bid`) REFERENCES `blog` (`bid`) on delete cascade 
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;


select * from posts;

insert into posts(pno, uid, title, content, heart) values(1, 1, "test", "test", 0);


CREATE TABLE reply (
  `rno` int NOT NULL AUTO_INCREMENT,
  `pno` int NOT NULL,
  `replytext` varchar(1000) NOT NULL,
  `replyer` varchar(50) NOT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (rno),
  CONSTRAINT fk_post FOREIGN KEY (pno) REFERENCES posts(pno) on delete cascade
);

SELECT * FROM users;

CREATE TABLE `roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'role id',
  `name` VARCHAR(100) NOT NULL COMMENT 'start with ROLE_',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

SELECT * FROM roles;

CREATE TABLE `user_roles` (
  `user_id` INT,
  `role_id` INT,
  PRIMARY KEY (`user_id`, `role_id`),
  FOREIGN KEY (`user_id`) REFERENCES users(`uid`) on delete cascade,
  FOREIGN KEY (`role_id`) REFERENCES roles(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user_roles(`user_id`, `role_id`) VALUES(1, 1);
INSERT INTO user_roles(`user_id`, `role_id`) VALUES(1, 2);
INSERT INTO user_roles(`user_id`, `role_id`) VALUES(1, 3);

INSERT INTO user_roles(`user_id`, `role_id`) VALUES(2, 1);

SELECT * FROM user_roles;

CREATE TABLE blog (
  `bid` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `blogname` varchar(100) NOT NULL,
  `template_num` int NOT NULL,
  `visitors_num` int default 0,
  PRIMARY KEY (bid),
  CONSTRAINT fk_users FOREIGN KEY (uid) REFERENCES users(uid) on delete cascade
);

$ docker run -p 6379:6379 --name redis_boot -d redis
