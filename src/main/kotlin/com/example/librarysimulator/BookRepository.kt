package com.example.librarysimulator

import com.example.librarysimulator.Book
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Int> {
    fun findAllByTitleContaining(title: String): Iterable<Book>
    fun findAllByDescriptionContaining(description: String): Iterable<Book>
    fun findAllByAuthor(author: String): Iterable<Book>
    fun findAllByIsbn(isbn: String): Iterable<Book>
    fun findAllByPrintYear(printYear: Int): Iterable<Book>
    fun findAllByReadAlready(readAlready: Boolean): Iterable<Book>
}