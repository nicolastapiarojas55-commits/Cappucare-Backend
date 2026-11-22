# Cappucare – Control de existencias de insumos médicos

App móvil Android para controlar el inventario de productos e insumos médicos de la organización Cappucare: registra entradas y salidas de stock, avisa cuando un producto está bajo el mínimo y mantiene un historial de movimientos. Proyecto de la asignatura **DSY1105 – Desarrollo de Aplicaciones Móviles** (Duoc UC).

## Repositorios del proyecto

| Parte | Tecnología | Repositorio |
|---|---|---|
| App móvil | Kotlin + Jetpack Compose | https://github.com/AlejandroRivera2005/CappucareSuministrosMedicos |

## 1. Nombre del proyecto

Cappucare Suministros Médicos

## 2. Integrantes

- Felix Rojas Tapia
- Alejandro Rivera

## 3. Funcionalidades

- **Acceso y perfiles:** registro con validación de formulario, aprobación de cuentas por un encargado y dos perfiles (*encargado*: administra productos y aprueba usuarios; *autorizado*: consulta y registra movimientos).
- **Inventario:** lista de productos con búsqueda y estados (*Bajo mínimo*, *Agotado*, *Inactivo*), detalle, creación, edición y eliminación.
- **Movimientos:** ingreso y salida de stock con motivo y responsable; no permite sacar más que el stock disponible. Historial general y por producto.
- **Alertas:** lista de productos bajo el mínimo y lista de reposición para compartir.
- **Recursos nativos:** notificaciones cuando un producto queda bajo el mínimo y cámara (vista previa, con permisos en tiempo de ejecución).
- **API externa:** [COMPLETAR: describe qué muestra la pantalla que usa PokeAPI].
- **Persistencia:** datos en el microservicio (PostgreSQL) con Room como caché local.
- **Arquitectura:** MVVM (model / view / viewModel), Hilt para inyección de dependencias, Retrofit para red, StateFlow para el estado de la interfaz.

## 4. Endpoints utilizados


| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/productos` | Lista todos los productos |
| GET | `/api/productos/{id}` | Obtiene un producto |
| POST | `/api/productos` | Crea un producto |
| PUT | `/api/productos/{id}` | Actualiza un producto |
| DELETE | `/api/productos/{id}` | Elimina un producto |
| GET | `/api/movimientos` | Lista todos los movimientos |
| GET | `/api/movimientos/producto/{id}` | Movimientos de un producto |
| POST | `/api/movimientos` | Registra un movimiento y actualiza el stock |

### API externa

- **PokeAPI:** `https://pokeapi.co/api/v2/`

## 5. Pasos para ejecutar

### Requisitos
Android Studio (versión reciente), JDK 21, emulador o dispositivo con Android 11 (API 30) o superior, y acceso a internet.

### Microservicio
```bash

export DB_URL="jdbc:postgresql://<host>:5432/<base_de_datos>"
export DB_USER="<usuario>"
export DB_PASSWORD="<contraseña>"

./mvnw spring-boot:run
```
Queda escuchando en el puerto 8080. Las credenciales se pasan por variables de entorno y no se incluyen en el repositorio.

### App móvil
1. Clonar este repositorio y abrirlo con Android Studio.
2. Esperar a que termine *Sync Gradle*.
3. Verificar que la URL base del microservicio apunta a su servidor ([COMPLETAR: archivo donde se configura, ej. `NetworkModule.kt`]).
4. Ejecutar la app en un emulador o dispositivo (*Run ▶*).
5. Credenciales de prueba (encargado): `encargado@cappucare.cl` / `Cappucare123`.

### Pruebas unitarias y cobertura
```bash
./gradlew testDebugUnitTest
./gradlew jacocoDebugUnitTestReport
```
Reporte en `app/build/reports/jacoco/jacocoDebugUnitTestReport/html/index.html`. Cobertura actual: **90 %** de instrucciones.

### Generar el APK firmado
```bash
./gradlew assembleRelease
```
El APK queda en `app/build/outputs/apk/release/`. La firma está configurada en `signingConfigs` de `app/build.gradle.kts` y usa el keystore `app/cappucare-key.jks`.

## 6. APK firmado y llave (.jks)

- Keystore: `app/cappucare-key.jks`


## Planificación y trabajo en equipo

Tablero de Trello: [COMPLETAR: link del tablero]

## Tecnologías

Kotlin, Jetpack Compose, Material 3, Room, Hilt, Retrofit, CameraX, JUnit, MockK, Robolectric, JaCoCo · Spring Boot, PostgreSQL (AWS).
