package com.example.librarysimulator

import com.example.librarysimulator.Book
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<Book, Int> {
    fun findAllByTitleContaining(description: String): Iterable<Book>
    fun findAllByAuthor(author: String): Iterable<Book>
    fun findByIsbn(isbn: String): Book?
    fun findAllByPrintYear(printYear: Int): Iterable<Book>
    fun findAllByReadAlreadyTrue(): Iterable<Book>
    fun findAllByReadAlreadyFalse(): Iterable<Book>
}