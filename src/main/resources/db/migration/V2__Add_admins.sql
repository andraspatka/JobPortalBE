insert into users(first_name, last_name, role, email, password)
values ('Jobportal','Admin',2,'job.portal@gmail.com','$2a$10$k6dWXP82L3f1dp6//Zo9nOWoxV/Qxb3QtzehKaBrW7LuK6Bu1SNCu'),
       ('Jobportal','Employer',0,'job.employer@gmail.com','$2a$10$k6dWXP82L3f1dp6//Zo9nOWoxV/Qxb3QtzehKaBrW7LuK6Bu1SNCu');

insert into company(name, admin_id) values ('Bosch', 1),('Ntt Data', 2);

update users set company=1 where id=1;
update users set company=2 where id=2;