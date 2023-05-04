create table dbprogetto.articoli
(
    id          int          not null
        primary key,
    idCreatore  varchar(50)  not null,
    nome        varchar(50)  not null,
    descrizione varchar(255) null,
    immagine    varchar(255) not null,
    idAsta      varchar(50)  null,
    prezzo      int          not null
);

