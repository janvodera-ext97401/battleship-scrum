import battleplan.BattlePlanServiceImpl
import gameplay.GameplayServiceImpl
import gamesettings.GameSettingsServiceImpl
import gui.GuiService
import model.PlacementResult
import model.Player
import model.ShotResult
import kotlin.random.Random


fun main() {
    val players = ArrayList<Player>()
    players.add(Player("player", false))
    players.add(Player("ai", true))


    val battlePlanServiceImpl = BattlePlanServiceImpl()
    val guiService = GuiService()
    val gameplayService = GameplayServiceImpl(players, battlePlanServiceImpl)
    val gameSettingsService = GameSettingsServiceImpl()

    fun promptUserForInput(): Int {
        guiService.printShipAutoplacementOption()
        return gameSettingsService.resolveAutoShipPlacementFunction(System.`in`)
    }

    fun placeShipsForUser(playerName: String) {
        battlePlanServiceImpl.getShipsToPlace().forEach {
            guiService.printQuestionForShipPlacement(it.length)
            val userInput = gameSettingsService.getShipPlacement(System.`in`)

            if (userInput == null) {
                guiService.onInvalidShipPlacementInput()
                return@forEach
            }
            val result = battlePlanServiceImpl.addShip(
                it.copy(
                    position = userInput.coordinates,
                    direction = userInput.direction,
                    ownerName = playerName
                )
            )

            if (result != PlacementResult.OK) {
                guiService.onShipPlacementError(result)
                return@forEach
            } else {
                guiService.printShipPlaced()
            }
        }
    }



    gameplayService.startGame()
    guiService.printBanner()
    battlePlanServiceImpl.autoPlaceShips(players.first { it.isNPC })

    var placementMode = 0
    do {
        placementMode = promptUserForInput()
        if (placementMode < 1) guiService.printError()
    } while (placementMode < 1)

    when (placementMode) {
        1 -> battlePlanServiceImpl.autoPlaceShips(players.first { !it.isNPC })
        2 -> placeShipsForUser(players.first { !it.isNPC }.name)
    }


    while (!gameplayService.isThereWinner()) {
        val playerOnTurn = gameplayService.whoIsOnTurn()
        val playerNotOnTurn = gameplayService.whoIsNotOnTurn()

        guiService.printWhoPlays(playerOnTurn)

        if (playerOnTurn.isNPC) {
            Thread.sleep(Random.nextLong(2000, 6000))
        }

        if (!playerOnTurn.isNPC) {
            // Enemy's battle plan
            val enemy_battleplan = battlePlanServiceImpl.getBattlePlan(playerNotOnTurn)
            guiService.printBattleplanDescription(true)
            guiService.printBattleplan(enemy_battleplan)

            // My battle plan
            val my_battleplan = battlePlanServiceImpl.getBattlePlan(playerOnTurn)
            guiService.printBattleplanDescription(false)
            guiService.printBattleplan(my_battleplan)
        }

        val turnResult = gameplayService.playerTurn()
        if (turnResult != ShotResult.INVALID) {
            guiService.printRandomAttackMessage()
        }
        guiService.printShot(playerOnTurn, turnResult)
    }
    guiService.printWinner()

}





