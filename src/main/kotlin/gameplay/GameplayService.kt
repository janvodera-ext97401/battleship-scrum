package gameplay

import model.Player

interface GameplayService {
    fun startGame()
    fun endGame()
    fun playerTurn(): Player
    fun whoIsOnTurn(): Player
}