package kotlin.gameplay

import model.Player

interface GameplayService {
    fun startGame()
    fun endGame()
    fun whoIsOnTurn(): Player
}