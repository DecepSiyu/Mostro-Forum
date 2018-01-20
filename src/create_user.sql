create schema web_routine;
use web_routine;
CREATE TABLE usr_info (
    usrname CHAR(20) PRIMARY KEY,
    sex CHAR(2),
    birthday DATE,
    email CHAR(25),
    passwd CHAR(20) NOT NULL,
    is_admin BOOL NOT NULL
);

CREATE TABLE plate_info (
    plate_id CHAR(10) PRIMARY KEY,
    name CHAR(10) NOT NULL
);

CREATE TABLE post_info (
    post_id CHAR(10) PRIMARY KEY,
    title CHAR(50) NOT NULL,
    content TEXT NOT NULL,
    auther CHAR(20) REFERENCES usr_info.usrname,
    plate_id CHAR(10) REFERENCES plate_info.plate_id,
    publish_time DATE NOT NULL
);

insert into usr_info values('mostro','男','1997-05-15','616040809@qq.com','123',true);
insert into plate_info values('0000000000','深度学习');
insert into plate_info values('0000000001','计算机视觉');
insert into usr_info values('bright','男','1997-05-15','bright196@163.com','123',false);

INSERT INTO `web_routine`.`post_info` (`post_id`, `publish_time`,`auther`,`title`, `content`, `plate_id`) VALUES ('0000000000','2018-01-17', 'mostro','深度学习目前主要有哪些研究方向?', '当深度学习进入自然语言时，如果还是像语音识别、图像处理那样从零知识开始做特征学习，相当于将丰富的语言知识弃之不用而另起炉灶，是不符合自然语言处理特点的。所以，深度学习的一个可能重要的发展方向是，如何在深度学习框架中高效地融合人们已经构建出来的丰富先验知识（包括语言知识、世界知识）。', '0000000000');

CREATE USER 'mostro' IDENTIFIED BY 'mostro';

GRANT all privileges ON `web_routine`.* TO 'mostro';

DROP USER 'mostro';

drop table usr_info;
drop table plate_info;
drop table post_info;

drop schema web_routine