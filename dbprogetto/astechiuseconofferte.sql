create definer = carlo@`%` view dbprogetto.astechiuseconofferte as
select `o`.`idAsta` AS `idAsta`, `o`.`idCreatore` AS `idCreatore`, `o`.`prezzoOfferto` AS `prezzoOfferto`
from (`dbprogetto`.`offerte` `o` join `dbprogetto`.`aste` `a` on ((`o`.`idAsta` = `a`.`id`)))
where (`a`.`dataTermine` <= now());

