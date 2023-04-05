insert into membership.user (username, password, nickname, activated) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin',true);
insert into membership.user (username, password, nickname, activated) values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user',true);

insert into membership.authority (authority_name) values ('ROLE_USER');
insert into membership.authority (authority_name) values ('ROLE_ADMIN');

insert into membership.user_authority (user_id, authority_name) values (1, 'ROLE_USER');
insert into membership.user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
insert into membership.user_authority (user_id, authority_name) values (2, 'ROLE_USER');

-- partner
insert into partner.tbl_partner (organization_name, business_number, industry_type_code, business_type_code, partner_email, partner_phone_number, representative_name, representative_phone_number, used_yn, service_id, created_by, updated_by, version) values ('42dot', '7348601407', 'NDST00000M', 'FCLT075999', 'jaesik.lee@42dot.ai', '01046942399', '송창현', '02', 'Y', 'O19anP0GQoaZcnK1indJpw', 'SYSTEM_ADMIN', 'SYSTEM_ADMIN', 0)