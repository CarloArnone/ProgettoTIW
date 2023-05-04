create table dbprogetto.utenti
(
    id                  varchar(50)                 not null
        primary key,
    userName            varchar(50)                 not null,
    passWord            varchar(100)                not null,
    indirizzoSpedizione varchar(255)                null,
    lingua              varchar(15) default 'it_IT' null
);

