
import battleplan.BattlePlanServiceImpl
import gameplay.GameplayServiceImpl
import gui.GuiService
import model.Player
import kotlin.random.Random


fun main(args: Array<String>) {


    println("Hello World!")

    println("Program arguments: ${args.joinToString()}")

    val players = ArrayList<Player>()
    players.add(Player("player", false))
    players.add(Player("ai", true))


    val battlePlanServiceImpl = BattlePlanServiceImpl()
    val guiService = GuiService()
    val gameplayService = GameplayServiceImpl(players, battlePlanServiceImpl)

    gameplayService.startGame()
    guiService.printBanner()
    while(!gameplayService.isThereWinner()) {
        val playerOnTurn = gameplayService.whoIsOnTurn()

        guiService.printWhoPlays(playerOnTurn)

        if (playerOnTurn.isNPC) {
            guiService.printAIIsTargeting()
            Thread.sleep(Random.nextLong(2000, 6000))
        }
        // TODO probably return shotResult from playerTurn or shotResult, with current player
        val turnResult = gameplayService.playerTurn()
        guiService.printShot(turnResult)
    }
}




