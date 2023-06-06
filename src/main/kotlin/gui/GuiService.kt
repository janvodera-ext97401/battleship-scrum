package gui

import model.Player
import model.ShotResult

class GuiService {
    fun printBanner() {
        println("                 __/___\n" +
                "           _____/______|\n" +
                "   _______/_____\\_______\\_____\n" +
                "   \\              < < <       |\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    }

    fun printWhoPlays(player: Player) {
        println("It's ${player.name}`s turn now.")
    }

    fun printError() {
        println("This action is invalid, try again.")
    }

    fun printAIIsTargeting() {
        println("AI is targeting, please wait...")
    }

    fun printShot(result: ShotResult) {
        val shotResultMessage = getShotResultMessage(result)
        println(shotResultMessage)
    }

    fun clearConsole() {
        println("\\033[H\\033[2J")
        System.out.flush()
    }

    private fun getShotResultMessage(result: ShotResult): String {
        return when (result) {
            ShotResult.HIT -> "          )      )     *     \n" +
                    "   (   ( /(   ( /(   (  `    \n" +
                    " ( )\\  )\\())  )\\())  )\\))(   \n" +
                    " )((_)((_)\\  ((_)\\  ((_)()\\  \n" +
                    "((_)_   ((_)   ((_) (_()((_) \n" +
                    " | _ ) / _ \\  / _ \\ |  \\/  | \n" +
                    " | _ \\| (_) || (_) || |\\/| | \n" +
                    " |___/ \\___/  \\___/ |_|  |_| \n" +
                    "                             "
            ShotResult.SUNK -> " (        )  (    (      (              )      )      (      \n" +
                    " )\\ )  ( /(  )\\ ) )\\ )   )\\ )        ( /(   ( /(      )\\ )   \n" +
                    "(()/(  )\\())(()/((()/(  (()/(    (   )\\())  )\\()) (  (()/(   \n" +
                    " /(_))((_)\\  /(_))/(_))  /(_))   )\\ ((_)\\ |((_)\\  )\\  /(_))  \n" +
                    "(_))   _((_)(_)) (_))   (_))  _ ((_) _((_)|_ ((_)((_)(_))_   \n" +
                    "/ __| | || ||_ _|| _ \\  / __|| | | || \\| || |/ / | __||   \\  \n" +
                    "\\__ \\ | __ | | | |  _/  \\__ \\| |_| || .` |  ' <  | _| | |) | \n" +
                    "|___/ |_||_||___||_|    |___/ \\___/ |_|\\_| _|\\_\\ |___||___/  "
            ShotResult.MISS -> "  __  __ ___ ___ ___ \n" +
                    " |  \\/  |_ _/ __/ __|\n" +
                    " | |\\/| || |\\__ \\__ \\\n" +
                    " |_|  |_|___|___/___/\n" +
                    "                     "
        }
    }

}
