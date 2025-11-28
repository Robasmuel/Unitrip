# CU04 – Buscar Ruta por Destino

 Estudiante (Usuario autenticado)

## Guion (Curso normal de eventos)

1. El estudiante inicia sesión y selecciona la opción “Buscar ruta” en la aplicación.
2. El sistema muestra el formulario de búsqueda con el campo Destino y filtros opcionales como: fecha, hora, conductor, cupos disponibles o punto de encuentro.
3. El estudiante ingresa el destino deseado.
4. El sistema valida que el campo destino no esté vacío.
5. El estudiante presiona el botón “Buscar”.
6. El sistema consulta en la base de datos todas las rutas activas que coincidan con el destino ingresado.
7. El sistema muestra una lista de rutas disponibles que cumplen con el criterio de búsqueda, incluyendo información como:
- Origen
- Destino
- Conductor
- Fecha y hora
- Cupos disponibles
- Punto de encuentro
8. El estudiante puede seleccionar una ruta para ver más detalles o inscribirse en ella.

## Excepciones (Caminos alternos)

**E1 – Campo destino vacío
4.1.1. El sistema muestra el mensaje: “Debe ingresar un destino para realizar la búsqueda.”
4.1.2. El botón Buscar permanece deshabilitado hasta ingresar un destino.

**E2 – No se encuentran rutas con ese destino
7.1. El sistema muestra: “No se encontraron rutas disponibles para este destino.”
7.2. El estudiante puede cambiar el destino o aplicar otros filtros.

**E3 – Error al consultar las rutas
6.1. El sistema muestra: “Error al buscar rutas. Intente nuevamente más tarde.”
6.2. El proceso se detiene hasta que el sistema vuelva a estar disponible.