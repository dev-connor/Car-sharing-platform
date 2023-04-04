insert into vehicle.user (username, password, nickname, activated) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin',true);
insert into vehicle.user (username, password, nickname, activated) values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user',true);

insert into vehicle.authority (authority_name) values ('ROLE_USER');
insert into vehicle.authority (authority_name) values ('ROLE_ADMIN');

insert into vehicle.user_authority (user_id, authority_name) values (1, 'ROLE_USER');
insert into vehicle.user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
insert into vehicle.user_authority (user_id, authority_name) values (2, 'ROLE_USER');