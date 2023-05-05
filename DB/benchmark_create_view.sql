-- Copyright Data Geekery GmbH
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- modificato da me solo nelle dichiarazioni e nel fetch per addarlo alle mie query
CREATE TABLE IF NOT EXISTS print (text VARCHAR(500));

delimiter //

CREATE PROCEDURE benchmark ()
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE v_ts BIGINT;
  DECLARE v_repeat INT DEFAULT 10000;
  DECLARE r, c INT;
  DECLARE idAsta INT;
  DECLARE idVincitore INT;
  DECLARE prezzoFinale INT;
  DECLARE spedizione varchar(50);

  DECLARE cur1 CURSOR FOR
    SELECT A.id, O.idCreatore, O.prezzoOfferto, U.indirizzoSpedizione
    FROM aste A left join offerte O on A.id = O.idAsta
            left join utenti U on O.idCreatore = U.id
    WHERE A.closed = true AND (O.prezzoOfferto = (SELECT MAX(prezzoOfferto)
                                             FROM offerte
                                             WHERE A.id = idAsta)
                                OR O.prezzoOfferto IS NULL
                            );

  DECLARE cur2 CURSOR FOR
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
                                           FROM offerte);

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  SET r = 0;

  REPEAT
    SET v_ts = ROUND(UNIX_TIMESTAMP(CURRENT_TIMESTAMP(4)) * 1000);
    SET c = 0;
    REPEAT
      OPEN cur1;

      read_loop: LOOP
        FETCH cur1 INTO idAsta, idVincitore, prezzoFinale, spedizione;
        IF done THEN
          LEAVE read_loop;
        END IF;
	  END LOOP;

      CLOSE cur1;
      SET c = c + 1;
    UNTIL c >= v_repeat END REPEAT;

    INSERT INTO print VALUES (CONCAT('Run ', r, ', Statement 1 : ', ROUND(UNIX_TIMESTAMP(CURRENT_TIMESTAMP(4)) * 1000) - v_ts));

    SET v_ts = ROUND(UNIX_TIMESTAMP(CURRENT_TIMESTAMP(4)) * 1000);
    SET c = 0;
    REPEAT
      OPEN cur2;

      read_loop: LOOP
        FETCH cur2 INTO idAsta, idVincitore, prezzoFinale, spedizione;
        IF done THEN
          LEAVE read_loop;
        END IF;
	  END LOOP;

      CLOSE cur2;
      SET c = c + 1;
    UNTIL c >= v_repeat END REPEAT;

    INSERT INTO print VALUES (CONCAT('Run ', r, ', Statement 2 : ', ROUND(UNIX_TIMESTAMP(CURRENT_TIMESTAMP(4)) * 1000) - v_ts));

    SET r = r + 1;
  UNTIL r >= 5 END REPEAT;
END//

delimiter ;

CALL benchmark();

SELECT text
FROM print
UNION ALL
SELECT null
UNION ALL
SELECT 'Copyright Data Geekery GmbH'
UNION ALL
SELECT 'https://www.jooq.org/benchmark';

DROP PROCEDURE benchmark;

DROP TABLE print;