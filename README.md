# Optimistic Locking API

## Overview

Optimistic Locking API is a Spring Boot-based RESTful service provides optimistic locking for resource management in distributed systems. Optimistic locking is a concurrency control mechanism that helps prevent data inconsistencies when multiple clients attempt to update the same resource simultaneously. This approach is especially useful in distributed environments where conflicts are rare but possible, and it avoids the overhead of database locks.

## Why Use Optimistic Locking?

In distributed systems, multiple processes or users may try to update the same data concurrently. Optimistic locking allows each process to work with a copy of the data and only checks for conflicts when saving changes. If a conflict is detected (i.e., the data has been modified since it was last read), the update is rejected, and the client can retry or handle the conflict appropriately. This reduces contention and improves scalability compared to pessimistic locking, which locks data for the duration of a transaction.

### Use Cases in Distributed Systems

- **Microservices architectures** where services interact with shared data.
- **Event-driven systems** where updates may come from different sources.
- **Collaborative applications** (e.g., document editing, inventory management) where concurrent updates are common.
- **APIs for mobile or web clients** that may cache and update data asynchronously.

## API Endpoints

All endpoints are prefixed with `/api/v1`.

### 1. Register a Resource

- **Endpoint:** `POST /api/v1/resource`
- **Description:** Registers a new resource. If a resource with the same ID already exists, an error is returned.
- **Request Body:**
  ```json
  {
    "resourceId": 1,
    "resourceMetaData": "Some metadata"
  }
  ```
- **Response:**
  - Status: `201 Created`
  - Header: `resourceId` (ID of the created resource)

### 2. Update Resource (Optimistic Lock)

- **Endpoint:** `PUT /api/v1/resource/update`
- **Description:** Updates an existing resource using optimistic locking. The request must include the current version of the resource. If the version does not match the latest in the database, the update is rejected.
- **Request Body:**
  ```json
  {
    "resourceId": 1,
    "resourceMetaData": "Updated metadata",
    "version": 1
  }
  ```
- **Response:**
  - Status: `200 OK`
  - Header: `updateable` (true if update succeeded, false otherwise)

### 3. Delete Resource

- **Endpoint:** `DELETE /api/v1/resource/delete`
- **Description:** Deletes a resource by ID.
- **Request Body:**
  ```json
  {
    "id": 1,
    "resourceMetaData": "Some metadata",
    "version": 2
  }
  ```
- **Response:**
  - Status: `204 No Content`

## Error Handling

The API returns meaningful error messages and status codes for invalid requests, such as attempting to create a duplicate resource or updating a resource with an outdated version.

## How It Works

- **Versioning:** Each resource has a `version` field. When updating, the client must provide the current version. The API checks if the version matches the latest in the database before applying the update.
- **Conflict Detection:** If the version does not match, the update is rejected, indicating that the resource has been modified by another process.

## Running the Project

1. Ensure you have Java and Maven installed.
2. Clone the repository.
3. Run the application:
   ```sh
   ./mvnw spring-boot:run
   ```
4. The API will be available at `http://localhost:8080/api/v1`.

## Example Workflow

1. **Create a resource** with version 1.
2. **Client A** reads the resource (version 1).
3. **Client B** reads the resource (version 1).
4. **Client A** updates the resource (version 1 → 2).
5. **Client B** tries to update the resource with version 1 (conflict detected, update rejected).
