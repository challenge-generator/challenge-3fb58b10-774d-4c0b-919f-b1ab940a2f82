# Implementación de Código Limpio en Proyecto Empresarial

En un proyecto empresarial de backend Java, el equipo de desarrollo necesita mejorar la calidad del código mediante la aplicación de principios de código limpio y arquitectura de software. El sistema gestiona órdenes de compra de clientes, interactúa con un servicio de inventario externo y debe mantener un registro de transacciones para auditoría. Los actores involucrados son el 'sistema de gestión de órdenes', el 'servicio de inventario' y el'registro de transacciones'. El sistema debe asegurar la consistencia de los datos entre las órdenes y el inventario, manejar errores de comunicación con el servicio externo y mantener un registro idempotente de cada transacción. La latencia máxima aceptable para la comunicación con el servicio de inventario es de 2 segundos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Aplicación de Código limpio y eficiente |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 10 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición de Principios de Código Limpio

**Objetivo:** Comprender y aplicar los principios KISS, SOLID, YAGNI y DRY en el código existente.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar áreas del código donde se puedan aplicar los principios de código limpio.
- Refactorizar el código para eliminar redundancias, mejorar la cohesión y el acoplamiento, y asegurar que cada clase tenga una única responsabilidad.

**Entregable:** Documento que detalla las áreas refactorizadas y cómo se aplicaron los principios de código limpio.

<details>
<summary>Pistas de conocimiento</summary>

- Principios de diseño de software.
- Patrones de diseño comunes.

</details>

### Fase 2: Implementación de Arquitectura de Software

**Objetivo:** Aplicar patrones de arquitectura de software para mejorar la estructura del proyecto.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Identificar patrones de arquitectura aplicables al proyecto (ej. MVC, Microservicios).
- Refactorizar la estructura del proyecto para seguir estos patrones.

**Entregable:** Diagrama de la nueva estructura del proyecto y documentación de los cambios realizados.

<details>
<summary>Pistas de conocimiento</summary>

- Patrones de arquitectura de software.
- Ventajas y desventajas de cada patrón.

</details>

### Fase 3: Verificación y Validación del Código

**Objetivo:** Escribir pruebas unitarias y de integración para verificar el código refactorizado.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Escribir pruebas unitarias para cada clase refactorizada.
- Escribir pruebas de integración para verificar la comunicación entre los componentes del sistema.

**Entregable:** Conjunto de pruebas unitarias y de integración que cubren el código refactorizado.

<details>
<summary>Pistas de conocimiento</summary>

- Prácticas de pruebas de software.
- Herramientas de pruebas comunes.

</details>

### Fase 4: Revisión y Mejora Continua

**Objetivo:** Realizar una revisión del código y proponer mejoras continuas.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Realizar una revisión del código refactorizado y las pruebas escritas.
- Proponer mejoras continuas para el proyecto basadas en los principios de código limpio y arquitectura de software.

**Entregable:** Documento con la revisión del código y propuestas de mejora.

<details>
<summary>Pistas de conocimiento</summary>

- Prácticas de revisión de código.
- Estrategias para la mejora continua del software.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los principios de código limpio y por qué son importantes?
- **paraQueSirve**: ¿Para qué sirve aplicar patrones de arquitectura de software en un proyecto?
- **comoSeUsa**: ¿Cómo se aplican las pruebas unitarias y de integración en un proyecto de backend Java?
- **erroresComunes**: ¿Cuáles son los errores comunes al aplicar principios de código limpio y arquitectura de software?
- **queDecisionesImplica**: ¿Qué decisiones implica la revisión y mejora continua del código?

## Criterios de Evaluacion

- Aplicación correcta de los principios de código limpio.
- Implementación adecuada de patrones de arquitectura de software.
- Escritura de pruebas unitarias y de integración efectivas.
- Propuesta de mejoras continuas basadas en los principios de código limpio y arquitectura de software.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
