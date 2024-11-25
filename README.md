### 1. Get All Companies
- **URL:** `/companies`
- **Method:** `GET`
- **Response:**
    - **Status:** `200 OK`
    - **Body:**
      ```json
      [
        {
          "id": 1,
          "name": "OOCL"
        },
        {
          "id": 2,
          "name": "COSCO"
        }
      ]
      ```

### 2. Get Specific Company
- **URL:** `/companies/{id}`
- **Method:** `GET`
- **Response:**
    - **Status:** `200 OK`
    - **Body:**
      ```json
      {
        "id": 1,
        "name": "OOCL"
      }
      ```

### 3. Get Employees of a Specific Company
- **URL:** `/companies/{id}/employees`
- **Method:** `GET`
- **Response:**
    - **Status:** `200 OK`
    - **Body:**
      ```json
      [
        {
          "id": 1,
          "name": "Tony Wong",
          "age": 1,
          "gender": "MALE",
          "salary": 100.0
        },
        {
          "id": 2,
          "name": "Wong Tony",
          "age": 10,
          "gender": "FEMALE",
          "salary": 200.0
        }
      ]
      ```

### 4. Get Companies with Pagination
- **URL:** `/companies`
- **Method:** `GET`
- **Response:**
    - **Status:** `200 OK`
    - **Body:**
      ```json
      {
        "page": 1,
        "size": 5,
        "total": 10,
        "companies": [
          {
            "id": 1,
            "name": "OOCL"
          },
          {
            "id": 2,
            "name": "COSCO"
          }
          // ... up to 5 companies
        ]
      }
      ```

### 5. Update an Employee's Company
- **URL:** `/employees/{employeeId}`
- **Method:** `PUT`
- **Request Body:**
    ```json
    {
      "companyId": 2
    }
    ```
- **Response:**
    - **Status:** `200 OK`
    - **Body:**
      ```json
      {
        "message": "Employee updated successfully.",
        "employee": {
          "id": 3,
          "name": "David",
          "age": 30,
          "gender": "MALE",
          "salary": 750.00,
          "companyId": 2
        }
      }
      ```

### 6. Add a New Company
- **URL:** `/companies`
- **Method:** `POST`
- **Request Body:**
    ```json
    {
      "name": "00CO"
    }
    ```
- **Response:**
    - **Status:** `201 Created`
    - **Body:**
      ```json
      {
        "message": "Company created successfully.",
        "company": {
          "id": 3,
          "name": "New Company"
        }
      }
      ```

### 7. Delete a Company
- **URL:** `/companies/{id}`
- **Method:** `DELETE`
- **Response:**
    - **Status:** `200 OK`
    - **Body:**
      ```json
      {
        "message": "Company deleted successfully."
      }
      ```

