/**
 * @Author: cooocy
 * @Date: 2018/6/26 14:22
 **/
drop table tbl_user if exists;
create table tbl_user (
  id serial primary key,
  mid int not null,
  name varchar(200),
  sex varchar(200),
  face varchar(2000),
  reg_time date,
  birthday date,
  sign varchar(200),
  current_level int,
  vip_type int,
  vip_status int,
  coins int,
  whisper int,
  following int,
  follower int,
  black int,
  archive_view int,
  article_view int,
  create_time timestamp
)