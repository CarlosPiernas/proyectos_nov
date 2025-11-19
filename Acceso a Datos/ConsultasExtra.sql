/*Ranking de clientes con más visitas*/
SELECT cl.nombre, cl.apellido,COUNT(c.id_cita) AS "Visitas totales"
FROM clientes cl 
JOIN citas c ON cl.id_cliente = c.id_cliente_clientes
GROUP BY cl.id_cliente, cl.nombre, cl.apellido
ORDER BY "Visitas totales" DESC
LIMIT 10;

/*Ranking de servicios más solicitados*/
SELECT s.nombre, COUNT(cs.id_cita_citas) AS "Solicitudes totales"
FROM servicios s
JOIN citas_servicios cs ON s.id_servicio = cs.id_servicio_servicios
GROUP BY s.nombre
ORDER BY "Solicitudes totales" DESC
LIMIT 5;

/*Ranking de dias con más citas*/
SELECT c.fecha, COUNT(c.id_cita) AS "Citas totales"
FROM citas c
GROUP BY c.fecha
ORDER BY "Citas totales" DESC
LIMIT 10;

/*Ranking de citas más caras*/
SELECT c.id_cita, CAST(c.precio as decimal (10,2)), cl.nombre, cl.apellido 
FROM citas c 
JOIN clientes cl ON c.id_cliente_clientes = cl.id_cliente
ORDER BY c.precio DESC
LIMIT 5;

/*Clientes que han pagado con tarjeta*/
SELECT distinct cl.nombre, cl.apellido, f.metodo_pago
FROM clientes cl
JOIN citas c ON c.id_cliente_clientes = cl.id_cliente
JOIN facturas f ON c.id_factura_facturas = f.id_factura
WHERE f.metodo_pago='tarjeta';

/*Clientes que han pagado con efectivo*/
SELECT distinct cl.nombre, cl.apellido, f.metodo_pago
FROM clientes cl
JOIN citas c ON c.id_cliente_clientes = cl.id_cliente
JOIN facturas f ON c.id_factura_facturas = f.id_factura
WHERE f.metodo_pago='efectivo';

/*Clientes que han pagado con tarjeta de compra del corte ingles*/
SELECT distinct cl.nombre, cl.apellido, f.metodo_pago
FROM clientes cl
JOIN citas c ON c.id_cliente_clientes = cl.id_cliente
JOIN facturas f ON c.id_factura_facturas = f.id_factura
WHERE f.metodo_pago='tarjeta de compra del Corte Inglés';