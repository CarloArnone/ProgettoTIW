create table dbprogetto.messaggi
(
    id        varchar(30)             not null
        primary key,
    lingua    varchar(15)             not null,
    messaggio varchar(255) default '' not null
);

