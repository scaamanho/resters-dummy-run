# Rester's Dummy Run
[Rester's Dummy Run Main Page](https://scaamanho.github.io/resters-dummy-run/index.html)

## Deploy Rest APIs in one clik!
Rester's Dummy Run is a easy management system to deploy Rest APIs with only one click.

Tranform any JSON file in a ready to use Rest API.

## Manage your APIs
With Rester's Dummy Run you can create, modifiy, duplicate or restore APIS in few clicks.

Access APIs use statistics from Rester's Dummy Run dashboard.

## Test your application's clients

Use Rester's Dummy Run to expose not developed APIs and test your Rest consumers.

Use GET, POST, PUT, DELETE methods to interact with avaliable data.

## Run in Docker
Use Rester's Dummy Run Docker image to get ready in seconds

Expose services with a reverse proxy (NGinx, Traefik, Ingress) or directly using a port.

## Share APIs with your team
Share exposed APIs with your team making avaliable to them with Rester's Dummy Run

## How to Use

### Prepare JSON file
Prepare a JSON file with a register or a list of them

```json
    {...}
```
or 
```json
    [{...},...,{...}]
```
### Deploy in Rester's Dummy Run
Choose your API path and deploy JSON file in Rester's Dummy Run engine.

### Consume published API
Consume your exposed API in Rester's Dummy Run with your prefered Rest client

### Swagger UI
Rester's Dummy Run can open Swagger Rest Client to interact with published APIs.



Gestiona todas tus apis Rest en un solo servidor de manera facil y sencilla.

Expone Json Dummys por api rest basadas en un Json de datos permitiendo usar
las operaciones `GET`, `POST`, `PUT`, `PATCH` y `DELETE` sobre esos datos o conjunto de datos

Posibilidad de configurar multiples APIs en un path determinado

![Reste's UI](assets/resters-ui.png)

## API methods
All your APIs will be exposed with the following endpoints:

* `/api/<your_api_path>` `[GET]`
* `/api/<your_api_path>/<element_number>` `[GET]`
* `/api/<your_api_path>` `[POST]`
* `/api/<your_api_path>` `[PUT]`
* `/api/<your_api_path>` `[PATCH]`
* `/api/<your_api_path>/<element_number>` `[DETETE]`

## Actuactor Endpoints
* `/health`
* `/info`
* `/prometheus`

## Deployments

See [`k8s-deployment.yaml`](k8s-deployment.yaml) and [`docker-compose.yaml`](docker-compose.yaml) to deploy in kubernetes and docker/docker swarm

### Enviroment Variables

Image Default Enviroment Variables:
```properties
SERVER_PORT= 8080
DB_DRIVER_CLASSNAME=org.h2.Driver
DB_DIALECT=org.hibernate.dialect.H2Dialect
DB_SCHEMA=public
DB_URL=jdbc:h2:file:C:/temp/test
DB_USR=sa
DB_PWD=
H2_CONSOLE=false
DEBUG=false
```

### Docker Image

```
docker run --rm -p 8080:8080 scaamanho/resters-dummy-run:latest
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

By default Rester's Dummy Run uses `H2` Database, but you can
configure `postgres` or `mariadb`(WIP)

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
DB_SCHEMA=public
DB_USR=postgres
DB_PWD=postgres
```

### MariaDB
To use MariaDB use the following enviroment variables

```
DB_DIALECT=org.hibernate.dialect.MariaDB103Dialect
DB_DRIVER_CLASSNAME=org.mariadb.jdbc.Driver
DB_URL=jdbc:mysql://localhost:3306/rester
DB_SCHEMA=public
DB_USR=root
DB_PWD=password
```