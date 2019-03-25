## Proyecto ContactChallenge

ContactChallenge es una aplicacion generada por [JHipster](https://www.jhipster.tech/)(Ver JHipster README en el repositorio), la cual esta en desarrollo y requiere tu expertise para completar varias tareas pendientes tanto en el **Backend** como en el **Frontend**.

Descargar la aplicacion base desde el repositrio [contact-challenge-git-repository](https://github.com/ncalderon/contact-challenge)

## Evaluacion

**Se evaluara la entrega en terminos de porcentaje de cumplimiento, calidad y organizacion del codigo.**

##### Requisitos para correr la aplicacion:

-   [Java SE Development Kit 8u201](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
-   [Apache Maven 3.6.0](https://maven.apache.org/download.cgi)
-   [NodeJS latest](https://nodejs.org/en/)
-   [NPM latest](https://docs.npmjs.com/try-the-latest-stable-version-of-npm)
-   [IntelliJ IDEA](https://www.jetbrains.com/idea/)(Recomendado), pero puede usar el IDE de su preferencia.

## Comandos para ejecutar la App por primera vez

1. `npm install`
2. `.\mvnw`
3. En otra consola ejecutar `npm start`
4. Ir a la pagina [http://localhost:9000](http://localhost:9000)

**Observacion**
Los puertos 8080 y 9000 deben estar disponibles para que la aplicacion suba.

### Tareas pendientes de completar

##### Backend

1. Se requiere implementar la interfaz `PersonInfoService`, la cual cuenta de un solo metodo `PersonFullDTO save(PersonFullDTO personFullDTO)`. Este metodo debe ser capaz de persistir la informacion de la entidad `Person`, las **N** vias de contactos(`Contact`) y sus **N** direcciones(`Address`). Recordar que si algo falla durante el proceso de guardar se requiere hacer rollback de lo persistido previamente al error.

##### Frontend

La aplicacion angular se encuentra en la carpeta `src/main/webapp/app`.

1. Se requiere completar el desarrollo del componente `src/main/webapp/app/entities/person-update-component (PersonUpdateComponent)`. Se requiere crear un formulario que permita crear o actualizar una persona, sus contactos o direcciones.

2. Se requiere completar el desarrollo del componente `src/main/webapp/app/entities/person-detail-component (PersonDetailComponent)`. Se requiere poder visualizar la informacion de la persona, sus contactos y direcciones.

Para acceder a las vistas de estos componentes ir a Entities\Person en el Navbar.

**Observaciones**
Los componentes `PersonUpdateComponent` y `PersonDetailComponent` tiene la configuracion basica e inicial , es **MUY** recomendable no cambiar dicha configuracion. Las cosas que puedan ser cambiadas de esa configuracion base tendran un **comentario de "OPCIONAL" arriba.**
