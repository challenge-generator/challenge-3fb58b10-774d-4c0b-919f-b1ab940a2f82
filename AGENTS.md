# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de Código Limpio en Proyecto Empresarial**.

| | |
|---|---|
| Tema | Aplicación de Código limpio y eficiente |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean con capas estándar (dominio, aplicación, infraestructura) |
| Tiempo estimado | 10 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.postgresql:postgresql n/a
- org.projectlombok:lombok n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.mockito:mockito-core n/a
- org.mockito:mockito-junit-jupiter n/a
- org.testcontainers:postgresql 1.20.1
- org.springframework.boot:spring-boot-configuration-processor n/a
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- io.github.resilience4j:resilience4j-reactor 2.2.0
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.6.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición de Principios de Código Limpio**: Documento que detalla las áreas refactorizadas y cómo se aplicaron los principios de código limpio.
- **Fase 2 — Implementación de Arquitectura de Software**: Diagrama de la nueva estructura del proyecto y documentación de los cambios realizados.
- **Fase 3 — Verificación y Validación del Código**: Conjunto de pruebas unitarias y de integración que cubren el código refactorizado.
- **Fase 4 — Revisión y Mejora Continua**: Documento con la revisión del código y propuestas de mejora.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapterTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (71)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `com.pragma.ordenes.infrastructure.persistence.OrdenEntity`
      El import com.pragma.ordenes.infrastructure.persistence.OrdenEntity usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `com.pragma.ordenes.infrastructure.persistence.OrdenRepository`
      El import com.pragma.ordenes.infrastructure.persistence.OrdenRepository usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `com.pragma.ordenes.infrastructure.persistence.entity.TransaccionEntity`
      El import com.pragma.ordenes.infrastructure.persistence.entity.TransaccionEntity usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `com.pragma.ordenes.infrastructure.persistence.repository.TransaccionRepository`
      El import com.pragma.ordenes.infrastructure.persistence.repository.TransaccionRepository usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `com.pragma.ordenes.infrastructure.adapters.dto.OrdenRequest`
      El import com.pragma.ordenes.infrastructure.adapters.dto.OrdenRequest usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `com.pragma.ordenes.infrastructure.adapters.dto.OrdenResponse`
      El import com.pragma.ordenes.infrastructure.adapters.dto.OrdenResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenRequest`
      El import com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenRequest usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenResponse`
      El import com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/RegistrarTransaccionUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.getClienteId`
      Se invoca `getClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.getId`
      Se invoca `getId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.getItems`
      Se invoca `getItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.setId`
      Se invoca `setId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.setFechaCreacion`
      Se invoca `setFechaCreacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.setFechaActualizacion`
      Se invoca `setFechaActualizacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.setEstado`
      Se invoca `setEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Orden.getEstado`
      Se invoca `getEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java` — `Transaccion.setCorrelationId`
      Se invoca `setCorrelationId` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/RegistrarTransaccionUseCase.java` — `Transaccion.getId`
      Se invoca `getId` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/RegistrarTransaccionUseCase.java` — `TransaccionRepositoryPort.buscarPorEntidadAfectada`
      Se invoca `buscarPorEntidadAfectada` sobre `TransaccionRepositoryPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/application/usecases/RegistrarTransaccionUseCase.java` — `TransaccionRepositoryPort.buscarPorEntidadId`
      Se invoca `buscarPorEntidadId` sobre `TransaccionRepositoryPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `Orden.getClienteId`
      Se invoca `getClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `Orden.getId`
      Se invoca `getId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `Orden.getItems`
      Se invoca `getItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java` — `Orden.getEstado`
      Se invoca `getEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java` — `Orden.getId`
      Se invoca `getId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java` — `InventarioServiceConfig.getBaseUrl`
      Se invoca `getBaseUrl` sobre `InventarioServiceConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java` — `Orden.getItems`
      Se invoca `getItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java` — `Orden.getClienteId`
      Se invoca `getClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getId`
      Se invoca `getId` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.actualizarId`
      Se invoca `actualizarId` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getFechaTransaccion`
      Se invoca `getFechaTransaccion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.actualizarFechaTransaccion`
      Se invoca `actualizarFechaTransaccion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getTipoOperacion`
      Se invoca `getTipoOperacion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getEntidadAfectada`
      Se invoca `getEntidadAfectada` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getEntidadId`
      Se invoca `getEntidadId` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getDetalles`
      Se invoca `getDetalles` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java` — `Transaccion.getUsuarioResponsable`
      Se invoca `getUsuarioResponsable` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `CrearOrdenUseCase.buscarPorId`
      Se invoca `buscarPorId` sobre `CrearOrdenUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `CrearOrdenUseCase.buscarPorClienteId`
      Se invoca `buscarPorClienteId` sobre `CrearOrdenUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `CrearOrdenUseCase.buscarPorEstado`
      Se invoca `buscarPorEstado` sobre `CrearOrdenUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `CrearOrdenUseCase.cancelarOrden`
      Se invoca `cancelarOrden` sobre `CrearOrdenUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `Orden.getItems`
      Se invoca `getItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `Orden.getId`
      Se invoca `getId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `Orden.getClienteId`
      Se invoca `getClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `Orden.getEstado`
      Se invoca `getEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `Orden.getFechaCreacion`
      Se invoca `getFechaCreacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `ItemOrden.getProductoId`
      Se invoca `getProductoId` sobre `ItemOrden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `ItemOrden.getCantidad`
      Se invoca `getCantidad` sobre `ItemOrden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `ItemOrden.getPrecioUnitario`
      Se invoca `getPrecioUnitario` sobre `ItemOrden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java` — `ItemOrden.getSubtotal`
      Se invoca `getSubtotal` sobre `ItemOrden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setId`
      Se invoca `setId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setClienteId`
      Se invoca `setClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setItems`
      Se invoca `setItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setEstado`
      Se invoca `setEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setFechaCreacion`
      Se invoca `setFechaCreacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `Orden.setFechaActualizacion`
      Se invoca `setFechaActualizacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java` — `InventarioServicePort.reservarInventario`
      Se invoca `reservarInventario` sobre `InventarioServicePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapterTest.java` — `InventarioServiceConfig.getBaseUrl`
      Se invoca `getBaseUrl` sobre `InventarioServiceConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapterTest.java` — `InventarioServiceConfig.getTimeout`
      Se invoca `getTimeout` sobre `InventarioServiceConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setClienteId`
      Se invoca `setClienteId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setItems`
      Se invoca `setItems` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setId`
      Se invoca `setId` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setEstado`
      Se invoca `setEstado` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setFechaCreacion`
      Se invoca `setFechaCreacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java` — `Orden.setFechaActualizacion`
      Se invoca `setFechaActualizacion` sobre `Orden`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (21)

- `pom.xml`
- `src/main/java/com/pragma/ordenes/OrdenesApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/ordenes/domain/model/Orden.java`
- `src/main/java/com/pragma/ordenes/domain/model/Transaccion.java`
- `src/main/java/com/pragma/ordenes/domain/ports/OrdenRepositoryPort.java`
- `src/main/java/com/pragma/ordenes/domain/ports/InventarioServicePort.java`
- `src/main/java/com/pragma/ordenes/domain/ports/TransaccionRepositoryPort.java`
- `src/main/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCase.java`
- `src/main/java/com/pragma/ordenes/application/usecases/RegistrarTransaccionUseCase.java`
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/OrdenJpaAdapter.java`
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapter.java`
- `src/main/java/com/pragma/ordenes/infrastructure/adapters/TransaccionJpaAdapter.java`
- `src/main/java/com/pragma/ordenes/infrastructure/controllers/OrdenController.java`
- `src/main/java/com/pragma/ordenes/infrastructure/exceptions/GlobalExceptionHandler.java`
- `src/main/java/com/pragma/ordenes/infrastructure/exceptions/InventarioServiceException.java`
- `src/main/java/com/pragma/ordenes/infrastructure/config/InventarioServiceConfig.java`
- `src/test/java/com/pragma/ordenes/application/usecases/CrearOrdenUseCaseTest.java`
- `src/test/java/com/pragma/ordenes/infrastructure/adapters/InventarioRestAdapterTest.java`
- `src/test/java/com/pragma/ordenes/infrastructure/controllers/OrdenControllerTest.java`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/ordenes`
- `src/main/java/com/pragma/ordenes/domain`
- `src/main/java/com/pragma/ordenes/domain/model`
- `src/main/java/com/pragma/ordenes/domain/ports`
- `src/main/java/com/pragma/ordenes/application`
- `src/main/java/com/pragma/ordenes/application/usecases`
- `src/main/java/com/pragma/ordenes/infrastructure`
- `src/main/java/com/pragma/ordenes/infrastructure/adapters`
- `src/main/java/com/pragma/ordenes/infrastructure/config`
- `src/main/java/com/pragma/ordenes/infrastructure/controllers`
- `src/main/java/com/pragma/ordenes/infrastructure/exceptions`
- `src/main/resources`
- `src/test/java/com/pragma/ordenes`
- `src/test/java/com/pragma/ordenes/application`
- `src/test/java/com/pragma/ordenes/infrastructure`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con capas estándar (dominio, aplicación, infraestructura)**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior
- Brecha que el reto ataca: Aplica principios y herramientas de verificación para escribir código limpio (KISS, SOLID, YAGNI y DRY).
- Mision: Candidato con experiencia Senior en Backend Java, enfocado en aplicar principios de código limpio y arquitectura de software en proyectos empresariales.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
