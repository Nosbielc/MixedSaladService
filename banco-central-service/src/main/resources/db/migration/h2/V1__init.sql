 create sequence CDS_SEQ;
 create sequence CS_SEQ;
 create sequence HIBERNATE_SEQUENCE;

 create table banco (
     id bigint auto_increment not null,
     cod_banco bigint not null,
     str_nome_base varchar(255) not null,
     str_nome varchar(255) not null,
     is_ativo integer not null,
     primary key (id)
 );

 create table transferencia (
    id bigint auto_increment not null,
    str_autenticacao varchar(255) not null,
    vlr_transferencia DECIMAL(20, 2) not null,
    dt_transferencia timestamp not null,
    banco_origem_id bigint not null,
    str_conta_origem varchar(255) not null,
    transferencia_status integer not null,
    banco_destino_id bigint not null,
    str_conta_destino varchar(255) not null,
    primary key (id)
 );

 alter table transferencia add constraint FK_BANCO_ORIGEM foreign key (banco_origem_id) references banco;
 alter table transferencia add constraint FK_BANCO_DESTINO foreign key (banco_destino_id) references banco;


