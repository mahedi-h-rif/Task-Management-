
# Task Management App

A Spring Boot and React-based application allowing users to add, update, or delete tasks. Users can view their tasks, while administrators have access to all user tasks.

### Entity Relation Diagrm:
![ER](https://github.com/mahedi-h-rif/Task-Management-/assets/136273197/ca41a593-03b3-4317-bd4d-81c5ab279943)

## Frontend Overview:
![420997458_250675128073166_4175326270824255456_n](https://github.com/mahedi-h-rif/Task-Management-/assets/136273197/f5af758f-c24a-4369-bb68-7d50e6cbd9a3)
![420715692_754593983231050_6362520309272348377_n](https://github.com/mahedi-h-rif/Task-Management-/assets/136273197/fd1816a2-9bae-4a17-ae8b-a98b35463130)
#### Task Creation:
![420997450_416584400708384_7646833917398952798_n](https://github.com/mahedi-h-rif/Task-Management-/assets/136273197/aa7faf04-e948-4596-ad89-56b429f391d5)
#### List of Tasks:
![420976433_945022497206969_3890218161723585817_n (1)](https://github.com/mahedi-h-rif/Task-Management-/assets/136273197/39b131ff-6a73-45e3-b179-72f186b3021e)


## API Reference


### Register a New User


```http
POST  /api/register
```

| Parameter | Type     |           
| :-------- | :------- | 
| `userName`|`string`  |                             
|`email`      |`string`|                             
|`password` | `string` |  |

Returns a response with the user's name and email (excluding the password) upon successful registration.

Request Body Example:<br>
 { <br>
   "userName": "Test", <br>
  "email": "test@example.com", <br>
  "password": "securePassword123" <br>
}<br>

Response Body Example:<br>
{  <br>
  "name": "Test",<br>
  "email": "test@example.com"  <br> 
}<br>


### User Login

```http
POST  /api/login
```

| Parameter | Type     |           
| :-------- | :------- | 
`userName`|`string`|                            
|`password` | `string` | 

Authenticates the user and returns a JWT (JSON Web Token) if the provided credentials are valid.



### Create a Task

```http
POST /api/createTask
```

| Parameter | Type     |           
| :-------- | :------- | 
|`Title`|`string`|                            
|`Description` | `string` | 
|`Status`| |`string`|

Authenticates the user and returns a JWT (JSON Web Token) if the provided credentials are valid.


Request Body Example:<br>
{<br>
  "title": "Sample Task",<br>
  "description": "This is a sample task description.",<br>
  "status": "IN_PROGRESS"<br>
}<br>



### Get All Tasks (Admin Only)



```http
  GET /api/allTasks
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Page` | `Integer` | `Page Number` |
| `Size`| `Integer`| `Number of tasks per page`|
| `SortBy`| `string`| `Sorting criteria`|

Retrieves all tasks for the authenticated user. Returns task details in a list.

Request Body Example:<br>
{<br>
  "page": 2,<br>
  "size": 20,<br>
  "sortBy": "title"<br>
}<br>

Response Body Example:<br>
{<br>
  "content": [<br>
    {<br>
      "id": 1,<br>
      "title": "Task 1",<br>
      "description": "Description for Task 1",<br>
      "status": "COMPLETED"<br>
    },<br>
    {<br>
      "id": 2,<br>
      "title": "Task 2",<br>
      "description": "Description for Task 2",<br>
      "status": "IN_PROGRESS"<br>
    }<br>
  ],<br>
  "totalElements": 2,<br>
  "totalPages": 1,<br>
  "size": 10,<br>
  "number": 0,
  "numberOfElements": 2,<br>
}<br>




#### Status Codes:
200 OK - Successful retrieval

401 Unauthorized - User not authenticated


#### Update Task



```http
  PUT /api/updateTask/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Id` | `Long` | `ID of the task to be updated` |


Updates an existing task with the provided details. Returns the updated task details.

#### Status Codes:
200 OK - Successful Update

500 - internal Server Error 




### Delete Task

```http
  PUT /api/deleteTask/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Id` | `Long` | `ID of the task to be deleted` |


Delete an existing task with the task id.

#### Status Codes:
200 OK - Successfully Deleted.

500 - internal Server Error 
