#Building CRUD API with FastAPI

FastAPI is a modern and high-performance web framework for building APIs with Python. Its ease of use, speed, and support for type hints make it a popular choice for developing web services. In this tutorial, we’ll create a CRUD API (Create, Read, Update, Delete) using FastAPI based on the provided code.

Introduction

In this tutorial, we’ll create a simple CRUD API for managing blog posts. The API will allow us to perform the following operations:

Retrieve a list of all blog posts
Create a new blog post
Retrieve details of a specific blog post
Update an existing blog post
Delete a blog post

Setting Up the Environment

Before we begin, make sure you have installed Python and FastAPI. You can install FastAPI and Uvicorn (ASGI server) using pip:

pip install fastapi uvicorn
Creating the CRUD API


uvicorn main:app --reload

Uvicorn is an ASGI (Asynchronous Server Gateway Interface) server that allows you to run ASGI applications, such as FastAPI, and serve them over the internet. To run the FastAPI application, you use the Uvicorn command with the following format

Now, the CRUD API will be available at http://127.0.0.1:8000.

Testing the Endpoints


http://127.0.0.1:8000/redoc


You can use Postman to do this.

Retrieve All Posts: Open your web browser or API client and navigate to http://127.0.0.1:8000/posts to retrieve all posts.
Create a Post: Use a POST request with JSON payload to http://127.0.0.1:8000/posts to create a new post.
Retrieve Latest Post: Visit http://127.0.0.1:8000/posts/latest to get the latest post.
Retrieve Post by ID: Access http://127.0.0.1:8000/posts/{id} to retrieve a specific post by ID.
Update Post: Use a PUT request with JSON payload to http://127.0.0.1:8000/posts/{id} to update an existing post.
Delete Post: Use a DELETE request to http://127.0.0.1:8000/posts/{id} to delete a post by ID.
Accessing the Interactive API Documentation

After starting the FastAPI application, you can access the interactive API documentation by visiting the /docs endpoint. For example, if your FastAPI server is running on http://127.0.0.1:8000, you can access the documentation at http://127.0.0.1:8000/docs.

By default, FastAPI also provides an alternative documentation page powered by ReDoc, which can be accessed at /redoc. For example, http://127.0.0.1:8000/redoc.


The automatic interactive API documentation feature of FastAPI, powered by Swagger UI and ReDoc, is a valuable tool for both developers and API consumers. It simplifies API exploration, testing, and integration by providing a user-friendly interface with detailed endpoint information and real-time request/response examples. This feature makes FastAPI an excellent choice for building robust and developer-friendly APIs.

https://medium.com/datauniverse/building-crud-api-with-fastapi-a-step-by-step-guide-689b90f8234c