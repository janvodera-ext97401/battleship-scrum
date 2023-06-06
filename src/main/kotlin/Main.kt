
import battleplan.BattlePlanServiceImpl
import gameplay.GameplayServiceImpl
import model.Player
import model.Point
import java.util.*
import kotlin.collections.ArrayList


fun main(args: Array<String>) {


    println("Hello World!")

    println("Program arguments: ${args.joinToString()}")

    val players = ArrayList<Player>()
    players.add(Player("player", false))
    players.add(Player("ai", true))


    val battlePlanServiceImpl = BattlePlanServiceImpl()
    val gameplayService = GameplayServiceImpl(players, battlePlanServiceImpl)

    gameplayService.startGame()

    while(true) {
        // TODO probably return shotResult from playerTurn or shotResult, with current player
        val playerOnTurn = gameplayService.playerTurn();


    }
}

fun createPointFrom(stringInput: String): Point {
    val alphabet = "abcdefghijklmnopqrstuvwxyz";

    val firstChar = stringInput.substring(0, 1).lowercase(Locale.getDefault());
    var firstIndex = alphabet.indexOf(firstChar);
    val secondChar = stringInput.substring(1, 2);
    val secondIndex = Integer.parseInt(secondChar) - 1;

    return Point(firstIndex, secondIndex)
}




