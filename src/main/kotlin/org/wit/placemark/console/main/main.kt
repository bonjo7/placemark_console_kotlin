package org.wit.placemark.console.main

import models.PlacemarkModel
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var placemark = PlacemarkModel()

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
    placemark.title = readLine()!!
    println("\nEnter a description: ")
    placemark.description = readLine()!!
    println("You entered \"${placemark.title}\" as the title and \"${placemark.description}\" as the description")
}

fun updatePlacemark() {
    println("Update Placemark")
    println("\n\nEnter a new title for \"${placemark.title}\": ")
    placemark.title = readLine()!!
    println("Enter a new description for \"${placemark.description}\": ")
    placemark.description = readLine()!!
    println("You updated \"${placemark.title}\" for title and \"${placemark.description}\" as description")
}

fun listAllPlacemarks() {
    println("You choose to list all placemarks")
}

