Abarrotes Tizimín-Requerimientos Funcionales

1. Gestión de Clientes RF-001
- El sistema debe permitir registrar nuevos clientes, solicitando su nombre, apellido paterno y los datos completos de su dirección (calle, número, colonia, código postal, ciudad, estado y teléfono).
-	Los clientes deben poder ser consultados con su respectiva información en una lista general.
-	El sistema debe permitir eliminar clientes utilizando su nombre como identificador. No se permitirá registrar dos clientes con el mismo nombre.

2. Gestión de Productos RF-002
-	El sistema debe permitir registrar nuevos productos, solicitando el nombre, precio al público, precio de proveedor y cantidad inicial en inventario.
-	Los productos deben poder visualizarse en un apartado de inventario, mostrando toda la información relevante.
-	También debe ser posible eliminar productos existentes, utilizando su nombre como referencia. No se permitirá duplicar nombres de productos.

3. Realización de Compras RF-003
-	El sistema debe permitir que un cliente realice una compra seleccionando productos del inventario.
-	Durante la compra, el  podrá elegir la cantidad deseada de cada producto, siempre verificando que haya suficiente existencia en inventario.
-	Al finalizar una compra, el sistema debe generar un ticket que incluya el nombre del cliente, la fecha, los productos adquiridos (nombre, cantidad, precio) y el total de la compra.

4. Visualización de Inventario RF-004
-	El sistema debe ofrecer la opción de consultar el inventario actualizado, mostrando el listado de productos disponibles junto con su cantidad en existencia.

5. Visualización de Clientes RF-005
-	El sistema debe ofrecer una sección donde se pueda consultar de manera actualizada todos los clientes registrados.

6. Control de Consistencia de Datos RF-006
-	El sistema debe asegurar que no existan clientes ni productos con nombres duplicados.
-	No se permitirá realizar una compra si no hay suficiente stock del producto seleccionado.
-	Todas las modificaciones en clientes o productos deberán reflejarse inmediatamente en el sistema.

7. Menú de Opciones RF-007
Se trabajará con javaFx, Al iniciar, el sistema mostrará un menú principal que debe incluir las siguientes opciones:
-	Clientes (Registrar un cliente, consultar o eliminar clientes).
-	Inventario (Registrar un producto, consultar o eliminar productos).
-	Compra.
-	Salir.

8. Persistencia de datos RF-008
-	El programa deberá ser capaz de guardar los datos de clientes y productos registrados a pesar de que la ejecución del programa sea terminada, para continuar trabajando en futuras sesiones.
-	Guardará los datos en un documento tipo .txt 
-	La persistencia deberá asegurar que los datos se guarden de manera correcta 


