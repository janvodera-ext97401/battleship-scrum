package gameplay

import com.sun.org.apache.xpath.internal.operations.Bool
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