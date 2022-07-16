--INSERT INTO model (field_varchar, field_decimal, field_date) 
--VALUES ('Leon', 90.69, '2008-7-04');


-- SELECT * FROM model;

INSERT INTO contract_type (date_created,description, is_active, name) 
VALUES
('2022-07-15', 'Description', 1,'Permanent' ),
('2022-07-15', 'Description', 1,'Fixed-Term' ),
('2022-07-15', 'Description', 1,'External' );

INSERT INTO api_hr.employee (birth_date, cell_phone, date_created, email, is_active, last_name, name, tax_id_number)
VALUES
('2001-09-03', '5525490959', '2022-07-16 15:46:09', 'leonriv2@gmail.com', 1, 'Rivera', 'Leon', 'RIRL010903UC9');



