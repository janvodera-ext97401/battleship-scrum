package gamesettings

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameSettingsServiceImplTest {
    private lateinit var gameSettingsService: GameSettingsService

    @BeforeEach
    fun setup() {
        gameSettingsService = GameSettingsServiceImpl()
    }

    @Test
    fun `resolveAutoShipPlacementFunction return 1`() {
        val inputStream = "1".byteInputStream()
        assertEquals(1, gameSettingsService.resolveAutoShipPlacementFunction(inputStream))
    }

    @Test
    fun `resolveAutoShipPlacementFunction returns -1 if input is 3`() {
        val inputStream = "3".byteInputStream()
        assertEquals(-1, gameSettingsService.resolveAutoShipPlacementFunction(inputStream))
    }

    @Test
    fun `resolveAutoShipPlacementFunction returns -1 if input is not a number`() {
        val inputStream = "3a".byteInputStream()
        assertEquals(-1, gameSettingsService.resolveAutoShipPlacementFunction(inputStream))
    }
}