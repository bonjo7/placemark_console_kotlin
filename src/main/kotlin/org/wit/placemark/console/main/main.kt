package org.wit.placemark.console.main

import jdk.nashorn.internal.objects.NativeString.search
import models.PlacemarkModel
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var placemark = PlacemarkModel()
val placemarks = ArrayList<PlacemarkModel>()

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
            4 -> searchPlacemark()
            -99 -> dummyData()
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
    println("\t 4. Search Placemarks")
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

    var aPlacemark = PlacemarkModel()

    println("Add Placemark")
    println("\n\nEnter a Title: ")
    aPlacemark.title = readLine()!!
    println("\nEnter a description: ")
    aPlacemark.description = readLine()!!

    if(aPlacemark.title.isNotEmpty() && aPlacemark.description.isNotEmpty()){
        aPlacemark.id = placemarks.size.toLong()
        placemarks.add(aPlacemark.copy())
        println("Successfully entered \"${aPlacemark.title}\" as the title and \"${aPlacemark.description}\" as the description")
        logger.info ("Placemark Added: [$aPlacemark]")
    }
    else
        println("Failed to add, please enter title and description next time")
        logger.info("Placemark not added")
}

fun updatePlacemark() {
    println("Update Placemark")
    println()
    listAllPlacemarks()
    var searchId = getId()
    val aPlacemark = search(searchId)
    var tempTitle : String?
    var tempDesc : String?

    if(aPlacemark != null) {
        print("Enter a new Title for \" ${aPlacemark.title}\": ")
        tempTitle = readLine()!!
        print("Enter a new Description for \"${aPlacemark.description}\": ")
        tempDesc= readLine()!!

        if(!tempTitle.isNullOrEmpty() && !tempDesc.isNullOrEmpty()){
            aPlacemark.title = tempTitle
            aPlacemark.description = tempDesc
            println("You updated \"${aPlacemark.title}\" for title and \"${aPlacemark.description}\" for description")
        }
        else
            logger.info ("Placemark not updated")
    }
    else
        println("Placemark Not Updated...")
}

fun listAllPlacemarks() {
    println("List Placemarks")
    println()
    placemarks.forEach{ logger.info { "${it}" } }
}

fun getId() : Long {
    var strId : String?
    var searchId : Long

    print("Enter ID to search/update: ")
    strId = readLine()!!

    searchId = if(strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        0
    return searchId

}

fun search(id : Long) : PlacemarkModel? {
    var foundPlacemark : PlacemarkModel? = placemarks.find { p -> p.id == id }
    return foundPlacemark
}

fun searchPlacemark() {

    var searchId = getId()
    val aPlacemark = search(searchId)

    if(aPlacemark != null)
        println("Placemark Details [ $aPlacemark ]")
    else
        println("Placemark Not Found...")
}

fun dummyData() {
    placemarks.add(PlacemarkModel(1, "New York New York", "So Good They Named It Twice"))
    placemarks.add(PlacemarkModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    placemarks.add(PlacemarkModel(3, "Waterford City", "You get great Blaas Here!!"))
}
