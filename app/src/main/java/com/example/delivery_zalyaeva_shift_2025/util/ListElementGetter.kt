package com.example.delivery_zalyaeva_shift_2025.util

fun <T> List<T>.getElements(number: Int, startIndex: Int): List<T> {
    if (this.size >= number){
        val list = ArrayList<T>()
        for(i in startIndex..number) {
            list.add(this[i])
        }
        return list
    }
    else {
        val list = ArrayList<T>()
        for(i in startIndex..this.size) {
            list.add(this[i])
        }
        return list
    }
}