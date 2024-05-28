

Pre requisite: Mysql 8, Java runtime 17+, Postman or any other tool to execute REST APIs, Maven 3.8

Build Jar using command

mvn clean install

Execute the jar using following command

java -jar .\tms-0.0.1-SNAPSHOT.jar

Seed data is already created for user(user role and admin role), task (Task 1, Task 2)
Send email service is created but not configured due to sendgrid account blockage, change the configuration in application.properties to use mail funcationality
Global exception handler is written to handle exception

You can use following APIs 

1) Sign up (default role is User), (Creation of Admin is not provided using API)

url: http://localhost:8005/auth/signup
type: POST
request: {
    "email":"cgokanixx@gmail.com",
    "password": "qwerty",
    "fullName": "Chintan Gokani"
} 

response: {
    "id": 1,
    "fullName": "Chintan Gokani",
    "email": "cgokani@gmail.com",
    "password": "$2a$10$asnCuixHhVfWQgSsEvmgpOaVYB.hhEvJ45NEAWQHiPk8j5BhBbTEm",
    "createdAt": "2024-05-27T13:03:02.550+00:00",
    "updatedAt": "2024-05-27T13:03:02.550+00:00",
    "enabled": true,
    "accountNonLocked": true,
    "accountNonExpired": true,
    "credentialsNonExpired": true,
    "authorities": [],
    "username": "cgokani@gmail.com"
}

2) login 

url: http://localhost:8005/auth/login
type: POST
request: {
    "email": "cgokani@gmail.com",
    "password": "qwerty"
}

response: {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjZ29rYW5pQGdtYWlsLmNvbSIsImlhdCI6MTcxNjgxOTIwMCwiZXhwIjoxNzE2ODIyODAwfQ.K3M4obyp7GgWdWbiCi5dI7hfR8THuhXHMWiz5toqFgY",
    "expiresIn": 3600000
}

For all folloing APIs use token generated after login.

3) users (to get the list of users) Only Admin can access

url: http://localhost:8005/users/
type: GET
response:[
    {
        "id": 1,
        "fullName": "Chintan Gokani",
        "email": "cgokani@gmail.com",
        "password": "$2a$10$bGbmz4IMjnXSMlIUKHVS3uKMF5MPPKcuGhWqHNL8PYCZL2UymIjDS",
        "createdAt": "2024-05-27T14:11:20.165+00:00",
        "updatedAt": "2024-05-27T14:11:20.165+00:00",
        "role": {
            "id": 1,
            "name": "USER",
            "description": "Default user role",
            "createdAt": "2024-05-27T14:11:19.680+00:00",
            "updatedAt": "2024-05-27T14:11:19.680+00:00"
        },
        "enabled": true,
        "credentialsNonExpired": true,
        "accountNonExpired": true,
        "authorities": [
            {
                "authority": "ROLE_USER"
            }
        ],
        "username": "cgokani@gmail.com",
        "accountNonLocked": true
    },
    {
        "id": 2,
        "fullName": "Chintan Gokani",
        "email": "cgokani1@gmail.com",
        "password": "$2a$10$PyxTJitHekK8ZfvgQbjiDOlqHOJ2N2lIMFVtuN9.KQk76tAvIECgm",
        "createdAt": "2024-05-27T14:11:20.551+00:00",
        "updatedAt": "2024-05-27T14:11:20.551+00:00",
        "role": {
            "id": 2,
            "name": "ADMIN",
            "description": "Administrator role",
            "createdAt": "2024-05-27T14:11:19.831+00:00",
            "updatedAt": "2024-05-27T14:11:19.831+00:00"
        },
        "enabled": true,
        "credentialsNonExpired": true,
        "accountNonExpired": true,
        "authorities": [
            {
                "authority": "ROLE_ADMIN"
            }
        ],
        "username": "cgokani1@gmail.com",
        "accountNonLocked": true
    }
]

Task APIs

1) Create Task 

url: http://localhost:8005/tasks
type: POST
request: {
    "title":"Task 3",
    "description": "New Task",
    "status" : "TO_DO",
    "priority" : 8,
    "due_date" : "2024-05-27T17:47:17.349+00:00"
}

response: {
    "id": 3,
    "title": "Task 3",
    "description": "New Task",
    "status": "TO_DO",
    "priority": 8,
    "dueDate": null,
    "createdAt": "2024-05-27T13:03:59.640+00:00",
    "updatedAt": "2024-05-27T13:03:59.640+00:00"
}




2) Get Task 

url: http://localhost:8005/tasks
type: GET


response: [
    {
        "id": 1,
        "title": "Task 1",
        "description": "New Task",
        "status": "TO_DO",
        "priority": 5,
        "dueDate": null,
        "createdAt": "2024-05-27T14:37:37.546+00:00",
        "updatedAt": "2024-05-27T14:37:37.546+00:00"
    },
    {
        "id": 2,
        "title": "Task 2",
        "description": "New Task",
        "status": "TO_DO",
        "priority": 6,
        "dueDate": null,
        "createdAt": "2024-05-27T14:37:37.667+00:00",
        "updatedAt": "2024-05-27T14:37:37.667+00:00"
    }
]



3) Get Task by ID

url: http://localhost:8005/tasks/1
type: GET


response: {
    "id": 1,
    "title": "Task 1",
    "description": "New Task",
    "status": "TO_DO",
    "priority": 5,
    "dueDate": null,
    "createdAt": "2024-05-27T14:45:16.735+00:00",
    "updatedAt": "2024-05-27T14:45:16.735+00:00"
} 


4) Get Task by title

url: http://localhost:8005/tasks/title/{Task 1}
type: GET

response: {
    "id": 1,
    "title": "Task 1",
    "description": "New Task",
    "status": "TO_DO",
    "priority": 5,
    "dueDate": null,
    "createdAt": "2024-05-27T14:45:16.735+00:00",
    "updatedAt": "2024-05-27T14:45:16.735+00:00"
} 


5) Get Task  by status

url: http://localhost:8005/tasks/status/{TO_DO}
type: GET

response: [
    {
        "id": 2,
        "title": "Task 2",
        "description": "New Task",
        "status": "TO_DO",
        "priority": 8,
        "dueDate": null,
        "createdAt": "2024-05-27T12:51:23.137+00:00",
        "updatedAt": "2024-05-27T12:51:23.137+00:00"
    },
    {
        "id": 3,
        "title": "Task 3",
        "description": "New3 Task",
        "status": "TO_DO",
        "priority": 8,
        "dueDate": null,
        "createdAt": "2024-05-27T12:51:29.518+00:00",
        "updatedAt": "2024-05-27T12:51:29.518+00:00"
    }
]


6) Get Task by description

url: http://localhost:8005/tasks/description/{New Task}
type: GET
request: [
    {
        "id": 1,
        "title": "Task 1",
        "description": "New Task",
        "status": "IN_PROGRESS",
        "priority": 8,
        "dueDate": null,
        "createdAt": "2024-05-27T12:51:10.686+00:00",
        "updatedAt": "2024-05-27T12:51:57.803+00:00"
    },
    {
        "id": 2,
        "title": "Task 2",
        "description": "New Task",
        "status": "TO_DO",
        "priority": 8,
        "dueDate": null,
        "createdAt": "2024-05-27T12:51:23.137+00:00",
        "updatedAt": "2024-05-27T12:51:23.137+00:00"
    }
]


7) Create Task 

url: http://localhost:8005/tasks
type: POST
request: {
    "title":"Task 3",
    "description": "New Task",
    "status" : "TO_DO",
    "priority" : 8,
    "due_date" : "2024-05-27T17:47:17.349+00:00"
}

response: {
    "id": 3,
    "title": "Task 3",
    "description": "New Task",
    "status": "TO_DO",
    "priority": 8,
    "dueDate": null,
    "createdAt": "2024-05-27T13:03:59.640+00:00",
    "updatedAt": "2024-05-27T13:03:59.640+00:00"
}

8) Filter Task 

url: http://localhost:8005/tasks/filter
type: POST
request: {
    "title" : "Task 1",
    "description": "New Task",
    "status":"TO_DO"
}

response: [
    {
        "id": 1,
        "title": "Task 1",
        "description": "New Task",
        "status": "TO_DO",
        "priority": 8,
        "dueDate": null,
        "createdAt": "2024-05-27T13:03:42.239+00:00",
        "updatedAt": "2024-05-27T13:03:42.239+00:00"
    }
]

9) Update Task 

url: http://localhost:8005/tasks/1
type: PATCH
request: {
    "status":"IN_PROGRESS"
}

response: {
    "id": 1,
    "title": "Task 1",
    "description": "New Task",
    "status": "IN_PROGRESS",
    "priority": 8,
    "dueDate": null,
    "createdAt": "2024-05-27T12:51:10.686+00:00",
    "updatedAt": "2024-05-27T12:51:57.803+00:00"
}


10) Delete Task
url:http://localhost:8005/tasks/1
type: Delete