create table token_seguranca (
   id bigint auto_increment not null,
   str_token varchar(255) not null,
   str_conta varchar(255) not null,
   banco_id bigint not null,
   primary key (id)
);

alter table token_seguranca add constraint FK_BANCO_TOKEN_SEGURANCA foreign key (banco_id) references banco;
