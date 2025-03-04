package com.example.cognitiveexercisesapp.ui.data

object GameInstructions {
    var currentLanguage = "EN" // Default language

    fun getWronAnswerGame1(): String {
        return if (currentLanguage == "NO") {
            "Oops!\nDu vlakte feil nummer."
        } else {
            "Oops!\nYou selected a wrong number"
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
            "Par ord i henhold til regelen ovenfor"
        } else {
            "Pair words according to the rule stated above"
        }
    }

    fun getGame3Instructions(): String {
        return if (currentLanguage == "NO") {
            "Knytt varen til riktig bilde"
        } else {
            "Associate the item with the correct image"
        }
    }


    fun toggleLanguage() {
        currentLanguage = if (currentLanguage == "EN") "NO" else "EN"
    }
}
