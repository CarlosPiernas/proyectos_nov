/*Creación de rol administrador*/
CREATE ROLE administrador WITH LOGIN PASSWORD '1234';

/*Creación de rol empleado*/
CREATE ROLE empleado WITH LOGIN PASSWORD '5678';

/*Otorgar permisos para administrador*/
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO administrador;

/*Otorgar permisos para empleado*/
GRANT SELECT ON ALL TABLES IN SCHEMA public TO empleado;
REVOKE INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public FROM empleado;