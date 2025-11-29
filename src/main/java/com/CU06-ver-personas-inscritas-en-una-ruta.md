# CU06 – Ver Personas Inscritas en una Ruta

**Actor:** Conductor (Usuario autenticado)

## Guion (Curso normal de eventos)

1. El conductor inicia sesión y accede a la opción "Mis Rutas" o "Rutas Registradas".
2. El sistema muestra una lista de todas las rutas que el conductor ha registrado.
3. El conductor selecciona una ruta de su interés.
4. El sistema valida que la ruta pertenezca al conductor autenticado.
5. El sistema muestra los detalles de la ruta:
   - Origen, destino, hora de salida
   - Cupos iniciales y cupos disponibles
   - Cantidad de pasajeros inscritos
6. El conductor presiona el botón "Ver Pasajeros" o "Ver Inscritos".
7. El sistema consulta la base de datos y obtiene la lista de todos los pasajeros inscritos en esa ruta.
8. El sistema muestra una tabla con los datos de los pasajeros inscritos:
   - Nombre completo
   - Correo electrónico
   - Programa académico
   - Fecha de inscripción
   - Punto de recogida (si aplica)
9. El conductor puede:
   - Exportar la lista de pasajeros (opcional)
   - Eliminar un pasajero de la ruta si es necesario
   - Volver a la lista de rutas

## Excepciones (Caminos alternos)

**E1 – La ruta no tiene pasajeros inscritos**
7.1. El sistema muestra: "No hay pasajeros inscritos en esta ruta aún."
7.2. Se muestra la cantidad de cupos disponibles.
7.3. El conductor puede volver a la lista de rutas.

**E2 – Error al consultar los pasajeros**
7.1. El sistema muestra: "Error al cargar la lista de pasajeros. Intente nuevamente."
7.2. Se ofrece la opción de reintentar o volver atrás.

**E3 – El conductor intenta acceder a una ruta de otro conductor**
4.1. El sistema valida que el conductor sea el propietario de la ruta.
4.2. Si no es el propietario, muestra: "No tienes permiso para ver esta información."
4.3. El proceso se detiene y se redirige al menú principal.

**E4 – Eliminar un pasajero**
9.1. El conductor selecciona un pasajero y presiona "Eliminar".
9.2. El sistema solicita confirmación: "¿Estás seguro de que deseas eliminar este pasajero?"
9.3. Si confirma:
   - Se elimina al pasajero de la ruta.
   - Se libera un cupo.
   - Se muestra: "Pasajero eliminado exitosamente."
9.4. Si cancela, la acción se anula.