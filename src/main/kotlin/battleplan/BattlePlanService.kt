package battleplan

import model.*

interface BattlePlanService {
    fun createBoard(size: Int): Boolean
    fun addShip(ship: Ship): PlacementResult
    fun shot(player: Player, point: Point): ShotResult
    fun getShipsToPlace(): List<Ship>
    fun autoPlaceShips(player: Player): Boolean
    fun getBattlePlan(player: Player): BattlePlan

}