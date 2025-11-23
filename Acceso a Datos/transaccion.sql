BEGIN;
DO $$
DECLARE
    -- Variables para nuevos IDs
    nuevo_id_cliente INTEGER;
    nuevo_id_cita INTEGER;
    nuevo_id_factura INTEGER;

    -- Variables de la atención
    servicio_id CONSTANT INTEGER := 3;     -- Servicio: Tinte
    producto_id CONSTANT INTEGER := 1;     -- Producto: Tinte para el cabello
    nombre_cliente CONSTANT TEXT := 'JOSE';
    apellido_cliente CONSTANT TEXT := 'FERRER';

    -- Variables de control y stock
    precio_servicio DOUBLE PRECISION;
    stock_actual_producto INTEGER;

BEGIN
    -- A. Generación de IDs y Bloqueo de Filas
    
    -- Determinar los IDs secuenciales disponibles
    SELECT COALESCE(MAX(id_cliente), 0) + 1 INTO nuevo_id_cliente FROM clientes;
    SELECT COALESCE(MAX(id_cita), 0) + 1 INTO nuevo_id_cita FROM citas;
    SELECT COALESCE(MAX(id_factura), 0) + 1 INTO nuevo_id_factura FROM facturas;
    
    -- Obtener precio del servicio
    SELECT precio INTO precio_servicio FROM servicios WHERE id_servicio = servicio_id;
    
    -- 🔒 Bloquear la fila del producto (FOR UPDATE para evitar concurrencia en stock)
    SELECT stock_actual INTO stock_actual_producto
    FROM inventario
    WHERE id_producto = producto_id
    FOR UPDATE;

    -- B. Verificación de Stock
    IF stock_actual_producto < 1 THEN
        -- Si no hay stock, se lanza una excepción que forzará el ROLLBACK
        RAISE EXCEPTION 'STOCK_INSUFICIENTE: Stock actual para el Tinte para el cabello es %', stock_actual_producto;
    END IF;

    -- C. Registro de Datos y Actualizaciones
    
    -- 1. Dar de Alta al Nuevo Cliente
    INSERT INTO clientes (id_cliente, nombre, apellido, vip, fecha_alta)
    VALUES (nuevo_id_cliente, nombre_cliente, apellido_cliente, FALSE, CURRENT_DATE);
    
    -- 2. Registrar la Factura (Actualiza la tabla de ingresos)
    INSERT INTO facturas (id_factura, importe, metodo_pago)
    VALUES (nuevo_id_factura, precio_servicio, 'efectivo');
    
    -- 3. Registrar la Cita (Registra la atención del cliente)
    INSERT INTO citas (id_cita, fecha, precio, id_factura_facturas, id_cliente_clientes)
    VALUES (nuevo_id_cita, CURRENT_DATE, precio_servicio, nuevo_id_factura, nuevo_id_cliente);
    
    -- 4. Asociar el Servicio a la Cita
    INSERT INTO citas_servicios (id_cita_citas, id_servicio_servicios)
    VALUES (nuevo_id_cita, servicio_id);

    -- 5. Descontar Stock (Descuenta el stock de los productos utilizados)
    UPDATE inventario
    SET stock_actual = stock_actual - 1
    WHERE id_producto = producto_id;

    RAISE NOTICE 'Transacción COMPLETADA. Nuevo Cliente (ID: %) y Cita (ID: %) registrados.', nuevo_id_cliente, nuevo_id_cita;

-- D. Manejo de la Excepción (Asegura el ROLLBACK)
EXCEPTION
    WHEN OTHERS THEN
        -- 🛑 ¡Aquí está el ROLLBACK explícito! 🛑
        ROLLBACK;
        RAISE NOTICE 'ERROR: %', SQLERRM;
        -- Notificación final de que la transacción ha sido revertida.
        RAISE EXCEPTION 'TRANSACCIÓN FALLIDA: Se ha ejecutado el ROLLBACK.';
END $$;

-- 3. Finalizar Transacción
-- Si el bloque DO se ejecutó sin excepciones, se aplica el COMMIT.
COMMIT;