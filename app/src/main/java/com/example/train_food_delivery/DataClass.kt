package com.example.train_food_delivery

class DataClass {
    var FirstName: String? = null
    var registerEmail:String? = null
    var ContactNo:String? = null

    constructor(FirstName: String?,
                registerEmail:String?,
                ContactNo:String?) {
        this.FirstName = FirstName
        this.registerEmail = registerEmail
        this.ContactNo = ContactNo
    }
    constructor() {}
}