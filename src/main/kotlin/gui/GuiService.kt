package gui

import model.Player
import model.ShotResult
import kotlin.random.Random

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

    fun printShot(player: Player, result: ShotResult) {
        val shotResultMessage = getShotResultMessage(result)
        println(this.getRandomAttackWeaponText())
        if (player.isNPC) {
            println("<–––––––––––––––")
        } else {
            println("––––––––––––––>")
        }
        Thread.sleep(2000)
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
            ShotResult.SUNK -> "          _ ._  _ , _ ._\n" +
                    "        (_ ' ( `  )_  .__)\n" +
                    "      ( (  (    )   `)  ) _)\n" +
                    "     (__ (_   (_ . _) _) ,__)\n" +
                    "         `~~`\\ ' . /`~~`\n" +
                    "              ;   ;\n" +
                    "              /   \\\n" +
                    "_____________/_ __ \\_____________\n\n" +
                " (        )  (    (      (              )      )      (      \n" +
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

    private fun getRandomAttackWeaponText(): String {
        val texts = arrayOf("Missile is flying…", "Torpedo fired!", "Attack!")
        val randomIndex = Random.nextInt(texts.size)
        return texts[randomIndex]
    }
}
