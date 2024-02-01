
# Task Management App

A Spring Boot and React-based application allowing users to add, update, or delete tasks. Users can view their tasks, while administrators have access to all user tasks.

### Entity Relation Diagrm:
![ER](https://github.com/mahedi-h-rif/Task-Management-/assets/136273197/ca41a593-03b3-4317-bd4d-81c5ab279943)

## API Reference

#### Register a New User


```http
POST  /api/register
```

| Parameter | Type     |           
| :-------- | :------- | 
| `userName`|`string`  |                             
|`email`      |`string`|                             
|`password` | `string` |  |

Returns a response with the user's name and email (excluding the password) upon successful registration.



#### User Login

```http
POST  /api/login
```

| Parameter | Type     |           
| :-------- | :------- | 
`userName`|`string`|                            
|`password` | `string` | 

Authenticates the user and returns a JWT (JSON Web Token) if the provided credentials are valid.

#### Get All Tasks (Admin Only)



```http
  GET /api/allTasks
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Page` | `Integer` | `Page Number` |
| `Size`| `Integer`| `Number of tasks per page`|
| `SortBy`| `string`| `Sorting criteria`|

Retrieves all tasks for the authenticated user. Returns task details in a list.

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

#### Delete Task

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
