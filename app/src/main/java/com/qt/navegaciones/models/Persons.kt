package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.PersonEntity

class Persons
{
    var persons = ArrayList<PersonEntity>()

   constructor(persons: ArrayList<PersonEntity>){
       this.persons = persons
   }

    fun getArrayListFullName(): ArrayList<String> {
        var list = ArrayList<String>()
        for (person in persons) {
            list.add(person.getFullName())
        }
        return list
    }
}



