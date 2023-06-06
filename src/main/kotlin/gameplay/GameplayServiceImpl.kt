package gameplay

import battleplan.BattlePlanService
import java.util.*
import model.Player
import model.Point
import model.ShotResult
import kotlin.random.Random

class GameplayServiceImpl(
    private val players: List<Player>,
    private val battlePlanService: BattlePlanService
) : GameplayService {
    private lateinit var playerOnTurn: Player
    private lateinit var winner: Player
    override fun startGame() {
        playerOnTurn = players.first()
        battlePlanService.createBoard(10);
    }

    override fun endGame() {
        TODO("Not yet implemented")
    }

    override fun playerTurn(): ShotResult {
        val shotCoordinates = when (playerOnTurn.isNPC) {
            true -> createRandomPoint()
            false -> createPointFromInput(readln())
        }

        return when (val result = battlePlanService.shot(playerOnTurn.name, shotCoordinates)) {
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

    override fun isThereWinner(): Boolean {
        return winner != null;
    }

    override fun setWinner(player: Player) {
        winner = player
    }

    private fun createPointFromInput(stringInput: String): Point {
        val alphabet = "abcdefghijklmnopqrstuvwxyz";

        val firstChar = stringInput.substring(0, 1).lowercase(Locale.getDefault());
        val firstIndex = alphabet.indexOf(firstChar);
        val secondChar = stringInput.substring(1, 2);
        val secondIndex = Integer.parseInt(secondChar) - 1;

        return Point(firstIndex, secondIndex)
    }

    private fun createRandomPoint(): Point {
        return Point(Random.nextInt(0, 10), Random.nextInt(0, 10))
    }
}