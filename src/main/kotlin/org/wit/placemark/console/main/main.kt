package org.wit.placemark.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var title : String = ""
var description: String = ""

fun main(args: Array<String>){
    logger.info { "Launching Placemark Console App" }
    println("\nPlacemark Kotlin App Version 1.0")

    var input: Int
    do {
        input = menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listAllPlacemarks()
            0 -> println("Exiting App - Bye Bye")
            else -> print("Invalid Option, select again")
        }
        println()
    } while (input != 0)
    logger.info("Shutting Down Placemark Console App")
}

fun menu(): Int {

    var option: Int
    var input: String? = null

    println("\nMain Menu")
    println("\n\t 1. Add Placemark")
    println("\t 2. Update Placemark")
    println("\t 3. List All Placemarks")
    println("\t 0. Exit")
    println("\nEnter an integer: ")

    input = readLine()!!

    option = if(input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        0
    return option
}

fun addPlacemark() {

    println("Add Placemark")
    println("\n\nEnter a Title: ")
    title = readLine()!!
    println("\nEnter a description: ")
    description = readLine()!!
    println("You entered \"$title\" as the title and \"$description\" as the description")
}

fun updatePlacemark() {
    println("Update Placemark")
    println("\n\nEnter a new title for \"$title\": ")
    title = readLine()!!
    println("Enter a new description for \"$description\": ")
    description = readLine()!!
    println("You updated \"$title\" for title and \"$description\" as description")
}

fun listAllPlacemarks() {
    println("You choose to list all placemarks")
}

