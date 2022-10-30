package com.example.librarysimulator

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
class BookController(private val repository: BookRepository) {
    @GetMapping
    fun all() = repository.findAll()

    @PostMapping
    fun newBook(@RequestBody newBook: Book) = repository.save(newBook)

    @GetMapping("/{id}")
    fun one(@PathVariable id: Int) = repository.findById(id).orElseThrow { BookNotFoundException(id) }

    @PutMapping("/{id}")
    fun updateBook(@RequestBody newBook: Book, @PathVariable id: Int) =
        repository.save(repository.findById(id).orElseThrow { BookNotFoundException(id) }.apply { update(newBook) })

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Int) = repository.deleteById(id)

    @ResponseBody
    @ExceptionHandler(BookNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun bookNotFoundHandler(ex: BookNotFoundException): String? {
        return ex.message
    }
}