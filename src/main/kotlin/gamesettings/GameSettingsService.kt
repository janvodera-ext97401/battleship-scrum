package gamesettings

import java.io.InputStream
import model.ShipPlacement

interface GameSettingsService {
    fun resolveAutoShipPlacementFunction(inputStream: InputStream): Int

    fun getShipPlacement(inputStream: InputStream): ShipPlacement?
}