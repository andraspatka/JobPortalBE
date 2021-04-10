alter table request add column status int;
update request set status = 0 where id is not null;