package gamesettings

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import model.Direction
import model.Point
import model.ShipPlacement

class GameSettingsServiceImpl : GameSettingsService {
    override fun resolveAutoShipPlacementFunction(inputStream: InputStream): Int {
        val input  = BufferedReader(InputStreamReader(inputStream)).readLine()

        if (input.isEmpty()) return -1

        return when (val number = if (input.all { char -> char.isDigit() }) Integer.parseInt(input) else -1) {
            1, 2 -> number
            else -> -1
        }
    }

    override fun getShipPlacement(inputStream: InputStream): ShipPlacement? {
        val input  = BufferedReader(InputStreamReader(inputStream)).readLine()

        if (input.isEmpty()) return null
        val splitted = input.split(",")

        if (splitted.size != 3) return null

        val coordinates = createPointFromInput(splitted.component1(), splitted.component2())

        coordinates?: return null

        val dirChar = splitted.component3()
        if (dirChar != "V" && dirChar != "H") return null

        val direction = when (dirChar) {
            "V" -> Direction.VERTICAL
            else -> Direction.HORIZONTAL
        }

        return ShipPlacement(coordinates, direction)
    }

    private fun createPointFromInput(rowChar: String, columnChar: String): Point? {
        val alphabet = "abcdefghijklmnopqrstuvwxyz";

        val rowNum = alphabet.indexOf(rowChar.lowercase())

        if (rowNum == -1) return null

        val columnNum = Integer.parseInt(columnChar) - 1

        if (columnNum < 0) return null

        return Point(rowNum, columnNum)
    }
}