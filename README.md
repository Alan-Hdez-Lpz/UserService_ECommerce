# UserService_ECommerce
Project: Building a Microservices-Based E-Commerce Application - User Service

How to set up the project:
Update the DataBase configuration properties in the application.properties file

How to run the application:
1. Create the DB in MySQL
2. Verify the properties of tihs microservice in the config-repo
3. Run the config-server and eureka-server
4. Run this microservice project

NOTE: The server port will change according server port value in the config-repo properties

API endpoints and sample requests for testing:

All the endpoints with this annotation @PreAuthorize("hasRole('ADMIN')") are able for ADMIN users.
To dientify the user logged is necessary a token that will be obtain with /login endpoint and you need to add it in the Authorization section  when you select Bearer token type on each endpoint that need authorization.

Token example: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiaWF0IjoxNzUwMTU0NTU2LCJleHAiOjE3NTAxNTgxNTZ9.bCUicFIxjj3GJ6ZlU2LVCMeykU6VpCaOHqfOZvkrlsji0scwcbODJI0zDEOb59-Hbada3aXAYEWBVJEROrMdFw

For ROLES:
CREATE:
 - POST -> http://localhost:8084/roles
 - BodyRequest:
{
  "name": "ADMIN"
}
- Needs Bearer token


READ:
- GET -> http://localhost:8084/roles/1 (get roles by ID)
- GET -> http://localhost:8084/roles (get all roles)

UPDATE:
- PUT -> http://localhost:8084/roles/1
 - BodyRequest:
{
  "name": "CUSTOMER"
}
- Needs Bearer token

DELETE:
- DELETE -> http://localhost:8084/roles/1
- Needs Bearer token

For USERS:
CREATE:
 - POST -> http://localhost:8084/users/register
 - BodyRequest:
{
  "userName": "john",
  "password": "password123",
  "role": {
    "name": "ADMIN"
  }
}
- Needs Bearer token

READ:
 - POST -> http://localhost:8084/users/login
 - BodyRequest:
{
  "userName": "john",
  "password": "password123"
}

- GET -> http://localhost:8084/users/profile (Needs Bearer token)
- GET -> http://localhost:8084/users/1 (get user profile by ID but if the user is not ADMIN just can get its own profile)

DELETE:
- DELETE -> http://localhost:8084/users/2
- Needs Bearer token
