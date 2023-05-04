CREATE SCHEMA dbProgetto;

CREATE TABLE Aste
(
    id             varchar(50) primary key,
    idCreatore     varchar(50) not null references Utenti(id) on update cascade on delete cascade,
    prezzoIniziale int not null,
    rialzoMinimo   int not null,
    dataTermine    datetime not null
);


CREATE TABLE Articoli
(
    id          int primary key,
    idCreatore  varchar(50)  not null references Utenti (id) on update cascade on delete cascade,
    nome        varchar(50)  not null,
    descrizione varchar(255),
    immagine    varchar(255) not null,
    idAsta      varchar(50) references Aste (id) on update cascade on delete cascade,
    prezzo      int          not null
);


CREATE TABLE Utenti
(
    id                  varchar(50) primary key,
    userName            varchar(50)  not null,
    passWord            varchar(100) not null,
    indirizzoSpedizione varchar(255),
    lingua              varchar(15) default 'it_IT'
);

CREATE TABLE Offerte
(
    idCreatore    varchar(50) references Utenti (id) on update cascade on delete cascade,
    idAsta        varchar(50) references Aste (id) on update cascade on delete cascade,
    prezzoOfferto int      not null,
    dataOfferta          datetime not null,
    primary key (idCreatore, idAsta, dataOfferta)
);

CREATE TABLE Messaggi
(
    id        varchar(30) primary key,
    lingua    varchar(15)  not null,
    messaggio varchar(255) not null default ''
);

CREATE VIEW AsteChiuseConOfferte(id, idVincitore, prezzoFinale) AS
(

SELECT idAsta, O.idCreatore, prezzoOfferto
FROM Offerte O
         join Aste A on O.idAsta = A.id
WHERE dataTermine <= CURRENT_TIMESTAMP

);

CREATE VIEW AsteChiuseSenzaOfferte(id) AS
(

SELECT id
FROM Aste A
WHERE dataTermine <= CURRENT_TIMESTAMP

);
