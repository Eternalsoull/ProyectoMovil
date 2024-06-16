package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.CarEntity

class Cars {
    var cars = ArrayList<CarEntity>()

    constructor(cars: ArrayList<CarEntity>){
        this.cars = cars
    }

    fun getArrayListFullName(): ArrayList<String> {
        var list = ArrayList<String>()
        for (car in cars) {
            list.add(car.getFullName())
        }
        return list
    }
}