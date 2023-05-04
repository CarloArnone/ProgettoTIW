create table dbprogetto.aste
(
    id             varchar(50) not null
        primary key,
    idCreatore     varchar(50) not null,
    prezzoIniziale int         not null,
    rialzoMinimo   int         not null,
    dataTermine    datetime    not null
);

