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




[COMMENT se voglio mostrare che se non ci sono offerte il prezzo massimo ragginto sia 0 nelle aste aperte]

CREATE VIEW asteAperte(idAsta, prezzoMassimoRaggiunto, rialzoMinimo, dataScadenza, oreRimanenti) AS (
    SELECT A.id, 0, A.rialzoMinimo, A.dataTermine, TIMESTAMPDIFF(HOUR, A.dataTermine, U.lastLogin )
    FROM aste A join utenti U on idCreatore = U.id
    WHERE closed = false AND A.id NOT IN (SELECT distinct idAsta
                                          FROM offerte)

    UNION

    SELECT A.id, O.prezzoOfferto, A.rialzoMinimo, A.dataTermine, TIMESTAMPDIFF(HOUR, A.dataTermine, U.lastLogin )
    FROM aste A join offerte O on A.id = O.idAsta
                left join utenti U on U.id = O.idCreatore
    WHERE closed = false AND O.prezzoOfferto = (SELECT MAX(prezzoOfferto)
                                             FROM offerte O1
                                             WHERE A.id = O1.idAsta)
);





[COMMENT se voglio far vedere che non c'è nessuna offerta ancora in un'asta aperta rappresentando prezzoMassimoRaggiunto = null]

CREATE VIEW asteAperte(idAsta, prezzoMassimoRaggiunto, rialzoMinimo, dataScadenza, oreRimanenti) AS (

    SELECT A.id, O.prezzoOfferto, A.rialzoMinimo, A.dataTermine, TIMESTAMPDIFF(HOUR , U.lastLogin, A.dataTermine)
    FROM aste A left join offerte O on A.id = O.idAsta
                left join utenti U on U.id = O.idCreatore
    WHERE A.closed = false AND (O.prezzoOfferto = (SELECT MAX(prezzoOfferto)
                                             FROM offerte O1
                                             WHERE A.id = O1.idAsta)
                                OR O.prezzoOfferto IS NULL
                            )
);



[COMMENT ritorna prezzoOfferto = null se non ci sono offerte per quell'asta]


CREATE VIEW asteChiuse(idAsta, idVincitore, prezzoFinale, indSpedizione) AS (
SELECT A.id, O.idCreatore, O.prezzoOfferto, U.indirizzoSpedizione
FROM aste A left join offerte O on A.id = O.idAsta
            left join utenti U on O.idCreatore = U.id
WHERE A.closed = true AND (O.prezzoOfferto = (SELECT MAX(prezzoOfferto)
                                             FROM offerte
                                             WHERE A.id = idAsta)
                                OR O.prezzoOfferto IS NULL
                            )
                                                                            );

[COMMENT se voglio ritornare lalalandia come indirizzo di spedizione se non ci sono offerte]

CREATE VIEW asteChiuse(idAsta, idVincitore, prezzoFinale, indSpedizione) AS (
SELECT A.id, O.idCreatore, O.prezzoOfferto, U.indirizzoSpedizione
FROM aste A join offerte O on A.id = O.idAsta
            left join utenti U on O.idCreatore = U.id
WHERE A.closed = true AND O.prezzoOfferto = (SELECT MAX(prezzoOfferto)
                                             FROM offerte O1
                                             WHERE A.id = O1.idAsta)

UNION

SELECT A.id, 0, 0, 'lalaLandia'
FROM aste A
WHERE A.closed = true AND A.id NOT IN (SELECT idAsta
                                       FROM offerte)
                                                                            );