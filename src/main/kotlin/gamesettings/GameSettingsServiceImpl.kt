package gamesettings

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class GameSettingsImpl : GameSettingsService {
    override fun resolveAutoShipPlacementFunction(inputStream: InputStream): Int {
        val input  = BufferedReader(InputStreamReader(inputStream)).readLine()
        println(input)
        println(input.isEmpty())
        println(input.all { char -> char.isDigit() })
        println("-----")

        if (input.isEmpty()) return -1

        return when (val number = if (input.all { char -> char.isDigit() }) Integer.parseInt(input) else -1) {
            1, 2 -> {println("nubmer is $number")
                number}
            else -> {println("nubmer is $number")
                -1}
        }
    }
}