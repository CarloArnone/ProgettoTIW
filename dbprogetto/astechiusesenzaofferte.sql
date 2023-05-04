create definer = carlo@`%` view dbprogetto.astechiusesenzaofferte as
select `a`.`id` AS `id`
from `dbprogetto`.`aste` `a`
where (`a`.`dataTermine` <= now());

