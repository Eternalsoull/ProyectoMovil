package com.qt.navegaciones.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.qt.navegaciones.R
import com.qt.navegaciones.models.Cars

class CarAdapter : BaseAdapter{
    var context : Context
    var cars : Cars

    constructor(context: Context, cars: Cars){
        this.context = context
        this.cars = cars
    }

    override fun getCount(): Int {
        return cars.cars.size
    }

    override fun getItem(position: Int): Any {
        return cars.cars[position]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View? {
        var view : View? = p1
        if (view == null){
            view = View.inflate(context, R.layout.item_adapter, null)
        }
        var car = cars.cars[position]
        var txtName = view?.findViewById<TextView>(R.id.txtFullName)
        txtName?.text = car.getFullName()
        var txtColor = view?.findViewById<TextView>(R.id.txtItemColor)
        txtColor?.text = car.color
        var txtPlaca = view?.findViewById<TextView>(R.id.txtItemPlaca)
        txtPlaca?.text = car.placa
        var imgView = view?.findViewById<ImageView>(R.id.imgProfile)
        Glide.with(context).load(car.url).into(imgView!!)


        return view

    }

}