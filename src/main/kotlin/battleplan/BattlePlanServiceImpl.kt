package battleplan

import model.*
import model.ShotResult

class BattlePlanServiceImpl : BattlePlanService {
    private var boardSize: Int = 0
    private val ships = mutableListOf<Ship>()
    private val shots = mutableListOf<Point>()

    override fun createBoard(size: Int): Boolean {
        boardSize = size
        resetGameState()
        return true
    }

    private fun resetGameState() {
        ships.clear()
        shots.clear()
    }

    override fun addShip(ship: Ship): PlacementResult {
        val result = validateShipPlacement(ship)
        if (result == PlacementResult.OK){
            ships.add(ship)
            return PlacementResult.OK
        }
        return result
    }

    private fun validateShipPlacement(ship: Ship): PlacementResult{
        val shipPoints = generateShipPoints(ship)
        if (ensurePointsAreWithinBounds(shipPoints)){
            return PlacementResult.OUT_OF_BOUND
        }
        if (ensureNoOverlapWithCurrentPlayerShips(ship, shipPoints)){
            return PlacementResult.PLACEMENT_CONFLICT
        }
        return PlacementResult.OK
    }

    private fun generateShipPoints(ship: Ship): List<Point> {
        return (0 until ship.length).map { i ->
            when (ship.direction) {
                Direction.HORIZONTAL -> Point(ship.position.row, ship.position.column + i)
                Direction.VERTICAL -> Point(ship.position.row + i, ship.position.column)
            }
        }
    }

    private fun ensurePointsAreWithinBounds(points: List<Point>): Boolean {
        points.forEach { point ->
            if (point.row < 0 || point.row >= boardSize || point.column < 0 || point.column >= boardSize) {
                return true
            }
        }
        return false
    }

    private fun ensureNoOverlapWithCurrentPlayerShips(ship: Ship, shipPoints: List<Point>): Boolean {
        return ships.any { it.ownerName == ship.ownerName && shipPoints.contains(it.position) }
    }

    override fun shot(playerName: String, point: Point): ShotResult{
        if (ensurePointIsWithinBounds(point)){
            return ShotResult.OUT_OF_BOUND
        }
        val result = recordShotResult(playerName, point)
        shots.add(point)
        return result
    }

    private fun ensurePointIsWithinBounds(point: Point): Boolean{
        return (point.row < 0 || point.row >= boardSize || point.column < 0 || point.column >= boardSize)
    }

    private fun recordShotResult(playerName: String, point: Point): ShotResult{
        val ship = findShipAtPoint(point)

        if (ship == null || ship.ownerName == playerName) {
            return ShotResult.MISS
        }

        ship.hit()
        if (ship.isSunk()) {
            return ShotResult.SUNK
        } else {
            return ShotResult.HIT
        }
    }

    private fun findShipAtPoint(point: Point): Ship? {
        return ships.find { ship ->
            (0 until ship.length).any { i ->
                when (ship.direction) {
                    Direction.HORIZONTAL -> ship.position == Point(point.row, point.column - i)
                    Direction.VERTICAL -> ship.position == Point(point.row - i, point.column)
                }
            }
        }
    }
}