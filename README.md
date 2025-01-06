# User Badge Management System

## Project Overview

The User Badge Management System is a RESTful API designed for managing competing user registrations, scores, and awarding badges based on performance. This system ensures structured user data, efficient CRUD operations, and appropriate badge allocation based on user scores.

## Features

### User Registration:

- Registers users with a unique User ID and Username.

- Initializes the user with a score of 0 and no badges.

### CRUD Operations:

- Create, Read, Update, and Delete user information.

### Score Updates:

- Allows updates to the user’s score only.

- Automatically awards badges based on the score.

### Badge Allocation:

#### Score Ranges:

- 1 <= Score < 30: Awarded Code Ninja badge.

- 30 <= Score < 60: Awarded Code Champ badge.

- 60 <= Score <= 100: Awarded Code Master badge.

A user can hold a maximum of three unique badges.

### User Retrieval:

- Retrieves all users sorted by their scores in ascending order with a time complexity of O(n log n).

### Validation and Error Handling:

- Ensures score updates are within the range of 0 to 100.

- Handles missing users and invalid requests with appropriate HTTP response codes.

## Project Structure

### Entities

1. User

    - id: Unique identifier (Primary Key).

    - userName: Unique username.

    - score: User’s score, initialized to 0.

    - badges: Set of badges awarded to the user.

2. Badge (Enum)

    - Represents badge types:

          CODE_NINJA
          CODE_CHAMP
          CODE_MASTER

### Relationships

- A user can have multiple badges, represented as a 1:N relationship.

## API Endpoints

### 1. Create User (Registration)

**Endpoint:** /user

**Method:** POST

**Request Body:**

```
{
"userName": "Sejal"
}
```

**Response:**

```
{
"id": "unique_id",
"userName": "Sejal",
"score": 0,
"badges": []
}
```

### 2. Retrieve All Users

Get the list of all the users sorted based on score

**Endpoint:** /user

**Method:** GET

**Response:**

```
{
"id": "unique_id",
"userName": "Sejal",
"score": 60,
"badges": ["CODE_MASTER"]
}
```

### 3. Update User Score

**Endpoint:** /user/{userId}

**Method:** PUT

**Request Body:**

```
{
"score": 45
}
```

**Response:**

```
{
"id": "unique_id",
"userName": "Sejal",
"score": 45,
"badges": ["CODE_CHAMP"]
}
```

### 4. Delete User

**Endpoint:** /user/{userId}

**Method:** DELETE


## Badge Allocation Rules

- The API automatically updates the badges based on the user’s score.

### **Examples:**

**Initial Registration:**

      score: 0

      badges: []

   **Score Update:**

      score: 25

      badges: ["CODE_NINJA"]

   **Score Update:**

      score: 45

      badges: ["CODE_CHAMP"]

   **Score Update:**

      score: 75

      badges: ["CODE_MASTER"]


## Validation & Error Handling

### Score Validation:

   - Ensures score is between 0 and 100.

### Error Scenarios:

   - User not found: Returns 404 Not Found.

   - Invalid score: Returns 400 Bad Request.

## Technologies Used

   - Backend: Java, Spring Boot

   - Database: MongoDB (User and Badge entities)

   - Tools: Maven, Postman

## How to Run

   - Clone the repository.

   - **Build the project using Maven:**

          mvn clean install

   - **Run the application:**

          mvn spring-boot:run

**Access the API at** http://localhost:8080/user.