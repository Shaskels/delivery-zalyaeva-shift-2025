package com.example.delivery_zalyaeva_shift_2025.util

fun <T> List<T>.getElements(number: Int, startIndex: Int): List<T> {
    val list = ArrayList<T>()
    if (this.size >= number){
        for(i in startIndex..< number + startIndex) {
            list.add(this[i])
        }
    }
    else {
        for(i in startIndex..this.size) {
            list.add(this[i])
        }
    }
    return list
}