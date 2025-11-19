-- 1. Top de peluqueras más activas: mostrar las 3 peluqueras que más clientes
-- han atendido en el último mes.

SELECT
    p.nombre,
    COUNT(c.id_cita) AS total_citas_ultimo_mes
FROM
    peluqueras p
JOIN
    servicios s ON p.id_peluquera = s.id_peluquera_peluqueras
JOIN
    citas_servicios cs ON s.id_servicio = cs.id_servicio_servicios
JOIN
    citas c ON cs.id_cita_citas = c.id_cita
WHERE
    c.fecha >= DATE '2025-12-30' - INTERVAL '1 month'
GROUP BY
    p.nombre
ORDER BY
    total_citas_ultimo_mes DESC
LIMIT 3;

---

-- 2. Clientes frecuentes: listar los clientes que han acudido más de 5 veces
-- durante el último trimestre.

SELECT
    cl.nombre,
    cl.apellido,
    COUNT(c.id_cita) AS total_visitas_trimestre
FROM
    clientes cl
JOIN
    citas c ON cl.id_cliente = c.id_cliente_clientes
WHERE
    c.fecha >= DATE '2025-12-30' - INTERVAL '3 months'
GROUP BY
    cl.id_cliente, cl.nombre, cl.apellido
HAVING
    COUNT(c.id_cita) > 5;

---

-- 3. Servicios más rentables: calcular el total de ingresos por tipo de servicio y
-- mostrar los 3 más rentables.

SELECT
    s.nombre AS servicio,
    SUM(c.precio) AS ingresos_totales
FROM
    servicios s
JOIN
    citas_servicios cs ON s.id_servicio = cs.id_servicio_servicios
JOIN
    citas c ON cs.id_cita_citas = c.id_cita
GROUP BY
    s.nombre
ORDER BY
    ingresos_totales DESC
LIMIT 3;

---

-- 4. Promedio de duración por peluquera: obtener la duración media de todos los
-- servicios realizados por cada peluquera.

SELECT
    p.nombre AS peluquera,
    AVG(s.duracion) AS duracion_media_servicio
FROM
    peluqueras p
JOIN
    servicios s ON p.id_peluquera = s.id_peluquera_peluqueras
GROUP BY
    p.nombre
ORDER BY
    duracion_media_servicio DESC;

---

-- 5. Stock crítico: mostrar los productos con cantidad inferior al 10% de su stock
-- máximo.

SELECT
    producto,
    stock_actual,
    stock_max
FROM
    inventario
WHERE
    stock_actual < (stock_max * 0.10);

---

-- 6. Clientes VIP y gasto medio: mostrar el gasto medio total de los clientes VIP.
-- Nota: La tabla 'citas' no tiene el 'id_cliente' asociado en los datos de ejemplo,
-- lo que impide calcular el gasto medio por cliente. Se utiliza el campo 'precio'
-- de la tabla 'citas' para estimar el gasto por cita, aunque esto no es un cálculo
-- de gasto total por cliente.

SELECT
    AVG(c.precio) AS gasto_medio_vip
FROM
    clientes cl
JOIN
    citas c ON cl.id_cliente = c.id_cliente_clientes
WHERE
    cl.vip = TRUE;

---

-- 7. Ingresos por día: calcular el total de ingresos de la peluquería agrupados por
-- día de la semana.

SELECT
    CASE EXTRACT(DOW FROM c.fecha)
        WHEN 0 THEN 'Domingo'
        WHEN 1 THEN 'Lunes'
        WHEN 2 THEN 'Martes'
        WHEN 3 THEN 'Miércoles'
        WHEN 4 THEN 'Jueves'
        WHEN 5 THEN 'Viernes'
        WHEN 6 THEN 'Sábado'
    END AS dia_semana,
    SUM(c.precio) AS ingresos_diarios
FROM
    citas c
GROUP BY
    dia_semana, EXTRACT(DOW FROM c.fecha)
ORDER BY
    EXTRACT(DOW FROM c.fecha);

---

-- 8. Servicios combinados: mostrar a los clientes que se han realizado “corte” y
-- “tinte” en la misma visita.

SELECT
    cl.nombre,
    cl.apellido
FROM
    clientes cl
JOIN
    citas c ON cl.id_cliente = c.id_cliente_clientes
JOIN
    many_citas_has_many_servicios mchs ON c.id_cita = mchs.id_cita_citas
JOIN
    servicios s ON mchs.id_servicio_servicios = s.id_servicio
WHERE
    s.nombre IN ('Corte', 'Tinte')
GROUP BY
    cl.id_cliente, cl.nombre, cl.apellido, c.id_cita -- Agrupar por cita para asegurar que fue en la misma visita
HAVING
    COUNT(DISTINCT s.nombre) = 2; -- Contar que ambos servicios (Corte y Tinte) están en la misma cita

---

-- 9. Ranking de productividad: crear una vista que muestre el número de servicios
-- realizados por cada peluquera, su total facturado y su posición en el ranking.

CREATE VIEW ranking_productividad AS
SELECT
    p.nombre AS peluquera,
    COUNT(s.id_servicio) AS total_servicios_realizados,
    COALESCE(SUM(c.precio), 0) AS total_facturado,
    RANK() OVER (ORDER BY COUNT(s.id_servicio) DESC, COALESCE(SUM(c.precio), 0) DESC) AS posicion_ranking
FROM
    peluqueras p
LEFT JOIN
    servicios s ON p.id_peluquera = s.id_peluquera_peluqueras
LEFT JOIN
    many_citas_has_many_servicios mchs ON s.id_servicio = mchs.id_servicio_servicios
LEFT JOIN
    citas c ON mchs.id_cita_citas = c.id_cita
GROUP BY
    p.id_peluquera, p.nombre
ORDER BY
    posicion_ranking;

-- Consulta para ver el ranking
SELECT * FROM ranking_productividad;

---

-- 10. Duración total de la jornada: sumar el tiempo total de cada peluquera que ha
-- pasado atendiendo clientes durante una jornada completa.
-- Nota: Esto asume que una "jornada completa" es el tiempo total acumulado
-- de los servicios que tiene asignados, ya que la tabla 'citas' no tiene el
-- campo de relación directo con 'peluqueras' o 'servicios' que permita agrupar
-- por una jornada real, y 'servicios' solo tiene la duración por servicio.

SELECT
    p.nombre AS peluquera,
    SUM(s.duracion) AS duracion_total_servicio_minutos
FROM
    peluqueras p
JOIN
    servicios s ON p.id_peluquera = s.id_peluquera_peluqueras
GROUP BY
    p.nombre
ORDER BY
    duracion_total_servicio_minutos DESC;

---

-- 11. Clientes inactivos: listar los clientes que no han acudido en los últimos 6
-- meses.

SELECT
    cl.nombre,
    cl.apellido
FROM
    clientes cl
WHERE
    cl.id_cliente NOT IN (
        SELECT DISTINCT c.id_cliente_clientes
        FROM citas c
        WHERE c.fecha >= DATE '2025-11-18' - INTERVAL '6 months'
    );

---

-- 12. Descuento automático: simular una consulta que calcule un 10% de
-- descuento a los clientes que hayan superado los 100€ en servicios durante el
-- mes.
-- Nota: Utiliza las citas de los últimos 30 días para el cálculo mensual y el precio
-- de la cita como base para el gasto.

WITH GastoMensual AS (
    SELECT
        cl.id_cliente,
        cl.nombre,
        cl.apellido,
        SUM(c.precio) AS gasto_total_mes
    FROM
        clientes cl
    JOIN
        citas c ON cl.id_cliente = c.id_cliente_clientes
    WHERE
        c.fecha >= DATE '2025-11-18' - INTERVAL '30 days'
    GROUP BY
        cl.id_cliente, cl.nombre, cl.apellido
)
SELECT
    nombre,
    apellido,
    gasto_total_mes,
    (gasto_total_mes * 0.10) AS descuento_sugerido
FROM
    GastoMensual
WHERE
    gasto_total_mes > 100
ORDER BY
    descuento_sugerido DESC;

---

-- 13. Uso de productos: calcular cuántas unidades de cada producto se han
-- utilizado en los últimos 30 días (suponiendo relación entre servicios y
-- productos).
-- Nota: Dado que las tablas intermedias 'many_citas_has_many_servicios' y
-- 'many_inventario_has_many_servicios' no tienen datos en el dump,
-- esta consulta asume 1 unidad de producto por cada servicio que lo requiere
-- y que se ha realizado en los últimos 30 días.

SELECT
    i.producto,
    COUNT(DISTINCT c.id_cita) AS usos_estimados_unidades
FROM
    inventario i
JOIN
    many_inventario_has_many_servicios mihms ON i.id_producto = mihms.id_producto_inventario
JOIN
    servicios s ON mihms.id_servicio_servicios = s.id_servicio
JOIN
    many_citas_has_many_servicios mchs ON s.id_servicio = mchs.id_servicio_servicios
JOIN
    citas c ON mchs.id_cita_citas = c.id_cita
WHERE
    s.requier_porducto = TRUE
    AND c.fecha >= DATE '2025-11-18' - INTERVAL '30 days'
GROUP BY
    i.producto
ORDER BY
    usos_estimados_unidades DESC;

---

-- 14. Ingresos por método de pago: agrupar las facturas según el método de pago
-- (efectivo, tarjeta, transferencia).
-- Nota: La tabla 'facturas' solo tiene 'efectivo', 'tarjeta', y 'tarjeta de compra
-- del Corte Inglés', por lo que se agrupará por estos métodos existentes.

SELECT
    metodo_pago,
    SUM(importe) AS total_ingresos
FROM
    facturas
GROUP BY
    metodo_pago
ORDER BY
    total_ingresos DESC;

---

-- 15. Servicios más largos: mostrar los 5 servicios cuya duración media haya sido
-- mayor, junto con el nombre de las peluqueras que más los realizan.
-- Nota: Se utiliza la duración definida en la tabla 'servicios' como duración media.
-- La peluquera que "más los realiza" se interpreta como la que tiene asignado
-- dicho servicio.

SELECT
    s.nombre AS servicio,
    s.duracion AS duracion_media_minutos,
    p.nombre AS peluquera_asignada
FROM
    servicios s
JOIN
    peluqueras p ON s.id_peluquera_peluqueras = p.id_peluquera
ORDER BY
    duracion_media_minutos DESC
LIMIT 5;
