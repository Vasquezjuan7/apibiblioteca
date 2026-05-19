# API Biblioteca

Sistema API REST para gestión de biblioteca universitaria construido con Spring Boot y MongoDB.

## 📋 Características

- **Gestión de Usuarios**: Crear, consultar, actualizar y eliminar usuarios
- **Gestión de Libros**: Administración completa de libros en el catálogo
- **Gestión de Ejemplares**: Control de copias disponibles de cada libro
- **Gestión de Préstamos**: Crear, consultar y registrar devoluciones de préstamos
- **Validación automática de estado**: Los ejemplares cambian de estado automáticamente
- **Manejo de errores mejorado**: Respuestas JSON claras con códigos HTTP apropiados

## 🛠️ Tecnologías

- **Java 11+**
- **Spring Boot 3.x**
- **MongoDB**
- **Maven**
- **Lombok**

## 📦 Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/biblioteca/
│   │   ├── controller/        # Controladores REST
│   │   ├── service/           # Lógica de negocio
│   │   ├── repository/        # Acceso a datos
│   │   ├── model/             # Entidades
│   │   ├── dto/               # Data Transfer Objects
│   │   └── exception/         # Manejo de excepciones
│   └── resources/
│       └── application.properties
```

## 🚀 Instalación y Ejecución

### Requisitos
- Java 11 o superior
- Maven 3.6+
- MongoDB (con conexión remota configurada)

### Pasos

1. **Clonar el repositorio:**
```bash
git clone https://github.com/Vasquezjuan7/apibiblioteca.git
cd apibiblioteca
```

2. **Configurar la conexión a MongoDB:**
   - Editar `src/main/resources/application.properties`
   - Actualizar `spring.data.mongodb.uri` con tu URI de MongoDB

3. **Construir el proyecto:**
```bash
mvn clean install
```

4. **Ejecutar la aplicación:**
```bash
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

## 📚 Endpoints

### Usuarios
- `POST   /api/usuarios`           - Crear usuario
- `GET    /api/usuarios`           - Listar todos
- `GET    /api/usuarios/{id}`      - Obtener por ID
- `PUT    /api/usuarios/{id}`      - Actualizar
- `DELETE /api/usuarios/{id}`      - Eliminar

### Libros
- `POST   /api/libros`             - Crear libro
- `GET    /api/libros`             - Listar todos
- `GET    /api/libros/{id}`        - Obtener por ID
- `PUT    /api/libros/{id}`        - Actualizar
- `DELETE /api/libros/{id}`        - Eliminar

### Ejemplares
- `POST   /api/ejemplares`         - Crear ejemplar
- `GET    /api/ejemplares`         - Listar todos
- `GET    /api/ejemplares/{id}`    - Obtener por ID
- `PUT    /api/ejemplares/{id}`    - Actualizar
- `DELETE /api/ejemplares/{id}`    - Eliminar

### Préstamos
- `POST   /api/prestamos`                    - Crear préstamo
- `GET    /api/prestamos`                    - Listar todos
- `GET    /api/prestamos/{id}`               - Obtener por ID
- `PUT    /api/prestamos/{id}`               - Actualizar
- `DELETE /api/prestamos/{id}`               - Eliminar
- `PUT    /api/prestamos/{id}/devolucion`    - Registrar devolución

## 📖 Ejemplo de Uso

### 1. Crear un Usuario
```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "correo": "juan@ejemplo.com",
    "tipoUsuario": "ESTUDIANTE",
    "codigoEstudiante": "E001",
    "programa": "Ingeniería en Sistemas"
  }'
```

### 2. Crear un Libro
```bash
curl -X POST http://localhost:8080/api/libros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Clean Code",
    "autor": "Robert C. Martin",
    "isbn": "0132350882",
    "año": 2008
  }'
```

### 3. Crear un Ejemplar
```bash
curl -X POST http://localhost:8080/api/ejemplares \
  -H "Content-Type: application/json" \
  -d '{
    "libroId": "ID_DEL_LIBRO",
    "codigoEjemplar": "EJ-001",
    "estado": "DISPONIBLE",
    "ubicacion": "Estante A-1"
  }'
```

### 4. Crear un Préstamo
```bash
curl -X POST http://localhost:8080/api/prestamos \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": "ID_DEL_USUARIO",
    "ejemplarId": "ID_DEL_EJEMPLAR",
    "fechaDevolucionEsperada": "2026-06-15T00:00:00Z"
  }'
```

### 5. Registrar Devolución
```bash
curl -X PUT http://localhost:8080/api/prestamos/ID_DEL_PRESTAMO/devolucion
```

## 🔄 Flujo de Préstamos

1. Usuario crea un préstamo con ejemplar en estado "DISPONIBLE"
2. El ejemplar cambia automáticamente a "PRESTADO"
3. El préstamo se crea con estado "ACTIVO"
4. Al registrar devolución, el préstamo pasa a "DEVUELTO"
5. El ejemplar vuelve a estado "DISPONIBLE"

## 🧪 Testing con Postman

Se incluye una colección de Postman en el archivo: `API Biblioteca.postman_collection.json`

Importa el archivo en Postman para tener todos los endpoints preconfigurados.

## 📝 Licencia

Este proyecto está disponible bajo licencia MIT.

## 👤 Autor

Juan Vásquez Sánchez

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para cambios mayores, por favor abre un issue primero para discutir los cambios propuestos.
