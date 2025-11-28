# CU03 – Registrar Ruta

Conductor (Usuario autenticado)

## Guion (Curso normal de eventos)

1. El conductor inicia sesión y selecciona la opción “Registrar ruta” en la aplicación.

2. El sistema muestra el formulario de creación de ruta con los campos: origen, destino, fecha/hora de salida, cupos disponibles, vehículo (opcional), punto de encuentro y tarifa (si aplica).

3. El conductor diligencia todos los campos requeridos.

4. El sistema valida en tiempo real:

- Que los campos obligatorios estén completos.
- Que la fecha y hora sean válidas y posteriores al momento actual.
- Que los cupos disponibles sean un número mayor a cero.

5. El conductor presiona el botón “Registrar Ruta”.

6. El sistema verifica que no exista una ruta activa previamente registrada con la misma información por ese conductor.

7. Si todas las validaciones son correctas, el sistema guarda la ruta en la base de datos.

8. El sistema muestra un mensaje confirmando que la ruta fue registrada exitosamente.

9. El conductor puede elegir regresar al menú principal o registrar otra ruta.

## Excepciones (Caminos alternos)

**E1 – Campos incompletos
4.1.1. El sistema muestra: “Debe completar todos los campos obligatorios.”
4.1.2. El botón Registrar Ruta permanece deshabilitado.
4.1.3. El flujo continúa cuando el conductor complete los campos faltantes.

**E2 – Fecha u hora inválida
4.2.1. El sistema muestra: “La fecha y hora deben ser posteriores al momento actual.”
4.2.2. El sistema solicita corregir el campo.

**E3 – Cupos no válidos
4.3.1. El sistema muestra: “Los cupos deben ser un número mayor a cero.”
4.3.2. El sistema no permite continuar hasta corregirlo.

**E4 – Ruta duplicada
6.1. El sistema muestra: “Ya existe una ruta registrada con estos datos.”
6.2. El proceso termina y no se crea la nueva ruta.

**E5 – Error al guardar la ruta
7.1. El sistema muestra: “No se pudo registrar la ruta. Intente nuevamente más tarde.”
7.2. El sistema conserva los datos ingresados para reintentar.