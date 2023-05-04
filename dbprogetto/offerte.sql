create table dbprogetto.offerte
(
    idCreatore    varchar(50) not null,
    idAsta        varchar(50) not null,
    prezzoOfferto int         not null,
    dataOfferta   datetime    not null,
    primary key (idCreatore, idAsta, dataOfferta)
);

