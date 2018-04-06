package com.example.baptiste.smack.Model

/**
 * Created by Baptiste on 05/04/2018.
 */
class Channel(val name:String, val description:String, val id:String) {
    override fun toString(): String {
        return "#$name"
    }
}