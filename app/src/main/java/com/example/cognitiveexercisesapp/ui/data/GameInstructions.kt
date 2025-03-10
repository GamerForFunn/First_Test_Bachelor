package com.example.cognitiveexercisesapp.ui.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


object GameInstructions {


    var difficulty by mutableStateOf(1f) // Default value


    var currentLanguage = "EN" // Default language

/* TODO im using the \n tag becaus its the only way i could figur out how to verticly center text :( */
    fun getWronAnswerGame1(): String {
        return if (currentLanguage == "NO") {
            "Oops!\nDu vlakte feil\nnummer."
        } else {
            "Oops!\nYou selected a\nwrong\nnumber"
        }
    }

    fun getExerciseFinished(): String {
        return if (currentLanguage == "NO") {
            "Øvelse ferdig!"
        } else {
            "Exercise Finished!"
        }
    }

    fun getGame1Instructions(): String {
        return if (currentLanguage == "NO") {
            "Trykk på flisene i rekkefølge fra laveste til høyeste"
        } else {
            "Tap the tiles in order from lowest to highest"
        }
    }

    fun getGame2Instructions(): String {
        return if (currentLanguage == "NO") {
            "Trykk på det alternativet som er det samme som det store bildet"
        } else {
            "Press the same image as the one above"
        }
    }

    fun getGame3Instructions(): String {
        return if (currentLanguage == "NO") {
            "Trykk på de to sokkene som har ord av lignende betydning"
        } else {
            "Press the two socks containing words of similar meaning"
        }
    }


    fun toggleLanguage() {
        currentLanguage = if (currentLanguage == "EN") "NO" else "EN"
    }
}
