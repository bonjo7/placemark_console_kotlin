package org.wit.placemark.console.main

import org.wit.placemark.console.main.models.PlacemarkModel
import mu.KotlinLogging
import org.wit.placemark.console.main.controllers.PlacemarkController
import org.wit.placemark.console.main.models.PlacemarkJSONStore
import org.wit.placemark.console.main.models.PlacemarkMemStore
import org.wit.placemark.console.main.views.PlacemarkView

private val logger = KotlinLogging.logger {}

//val placemarks = PlacemarkMemStore()
val placemarks = PlacemarkJSONStore()
val placemarkView = PlacemarkView()

fun main(args: Array<String>) {
    PlacemarkController().start()
}

fun addPlacemark(){
    var aPlacemark = PlacemarkModel()

    if (placemarkView.addPlacemarkData(aPlacemark))
        placemarks.create(aPlacemark)
    else
        logger.info("Placemark Not Added")
}

fun updatePlacemark() {

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

fun searchPlacemark() {
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