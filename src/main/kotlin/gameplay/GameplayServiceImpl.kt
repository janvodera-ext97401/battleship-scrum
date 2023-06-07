package gameplay

import battleplan.BattlePlanService
import model.Player
import model.Point
import model.ShotResult
import kotlin.random.Random

class GameplayServiceImpl(
    private val players: List<Player>,
    private val battlePlanService: BattlePlanService
) : GameplayService {
    private lateinit var playerOnTurn: Player
    private lateinit var playerNotOnTurn: Player
    override fun startGame() {
        playerOnTurn = players.first()
        playerNotOnTurn = players.first { player -> player != playerOnTurn }
        battlePlanService.createBoard(10);
    }

    override fun endGame() {
        //todo;
    }

    override fun playerTurn(): ShotResult {
        val shotCoordinates = when (playerOnTurn.isNPC) {
            true -> createRandomPoint()
            false -> createPointFromInput(readln())
        } ?: return ShotResult.INVALID

        return when (val result = battlePlanService.shot(playerOnTurn, shotCoordinates)) {
            ShotResult.MISS -> {
                playerOnTurn = players.filterNot { player -> player == playerOnTurn }.first()
                result
            }

            else -> result
        }
    }

    override fun whoIsOnTurn(): Player {
        return playerOnTurn
    }

    override fun whoIsNotOnTurn(): Player {
        return playerNotOnTurn
    }

    override fun isThereWinner(): Boolean {
        return false;
    }

    override fun setWinner(player: Player) {
       // winner = player
    }

    private fun createPointFromInput(stringInput: String): Point? {
        val alphabet = "abcdefghijklmnopqrstuvwxyz";
        val coordinates = stringInput.split(",")

        if (coordinates.size != 2) return null

        val rowChar = coordinates.first().lowercase()
        val rowNum = alphabet.indexOf(rowChar)

        if (rowNum == -1) return null

        val columnChar = coordinates.component2()

        val columnNum = Integer.parseInt(columnChar) - 1

        if (columnNum < 0) return null

        return Point(rowNum, columnNum)
    }

    private fun createRandomPoint(): Point {
        return Point(Random.nextInt(0, 10), Random.nextInt(0, 10))
    }
}
