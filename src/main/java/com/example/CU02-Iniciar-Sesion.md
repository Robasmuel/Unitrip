# CU02 – Iniciar Sesión

Usuario (No autenticado)

## Guion (Curso normal de eventos)

1. El usuario abre la aplicación y selecciona “Iniciar sesión”.
2. El sistema muestra el formulario con los campos: correo institucional y contraseña.
3. El usuario ingresa los datos requeridos.
4. El sistema valida:
    - Que los campos no estén vacíos.
    - Que el formato del correo sea válido.
5. El usuario presiona el botón “Ingresar”.
6. El sistema verifica que el usuario exista y que las credenciales coincidan.
7. Si las credenciales son correctas, el sistema inicia la sesión.
8. El sistema redirige al menú principal según el rol del usuario (conductor o pasajero).

## Excepciones (Caminos alternos)

**E1 – Campos vacíos**  
4.1. El sistema muestra: “Debe completar todos los campos.”

**E2 – Formato de correo inválido**  
4.2. El sistema muestra: “Ingrese un correo institucional válido.”

**E3 – Usuario no encontrado**  
6.1. El sistema muestra: “No existe un usuario con este correo.”  
6.2. El usuario puede optar por registrarse.

**E4 – Contraseña incorrecta**  
6.3. El sistema muestra: “Contraseña incorrecta.”  
6.4. El sistema permite reintentar.

**E5 – Error al verificar credenciales**  
6.5. El sistema muestra: “Ocurrió un error. Intente nuevamente más tarde.”  
