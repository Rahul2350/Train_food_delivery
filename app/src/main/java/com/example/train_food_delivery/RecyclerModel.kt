package com.example.train_food_delivery

class RecyclerModel (foodname: String?,resname: String?,price:String?,foodimage : Int?) {
    private var foodname: String
    private var resname: String
    private var price: String
    private var foodimage : Int

    init {
        this.foodname = foodname!!
        this.resname =  resname!!
        this.price = price!!
        this.foodimage = foodimage!!
    }
    fun getfoodname():String? {
        return foodname
    }
    fun getresname():String? {
        return resname
    }
    fun getprice():String? {
        return price
    }
    fun getIamge():Int?{
        return foodimage
    }
}