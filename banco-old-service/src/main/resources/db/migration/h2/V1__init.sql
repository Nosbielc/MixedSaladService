 create sequence CDS_SEQ;
 create sequence CS_SEQ;
 create sequence HIBERNATE_SEQUENCE;

 create table User (
     id bigint auto_increment not null,
     name varchar(255) not null,
     email varchar(255) not null,
     primary key (id)
 );


