# CU05 – Inscribirse en una Ruta

**Actor:** Estudiante/Pasajero (Usuario autenticado)

## Guion (Curso normal de eventos)

1. El estudiante inicia sesión como pasajero y accede a la opción "Buscar rutas".
2. El estudiante busca y visualiza la lista de rutas disponibles (ver CU04).
3. El estudiante selecciona una ruta de su interés de la tabla.
4. El sistema valida que:
   - La ruta tenga cupos disponibles (cupos > 0).
   - El estudiante no esté ya inscrito en esa ruta.
   - La ruta esté vigente (fecha y hora posterior al momento actual).
5. El sistema muestra los detalles de la ruta:
   - Origen, destino, hora de salida
   - Nombre del conductor
   - Cupos disponibles
   - Punto de encuentro
   - Botón "Confirmar Inscripción"
6. El estudiante presiona el botón "Confirmar Inscripción".
7. El sistema descuenta un cupo de la ruta.
8. El sistema registra al estudiante como pasajero en esa ruta.
9. El sistema muestra un mensaje confirmando: "¡Inscripción exitosa! Ya estás registrado en esta ruta."
10. El estudiante puede ver la ruta en su lista de "Mis Rutas" o volver a buscar otras rutas.

## Excepciones (Caminos alternos)

**E1 – No hay cupos disponibles**
4.1.1. El sistema muestra: "No hay cupos disponibles en esta ruta."
4.1.2. El botón "Confirmar Inscripción" permanece deshabilitado.
4.1.3. El estudiante puede ver rutas alternativas.

**E2 – El estudiante ya está inscrito**
4.2.1. El sistema muestra: "Ya estás inscrito en esta ruta."
4.2.2. El botón "Confirmar Inscripción" aparece deshabilitado.
4.2.3. Se ofrece la opción de "Cancelar Inscripción" en su lugar.

**E3 – La ruta ya no es vigente**
4.3.1. El sistema muestra: "Esta ruta ya no está disponible (pasada de fecha)."
4.3.2. El botón "Confirmar Inscripción" permanece deshabilitado.

**E4 – Error al procesar la inscripción**
8.1. El sistema muestra: "No se pudo completar la inscripción. Intente nuevamente."
8.2. El sistema no descuenta cupos y conserva los datos para reintentar.