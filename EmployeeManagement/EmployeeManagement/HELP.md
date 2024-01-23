1.API Request
POST http://localhost:8080/api/v1/employees
{
    "firstName":"Lalitha",
    "lastName":"Eti",
    "emailId":"lalitha.eti@gmail.com",
    "phoneNumber":"7893268928",
    "doj":"2023-01-01",
    "salary":"100000"
}

Response:
{
    "employeeId": 1,
    "firstName": "Lalitha",
    "lastName": "Eti",
    "emailId": "lalitha.eti@gmail.com",
    "phoneNumber": "7893268928",
    "doj": "2023-01-01",
    "salary": 100000.0,
    "taxDeduction": {
        "id": 2,
        "yearlySalary": null,
        "taxAmount": null,
        "cessAmount": null
    }
}

2.API 
Get Request :http://localhost:8080/api/v1/employees/taxdeduction/1
Response:
{
    "employeeId": 1,
    "firstName": "Lalitha",
    "lastName": "Eti",
    "emailId": "lalitha.eti@gmail.com",
    "phoneNumber": "7893268928",
    "doj": "2023-01-01",
    "salary": 100000.0,
    "taxDeduction": {
        "id": 2,
        "yearlySalary": 1200000.0,
        "taxAmount": 102500.0,
        "cessAmount": 0.0
    }
}
