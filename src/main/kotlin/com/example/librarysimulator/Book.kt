package com.example.librarysimulator

import javax.persistence.*
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

@Entity
data class Book(
    var title: String?,
    var description: String?,
    var author: String?,
    var isbn: String?,
    var printYear: Int?,
    var readAlready: Boolean?,
    @Id @GeneratedValue var id: Int? = null
) {
    fun update(newBook: Book) {
        for (property in Book::class.memberProperties) {
            if (property.name != "id" && property is KMutableProperty<*>) {
                if (property.get(newBook) != null) {
                    property.setter.call(this, property.get(newBook))
                }
            }
        }
    }
}
