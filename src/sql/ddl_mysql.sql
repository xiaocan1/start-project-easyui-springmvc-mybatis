-- SYS_GROUP
drop table IF EXISTS SYS_GROUP;
drop table IF EXISTS SYS_MENU;
drop table IF EXISTS SYS_ROLE;
drop table IF EXISTS SYS_ROLE_MENU;
drop table IF EXISTS SYS_USER;

create table SYS_GROUP
(
  GROUP_ID   int auto_increment primary key not null,
  GROUP_NAME VARCHAR(100) not null,
  GROUP_NO   VARCHAR(10),
  REMARK     VARCHAR(500),
  ADD_TIME   datetime NOT NULL DEFAULT NOW()
);
ALTER TABLE SYS_GROUP COMMENT='系统用户组表';
ALTER table SYS_GROUP MODIFY `GROUP_ID` int COMMENT '组ID';
ALTER table SYS_GROUP MODIFY `GROUP_NAME` VARCHAR(100)  COMMENT '组名';
ALTER table SYS_GROUP MODIFY `GROUP_NO` VARCHAR(10) DEFAULT NULL COMMENT '组编号';
ALTER table SYS_GROUP MODIFY `REMARK` VARCHAR(500) DEFAULT NULL COMMENT '备注';
ALTER table SYS_GROUP MODIFY `ADD_TIME` datetime  COMMENT '添加时间';

  
-- SYS_MENU
create table SYS_MENU
(
  MENU_ID        int auto_increment primary key not null,
  MENU_NAME      VARCHAR(100) not null,
  MENU_URL       VARCHAR(100),
  PARENT_MENU_ID VARCHAR(32),
  UPDATE_TIME    datetime NOT NULL DEFAULT NOW()
);

/**
comment on table SYS_MENU
  is '系统菜单表';
comment on column SYS_MENU.MENU_ID
  is 'ID、主键';
comment on column SYS_MENU.MENU_NAME
  is '菜单名';
comment on column SYS_MENU.MENU_URL
  is '菜单对应的URL';
comment on column SYS_MENU.PARENT_MENU_ID
  is '父菜单';
  
alter table SYS_MENU
  add constraint PK_SYS_MENU primary key (MENU_ID);
  */
  
-- SYS_ROLE
create table SYS_ROLE
(
  ROLE_ID     int auto_increment primary key not null,
  ROLE_NAME   VARCHAR(50),
  REMARK      VARCHAR(500),
  UPDATE_TIME datetime NOT NULL DEFAULT NOW()
);

/**
comment on table SYS_ROLE
  is '系统用户角色表';
comment on column SYS_ROLE.ROLE_ID
  is 'ID、主键';
comment on column SYS_ROLE.ROLE_NAME
  is '角色名';
comment on column SYS_ROLE.REMARK
  is '备注';
  
alter table SYS_ROLE
  add constraint PK_SYS_ROLE primary key (ROLE_ID);
  */
-- SYS_ROLE_MENU
create table SYS_ROLE_MENU
(
  ID      int auto_increment primary key not null,
  ROLE_ID VARCHAR(32),
  MENU_ID VARCHAR(32)
);

/**
comment on table SYS_ROLE_MENU
  is '角色菜单表';
comment on column SYS_ROLE_MENU.ID
  is '主键';
comment on column SYS_ROLE_MENU.ROLE_ID
  is '角色ID，对应角色表';
comment on column SYS_ROLE_MENU.MENU_ID
  is '菜单ID，对应菜单表';

alter table SYS_ROLE_MENU
  add constraint PK_SYS_ROLE_MENU primary key (ID);
*/

-- SYS_USER
create table SYS_USER
(
  USER_ID  int auto_increment primary key not null,
  USERNAME VARCHAR(50),
  PASSWORD VARCHAR(40),
  NAME     VARCHAR(50),
  ROLE_ID  VARCHAR(32),
  GROUP_ID VARCHAR(32),
  ADD_TIME datetime NOT NULL DEFAULT NOW(),
	UNIQUE KEY `IDX_SYS_USER_UNI_USERNAME` (`USERNAME`)
);

/**
comment on table SYS_USER
  is '系统用户表';
comment on column SYS_USER.USER_ID
  is 'ID、主键';
comment on column SYS_USER.USERNAME
  is '用户名、登录名';
comment on column SYS_USER.PASSWORD
  is '密码（MD5存储）';
comment on column SYS_USER.NAME
  is '姓名';
comment on column SYS_USER.ROLE_ID
  is '角色ID';
comment on column SYS_USER.GROUP_ID
  is '组ID（可作为部门标识）';
comment on column SYS_USER.ADD_TIME
  is '添加时间';

alter table SYS_USER
  add constraint PK_SYS_USER primary key (USER_ID);
alter table SYS_USER
  add constraint IDX_SYS_USER_UNI_USERNAME unique (USERNAME);
*/