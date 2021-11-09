# Rester's Dummy Run

[TODO]

Gestiona todas tus apis Rest en un solo servidor de manera facil y sencilla.

Expone Json Dummys por api rest basadas en un Json de datos permitiendo usar
las operaciones GET POST PUT DELETE sobre esos datos o conjunto de datos

Posibilidad de configurar multiples APIs en un path determinado

![Reste's UI](assets/resters-ui.png)

## API methods
All your APIs will be exposed with the following endpoints:

* `/api/<your_api_path>` `[GET]`
* `/api/<your_api_path>/<element_number>` `[GET]`
* `/api/your_api_path` `[POST]`
* `/api/your_api_path` `[PUT]`
* `/api/<your_api_path>/<element_number>` `[DETETE]`
* `/api/<your_api_path>` `[PATCH]`

## Flyway Init Scripts
[TODO]

## Actuactor Endpoints
* `/health`
* `/info`

## Deployments

### Enviroment Variables



### Docker Image

```
docker run --rm -itd -p 8080:8080 rester-dummy-run
```
### Docker Compose

```
docker-compose up -d
```

### Kubernetes

```
kubectl apply -f k8s-deployment.yaml -n default
```

## Using Diferent Databases

By default Rester's Dummy Run uses H2 Database

### H2 DB
To use H2 DB use the following enviroment variables
(This will be default db if you specify nothing)

```
DB_DIALECT=org.hibernate.dialect.H2Dialect
DB_DRIVER_CLASSNAME=org.h2.Driver
DB_URL=jdbc:h2:file:/database/resters-dummy-run
DB_USR=sa
DB_PWD=
```

### Postgres DB
To use Postgres DB use the following enviroment variables

```
DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect
DB_DRIVER_CLASSNAME=org.postgresql.Driver
DB_URL=jdbc:postgresql://localhost:5432/rester
DB_USR=postgres
DB_PWD=postgres
```

### MariaDB
To use MariaDB use the following enviroment variables

```
DB_DIALECT=org.hibernate.dialect.MariaDB103Dialect
DB_DRIVER_CLASSNAME=org.mariadb.jdbc.Driver
DB_URL=jdbc:mysql://localhost:3306/rester
DB_USR=root
DB_PWD=password
```