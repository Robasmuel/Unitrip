# CU01 – Registrar Usuario con Correo Institucional

Usuario (No autenticado)

## Guion (Curso normal de eventos)

1. El usuario abre la aplicación y selecciona la opción “Registrarse”.
2. El sistema muestra el formulario de registro con los campos: nombre completo, identificación, correo institucional, programa académico, número de teléfono y contraseña.
3. El usuario diligencia todos los campos requeridos.
4. El sistema valida en tiempo real:
    - Que los campos obligatorios estén completos.
    - Que el correo pertenezca al dominio institucional permitido (ej.: @university.edu).
    - Que la contraseña cumpla con los requisitos mínimos (seguridad).
5. El usuario presiona el botón “Crear Cuenta”.
6. El sistema verifica que el correo institucional no esté registrado previamente.
7. Si todo es válido, el sistema crea la cuenta y envía un correo de verificación al usuario.
8. El sistema muestra un mensaje indicando que el registro fue exitoso y que debe confirmar el correo.
9. El usuario puede regresar al inicio o proceder a iniciar sesión.

## Excepciones (Caminos alternos)

**E1 – Campos incompletos**  
4.1.1. El sistema muestra: “Debe completar todos los campos obligatorios.”  
4.1.2. El botón Crear Cuenta permanece deshabilitado.

**E2 – Correo no institucional**  
4.2.1. El sistema muestra: “Debe ingresar un correo institucional válido.”  
4.2.2. El sistema no permite continuar hasta corregir el correo.

**E3 – Contraseña débil**  
4.3.1. El sistema muestra: “La contraseña no cumple los requisitos mínimos.”  
4.3.2. El sistema resalta los requisitos faltantes.

**E4 – Correo ya registrado**  
6.1. El sistema muestra: “Este correo institucional ya está registrado.”  
6.2. El proceso termina sin crear la cuenta.

**E5 – Error al guardar el usuario**  
7.1. El sistema muestra: “No se pudo crear la cuenta. Intente nuevamente más tarde.”  
7.2. El sistema conserva los datos para reintentar.  
