# Pioneer - personal pet project
This is the second personal project where I'm practicing in using Spring WebFlux with Spring Cloud.
There are several interesting things that are implemented in different microservices:
- Spring Cloud Gateway (pioneer-gateway)
- Discovery server (pioneer-eserver)

# API Endpoints
API endpoints allow us to work with the application using http://localhost:8000 path.

## Work with Users API

| method | path        | expects                                       | return type    | description                                      |
|--------|-------------|-----------------------------------------------|----------------|--------------------------------------------------|
| GET    | /users      | -                                             | array of users | returns all users from the database              |
| GET    | /users/{id} | path variable with user's id                  | User           | returns User by id                               |
| POST   | /users      | body of User                                  | User           | creates User                                     |
| UPDATE | /users/{id} | path variable with user's id and body of User | User           | updates User by id                               |
| DELETE | /users/{id} | path variable with user's id                  | void           | deletes User by id and breaks relation with Team |

## Work with Team API

| method | path        | expects                                       | return type      | description                                       |
|--------|-------------|-----------------------------------------------|------------------|---------------------------------------------------|
| GET    | /teams      | -                                             | array of teams   | returns all users from the database               |
| GET    | /teams/{id} | path variable with team's id                  | Team             | returns Team by id                                |
| POST   | /teams      | body of User                                  | Team             | creates Team with users                           |
| DELETE | /teams/{id} | path variable with team's id                  | void             | deletes Team by id and breaks relations with User |