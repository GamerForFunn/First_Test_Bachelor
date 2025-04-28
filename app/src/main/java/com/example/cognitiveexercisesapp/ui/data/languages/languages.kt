package com.example.cognitiveexercisesapp.ui.data.languages

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.cognitiveexercisesapp.ui.data.game3model.Game3Difficulty

object LanguageManager {
    var currentLanguage by mutableStateOf("NO")

    val language: LanguageTexts
        get() = if (currentLanguage == "EN") English else Norwegian
}

interface LanguageTexts {
    val mainActivity: MainActivityTexts
    val homeScreen: HomeScreenTexts
    val gameListScreen: GameListScreenTexts
    val commonGameTexts: CommonGameTexts
    val game1Instructions: Game1InstructionsTexts
    val game2Instructions: Game2InstructionsTexts
    val game3Instructions: Game3InstructionsTexts
    val wrongAnswerScreen: WrongAnswerScreenTexts
    val exerciseFinishedScreen: ExerciseFinishedScreenTexts
    val comparisonChartScreen: ComparisonChartScreenTexts
    val permissionStatus: PermissionStatusTexts
}
// ____________________________________________________________________________________ English __________________________________________________________________________________
object English : LanguageTexts {
    override val mainActivity = MainActivityTexts(
        home = "Home",
        game = "Game",
    )

    override val homeScreen = HomeScreenTexts(
        appTitle = "Brain Exercises",
        selectLanguage = "Select Language:",
        selectDifficulty = "Select Difficulty:",
        permissionsTitle = "Permissions",
        permissionsExplanation = "Both permissions must be granted to use the app.",
        overlayPermission = "Display over other apps",
        usagePermission = "Usage access",
        appReady = "The app is ready to use!"
    )

    override val gameListScreen = GameListScreenTexts(
        availableGames = "Available Games",
        game1Title = "Game 1",
        game1Description = "Ascending Order",
        game2Title = "Game 2",
        game2Description = "Find the Right Picture",
        game3Title = "Game 3",
        game3Description = "Match the Socks"
    )

    override val commonGameTexts = CommonGameTexts(
        cancel = "Cancel",
        start = "Start",
        help = "Help"
    )

    override val game1Instructions = Game1InstructionsTexts(
        instructionsTitle = "Number Game!",
        instructions = "Press the numbers in ascending order",
        runningGameHint = "___hint___E"
    )

    override val game2Instructions = Game2InstructionsTexts(
        instructionsTitle = "Find the Picture!",
        instructions = "Press the option that is identical to the large picture",
        runningGameHint = "Please select the correct image!"
    )

    override val game3Instructions = Game3InstructionsTexts(
        instructionsTitle = "Match the Words!",
        instructions = "Press the two socks that contain words of the same meaning to find the correct pair",
        runningGameHint = "Match the socks!"
    )

    override val wrongAnswerScreen = WrongAnswerScreenTexts(
        title = "Oops!\nWrong Answer",
        text = "You can try this game again, or quit.\nWhat do you want to do?",
        exit = "Quit",
        tryAgain = "Try Again",
        note = "Note: If you exit the game without completing it, you will not receive any points."
    )

    override val exerciseFinishedScreen = ExerciseFinishedScreenTexts(
        title = "Game Done!",
        time = "Time:",
        seconds = "seconds",
        wrong = "Wrong:",
        difficulty = "Difficulty:",
        score = "Score:",
        continueButton = "Continue",
    )

    override val comparisonChartScreen = ComparisonChartScreenTexts(
        title = "Score Chart",
        points = "Your points",
        average = "Average points",
        info = "Other players scored an average of ",
        continueButton = "Continue",
        infoPoint = " points",
    )

    override val permissionStatus = PermissionStatusTexts(
        buttonTextOn = "Enabled",
        buttonTextOff = "Not enabled"
    )
}
// ____________________________________________________________________________________ Norwegian ____________________________________________________________________________________
object Norwegian : LanguageTexts {

    override val mainActivity = MainActivityTexts(
        home = "Hjem",
        game = "Spill",
    )
    override val homeScreen = HomeScreenTexts(
        appTitle = "Hjernetrim",
        selectLanguage = "Velg språk:",
        selectDifficulty = "Velg vanskelighetsgrad:",
        permissionsTitle = "Tillatelser",
        permissionsExplanation = "For å kunne bruke appen må begge tillatelser være aktivert.",
        overlayPermission = "Vis over andre apper",
        usagePermission = "Brukstilgang",
        appReady = "Appen er klar til bruk!"
    )

    override val gameListScreen = GameListScreenTexts(
        availableGames = "Tilgjengelige spill",
        game1Title = "Spill 1",
        game1Description = "Stigende Rekkefølge",
        game2Title = "Spill 2",
        game2Description = "Finn riktig bilde",
        game3Title = "Spill 3",
        game3Description = "Par sokkene"
    )
    override val commonGameTexts = CommonGameTexts(
        cancel = "Avbryt",
        start = "Start",
        help = "Hjelp"
    )

    override val game1Instructions = Game1InstructionsTexts(
        instructionsTitle = "Tallspill!",
        instructions = "Trykk på tallene i stigende rekkefølge",
        runningGameHint = "___hint___N"
    )

    override val game2Instructions = Game2InstructionsTexts(
        instructionsTitle = "Finn bildet!",
        instructions = "Trykk på det alternativet som er det samme som et store bildet",
        runningGameHint = "Vennligst velg riktig bilde!"
    )

    override val game3Instructions = Game3InstructionsTexts(
        instructionsTitle = "Par ordene!",
        instructions = "Trykk på de to sokkene som inneholder ord av lik betydning for å finne det riktige paret",
        runningGameHint = "Par sokkene!"
    )

    override val wrongAnswerScreen = WrongAnswerScreenTexts(
        title = "Oops!\nDu valgte feil svar.",
        text = "Du kan prøve dette spillet igjen, eller avslutte.\nHva vil du gjøre?",
        exit = "Avbryt",
        tryAgain = "Prøv igjen",
        note = "Merk: Hvis du avslutter spillet uten å fullføre det, vil du ikke motta noen poeng"
    )

    override val exerciseFinishedScreen = ExerciseFinishedScreenTexts(
        title = "Spill fullført!",
        time = "Tid:",
        seconds = "sekunder",
        wrong = "Feil:",
        difficulty = "Nivå:",
        score = "Poeng:",
        continueButton = "Fortsett",
    )

    override val comparisonChartScreen = ComparisonChartScreenTexts(
        title = "Poengdiagram",
        points = "Dine poeng",
        average = "Snittspoeng",
        info = "Andre spillere fikk i gjennomsnitt ",
        continueButton = "Fortsett",
        infoPoint = " poeng",
    )

    override val permissionStatus = PermissionStatusTexts(
        buttonTextOn = "Aktivert",
        buttonTextOff = "Ikke aktivert"
    )


}
data class MainActivityTexts(
    val home: String,
    val game: String
)


data class HomeScreenTexts(
    val appTitle: String,
    val selectLanguage: String,
    val selectDifficulty: String,
    val permissionsTitle: String,
    val permissionsExplanation: String,
    val overlayPermission: String,
    val usagePermission: String,
    val appReady: String
)

data class GameListScreenTexts(
    val availableGames: String,
    val game1Title: String,
    val game1Description: String,
    val game2Title: String,
    val game2Description: String,
    val game3Title: String,
    val game3Description: String
)
data class CommonGameTexts(
    val cancel: String,
    val start: String,
    val help: String,
)
data class Game1InstructionsTexts(
    val instructions: String,
    val instructionsTitle: String,
    val runningGameHint: String,
)
data class Game2InstructionsTexts(
    val instructions: String,
    val instructionsTitle: String,
    val runningGameHint: String,
)
data class Game3InstructionsTexts(
    val instructions: String,
    val instructionsTitle: String,
    val runningGameHint: String,
)

data class WrongAnswerScreenTexts(
    val title: String,
    val text: String,
    val exit: String,
    val tryAgain: String,
    val note: String,
)

data class ExerciseFinishedScreenTexts(
    val title: String,
    val time: String,
    val seconds: String,
    val wrong: String,
    val difficulty: String,
    val score: String,
    val continueButton: String,
)

data class ComparisonChartScreenTexts(
    val title: String,
    val points: String,
    val average: String,
    val info: String,
    val continueButton: String,
    val infoPoint: String,
)

data class PermissionStatusTexts(
    val buttonTextOn: String,
    val buttonTextOff: String,
)
