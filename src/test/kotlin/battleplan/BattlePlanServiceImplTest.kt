package battleplan

import model.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BattlePlanServiceImplTest {

    private lateinit var battlePlanService: BattlePlanService

    @BeforeEach
    fun setup() {
        battlePlanService = BattlePlanServiceImpl()
    }

    @Test
    fun `createBoard should initialize the board with the given size`() {
        // Given
        val size = 10

        // When
        val result = battlePlanService.createBoard(size)

        // Then
        assertEquals(true, result)
    }

    @Test
    fun `addShip should return OK when ship is added correctly`() {
        // Given
        battlePlanService.createBoard(10)
        val ship = Ship(Point(0, 0), 3, Direction.HORIZONTAL, "Player1")

        // When
        val result = battlePlanService.addShip(ship)

        // Then
        assertEquals(PlacementResult.OK, result)
    }

    @Test
    fun `addShip should return OUT_OF_BOUND when ship is out of board boundaries`() {
        // Given
        battlePlanService.createBoard(10)
        val ship = Ship(Point(0, 10), 3, Direction.HORIZONTAL, "Player1")

        // When
        val result = battlePlanService.addShip(ship)

        // Then
        assertEquals(PlacementResult.OUT_OF_BOUND, result)
    }

    @Test
    fun `addShip should return PLACEMENT_CONFLICT when ship is placed on another ship`() {
        // Given
        battlePlanService.createBoard(10)
        val ship1 = Ship(Point(0, 0), 3, Direction.HORIZONTAL, "Player1")
        val ship2 = Ship(Point(0, 0), 3, Direction.HORIZONTAL, "Player1")

        // When
        battlePlanService.addShip(ship1)
        val result = battlePlanService.addShip(ship2)

        // Then
        assertEquals(PlacementResult.PLACEMENT_CONFLICT, result)
    }

    @Test
    fun `shot should return HIT when a ship is hit`() {
        // Given
        battlePlanService.createBoard(10)
        val ship = Ship(Point(0, 0), 3, Direction.HORIZONTAL, "Player1")
        battlePlanService.addShip(ship)

        // When
        val result = battlePlanService.shot("Player2", Point(0, 0))

        // Then
        assertEquals(ShotResult.HIT, result)
    }

    @Test
    fun `shot should return MISS when no ship is hit`() {
        // Given
        battlePlanService.createBoard(10)
        val ship = Ship(Point(0, 0), 3, Direction.HORIZONTAL, "Player1")
        battlePlanService.addShip(ship)

        // When
        val result = battlePlanService.shot("Player1", Point(5, 5))

        // Then
        assertEquals(ShotResult.MISS, result)
    }

    @Test
    fun `shot should return OUT_OF_BOUND when shot is outside of board boundaries`() {
        // Given
        battlePlanService.createBoard(10)
        val ship = Ship(Point(0, 0), 3, Direction.HORIZONTAL, "Player1")
        battlePlanService.addShip(ship)

        // When
        val result = battlePlanService.shot("Player1", Point(10, 10))

        // Then
        assertEquals(ShotResult.OUT_OF_BOUND, result)
    }
}