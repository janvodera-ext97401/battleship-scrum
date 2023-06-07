
import battleplan.BattlePlanServiceImpl
import gameplay.GameplayServiceImpl
import gui.GuiService
import model.Direction
import model.Player
import model.Point
import model.Ship
import model.ShotResult
import kotlin.random.Random


fun main() {
    val players = ArrayList<Player>()
    players.add(Player("player", false))
    players.add(Player("ai", true))


    val battlePlanServiceImpl = BattlePlanServiceImpl()
    val guiService = GuiService()
    val gameplayService = GameplayServiceImpl(players, battlePlanServiceImpl)

    gameplayService.startGame()
    guiService.printBanner()

    players.forEach {
        val point1 = Point(1, 1)
        val ship1 = Ship(point1, 1, Direction.HORIZONTAL, it.name)

        val point2 = Point(2, 2)
        val ship2 = Ship(point2, 2, Direction.HORIZONTAL, it.name)

        val point3 = Point(3, 3)
        val ship3 = Ship(point3, 3, Direction.HORIZONTAL, it.name)

        val point4 = Point(4, 4)
        val ship4 = Ship(point4, 4, Direction.HORIZONTAL, it.name)

        battlePlanServiceImpl.addShip(ship1)
        battlePlanServiceImpl.addShip(ship2)
        battlePlanServiceImpl.addShip(ship3)
        battlePlanServiceImpl.addShip(ship4)
    }

    while(!gameplayService.isThereWinner()) {
        val playerOnTurn = gameplayService.whoIsOnTurn()

        guiService.printWhoPlays(playerOnTurn)

        if (playerOnTurn.isNPC) {
            Thread.sleep(Random.nextLong(2000, 6000))
        }

        val turnResult = gameplayService.playerTurn()
        if (turnResult != ShotResult.INVALID) {
            guiService.printRandomAttackMessage()
        }
            guiService.printShot(playerOnTurn, turnResult)
    }
    guiService.printWinner()
}




