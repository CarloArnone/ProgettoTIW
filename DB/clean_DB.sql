CREATE VIEW asteNonEsistenti(id) AS (SELECT distinct id
                              FROM aste
                              WHERE id NOT IN (
                                  SELECT distinct idAsta
                                  FROM articoli
                                  WHERE idAsta IS NOT NULL
                                  ));


DELETE FROM aste WHERE id IN (SELECT id
                              FROM asteNonEsistenti);

DROP VIEW asteNonEsistenti;

CREATE VIEW prezzoCorretto(idAsta, startinPrice) AS (
                                                    SELECT idAsta, SUM(prezzo)
                                                    FROM articoli
                                                    WHERE idAsta IS NOT NULL
                                                    GROUP BY idAsta
                                                    );


UPDATE aste A,
    (SELECT idAsta, startinPrice
    FROM prezzoCorretto) pc
SET prezzoIniziale = pc.startinPrice
WHERE A.id = pc.idAsta;

DROP VIEW prezzoCorretto;