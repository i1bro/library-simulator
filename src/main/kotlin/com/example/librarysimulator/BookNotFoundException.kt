package com.example.librarysimulator

class BookNotFoundException(id: Int) : RuntimeException("Could not find book with id $id")
