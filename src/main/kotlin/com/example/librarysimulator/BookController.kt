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

    @GetMapping("/search")
    fun search(@RequestParam allRequestParams: Map<String, String>): Iterable<Book> {
        if (allRequestParams.size > 1) {
            throw IncorrectSearchParametersException()
        }
        if (allRequestParams.isEmpty()) {
            return all()
        }
        lateinit var param: String
        lateinit var value: String
        allRequestParams.forEach { (t, u) ->
            param = t
            value = u
        }
        try {
            return when (param) {
                "title" -> repository.findAllByTitleContaining(value)
                "description" -> repository.findAllByDescriptionContaining(value)
                "author" -> repository.findAllByAuthor(value)
                "isbn" -> repository.findAllByIsbn(value)
                "printYear" -> repository.findAllByPrintYear(value.toInt())
                "readAlready" -> repository.findAllByReadAlready(value.toBooleanStrict())
                "id" -> repository.findAllById(listOf(value.toInt()))
                else -> throw IncorrectSearchParametersException()
            }
        } catch(ex: IllegalArgumentException) {
            throw IncorrectSearchParametersException()
        }
    }

    @ExceptionHandler(BookNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun bookNotFoundHandler(ex: BookNotFoundException): String? = ex.message

    @ExceptionHandler(IncorrectSearchParametersException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun tooManySearchParametersHandler(ex: IncorrectSearchParametersException): String? = ex.message
}
