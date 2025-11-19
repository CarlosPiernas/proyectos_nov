/*Indice para la consulta de clientes utilizada en las interfaces*/
CREATE INDEX clientesInterfaz
ON clientes (id_cliente, nombre, apellido, num_visitas, vip, fecha_alta); 

/*Indice para la consulta de peluqueras utilizada en las interfaces*/
CREATE INDEX peluquerasInterfaz
ON peluqueras (id_peluquera, exp_anio, nombre, especialidad, estado);

/*Indice para la consulta de servicios utilizada en las interfaces*/
CREATE INDEX serviciosInterfaz
ON servicios (id_servicio, nombre, precio, duracion, requier_porducto, id_peluquera_peluqueras);

/*Indice para la consulta de productos utilizada en las interfaces*/
CREATE INDEX inventarioInterfaz
ON inventario (id_producto, producto, stock_actual, proveedor, stock_max);

/*Indice para la consulta de servicios rentables utilizada en las interfaces*/
--utiliza el mismo indice que la consulta de los datos de servicios

/*Indice para la consulta de clientes vip utilizada en las interfaces*/
CREATE INDEX clientes_vip 
ON clientes (id_cliente, nombre, apellido, num_visitas, vip, fecha_alta) 
WHERE vip = true;

/*Indice para la consulta de productos con stock bajo utilizada en las interfaces*/
CREATE INDEX stockBajoInterfaz
ON inventario (id_producto, producto, stock_actual, proveedor, stock_max)
WHERE stock_actual < 5;
