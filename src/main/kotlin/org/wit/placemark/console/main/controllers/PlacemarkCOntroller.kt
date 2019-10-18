package org.wit.placemark.console.main.controllers

import mu.KotlinLogging
import org.wit.placemark.console.main.addPlacemark
import org.wit.placemark.console.main.models.PlacemarkJSONStore
import org.wit.placemark.console.main.models.PlacemarkModel
import org.wit.placemark.console.main.searchPlacemark
import org.wit.placemark.console.main.updatePlacemark
import org.wit.placemark.console.main.views.PlacemarkView

class PlacemarkController {

    //val placemarks = PlacemarkMemStore()

    val placemarks = PlacemarkJSONStore()
    val placemarkView = PlacemarkView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark Console App" }
        println("Placemark Kotlin App Version 1.0")
    }

    fun menu() :Int { return placemarkView.menu() }

    fun add(){
        var aPlacemark = PlacemarkModel()

        if (placemarkView.addPlacemarkData(aPlacemark))
            placemarks.create(aPlacemark)
        else
            logger.info("Placemark Not Added")
    }

    fun list() {
        placemarkView.listPlacemarks(placemarks)
    }

    fun update() {

        placemarkView.listPlacemarks(placemarks)
        var searchId = placemarkView.getId()
        val aPlacemark = search(searchId)

        if(aPlacemark != null) {
            if(placemarkView.updatePlacemarkData(aPlacemark)) {
                placemarks.update(aPlacemark)
                placemarkView.showPlacemark(aPlacemark)
                logger.info("Placemark Updated : [ $aPlacemark ]")
            }
            else
                logger.info("Placemark Not Updated")
        }
        else
            println("Placemark Not Updated...")
    }

    fun search() {
        val aPlacemark = search(placemarkView.getId())!!
        placemarkView.showPlacemark(aPlacemark)
    }


    fun search(id: Long) : PlacemarkModel? {
        var foundPlacemark = placemarks.findOne(id)
        return foundPlacemark
    }

    fun dummyData() {
        placemarks.create(PlacemarkModel(title = "New York New York", description = "So Good They Named It Twice"))
        placemarks.create(PlacemarkModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
        placemarks.create(PlacemarkModel(title = "Waterford City", description = "You get great Blaas Here!!"))
    }

    fun start(){
        logger.info { "Launching Placemark Console App" }
        println("Placemark Kotlin App Version 1.0")

        var input: Int

        do {
            input = org.wit.placemark.console.main.placemarkView.menu()
            when(input) {
                1 -> addPlacemark()
                2 -> updatePlacemark()
                3 -> list()
                4 -> searchPlacemark()
                -99 -> dummyData()
                0 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != 0)
        logger.info { "Shutting Down Placemark Console App" }
    }
}