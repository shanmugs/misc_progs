Python CRUD Rest API using Flask, SQLAlchemy, Postgres, Docker, Docker Compose
https://www.youtube.com/live/fHQWTsWqBdE

localhost or 0.0.0.0 
GET http://172.18.0.3:4000/test

GET http://172.18.0.3:4000/users
POST http://172.18.0.3:4000/users
   {
	"username":"Sathish",
	"email":"sat@pp.com"
   }

GET http://172.18.0.3:4000/users/4
DEL>ETE http://172.18.0.3:4000/users/4


create a CRUD Rest API in Python, using the following:

Flask (Python web framework)
SQLAlchemy (ORM)
Postgres (database)
Docker (containerization)
Docker Compose (to run the application and the database in containers)

create 5 endpoints for basic CRUD operations:

Create
Read all
Read one
Update
Delete
Here are the steps we are going through:

Create a Flask application using SQLALchemy as an ORM.
Dockerize the Flask application writing a Dockerfile and a docker-compose.yml file to run the application and the database
Run the Postgres database in a container using Docker Compose, and test it with TablePlus
Run the Flask application in a container using Docker Compose, and test it with Postman


Create a Flask application using SQLALchemy as an ORM
Create a new folder:

mkdir flask-crud-api
step into the folder:

cd flask-crud-api
Open the folder with your favorite IDE. I am using VSCode, so I will use the command:

code .
We need just 4 files for the Flask application, including containerization.

You can create these files in different ways. One of them is to create them manually, the other one is to create them with the command line:

touch requirements.txt app.py Dockerfile docker-compose.yml

The requirements.txt file contains all the dependencies of the project.

flask
psycopg2-binary
Flask-SQLAlchemy
Short explanation of the dependencies:

flask is the Python web framework we are gonna use.

psycopg2-binary is the driver to make the connection with the Postgres database.

Flask-SQLAlchemy is the ORM to make the queries to the database.

The app.py file is the main file of the application: it contains all the endpoints and the logic of the application.

We are importing:

Flask as a framework
request to handle the HTTP
jsonify to handle the json format, not native in Python
make_response to handle the HTTP responses
flask_sqlalchemy to handle the db queries
environ to handle the environment variables
We are creating Flask app, configuring the database bu setting an environment variable called 'DB_URL'. We will set it later in the docker-compose.yml file.

Then we are creating a User class with an id, a username and an email. the id will be autoincremented automatically by SQLAlchemy when we will create the users. the __tablename__ = 'users' line is to define the name of the table in the database

An important line is db.create_all(). This will synchronize the database with the model defined, for example creating an "users" table.

Then we have 6 endpoints

test: just a test route
create a user: create a user with a username and an email
get all users: get all the users in the database
get one user: get one user by id
update one user: update one user by id
delete one user: delete one user by id
All the routes have error handling, for example if the user is not found, we will return a 404 HTTP response.





https://dev.to/francescoxx/python-crud-rest-api-using-flask-sqlalchemy-postgres-docker-docker-compose-3kh4

