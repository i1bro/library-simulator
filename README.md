# Library Simulator

A small app that simulates a library. By default, launches at localhost:8080

## Supported Methods

### GET /
Return list of all books.
### POST /
Create and returns a new book. Fields that are not specified default to ***null***

Example: **POST localhost:8080/**
```json
{
  "title": "Harry Potter",
  "description": "good book",
  "readAlready": true
}
```
Response body:
```json
{
  "title": "Harry Potter",
  "description": "good book",
  "author": null,
  "isbn": null,
  "printYear": null,
  "readAlready": true,
  "id": 1
}
```
### GET /{id}
Return book with specified ***id***.
### PUT /{id}
Update and return book with specified ***id***. Only specified fields are updated, ***id*** field is ignored

Example: **PUT localhost:8080/1**

```json
{
  "description": "bad book"
}
```
Response body:
```json
{
  "title": "Harry Potter",
  "description": "bad book",
  "author": null,
  "isbn": null,
  "printYear": null,
  "readAlready": true,
  "id": 1
}
```
### DELETE /{id}
Delete book with specified ***id***.
### GET /search?key=value
Return list of books in which field ***key*** equals ***value***(or contains, depending on field)

Example: **GET localhost:8080/search?description=bad**

Response body:
```json
[{
  "title": "Harry Potter",
  "description": "bad book",
  "author": null,
  "isbn": null,
  "printYear": null,
  "readAlready": true,
  "id": 1
}]
```

## Run
```./gradlew bootRun```
