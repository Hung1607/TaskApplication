alter table task
drop column status;

alter table task
add column status SMALLINT default '2' NOT NULL