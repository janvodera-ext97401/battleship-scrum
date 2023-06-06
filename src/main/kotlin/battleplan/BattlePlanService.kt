package battleplan

import model.*

interface BattlePlanService {
    fun createBoard(size: Int): Boolean
    fun addShip(ship: Ship): PlacementResult
    fun shot(playerName: String, point: Point): ShotResult
}