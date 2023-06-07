package battleplan

import model.*
import model.ShotResult
import kotlin.random.Random

class BattlePlanServiceImpl : BattlePlanService {
    private var boardSize: Int = 0
    private val ships = mutableListOf<Ship>()
    private val shots = mutableListOf<Shot>()

    override fun createBoard(size: Int): Boolean {
        boardSize = size
        resetGameState()
        return true
    }

    override fun addShip(ship: Ship): PlacementResult {
        val result = validateShipPlacement(ship)
        if (result == PlacementResult.OK) {
            ships.add(ship)
            return PlacementResult.OK
        }
        return result
    }

    override fun shot(player: Player, point: Point): ShotResult {
        if (ensurePointIsWithinBounds(point)) {
            return ShotResult.OUT_OF_BOUND
        }
        val result = recordShotResult(player.name, point)
        shots.add(Shot(point, ShotResult.NONE, player.name))
        return result
    }

    override fun getShipsToPlace(): List<Ship> {
        return listOf(
            Ship(Point(0, 0), 4, Direction.HORIZONTAL, ""),
            Ship(Point(0, 0), 3, Direction.HORIZONTAL, ""),
            Ship(Point(0, 0), 2, Direction.HORIZONTAL, ""),
            Ship(Point(0, 0), 1, Direction.HORIZONTAL, "")
        )
    }

    override fun autoPlaceShips(player: Player): Boolean {
        val shipsToPlace = getShipsToPlace()
        for (ship in shipsToPlace) {
            var placementResult: PlacementResult
            do {
                val randomPoint = Point(Random.nextInt(boardSize), Random.nextInt(boardSize))
                val randomDirection = if (Random.nextBoolean()) Direction.HORIZONTAL else Direction.VERTICAL

                ship.position = randomPoint
                ship.direction = randomDirection
                ship.ownerName = player.name

                placementResult = validateShipPlacement(ship)

            } while (placementResult != PlacementResult.OK)

            ships.add(ship)
        }
        return true
    }

    override fun getBattlePlan(player: Player): BattlePlan {
        val fields = Array(boardSize) { Array(boardSize) { Field(FieldType.WATER) } }

        // Mark player's shots
        for (shot in shots.filter { it.ownerName == player.name }) {
            fields[shot.position.row][shot.position.column].fieldType = FieldType.MISS
        }

        // Mark hit parts of opponent's ships
        for (ship in ships.filter { it.ownerName != player.name }) {
            for (point in generateShipPoints(ship)) {
                if (wasShipHit(point)) {
                    // This part of the ship was hit
                    if (ship.isSunk()) {
                        fields[point.row][point.column].fieldType = FieldType.SUNK
                    } else {
                        fields[point.row][point.column].fieldType = FieldType.HIT
                    }
                }
            }
        }

        // Mark player's ships
        for (ship in ships.filter { it.ownerName == player.name }) {
            for (point in generateShipPoints(ship)) {
                fields[point.row][point.column].fieldType = FieldType.SHIP
            }
        }

        return BattlePlan(fields.map { it.toList() })
    }

    private fun wasShipHit(point: Point): Boolean {
        return shots.any { shot -> shot.position == point }
    }


    private fun resetGameState() {
        ships.clear()
        shots.clear()
    }

    private fun validateShipPlacement(ship: Ship): PlacementResult {
        val shipPoints = generateShipPoints(ship)
        if (ensurePointsAreWithinBounds(shipPoints)) {
            return PlacementResult.OUT_OF_BOUND
        }
        if (ensureNoOverlapWithCurrentPlayerShips(ship, shipPoints)) {
            return PlacementResult.PLACEMENT_CONFLICT
        }
        if (ensureNoAdjacentShips(ship, shipPoints)) {
            return PlacementResult.PLACEMENT_CONFLICT
        }
        return PlacementResult.OK
    }

    private fun ensureNoOverlapWithCurrentPlayerShips(ship: Ship, shipPoints: List<Point>): Boolean {
        return ships.any { existingShip ->
            existingShip.ownerName == ship.ownerName && generateShipPoints(existingShip).intersect(shipPoints.toSet())
                .isNotEmpty()
        }
    }

    private fun ensureNoAdjacentShips(ship: Ship, shipPoints: List<Point>): Boolean {
        val adjacentPoints = mutableListOf<Point>()
        shipPoints.forEach {
            adjacentPoints.addAll(generateAdjacentPoints(it))
        }
        return ships.any { existingShip ->
            existingShip.ownerName == ship.ownerName && generateShipPoints(existingShip).intersect(adjacentPoints.toSet())
                .isNotEmpty()
        }
    }

    private fun generateAdjacentPoints(point: Point): List<Point> {
        val row = point.row
        val column = point.column
        return listOf(
            Point(row - 1, column - 1), Point(row - 1, column), Point(row - 1, column + 1),
            Point(row, column - 1), Point(row, column + 1),
            Point(row + 1, column - 1), Point(row + 1, column), Point(row + 1, column + 1)
        ).filter { !ensurePointIsWithinBounds(it) }
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
        return points.any { point ->
            point.row < 0 || point.row >= boardSize || point.column < 0 || point.column >= boardSize
        }
    }

    private fun ensurePointIsWithinBounds(point: Point): Boolean {
        return point.row < 0 || point.row >= boardSize || point.column < 0 || point.column >= boardSize
    }

    private fun recordShotResult(playerName: String, point: Point): ShotResult {
        val opponentShip = ships.find { ship ->
            generateShipPoints(ship).contains(point) && ship.ownerName != playerName
        }
        if (opponentShip != null) {
            opponentShip.hit()
            return if (opponentShip.isSunk()) ShotResult.SUNK else ShotResult.HIT
        }
        return ShotResult.MISS
    }

}
