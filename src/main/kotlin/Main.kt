
import battleplan.BattlePlanServiceImpl
import gameplay.GameplayServiceImpl
import model.Player


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




