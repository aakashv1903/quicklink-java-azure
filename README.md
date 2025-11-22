# QuickLink: Cloud-Native URL Shortener 🚀

QuickLink is a production-ready, cloud-native REST API for shortening URLs. It is built with Java Spring Boot and deployed on Microsoft Azure using a serverless SQL database.

This project demonstrates a complete DevOps workflow: from local development with an in-memory database to a secure, scalable cloud deployment.

## 🌐 Live Demo

You can test the live API deployed on Azure App Service here:

**Base URL**: https://quicklink-2025-gnehbad4cgeadvb4.southindia-01.azurewebsites.net

## 🏗️ Architecture

The application follows a standard 3-tier architecture deployed entirely on the Azure Cloud.

graph LR
    User((User/Postman)) -- HTTPS --> App[Azure App Service\n(Spring Boot)]
    App -- JDBC (Secure) --> DB[(Azure SQL Database)]


Compute: Azure App Service (Linux/Java 17 Runtime)

Database: Azure SQL Database (Relational persistence)

Security: Network firewalls and Environment Variable configuration for credentials.

## 🛠️ Tech Stack

Language: Java 17

Framework: Spring Boot 3 (Spring Web, Spring Data JPA)

Database: Azure SQL (Production), H2 (Local Testing)

Build Tool: Maven

Cloud Provider: Microsoft Azure

Tools: VS Code, Postman, Git

## 🔌 API Documentation

1. Create a Short Link

Takes a long URL and returns a shortened version.

Endpoint: POST /api/shorten

Content-Type: application/json

Body:

{
  "longUrl": "[https://www.linkedin.com/in/aakashv1903/](https://www.linkedin.com/in/aakashv1903/)"
}


Response (200 OK):

{
    "id": 1,
    "shortId": "aK3bF",
    "longUrl": "[https://www.linkedin.com/in/aakashv1903/](https://www.linkedin.com/in/aakashv1903/)",
    "clickCount": 0,
    "createdAt": "2025-11-18T10:00:00.000+00:00"
}


2. Redirect (Use the Link)

Redirects the user to the original Long URL.

Endpoint: GET /{shortId}

Example: https://aakash-quicklink-2025.azurewebsites.net/aK3bF

Behavior: Returns HTTP 302 Redirect to the destination.

## 🚀 How to Run Locally

To run this project on your own machine:

Clone the repository:

git clone [https://github.com/aakashv1903/quicklink-java-azure.git](https://github.com/aakashv1903/quicklink-java-azure.git)
cd quicklink-java-azure


Configure the Database:

Option A (Cloud): Update src/main/resources/application.properties with your Azure SQL credentials.

Option B (Local): Use the H2 in-memory database (enabled by default in the dev profile if configured).

Build and Run:

./mvnw spring-boot:run


Test:
Open Postman and send a POST request to http://localhost:8080/api/shorten.

## ☁️ Deployment Process

This application was deployed using the following steps:

Packaging: Built the production JAR using mvn package.

Infrastructure: Provisioned an Azure App Service (Linux/Java) and Azure SQL Database via the Azure Portal.

Security: Configured Azure SQL Firewall to allow App Service access.

Configuration: Used App Service Application Settings to securely inject database credentials (SPRING_DATASOURCE_PASSWORD) at runtime, keeping secrets out of the source code.

Deployment: Deployed the artifact using Azure Deployment Center.

Author: Aakash V
[LinkedIn](https://www.linkedin.com/in/aakashv1903/) | [GitHub](https://www.github.com/aakashv1903/)
