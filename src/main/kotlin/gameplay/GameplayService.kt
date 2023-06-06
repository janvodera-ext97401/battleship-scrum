package gameplay

import model.Player
import model.ShotResult

interface GameplayService {
    fun startGame()
    fun endGame()
    fun playerTurn(): ShotResult
    fun whoIsOnTurn(): Player
    fun isThereWinner(): Boolean
    fun setWinner(player: Player)
}