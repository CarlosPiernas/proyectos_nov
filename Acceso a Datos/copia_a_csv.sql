COPY public.clientes TO 'C:/Users/Carlos/Desktop/datos_csv/clientes.csv'
WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');

COPY public.citas TO 'C:/Users/Carlos/Desktop/datos_csv/citas.csv'
WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');

COPY public.facturas TO 'C:/Users/Carlos/Desktop/datos_csv/facturas.csv'
WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');

COPY public.inventario TO 'C:/Users/Carlos/Desktop/datos_csv/inventario.csv'
WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');

COPY public.peluqueras TO 'C:/Users/Carlos/Desktop/datos_csv/peluqueras.csv'
WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');

COPY public.servicios TO 'C:/Users/Carlos/Desktop/datos_csv/servicios.csv'
WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');

COPY public.citas_servicios TO 'C:/Users/Carlos/Desktop/datos_csv/citas_servicios.csv'
WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');

COPY public.inventario_servicios TO 'C:/Users/Carlos/Desktop/datos_csv/inventario_servicios.csv'
WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');

