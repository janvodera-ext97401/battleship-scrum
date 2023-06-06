package battleplan

import model.Point
import model.Ship
import model.Shot

interface BattlePlanService {
    fun createBoard(size: Int): Boolean
    fun addShip(ship: Ship)
    fun shot(playerName: String, point: Point)
}