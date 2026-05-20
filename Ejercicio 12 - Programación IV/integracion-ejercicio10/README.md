Integracion de Ejercicio10 con LogHub

Estos archivos son la implementacion cliente para el proyecto anterior `Ejercicio10`.

Pasos para usarla:

1. Copiar la carpeta `src/main/java/com/example/Ejercicio10/loghub` dentro del proyecto `Ejercicio10`.
2. Agregar las propiedades de `application.properties.fragment` al `application.properties` de `Ejercicio10`.
3. Registrar `Ejercicio10` en LogHub con `POST /api/applications`.
4. Reemplazar `loghub.app-id` y `loghub.api-key` por los valores devueltos por LogHub.
5. Iniciar LogHub y luego iniciar `Ejercicio10`.

El filtro intercepta todas las peticiones entrantes de `Ejercicio10` y envia un log asincronico a LogHub con:

- Metodo HTTP.
- Path visitado.
- Codigo de estado resultante.

Ejemplo de mensaje enviado:

`HTTP GET /productos -> 200`

Si LogHub no esta disponible, el proyecto `Ejercicio10` sigue respondiendo normalmente porque el envio del log no interrumpe la peticion original.
