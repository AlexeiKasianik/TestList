package com.itexus.app.data.model

import android.os.Parcelable
import java.util.*
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Item : Parcelable {

    abstract val id: Int

    data class Notice(
        override val id: Int,
        val title: String = "Mock Notice title",
        val details: String = "Mock Notice details",
        val flightDate: Date = Date(),
        val gate: String = "Mock gate",
    ) : Item() {
        override fun toString(): String {
            return "$title \n$details \n$flightDate \n$gate"
        }
    }

    data class Event(
        override val id: Int,
        val title: String = "Mock Event title",
        val details: String = "Mock Event details",
        val startTime: Date = Date(),
        val endTime: Date = Date(),
        val name: String = "Mock name",
    ) : Item() {
        override fun toString(): String {
            return "$title \n$details \n$startTime \n$endTime \n$name"
        }
    }

    data class Move(
        override val id: Int,
        val title: String = "Mock Move title",
        val details: String = "Mock Move details",
        var fromPlace: String = "Mock from place",
        var toPlace: String = "Mock to place",
        var estimateTime: Double = 0.0,
    ) : Item() {
        override fun toString(): String {
            return "$title \n$details \n$fromPlace \n$toPlace \n$estimateTime"
        }
    }
}
