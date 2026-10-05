# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapterTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `com.pragma.ordenes.infrastructure.persistence.OrdenEntity`: El import com.pragma.ordenes.infrastructure.persistence.OrdenEntity usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `com.pragma.ordenes.infrastructure.persistence.OrdenRepository`: El import com.pragma.ordenes.infrastructure.persistence.OrdenRepository usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `com.pragma.ordenes.infrastructure.persistence.entity.TransaccionEntity`: El import com.pragma.ordenes.infrastructure.persistence.entity.TransaccionEntity usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `com.pragma.ordenes.infrastructure.persistence.repository.TransaccionRepository`: El import com.pragma.ordenes.infrastructure.persistence.repository.TransaccionRepository usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `com.pragma.ordenes.infrastructure.adapters.dto.OrdenRequest`: El import com.pragma.ordenes.infrastructure.adapters.dto.OrdenRequest usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `com.pragma.ordenes.infrastructure.adapters.dto.OrdenResponse`: El import com.pragma.ordenes.infrastructure.adapters.dto.OrdenResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenRequest`: El import com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenRequest usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenResponse`: El import com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/ordenes/application/usecases/RegistrarTransaccionUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.getClienteId`: Se invoca `getClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.getId`: Se invoca `getId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.getItems`: Se invoca `getItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.setId`: Se invoca `setId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.setFechaCreacion`: Se invoca `setFechaCreacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.setFechaActualizacion`: Se invoca `setFechaActualizacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.setEstado`: Se invoca `setEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.getEstado`: Se invoca `getEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Transaccion.setCorrelationId`: Se invoca `setCorrelationId` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/application/usecases/RegistrarTransaccionUseCase.java` — `Transaccion.getId`: Se invoca `getId` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/application/usecases/RegistrarTransaccionUseCase.java` — `TransaccionRepositoryPort.buscarPorEntidadAfectada`: Se invoca `buscarPorEntidadAfectada` sobre `TransaccionRepositoryPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/application/usecases/RegistrarTransaccionUseCase.java` — `TransaccionRepositoryPort.buscarPorEntidadId`: Se invoca `buscarPorEntidadId` sobre `TransaccionRepositoryPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `Orden.getClienteId`: Se invoca `getClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `Orden.getId`: Se invoca `getId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `Orden.getItems`: Se invoca `getItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `Orden.getEstado`: Se invoca `getEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java` — `Orden.getId`: Se invoca `getId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java` — `InventarioServiceConfig.getBaseUrl`: Se invoca `getBaseUrl` sobre `InventarioServiceConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java` — `Orden.getItems`: Se invoca `getItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java` — `Orden.getClienteId`: Se invoca `getClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getId`: Se invoca `getId` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.actualizarId`: Se invoca `actualizarId` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getFechaTransaccion`: Se invoca `getFechaTransaccion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.actualizarFechaTransaccion`: Se invoca `actualizarFechaTransaccion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getTipoOperacion`: Se invoca `getTipoOperacion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getEntidadAfectada`: Se invoca `getEntidadAfectada` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getEntidadId`: Se invoca `getEntidadId` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getDetalles`: Se invoca `getDetalles` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getUsuarioResponsable`: Se invoca `getUsuarioResponsable` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `CrearOrdenUseCase.buscarPorId`: Se invoca `buscarPorId` sobre `CrearOrdenUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `CrearOrdenUseCase.buscarPorClienteId`: Se invoca `buscarPorClienteId` sobre `CrearOrdenUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `CrearOrdenUseCase.buscarPorEstado`: Se invoca `buscarPorEstado` sobre `CrearOrdenUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `CrearOrdenUseCase.cancelarOrden`: Se invoca `cancelarOrden` sobre `CrearOrdenUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `Orden.getItems`: Se invoca `getItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `Orden.getId`: Se invoca `getId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `Orden.getClienteId`: Se invoca `getClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `Orden.getEstado`: Se invoca `getEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `Orden.getFechaCreacion`: Se invoca `getFechaCreacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `ItemOrden.getProductoId`: Se invoca `getProductoId` sobre `ItemOrden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `ItemOrden.getCantidad`: Se invoca `getCantidad` sobre `ItemOrden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `ItemOrden.getPrecioUnitario`: Se invoca `getPrecioUnitario` sobre `ItemOrden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `ItemOrden.getSubtotal`: Se invoca `getSubtotal` sobre `ItemOrden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setId`: Se invoca `setId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setClienteId`: Se invoca `setClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setItems`: Se invoca `setItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setEstado`: Se invoca `setEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setFechaCreacion`: Se invoca `setFechaCreacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setFechaActualizacion`: Se invoca `setFechaActualizacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `InventarioServicePort.reservarInventario`: Se invoca `reservarInventario` sobre `InventarioServicePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapterTest.java` — `InventarioServiceConfig.getBaseUrl`: Se invoca `getBaseUrl` sobre `InventarioServiceConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapterTest.java` — `InventarioServiceConfig.getTimeout`: Se invoca `getTimeout` sobre `InventarioServiceConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setClienteId`: Se invoca `setClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setItems`: Se invoca `setItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setId`: Se invoca `setId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setEstado`: Se invoca `setEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setFechaCreacion`: Se invoca `setFechaCreacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setFechaActualizacion`: Se invoca `setFechaActualizacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior

### Brecha de conocimiento
Aplica principios y herramientas de verificación para escribir código limpio (KISS, SOLID, YAGNI y DRY).

### Misión / candidato
Candidato con experiencia Senior en Backend Java, enfocado en aplicar principios de código limpio y arquitectura de software en proyectos empresariales.

### Reto
- Tema: Aplicación de Código limpio y eficiente
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de Código Limpio en Proyecto Empresarial
- Tiempo estimado: 10 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición de Principios de Código Limpio — objetivo: Comprender y aplicar los principios KISS, SOLID, YAGNI y DRY en el código existente. — entregable (NO resolver): Documento que detalla las áreas refactorizadas y cómo se aplicaron los principios de código limpio.
- Fase 2: Implementación de Arquitectura de Software — objetivo: Aplicar patrones de arquitectura de software para mejorar la estructura del proyecto. — entregable (NO resolver): Diagrama de la nueva estructura del proyecto y documentación de los cambios realizados.
- Fase 3: Verificación y Validación del Código — objetivo: Escribir pruebas unitarias y de integración para verificar el código refactorizado. — entregable (NO resolver): Conjunto de pruebas unitarias y de integración que cubren el código refactorizado.
- Fase 4: Revisión y Mejora Continua — objetivo: Realizar una revisión del código y proponer mejoras continuas. — entregable (NO resolver): Documento con la revisión del código y propuestas de mejora.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>ordenes</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>ordenes</name>
    <description>Sistema de gestión de órdenes con integración a inventario externo</description>

    <properties>
        <java.version>21</java.version>
        <spring-boot.version>3.5.6</spring-boot.version>
        <resilience4j.version>2.2.0</resilience4j.version>
        <springdoc-openapi.version>2.6.0</springdoc-openapi.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi.version}</version>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <version>1.20.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>
</project>

// === ARCHIVO: src/main/java/com/pragma/ordenes/OrdenesApplication.java ===
package com.pragma.ordenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
public class OrdenesApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdenesApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerConfig circuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .permittedNumberOfCallsInHalfOpenState(2)
                .slidingWindowSize(2)
                .recordExceptions(
                    org.springframework.web.client.HttpServerErrorException.class,
                    org.springframework.web.client.ResourceAccessException.class,
                    java.util.concurrent.TimeoutException.class,
                    java.io.IOException.class
                )
                .build();
    }

    @Bean
    public TimeLimiterConfig timeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2))
                .build();
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: ordenes-service
  datasource:
    url: jdbc:postgresql://localhost:5432/ordenes_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 2000
      maximum-pool-size: 10
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
  mvc:
    throw-exception-if-no-handler-found: true
  web:
    resources:
      add-mappings: false

server:
  port: 8080
  servlet:
    context-path: /api

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  health:
    circuitbreakers:
      enabled: true

resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
        recordExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - org.springframework.web.client.ResourceAccessException
          - java.util.concurrent.TimeoutException
          - java.io.IOException
    instances:
      inventarioService:
        baseConfig: default
        failureRateThreshold: 30
        waitDurationInOpenState: 3s
  timelimiter:
    configs:
      default:
        timeoutDuration: 2s
    instances:
      inventarioService:
        baseConfig: default

inventario:
  service:
    url: http://localhost:8081/api/inventario
    timeout: 2000
    max-retries: 3

logging:
  level:
    com.pragma.ordenes: DEBUG
    org.springframework.web: INFO
    org.hibernate: INFO
    io.github.resilience4j: DEBUG

// === ARCHIVO: src/main/java/com/pragma/ordenes/domain/model/Orden.java ===
package com.pragma.ordenes.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Entidad de dominio que representa una orden de compra.
 * Contiene la lógica de negocio relacionada con la creación y validación de órdenes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orden {
    @NotNull(message = "El ID de la orden no puede ser nulo")
    private UUID id;

    @NotBlank(message = "El ID del cliente no puede estar vacío")
    @Size(max = 50, message = "El ID del cliente no puede exceder 50 caracteres")
    private String clienteId;

    @NotNull(message = "La lista de items no puede ser nula")
    @Size(min = 1, message = "La orden debe contener al menos un item")
    private List<ItemOrden> items;

    @NotNull(message = "El estado de la orden no puede ser nulo")
    private EstadoOrden estado;

    @NotNull(message = "La fecha de creación no puede ser nula")
    private LocalDateTime fechaCreacion;

    @NotNull(message = "La fecha de actualización no puede ser nula")
    private LocalDateTime fechaActualizacion;

    /**
     * Calcula el total de la orden sumando el precio de todos los items.
     * @return Total de la orden como BigDecimal
     */
    public BigDecimal calcularTotal() {
        return items.stream()
                .map(ItemOrden::getPrecioTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Valida que la orden tenga items con cantidades positivas.
     * @throws IllegalArgumentException si algún item tiene cantidad no positiva
     */
    public void validarItems() {
        if (items.stream().anyMatch(item -> item.getCantidad() <= 0)) {
            throw new IllegalArgumentException("Todos los items deben tener cantidad positiva");
        }
    }

    /**
     * Actualiza el estado de la orden y registra la fecha de actualización.
     * @param nuevoEstado Estado al que se actualizará la orden
     */
    public void actualizarEstado(EstadoOrden nuevoEstado) {
        this.estado = nuevoEstado;
        this.fechaActualizacion = LocalDateTime.now();
    }

    /**
     * Representa un item dentro de una orden.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ItemOrden {
        @NotNull(message = "El ID del producto no puede ser nulo")
        private UUID productoId;

        @NotBlank(message = "El nombre del producto no puede estar vacío")
        @Size(max = 100, message = "El nombre del producto no puede exceder 100 caracteres")
        private String nombreProducto;

        @NotNull(message = "La cantidad no puede ser nula")
        @Positive(message = "La cantidad debe ser positiva")
        private Integer cantidad;

        @NotNull(message = "El precio unitario no puede ser nulo")
        @Positive(message = "El precio unitario debe ser positivo")
        private BigDecimal precioUnitario;

        /**
         * Calcula el precio total del item.
         * @return Precio total como BigDecimal
         */
        public BigDecimal getPrecioTotal() {
            return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
        }
    }

    /**
     * Enumeración de estados posibles para una orden.
     */
    public enum EstadoOrden {
        PENDIENTE,
        PROCESANDO,
        COMPLETADA,
        CANCELADA,
        FALLIDA
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/domain/model/Transaccion.java ===
package com.pragma.ordenes.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad de dominio que representa una transacción para auditoría.
 * Registra todas las operaciones relevantes del sistema para trazabilidad.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {
    @NotNull(message = "El ID de la transacción no puede ser nulo")
    private UUID id;

    @NotBlank(message = "El tipo de operación no puede estar vacío")
    @Size(max = 50, message = "El tipo de operación no puede exceder 50 caracteres")
    private String tipoOperacion;

    @NotBlank(message = "La entidad afectada no puede estar vacía")
    @Size(max = 50, message = "La entidad afectada no puede exceder 50 caracteres")
    private String entidadAfectada;

    @NotNull(message = "El ID de la entidad no puede ser nulo")
    private UUID entidadId;

    @NotBlank(message = "Los detalles no pueden estar vacíos")
    @Size(max = 500, message = "Los detalles no pueden exceder 500 caracteres")
    private String detalles;

    @NotNull(message = "La fecha de la transacción no puede ser nula")
    private LocalDateTime fechaTransaccion;

    @Size(max = 100, message = "El usuario responsable no puede exceder 100 caracteres")
    private String usuarioResponsable;

    /**
     * Actualiza los detalles de la transacción.
     * @param nuevosDetalles Nuevos detalles a registrar
     */
    public void actualizarDetalles(String nuevosDetalles) {
        this.detalles = nuevosDetalles;
        this.fechaTransaccion = LocalDateTime.now();
    }

    /**
     * Crea una nueva transacción para registrar una operación.
     * @param tipoOperacion Tipo de operación realizada
     * @param entidadAfectada Entidad que fue modificada
     * @param entidadId ID de la entidad modificada
     * @param detalles Detalles adicionales de la operación
     * @return Nueva instancia de Transaccion
     */
    public static Transaccion crearTransaccion(String tipoOperacion, String entidadAfectada,
            UUID entidadId, String detalles) {
        return Transaccion.builder()
                .id(UUID.randomUUID())
                .tipoOperacion(tipoOperacion)
                .entidadAfectada(entidadAfectada)
                .entidadId(entidadId)
                .detalles(detalles)
                .fechaTransaccion(LocalDateTime.now())
                .usuarioResponsable("sistema")
                .build();
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/domain/ports/OrdenRepositoryPort.java ===
package com.pragma.ordenes.domain.ports;


import com.pragma.ordenes.domain.model.EstadoOrden;
import com.pragma.ordenes.domain.model.Orden;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interfaz de puerto que define las operaciones de persistencia para órdenes.
 * Es implementada por la capa de infraestructura pero definida por el dominio.
 */
public interface OrdenRepositoryPort {
    /**
     * Guarda una orden en el repositorio.
     * @param orden Orden a guardar
     * @return Orden guardada
     */
    Orden guardar(Orden orden);

    /**
     * Busca una orden por su ID.
     * @param id ID de la orden
     * @return Optional con la orden si existe
     */
    Optional<Orden> buscarPorId(UUID id);

    /**
     * Busca todas las órdenes de un cliente específico.
     * @param clienteId ID del cliente
     * @return Lista de órdenes del cliente
     */
    List<Orden> buscarPorClienteId(String clienteId);

    /**
     * Busca todas las órdenes con un estado específico.
     * @param estado Estado de la orden
     * @return Lista de órdenes con el estado dado
     */
    List<Orden> buscarPorEstado(Orden.EstadoOrden estado);

    /**
     * Actualiza el estado de una orden existente.
     * @param id ID de la orden
     * @param nuevoEstado Nuevo estado
     * @return Orden actualizada
     */
    Orden actualizarEstado(UUID id, Orden.EstadoOrden nuevoEstado);
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/domain/ports/InventarioServicePort.java ===
package com.pragma.ordenes.domain.ports;


import com.pragma.ordenes.domain.model.ItemOrden;
import com.pragma.ordenes.domain.model.Orden;
import java.util.List;
import java.util.UUID;

/**
 * Puerto que define la interfaz para interactuar con el servicio externo de inventario.
 * Este puerto es implementado por la capa de infraestructura (adaptadores)
 * y consumido por los casos de uso de la capa de aplicación.
 */
public interface InventarioServicePort {

    /**
     * Verifica la disponibilidad de todos los items de una orden en el inventario.
     * @param items Lista de items de la orden con productoId y cantidad
     * @return true si todos los items están disponibles, false en caso contrario
     */
    boolean verificarDisponibilidad(List<Orden.ItemOrden> items);

    /**
     * Decrementa el stock de los items en el inventario após la confirmación de la orden.
     * @param items Lista de items a decrementar
     * @throws Exception si ocurre un error al actualizar el inventario
     */
    void decrementarStock(List<Orden.ItemOrden> items) throws Exception;

    /**
     * Restaura el stock de los items en caso de fallo en el procesamiento de la orden.
     * @param items Lista de items a restaurar
     */
    void restaurarStock(List<Orden.ItemOrden> items);

    /**
     * Obtiene el precio de un producto específico del inventario externo.
     * @param productoId Identificador del producto
     * @return Precio del producto
     */
    java.math.BigDecimal obtenerPrecioProducto(UUID productoId);

    /**
     * Valida que un producto exista en el inventario.
     * @param productoId Identificador del producto
     * @return true si el producto existe, false en caso contrario
     */
    boolean validarProductoExistente(UUID productoId);
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/domain/ports/TransaccionRepositoryPort.java ===
package com.pragma.ordenes.domain.ports;

import com.pragma.ordenes.domain.model.Transaccion;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto que define las operaciones de persistencia para el registro de transacciones.
 * Este puerto sigue el principio de inversión de dependencias: el dominio define la interfaz,
 * la infraestructura provee la implementación.
 */
public interface TransaccionRepositoryPort {

    /**
     * Persiste una nueva transacción en el registro de auditoría.
     * @param transaccion La transacción a guardar
     * @return La transacción guardada con su ID asignado
     */
    Transaccion guardar(Transaccion transaccion);

    /**
     * Busca una transacción por su identificador único.
     * @param id Identificador de la transacción
     * @return Optional con la transacción si existe
     */
    Optional<Transaccion> buscarPorId(UUID id);

    /**
     * Recupera todas las transacciones asociadas a una entidad específica.
     * @param entidadAfectada Nombre de la entidad (ej. "ORDEN", "INVENTARIO")
     * @param entidadId Identificador de la entidad
     * @return Lista de transacciones relacionadas
     */
    List<Transaccion> buscarPorEntidad(String entidadAfectada, UUID entidadId);

    /**
     * Obtiene transacciones por tipo de operación.
     * @param tipoOperacion Tipo de operación (ej. "CREACION", "ACTUALIZACION", "CANCELACION")
     * @return Lista de transacciones del tipo especificado
     */
    List<Transaccion> buscarPorTipoOperacion(String tipoOperacion);

    /**
     * Recupera transacciones dentro de un rango de fechas.
     * @param fechaInicio Fecha inicial del rango
     * @param fechaFin Fecha final del rango
     * @return Lista de transacciones en el rango especificado
     */
    List<Transaccion> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Busca una transacción por su identificador de correlación para garantizar idempotencia.
     * @param correlationId Identificador de correlación
     * @return Optional con la transacción existente si ya fue procesada
     */
    Optional<Transaccion> buscarPorCorrelationId(String correlationId);

    /**
     * Actualiza los detalles de una transacción existente.
     * @param id Identificador de la transacción
     * @param nuevosDetalles Nuevos detalles a registrar
     * @return La transacción actualizada
     */
    Transaccion actualizarDetalles(UUID id, String nuevosDetalles);

    /**
     * Cuenta el número total de transacciones en el sistema.
     * @return Cantidad total de transacciones
     */
    long contarTransacciones();
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java ===
package com.pragma.ordenes.application.usecases;



import com.pragma.ordenes.domain.model.ItemOrden;
import com.pragma.ordenes.domain.model.EstadoOrden;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.model.Transaccion;
import com.pragma.ordenes.domain.ports.InventarioServicePort;
import com.pragma.ordenes.domain.ports.OrdenRepositoryPort;
import com.pragma.ordenes.domain.ports.TransaccionRepositoryPort;
import com.pragma.ordenes.infrastructure.exceptions.InventarioServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Caso de uso para la creación de órdenes.
 * Coordina la lógica de negocio y la interacción con los puertos de inventario
 * y registro de transacciones. Implementa el patrón de aplicación de casos de uso
 * manteniendo el dominio limpio de dependencias de infraestructura.
 */
@Service
public class CrearOrdenUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CrearOrdenUseCase.class);

    private final OrdenRepositoryPort ordenRepository;
    private final InventarioServicePort inventarioService;
    private final TransaccionRepositoryPort transaccionRepository;

    public CrearOrdenUseCase(
            OrdenRepositoryPort ordenRepository,
            InventarioServicePort inventarioService,
            TransaccionRepositoryPort transaccionRepository) {
        this.ordenRepository = ordenRepository;
        this.inventarioService = inventarioService;
        this.transaccionRepository = transaccionRepository;
    }

    /**
     * Ejecuta el flujo de creación de una orden.
     * Este método orquesta todo el proceso: validación de la orden,
     * verificación de disponibilidad en inventario, persistencia de la orden,
     * decremento de stock y registro de transacciones para auditoría.
     *
     * @param orden La orden a crear
     * @param usuarioResponsable Usuario que realiza la operación
     * @return La orden creada con su ID asignado
     * @throws InventarioServiceException si el inventario no está disponible
     * @throws IllegalArgumentException si la orden no es válida
     */
    public Orden ejecutar(Orden orden, String usuarioResponsable) {
        logger.info("Iniciando proceso de creación de orden para cliente: {}", orden.getClienteId());

        validarOrden(orden);
        verificarDisponibilidadInventario(orden);
        Orden ordenGuardada = persistirOrden(orden);
        actualizarInventario(ordenGuardada);
        registrarTransacciones(ordenGuardada, usuarioResponsable);

        logger.info("Orden {} creada exitosamente para cliente: {}",
                ordenGuardada.getId(), ordenGuardada.getClienteId());
        return ordenGuardada;
    }

    private void validarOrden(Orden orden) {
        logger.debug("Validando orden para cliente: {}", orden.getClienteId());

        if (orden.getClienteId() == null || orden.getClienteId().isBlank()) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }

        if (orden.getItems() == null || orden.getItems().isEmpty()) {
            throw new IllegalArgumentException("La orden debe contener al menos un item");
        }

        orden.validarItems();

        if (orden.calcularTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El total de la orden debe ser mayor a cero");
        }

        logger.debug("Orden validada correctamente. Total: {}", orden.calcularTotal());
    }

    private void verificarDisponibilidadInventario(Orden orden) {
        logger.debug("Verificando disponibilidad en inventario para {} items", orden.getItems().size());

        boolean disponible = inventarioService.verificarDisponibilidad(orden.getItems());

        if (!disponible) {
            logger.error("No hay disponibilidad en inventario para la orden del cliente: {}",
                    orden.getClienteId());
            throw new InventarioServiceException(
                    "No todos los items solicitados están disponibles en inventario");
        }

        logger.debug("Inventario verificado: todos los items disponibles");
    }

    private Orden persistirOrden(Orden orden) {
        logger.debug("Persistiendo orden para cliente: {}", orden.getClienteId());

        orden.setId(UUID.randomUUID());
        orden.setFechaCreacion(LocalDateTime.now());
        orden.setFechaActualizacion(LocalDateTime.now());
        orden.setEstado(Orden.EstadoOrden.PENDIENTE);

        Orden ordenGuardada = ordenRepository.guardar(orden);

        logger.info("Orden {} persistida con estado: {}",
                ordenGuardada.getId(), ordenGuardada.getEstado());
        return ordenGuardada;
    }

    private void actualizarInventario(Orden orden) {
        logger.debug("Actualizando inventario para orden: {}", orden.getId());

        try {
            inventarioService.decrementarStock(orden.getItems());
            logger.info("Inventario actualizado correctamente para orden: {}", orden.getId());
        } catch (Exception e) {
            logger.error("Error al actualizar inventario para orden: {}. Restaurando estado...",
                    orden.getId(), e);
            inventarioService.restaurarStock(orden.getItems());
            throw new InventarioServiceException(
                    "Error al actualizar el inventario: " + e.getMessage(), e);
        }
    }

    private void registrarTransacciones(Orden orden, String usuarioResponsable) {
        logger.debug("Registrando transacciones de auditoría para orden: {}", orden.getId());

        String correlationId = UUID.randomUUID().toString();

        Transaccion transaccionOrden = Transaccion.crearTransaccion(
                "CREACION",
                "ORDEN",
                orden.getId(),
                String.format("Orden creada para cliente %s con %d items, total: %s",
                        orden.getClienteId(),
                        orden.getItems().size(),
                        orden.calcularTotal()),
                usuarioResponsable
        );
        transaccionOrden.setCorrelationId(correlationId);
        transaccionRepository.guardar(transaccionOrden);

        for (Orden.ItemOrden item : orden.getItems()) {
            Transaccion transaccionInventario = Transaccion.crearTransaccion(
                    "DECREMENTO_STOCK",
                    "INVENTARIO",
                    item.getProductoId(),
                    String.format("Stock decrementado en %d unidades para orden %s",
                            item.getCantidad(),
                            orden.getId()),
                    usuarioResponsable
            );
            transaccionInventario.setCorrelationId(correlationId);
            transaccionRepository.guardar(transaccionInventario);
        }

        logger.info("Transacciones de auditoría registradas para orden: {}", orden.getId());
    }

    /**
     * Calcula el total de una orden sin persistirla.
     * Útil para previsualización antes de confirmar la compra.
     *
     * @param orden La orden a calcular
     * @return Total calculado
     */
    public BigDecimal calcularTotalPreview(Orden orden) {
        if (orden == null || orden.getItems() == null) {
            return BigDecimal.ZERO;
        }
        return orden.calcularTotal();
    }

    /**
     * Verifica si una orden puede ser cancelada según su estado actual.
     *
     * @param ordenId ID de la orden
     * @return true si la orden puede ser cancelada
     */
    public boolean puedeCancelar(UUID ordenId) {
        return ordenRepository.buscarPorId(ordenId)
                .map(orden -> orden.getEstado() == Orden.EstadoOrden.PENDIENTE ||
                        orden.getEstado() == Orden.EstadoOrden.CONFIRMADA)
                .orElse(false);
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/application/usecases/RegistrarTransaccionUseCase.java ===
package com.pragma.ordenes.application.usecases;

import com.pragma.ordenes.domain.model.Transaccion;
import com.pragma.ordenes.domain.ports.TransaccionRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrarTransaccionUseCase {

    private static final Logger log = LoggerFactory.getLogger(RegistrarTransaccionUseCase.class);

    private final TransaccionRepositoryPort transaccionRepository;

    public RegistrarTransaccionUseCase(TransaccionRepositoryPort transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Transactional
    public Transaccion ejecutar(String tipoOperacion, String entidadAfectada, UUID entidadId, 
                                 String usuarioResponsable, String detalles) {
        log.info("Iniciando registro de transacción: tipo={}, entidad={}, entidadId={}",
                tipoOperacion, entidadAfectada, entidadId);

        Transaccion transaccion = Transaccion.crearTransaccion(
                tipoOperacion,
                entidadAfectada,
                entidadId,
                usuarioResponsable,
                detalles
        );

        Transaccion guardada = transaccionRepository.guardar(transaccion);
        log.info("Transacción registrada exitosamente con ID: {}", guardada.getId());

        return guardada;
    }

    @Transactional(readOnly = true)
    public Optional<Transaccion> buscarPorId(UUID id) {
        log.debug("Buscando transacción por ID: {}", id);
        return transaccionRepository.buscarPorId(id);
    }

    @Transactional(readOnly = true)
    public java.util.List<Transaccion> buscarPorEntidadAfectada(String entidadAfectada) {
        log.debug("Buscando transacciones para entidad: {}", entidadAfectada);
        return transaccionRepository.buscarPorEntidadAfectada(entidadAfectada);
    }

    @Transactional(readOnly = true)
    public java.util.List<Transaccion> buscarPorEntidadId(UUID entidadId) {
        log.debug("Buscando transacciones para entidadId: {}", entidadId);
        return transaccionRepository.buscarPorEntidadId(entidadId);
    }

    @Transactional
    public Transaccion actualizarDetalles(UUID id, String nuevosDetalles) {
        log.info("Actualizando detalles de transacción: {}", id);
        Optional<Transaccion> transaccionOpt = transaccionRepository.buscarPorId(id);
        
        if (transaccionOpt.isEmpty()) {
            log.error("Transacción no encontrada: {}", id);
            throw new IllegalArgumentException("Transacción no encontrada con ID: " + id);
        }
        
        Transaccion transaccion = transaccionOpt.get();
        transaccion.actualizarDetalles(nuevosDetalles);
        return transaccionRepository.guardar(transaccion);
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java ===
package com.pragma.ordenes.infrastructure.adapters;


import com.pragma.ordenes.domain.model.EstadoOrden;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.ports.OrdenRepositoryPort;
import com.pragma.ordenes.infrastructure.persistence.OrdenEntity;
import com.pragma.ordenes.infrastructure.persistence.OrdenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OrdenJpaAdapter implements OrdenRepositoryPort {

    private static final Logger log = LoggerFactory.getLogger(OrdenJpaAdapter.class);

    private final OrdenRepository ordenRepository;
    private final OrdenMapper ordenMapper;

    public OrdenJpaAdapter(OrdenRepository ordenRepository, OrdenMapper ordenMapper) {
        this.ordenRepository = ordenRepository;
        this.ordenMapper = ordenMapper;
    }

    @Override
    public Orden guardar(Orden orden) {
        log.info("Guardando orden para cliente: {}", orden.getClienteId());
        
        if (orden.getId() == null) {
            orden = new Orden(
                    UUID.randomUUID(),
                    orden.getClienteId(),
                    orden.getItems(),
                    orden.getEstado(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
        }

        OrdenEntity entity = ordenMapper.toEntity(orden);
        OrdenEntity savedEntity = ordenRepository.save(entity);
        log.info("Orden guardada exitosamente con ID: {}", savedEntity.getId());
        
        return ordenMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Orden> buscarPorId(UUID id) {
        log.debug("Buscando orden por ID: {}", id);
        return ordenRepository.findById(id)
                .map(ordenMapper::toDomain);
    }

    @Override
    public List<Orden> buscarPorClienteId(String clienteId) {
        log.debug("Buscando órdenes para cliente: {}", clienteId);
        return ordenRepository.findByClienteId(clienteId)
                .stream()
                .map(ordenMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Orden> buscarPorEstado(Orden.EstadoOrden estado) {
        log.debug("Buscando órdenes por estado: {}", estado);
        return ordenRepository.findByEstado(estado)
                .stream()
                .map(ordenMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Orden actualizarEstado(UUID id, Orden.EstadoOrden nuevoEstado) {
        log.info("Actualizando estado de orden {} a {}", id, nuevoEstado);
        
        Optional<OrdenEntity> entityOpt = ordenRepository.findById(id);
        if (entityOpt.isEmpty()) {
            log.error("Orden no encontrada para actualización: {}", id);
            throw new IllegalArgumentException("Orden no encontrada con ID: " + id);
        }
        
        OrdenEntity entity = entityOpt.get();
        entity.setEstado(nuevoEstado);
        entity.setFechaActualizacion(LocalDateTime.now());
        
        OrdenEntity savedEntity = ordenRepository.save(entity);
        log.info("Estado de orden actualizado exitosamente");
        
        return ordenMapper.toDomain(savedEntity);
    }

    public List<Orden> buscarTodas() {
        log.debug("Buscando todas las órdenes");
        return ordenRepository.findAll()
                .stream()
                .map(ordenMapper::toDomain)
                .collect(Collectors.toList());
    }

    public void eliminar(UUID id) {
        log.info("Eliminando orden: {}", id);
        ordenRepository.deleteById(id);
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java ===
package com.pragma.ordenes.infrastructure.adapters;

import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.ports.InventarioServicePort;
import com.pragma.ordenes.infrastructure.config.InventarioServiceConfig;
import com.pragma.ordenes.infrastructure.exceptions.InventarioServiceException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InventarioRestAdapter implements InventarioServicePort {

    private static final Logger log = LoggerFactory.getLogger(InventarioRestAdapter.class);
    private static final int TIMEOUT_SECONDS = 2;

    private final RestTemplate restTemplate;
    private final InventarioServiceConfig config;

    public InventarioRestAdapter(RestTemplate restTemplate, InventarioServiceConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @Override
    @CircuitBreaker(name = "inventarioCircuitBreaker", fallbackMethod = "reservarInventarioFallback")
    @Retry(name = "inventarioRetry")
    public boolean reservarInventario(Orden orden) {
        log.info("Iniciando reserva de inventario para orden: {}", orden.getId());
        
        validarOrden(orden);
        
        Map<String, Object> requestBody = construirRequestBody(orden);
        HttpEntity<Map<String, Object>> request = construirRequest(requestBody);
        
        String url = config.getBaseUrl() + "/api/inventario/reservar";
        log.debug("Llamando a servicio de inventario: {}", url);
        
        try {
            Map<String, Object> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    Map.class
            ).getBody();
            
            boolean success = response != null && Boolean.TRUE.equals(response.get("success"));
            log.info("Reserva de inventario completada para orden {}: {}", orden.getId(), success);
            return success;
            
        } catch (Exception e) {
            log.error("Error al reservar inventario para orden {}: {}", orden.getId(), e.getMessage());
            throw new InventarioServiceException("Error al reservar inventario: " + e.getMessage(), e);
        }
    }

    @Override
    @CircuitBreaker(name = "inventarioCircuitBreaker", fallbackMethod = "confirmarReservaFallback")
    @Retry(name = "inventarioRetry")
    public boolean confirmarReserva(String ordenId) {
        log.info("Confirmando reserva de inventario para orden: {}", ordenId);
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("ordenId", ordenId);
        HttpEntity<Map<String, Object>> request = construirRequest(requestBody);
        
        String url = config.getBaseUrl() + "/api/inventario/confirmar";
        
        try {
            Map<String, Object> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    Map.class
            ).getBody();
            
            boolean success = response != null && Boolean.TRUE.equals(response.get("success"));
            log.info("Confirmación de reserva completada para orden {}: {}", ordenId, success);
            return success;
            
        } catch (Exception e) {
            log.error("Error al confirmar reserva para orden {}: {}", ordenId, e.getMessage());
            throw new InventarioServiceException("Error al confirmar reserva: " + e.getMessage(), e);
        }
    }

    @Override
    @CircuitBreaker(name = "inventarioCircuitBreaker", fallbackMethod = "cancelarReservaFallback")
    @Retry(name = "inventarioRetry")
    public boolean cancelarReserva(String ordenId) {
        log.info("Cancelando reserva de inventario para orden: {}", ordenId);
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("ordenId", ordenId);
        HttpEntity<Map<String, Object>> request = construirRequest(requestBody);
        
        String url = config.getBaseUrl() + "/api/inventario/cancelar";
        
        try {
            Map<String, Object> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    Map.class
            ).getBody();
            
            boolean success = response != null && Boolean.TRUE.equals(response.get("success"));
            log.info("Cancelación de reserva completada para orden {}: {}", ordenId, success);
            return success;
            
        } catch (Exception e) {
            log.error("Error al cancelar reserva para orden {}: {}", ordenId, e.getMessage());
            throw new InventarioServiceException("Error al cancelar reserva: " + e.getMessage(), e);
        }
    }

    private void validarOrden(Orden orden) {
        if (orden == null) {
            throw new InventarioServiceException("La orden no puede ser nula");
        }
        if (orden.getId() == null) {
            throw new InventarioServiceException("El ID de la orden no puede ser nulo");
        }
        if (orden.getItems() == null || orden.getItems().isEmpty()) {
            throw new InventarioServiceException("La orden debe tener al menos un item");
        }
    }

    private Map<String, Object> construirRequestBody(Orden orden) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("ordenId", orden.getId().toString());
        requestBody.put("clienteId", orden.getClienteId());
        
        List<Map<String, Object>> items = orden.getItems().stream()
                .map(item -> {
                    Map<String, Object> itemMap = new HashMap<>();
                    itemMap.put("productoId", item.getProductoId());
                    itemMap.put("cantidad", item.getCantidad());
                    return itemMap;
                })
                .collect(Collectors.toList());
        
        requestBody.put("items", items);
        return requestBody;
    }

    private HttpEntity<Map<String, Object>> construirRequest(Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Service-Name", "ordenes-service");
        return new HttpEntity<>(body, headers);
    }

    private boolean reservarInventarioFallback(Orden orden, Exception e) {
        log.warn("FALLBACK: Reservar inventario para orden {} - Error: {}", orden.getId(), e.getMessage());
        return false;
    }

    private boolean confirmarReservaFallback(String ordenId, Exception e) {
        log.warn("FALLBACK: Confirmar reserva para orden {} - Error: {}", ordenId, e.getMessage());
        return false;
    }

    private boolean cancelarReservaFallback(String ordenId, Exception e) {
        log.warn("FALLBACK: Cancelar reserva para orden {} - Error: {}", ordenId, e.getMessage());
        return false;
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java ===
package com.pragma.ordenes.infrastructure.adapters;

import com.pragma.ordenes.domain.model.Transaccion;
import com.pragma.ordenes.domain.ports.TransaccionRepositoryPort;
import com.pragma.ordenes.infrastructure.persistence.entity.TransaccionEntity;
import com.pragma.ordenes.infrastructure.persistence.repository.TransaccionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransaccionJpaAdapter implements TransaccionRepositoryPort {

    private final TransaccionRepository transaccionRepository;

    public TransaccionJpaAdapter(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Transaccion guardar(Transaccion transaccion) {
        if (transaccion.getId() == null) {
            transaccion.actualizarId(UUID.randomUUID());
        }
        if (transaccion.getFechaTransaccion() == null) {
            transaccion.actualizarFechaTransaccion(LocalDateTime.now());
        }
        TransaccionEntity entity = toEntity(transaccion);
        TransaccionEntity savedEntity = transaccionRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Transaccion> buscarPorId(UUID id) {
        return transaccionRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Transaccion> buscarPorEntidadAfectadaYEntidadId(String entidadAfectada, UUID entidadId) {
        return transaccionRepository.findByEntidadAfectadaAndEntidadId(entidadAfectada, entidadId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaccion> buscarPorUsuarioResponsable(String usuarioResponsable) {
        return transaccionRepository.findByUsuarioResponsable(usuarioResponsable)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaccion> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return transaccionRepository.findByFechaTransaccionBetween(fechaInicio, fechaFin)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existeTransaccionConDetalles(String tipoOperacion, String entidadAfectada, 
                                                  UUID entidadId, String detalles) {
        return transaccionRepository.existsByTipoOperacionAndEntidadAfectadaAndEntidadIdAndDetalles(
                tipoOperacion, entidadAfectada, entidadId, detalles);
    }

    @Override
    public List<Transaccion> buscarTodos() {
        return transaccionRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private TransaccionEntity toEntity(Transaccion transaccion) {
        TransaccionEntity entity = new TransaccionEntity();
        if (transaccion.getId() != null) {
            entity.setId(transaccion.getId());
        }
        entity.setTipoOperacion(transaccion.getTipoOperacion());
        entity.setEntidadAfectada(transaccion.getEntidadAfectada());
        entity.setEntidadId(transaccion.getEntidadId());
        entity.setDetalles(transaccion.getDetalles());
        entity.setFechaTransaccion(transaccion.getFechaTransaccion());
        entity.setUsuarioResponsable(transaccion.getUsuarioResponsable());
        return entity;
    }

    private Transaccion toDomain(TransaccionEntity entity) {
        return Transaccion.builder()
                .id(entity.getId())
                .tipoOperacion(entity.getTipoOperacion())
                .entidadAfectada(entity.getEntidadAfectada())
                .entidadId(entity.getEntidadId())
                .detalles(entity.getDetalles())
                .fechaTransaccion(entity.getFechaTransaccion())
                .usuarioResponsable(entity.getUsuarioResponsable())
                .build();
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java ===
package com.pragma.ordenes.infrastructure.controllers;

import com.pragma.ordenes.application.usecases.CrearOrdenUseCase;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.model.Orden.EstadoOrden;
import com.pragma.ordenes.domain.model.Orden.ItemOrden;
import com.pragma.ordenes.infrastructure.adapters.dto.OrdenRequest;
import com.pragma.ordenes.infrastructure.adapters.dto.OrdenResponse;
import com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenRequest;
import com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ordenes")
@Tag(name = "Órdenes", description = "API para la gestión de órdenes de compra")
public class OrdenController {

    private final CrearOrdenUseCase crearOrdenUseCase;

    public OrdenController(CrearOrdenUseCase crearOrdenUseCase) {
        this.crearOrdenUseCase = crearOrdenUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva orden", description = "Crea una nueva orden de compra y reserva el inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Orden creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "Conflicto de inventario"),
            @ApiResponse(responseCode = "503", description = "Servicio de inventario no disponible")
    })
    public ResponseEntity<OrdenResponse> crearOrden(@Valid @RequestBody OrdenRequest request) {
        Orden orden = mapRequestToDomain(request);
        Orden ordenCreada = crearOrdenUseCase.ejecutar(orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapDomainToResponse(ordenCreada));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener orden por ID", description = "Retorna los detalles de una orden específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden encontrada"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    public ResponseEntity<OrdenResponse> obtenerOrdenPorId(
            @Parameter(description = "ID de la orden", required = true) @PathVariable UUID id) {
        Orden orden = crearOrdenUseCase.buscarPorId(id);
        if (orden == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapDomainToResponse(orden));
    }

    @GetMapping
    @Operation(summary = "Listar órdenes por cliente", description = "Retorna todas las órdenes de un cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de órdenes retornada")
    })
    public ResponseEntity<List<OrdenResponse>> listarOrdenesPorCliente(
            @Parameter(description = "ID del cliente", required = true) @RequestParam String clienteId) {
        List<Orden> ordenes = crearOrdenUseCase.buscarPorClienteId(clienteId);
        List<OrdenResponse> responses = ordenes.stream()
                .map(this::mapDomainToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Listar órdenes por estado", description = "Retorna todas las órdenes con un estado específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de órdenes retornada")
    })
    public ResponseEntity<List<OrdenResponse>> listarOrdenesPorEstado(
            @Parameter(description = "Estado de la orden", required = true) @PathVariable EstadoOrden estado) {
        List<Orden> ordenes = crearOrdenUseCase.buscarPorEstado(estado);
        List<OrdenResponse> responses = ordenes.stream()
                .map(this::mapDomainToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar orden", description = "Cancela una orden existente liberando el inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden cancelada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada"),
            @ApiResponse(responseCode = "409", description = "La orden no puede ser cancelada en su estado actual")
    })
    public ResponseEntity<OrdenResponse> cancelarOrden(
            @Parameter(description = "ID de la orden", required = true) @PathVariable UUID id) {
        Orden ordenCancelada = crearOrdenUseCase.cancelarOrden(id);
        if (ordenCancelada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapDomainToResponse(ordenCancelada));
    }

    private Orden mapRequestToDomain(OrdenRequest request) {
        List<ItemOrden> items = request.getItems().stream()
                .map(this::mapItemRequestToDomain)
                .collect(Collectors.toList());
        
        return Orden.builder()
                .id(request.getId() != null ? UUID.fromString(request.getId()) : null)
                .clienteId(request.getClienteId())
                .items(items)
                .estado(EstadoOrden.PENDIENTE)
                .build();
    }

    private ItemOrden mapItemRequestToDomain(ItemOrdenRequest request) {
        return ItemOrden.builder()
                .productoId(request.getProductoId())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .build();
    }

    private OrdenResponse mapDomainToResponse(Orden orden) {
        List<ItemOrdenResponse> items = orden.getItems().stream()
                .map(this::mapItemDomainToResponse)
                .collect(Collectors.toList());
        
        return OrdenResponse.builder()
                .id(orden.getId().toString())
                .clienteId(orden.getClienteId())
                .items(items)
                .estado(orden.getEstado().name())
                .total(orden.calcularTotal().toString())
                .fechaCreacion(orden.getFechaCreacion() != null ? orden.getFechaCreacion().toString() : null)
                .build();
    }

    private ItemOrdenResponse mapItemDomainToResponse(ItemOrden item) {
        return ItemOrdenResponse.builder()
                .productoId(item.getProductoId())
                .cantidad(item.getCantidad())
                .precioUnitario(item.getPrecioUnitario().toString())
                .subtotal(item.getSubtotal().toString())
                .build();
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/infrastructure/exceptions/GlobalExceptionHandler.java ===
package com.pragma.ordenes.infrastructure.exceptions;


import com.pragma.ordenes.domain.model.Orden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InventarioServiceException.class)
    public ResponseEntity<ErrorResponse> handleInventarioServiceException(
            InventarioServiceException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error("Servicio de Inventario No Disponible")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

    @ExceptionHandler(InventarioInsuficienteException.class)
    public ResponseEntity<ErrorResponse> handleInventarioInsuficienteException(
            InventarioInsuficienteException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Inventario Insuficiente")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Valor inválido",
                        (existing, replacement) -> existing
                ));
        
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Error de Validación")
                .message("Los datos de entrada no son válidos")
                .path(request.getDescription(false).replace("uri=", ""))
                .errors(errors)
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(OrdenNoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleOrdenNoEncontradaException(
            OrdenNoEncontradaException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Orden No Encontrada")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(OrdenNoCancelableException.class)
    public ResponseEntity<ErrorResponse> handleOrdenNoCancelableException(
            OrdenNoCancelableException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Orden No Cancelable")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Error Interno del Servidor")
                .message("Ha ocurrido un error inesperado. Por favor, contacte al administrador.")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @lombok.Data
    @lombok.Builder
    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
    }

    @lombok.Data
    @lombok.Builder
    public static class ValidationErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private Map<String, String> errors;
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/infrastructure/exceptions/InventarioServiceException.java ===
package com.pragma.ordenes.infrastructure.exceptions;

public class InventarioServiceException extends RuntimeException {
    private final String codigoError;
    private final String detalleTecnico;

    public InventarioServiceException(String mensaje) {
        super(mensaje);
        this.codigoError = "INV_ERROR_GENERICO";
        this.detalleTecnico = null;
    }

    public InventarioServiceException(String mensaje, String codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
        this.detalleTecnico = null;
    }

    public InventarioServiceException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = "INV_ERROR_GENERICO";
        this.detalleTecnico = causa != null ? causa.getMessage() : null;
    }

    public InventarioServiceException(String mensaje, String codigoError, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.detalleTecnico = causa != null ? causa.getMessage() : null;
    }

    public InventarioServiceException(String mensaje, String codigoError, String detalleTecnico, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.detalleTecnico = detalleTecnico;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public String getDetalleTecnico() {
        return detalleTecnico;
    }

    public boolean isTemporal() {
        return "INV_ERROR_TIMEOUT".equals(codigoError) || 
               "INV_ERROR_CONEXION".equals(codigoError) ||
               "INV_ERROR_SERVICIO_NO_DISPONIBLE".equals(codigoError);
    }

    public static InventarioServiceException timeout(String mensaje) {
        return new InventarioServiceException(mensaje, "INV_ERROR_TIMEOUT");
    }

    public static InventarioServiceException conexion(String mensaje, Throwable causa) {
        return new InventarioServiceException(mensaje, "INV_ERROR_CONEXION", causa);
    }

    public static InventarioServiceException servicioNoDisponible(String mensaje) {
        return new InventarioServiceException(mensaje, "INV_ERROR_SERVICIO_NO_DISPONIBLE");
    }

    public static InventarioServiceException respuestaInvalida(String mensaje, Throwable causa) {
        return new InventarioServiceException(mensaje, "INV_ERROR_RESPUESTA_INVALIDA", causa);
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordenes/infrastructure/config/InventarioServiceConfig.java ===
package com.pragma.ordenes.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ConfigurationProperties(prefix = "app.inventario")
@Getter
@Setter
@Slf4j
public class InventarioServiceConfig {
    private String baseUrl;
    private int connectTimeout = 5000;
    private int readTimeout = 10000;
    private int maxRetries = 3;
    private long retryDelay = 1000L;
    private boolean enabled = true;

    @Bean
    public RestTemplate inventarioRestTemplate() {
        log.info("Configurando RestTemplate para servicio de inventario: baseUrl={}, timeouts=[{}ms, {}ms]",
                baseUrl, connectTimeout, readTimeout);
        
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        
        RestTemplate restTemplate = new RestTemplate(factory);
        
        return restTemplate;
    }

    public String getUrlBase() {
        return baseUrl != null ? baseUrl.trim() : null;
    }

    public String getEndpointVerificacionStock() {
        return getUrlBase() + "/api/v1/inventario/verificar-stock";
    }

    public String getEndpointReservarStock() {
        return getUrlBase() + "/api/v1/inventario/reservar";
    }

    public String getEndpointConfirmarReserva() {
        return getUrlBase() + "/api/v1/inventario/confirmar-reserva";
    }

    public String getEndpointCancelarReserva() {
        return getUrlBase() + "/api/v1/inventario/cancelar-reserva";
    }

    public boolean isIntegracionHabilitada() {
        return enabled && baseUrl != null && !baseUrl.isBlank();
    }

    public void validarConfiguracion() {
        if (!enabled) {
            log.warn("Integración con servicio de inventario deshabilitada");
            return;
        }
        
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("La URL base del servicio de inventario no está configurada");
        }
        
        if (connectTimeout <= 0) {
            throw new IllegalStateException("El timeout de conexión debe ser mayor a 0");
        }
        
        if (readTimeout <= 0) {
            throw new IllegalStateException("El timeout de lectura debe ser mayor a 0");
        }
        
        if (readTimeout > 2000) {
            log.warn("El timeout de lectura excede el máximo recomendado de 2000ms. Valor actual: {}ms", readTimeout);
        }
        
        log.info("Configuración del servicio de inventario validada correctamente");
    }
}

// === ARCHIVO: src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java ===
package com.pragma.ordenes.application.usecases;


import com.pragma.ordenes.domain.model.EstadoOrden;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.model.Transaccion;
import com.pragma.ordenes.domain.ports.InventarioServicePort;
import com.pragma.ordenes.domain.ports.OrdenRepositoryPort;
import com.pragma.ordenes.domain.ports.TransaccionRepositoryPort;
import com.pragma.ordenes.infrastructure.exceptions.InventarioServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitarios para CrearOrdenUseCase")
class CrearOrdenUseCaseTest {

    @Mock
    private OrdenRepositoryPort ordenRepository;

    @Mock
    private InventarioServicePort inventarioService;

    @Mock
    private TransaccionRepositoryPort transaccionRepository;

    @InjectMocks
    private CrearOrdenUseCase crearOrdenUseCase;

    private Orden ordenValida;

    @BeforeEach
    void setUp() {
        ordenValida = new Orden();
        ordenValida.setId(UUID.randomUUID());
        ordenValida.setClienteId("cliente-123");
        ordenValida.setItems(new ArrayList<>());
        ordenValida.setEstado(Orden.EstadoOrden.PENDIENTE);
        ordenValida.setFechaCreacion(LocalDateTime.now());
        ordenValida.setFechaActualizacion(LocalDateTime.now());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe crear una orden exitosamente cuando el inventario responde correctamente")
    void debeCrearOrdenExitosamente_CuandoInventarioResponde() {
        // Given
        when(inventarioService.reservarInventario(any())).thenReturn(true);
        when(ordenRepository.guardar(any(Orden.class))).thenReturn(ordenValida);
        when(transaccionRepository.guardar(any(Transaccion.class))).thenAnswer(i -> i.getArgument(0));

        // When
        Orden resultado = crearOrdenUseCase.ejecutar(ordenValida);

        // Then
        assertNotNull(resultado);
        verify(ordenRepository, times(1)).guardar(any(Orden.class));
        verify(inventarioService, times(1)).reservarInventario(any());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe lanzar excepción cuando el inventario no tiene disponibilidad")
    void debeLanzarExcepcion_CuandoInventarioNoDisponible() {
        // Given
        when(inventarioService.reservarInventario(any())).thenReturn(false);

        // When & Then
        assertThrows(InventarioServiceException.class, () -> {
            crearOrdenUseCase.ejecutar(ordenValida);
        });
        verify(ordenRepository, never()).guardar(any(Orden.class));
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe registrar transacción al crear orden exitosamente")
    void debeRegistrarTransaccion_CuandoOrdenCreada() {
        // Given
        when(inventarioService.reservarInventario(any())).thenReturn(true);
        when(ordenRepository.guardar(any(Orden.class))).thenReturn(ordenValida);
        when(transaccionRepository.guardar(any(Transaccion.class))).thenAnswer(i -> i.getArgument(0));

        // When
        crearOrdenUseCase.ejecutar(ordenValida);

        // Then
        verify(transaccionRepository, times(1)).guardar(any(Transaccion.class));
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe validar que la orden tenga items antes de procesar")
    void debeValidarItems_CuandoOrdenNoTieneItems() {
        // Given
        ordenValida.setItems(new ArrayList<>());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            crearOrdenUseCase.ejecutar(ordenValida);
        });
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe manejar error de conexión con inventario")
    void debeManejarErrorConexionInventario() {
        // Given
        when(inventarioService.reservarInventario(any()))
                .thenThrow(new InventarioServiceException("Error de conexión con inventario"));

        // When & Then
        assertThrows(InventarioServiceException.class, () -> {
            crearOrdenUseCase.ejecutar(ordenValida);
        });
    }
}

// === ARCHIVO: src/test/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapterTest.java ===
package com.pragma.ordenes.infrastructure.adapters;

import com.pragma.ordenes.infrastructure.config.InventarioServiceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests de integración para InventarioRestAdapter")
class InventarioRestAdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private InventarioServiceConfig config;

    private InventarioRestAdapter inventarioRestAdapter;

    private static final String INVENTARIO_BASE_URL = "http://localhost:8081/api/inventario";

    @BeforeEach
    void setUp() {
        when(config.getBaseUrl()).thenReturn(INVENTARIO_BASE_URL);
        when(config.getTimeout()).thenReturn(2000);
        inventarioRestAdapter = new InventarioRestAdapter(restTemplate, config);
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe retornar true cuando la reserva de inventario es exitosa")
    void debeRetornarTrue_CuandoReservaExitosa() {
        // Given
        Map<String, Object> request = crearRequestReserva();
        Map<String, Object> response = new HashMap<>();
        response.put("exito", true);
        response.put("mensaje", "Reserva realizada");

        when(restTemplate.postForEntity(
                eq(INVENTARIO_BASE_URL + "/reservar"),
                any(),
                eq(Map.class)
        )).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // When
        boolean resultado = inventarioRestAdapter.reservarInventario(request);

        // Then
        assertTrue(resultado);
        verify(restTemplate, times(1)).postForEntity(
                eq(INVENTARIO_BASE_URL + "/reservar"),
                any(),
                eq(Map.class)
        );
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe retornar false cuando la reserva de inventario falla")
    void debeRetornarFalse_CuandoReservaFalla() {
        // Given
        Map<String, Object> request = crearRequestReserva();
        Map<String, Object> response = new HashMap<>();
        response.put("exito", false);
        response.put("mensaje", "Stock insuficiente");

        when(restTemplate.postForEntity(
                eq(INVENTARIO_BASE_URL + "/reservar"),
                any(),
                eq(Map.class)
        )).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // When
        boolean resultado = inventarioRestAdapter.reservarInventario(request);

        // Then
        assertFalse(resultado);
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe lanzar excepción cuando el servicio de inventario no responde")
    void debeLanzarExcepcion_CuandoServicioNoResponde() {
        // Given
        Map<String, Object> request = crearRequestReserva();

        when(restTemplate.postForEntity(
                anyString(),
                any(),
                any(Class.class)
        )).thenThrow(new RestClientException("Connection refused"));

        // When & Then
        assertThrows(Exception.class, () -> {
            inventarioRestAdapter.reservarInventario(request);
        });
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe usar timeout configurado para las peticiones")
    void debeUsarTimeoutConfigurado() {
        // Given
        Map<String, Object> request = crearRequestReserva();
        Map<String, Object> response = new HashMap<>();
        response.put("exito", true);

        when(restTemplate.postForEntity(
                anyString(),
                any(),
                any(Class.class)
        )).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // When
        inventarioRestAdapter.reservarInventario(request);

        // Then
        verify(config, times(1)).getTimeout();
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe construir URL correcta para reservar inventario")
    void debeConstruirURLCorrecta() {
        // Given
        Map<String, Object> request = crearRequestReserva();
        Map<String, Object> response = new HashMap<>();
        response.put("exito", true);

        when(restTemplate.postForEntity(
                anyString(),
                any(),
                any(Class.class)
        )).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // When
        inventarioRestAdapter.reservarInventario(request);

        // Then
        verify(restTemplate).postForEntity(
                eq(INVENTARIO_BASE_URL + "/reservar"),
                any(),
                any(Class.class)
        );
    }

    private Map<String, Object> crearRequestReserva() {
        Map<String, Object> request = new HashMap<>();
        request.put("productoId", UUID.randomUUID().toString());
        request.put("cantidad", 5);
        request.put("ordenId", UUID.randomUUID().toString());
        return request;
    }
}

// === ARCHIVO: src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java ===
package com.pragma.ordenes.infrastructure.controllers;


import com.pragma.ordenes.domain.model.EstadoOrden;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.ordenes.application.usecases.CrearOrdenUseCase;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.infrastructure.exceptions.GlobalExceptionHandler;
import com.pragma.ordenes.infrastructure.exceptions.InventarioServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrdenController.class)
@Import(GlobalExceptionHandler.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Tests de integración para OrdenController")
class OrdenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CrearOrdenUseCase crearOrdenUseCase;

    private Orden ordenRequest;
    private Orden ordenResponse;

    @BeforeEach
    void setUp() {
        ordenRequest = new Orden();
        ordenRequest.setClienteId("cliente-123");
        ordenRequest.setItems(new ArrayList<>());

        ordenResponse = new Orden();
        ordenResponse.setId(UUID.randomUUID());
        ordenResponse.setClienteId("cliente-123");
        ordenResponse.setItems(new ArrayList<>());
        ordenResponse.setEstado(Orden.EstadoOrden.PENDIENTE);
        ordenResponse.setFechaCreacion(LocalDateTime.now());
        ordenResponse.setFechaActualizacion(LocalDateTime.now());
    }

    @Test
    @DisplayName("Debe crear una orden exitosamente cuando los datos son válidos")
    void debeCrearOrdenExitosamente() throws Exception {
        // Given
        when(crearOrdenUseCase.ejecutar(any(Orden.class))).thenReturn(ordenResponse);

        // When & Then
        mockMvc.perform(post("/api/ordenes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordenRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.clienteId").value("cliente-123"))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));

        verify(crearOrdenUseCase, times(1)).ejecutar(any(Orden.class));
    }

    @Test
    @DisplayName("Debe retornar 400 cuando el clienteId está vacío")
    void debeRetornar400_CuandoClienteIdVacio() throws Exception {
        // Given
        ordenRequest.setClienteId("");

        // When & Then
        mockMvc.perform(post("/api/ordenes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordenRequest)))
                .andExpect(status().isBadRequest());

        verify(crearOrdenUseCase, never()).ejecutar(any(Orden.class));
    }

    @Test
    @DisplayName("Debe manejar excepción de inventario y retornar 503")
    void debeManejarExcepcionInventario() throws Exception {
        // Given
        when(crearOrdenUseCase.ejecutar(any(Orden.class)))
                .thenThrow(new InventarioServiceException("Inventario no disponible"));

        // When & Then
        mockMvc.perform(post("/api/ordenes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordenRequest)))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.mensaje").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 cuando la orden no tiene items")
    void debeRetornar400_SinItems() throws Exception {
        // Given
        ordenRequest.setItems(new ArrayList<>());

        // When & Then
        mockMvc.perform(post("/api/ordenes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordenRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Debe obtener una orden por ID exitosamente")
    void debeObtenerOrdenPorId() throws Exception {
        // Given
        UUID ordenId = UUID.randomUUID();
        ordenResponse.setId(ordenId);

        // When & Then
        mockMvc.perform(get("/api/ordenes/" + ordenId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ordenId.toString()));
    }

    @Test
    @DisplayName("Debe retornar 404 cuando la orden no existe")
    void debeRetornar404_CuandoOrdenNoExiste() throws Exception {
        // Given
        UUID ordenId = UUID.randomUUID();

        // When & Then
        mockMvc.perform(get("/api/ordenes/" + ordenId))
                .andExpect(status().isNotFound());
    }
}

// === ARCHIVO: README.md ===
# Sistema de Gestión de Órdenes

Proyecto empresarial de backend Java construido con Spring Boot 3.5 que gestiona órdenes de compra de clientes, integra con un servicio de inventario externo y mantiene un registro de transacciones para auditoría.

## Arquitectura

El proyecto sigue una arquitectura hexagonal (ports & adapters) con separación en capas:

```
src/main/java/com/pragma/ordenes/
├── domain/           # Núcleo del negocio - entidades y puertos
│   ├── model/        # Entidades del dominio (Orden, Transaccion)
│   └── ports/        # Interfaces que el dominio define
├── application/      # Casos de uso y lógica de aplicación
│   └── usecases/     # Implementación de casos de uso
└── infrastructure/   # Adaptadores y configuración externa
    ├── adapters/     # Implementaciones de puertos (JPA, REST)
    ├── controllers/  # Endpoints REST
    ├── config/       # Configuración de servicios externos
    └── exceptions/   # Manejo centralizado de errores
```

## Tecnologías

- **Java 21** - Lenguaje de programación
- **Spring Boot 3.5.6** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **PostgreSQL** - Base de datos
- **Resilience4j** - Patrones de tolerancia a fallos (Circuit Breaker, Retry)
- **SpringDoc OpenAPI** - Documentación de API
- **Lombok** - Reducción de boilerplate

## Requisitos Previos

- JDK 21 o superior
- Maven 3.9+
- PostgreSQL 15+ (o Docker para contenedores)
- Conexión a internet para descargar dependencias

## Configuración

El proyecto utiliza un archivo de configuración centralizado en `src/main/resources/application.yml`. Las propiedades principales incluyen:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ordenes
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false

app:
  inventario:
    base-url: http://localhost:8081
    timeout: 2000
  resilience:
    retry:
      max-attempts: 3
    circuit-breaker:
      failure-rate-threshold: 50
```

## Compilación

Para compilar el proyecto sin ejecutar pruebas:

```bash
mvn clean compile
```

Para compilar incluyendo pruebas:

```bash
mvn clean verify
```

## Ejecución

### Modo Desarrollo

```bash
mvn spring-boot:run
```

La aplicación arrancará en `http://localhost:8080`

### Con Perfil Específico

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Construir JAR ejecutable

```bash
mvn clean package
java -jar target/ordenes-0.0.1-SNAPSHOT.jar
```

## API REST

### Endpoints Principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /api/ordenes | Crear nueva orden |
| GET | /api/ordenes/{id} | Obtener orden por ID |
| GET | /api/ordenes/cliente/{clienteId} | Listar órdenes por cliente |
| GET | /api/ordenes/estado/{estado} | Listar órdenes por estado |

### Documentación Interactiva

Swagger UI disponible en: `http://localhost:8080/swagger-ui.html`

OpenAPI spec en: `http://localhost:8080/v3/api-docs`

### Métricas

Actuator endpoints disponibles en: `http://localhost:8080/actuator`

## Pruebas

Ejecutar todas las pruebas:

```bash
mvn test
```

Ejecutar pruebas con cobertura:

```bash
mvn test jacoco:report
```

El reporte de cobertura se genera en `target/site/jacoco/index.html`

## Docker

### Construir Imagen

```bash
docker build -t ordenes-app:latest .
```

### Ejecutar Contenedor

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/ordenes \
  ordenes-app:latest
```

## Integraciones

### Servicio de Inventario

El sistema se integra con un servicio de inventario externo para validar disponibilidad de productos. La comunicación está protegida con:

- **Circuit Breaker**: Previene fallos en cascada cuando el servicio externo no responde
- **Retry**: Reintentos automáticos con backoff exponencial
- **Timeout**: Máximo 2 segundos por operación

### Registro de Transacciones

Cada operación crítica genera una transacción registrable para auditoría. Las transacciones incluyen:

- Tipo de operación
- Entidad afectada
- ID de la entidad
- Detalles de la operación
- Usuario responsable
- Fecha y hora

## Contratos de Puertos

El dominio define los siguientes puertos (interfaces) que la infraestructura debe implementar:

- `OrdenRepositoryPort`: Persistencia de órdenes
- `InventarioServicePort`: Integración con servicio de inventario
- `TransaccionRepositoryPort`: Registro de transacciones

## Manejo de Errores

El sistema implementa un manejo de excepciones centralizado a través de `GlobalExceptionHandler` que proporciona respuestas consistentes para diferentes tipos de errores:

- Errores de validación (400 Bad Request)
- Recursos no encontrados (404 Not Found)
- Errores de servicios externos (503 Service Unavailable)
- Errores internos del servidor (500 Internal Server Error)

## Contribuir

1. Fork del repositorio
2. Crear rama de feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit de cambios (`git commit -am 'Agrega nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear Pull Request
```
