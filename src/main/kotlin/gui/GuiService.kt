package gui

import model.BattlePlan
import model.FieldType
import model.PlacementResult
import model.Player
import model.ShotResult
import kotlin.random.Random


class GuiService {
    fun printBanner() {
        println("Welcome to glorious")
        println("                 __/___\n" +
                "           _____/______|\n" +
                "   _______/_____\\_______\\_____\n" +
                "   \\              < < <       |\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        println("__________         __    __  .__                .__    .__              \n" +
                "\\______   \\_____ _/  |__/  |_|  |   ____   _____|  |__ |__|_____  ______\n" +
                " |    |  _/\\__  \\\\   __\\   __\\  | _/ __ \\ /  ___/  |  \\|  \\____ \\/  ___/\n" +
                " |    |   \\ / __ \\|  |  |  | |  |_\\  ___/ \\___ \\|   Y  \\  |  |_> >___ \\ \n" +
                " |______  /(____  /__|  |__| |____/\\___  >____  >___|  /__|   __/____  >\n" +
                "        \\/      \\/                     \\/     \\/     \\/   |__|       \\/ ")
    }

    fun printWhoPlays(player: Player) {
        this.printDivider()
        if (player.isNPC) {
            println("${player.name} is targeting, please wait…")
        } else {
            println("It's ${player.name}`s turn now. Input coordinates where to shoot in format \"A,3\": ")
        }
    }

    fun printError() {
        println("This action is invalid, try again.")
    }

    fun printShot(player: Player, result: ShotResult) {
        val shotResultMessage = getShotResultMessage(result)
        if (player.isNPC) {
            println("<–––––––––––––––")
        } else {
            println("––––––––––––––>")
        }
        Thread.sleep(2000)
        println(shotResultMessage)
    }

    fun clearConsole() {
        // This seems to not work in IDE terminal
        try {
            val os = System.getProperty("os.name")
            if (os.contains("Windows")) {
                val cls = arrayOf("cmd.exe", "/c", "cls")
                Runtime.getRuntime().exec(cls)
            } else {
                Runtime.getRuntime().exec("clear")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        System.out.flush()
    }

    private fun getShotResultMessage(result: ShotResult): Any {
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

            ShotResult.OUT_OF_BOUND -> "Coordinates are out of map, Commander! Select new coordinates."
            else -> "Coordinates are in invalid format, stick to the \"A,3\" format."
        }
    }

    fun printDivider() {
        println("~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~")
    }

    fun printRandomAttackMessage() {
        val texts = arrayOf(
            "Missile is flying…",
            "Torpedo fired!",
            "Attack!",
            "Shot fired!",
            "Huuuiiiiii…"
        )
        val randomIndex = Random.nextInt(texts.size)
        println(texts[randomIndex])
    }

    fun printWinner() {
        println("                                   .''.       \n" +
                "       .''.      .        *''*    :_\\/_:     . \n" +
                "      :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'.\n" +
                "  .''.: /\\ :   ./)\\   ':'* /\\ * :  '..'.  -=:o:=-\n" +
                " :_\\/_:'.:::.    ' *''*    * '.\\'/.' _\\(/_'.':'.'\n" +
                " : /\\ : :::::     *_\\/_*     -= o =-  /)\\    '  *\n" +
                "  '..'  ':::'     * /\\ *     .'/.\\'.   '\n" +
                "      *            *..*         :\n" +
                "        *\n" +
                "        *")
        println("██╗    ██╗██╗███╗   ██╗███╗   ██╗███████╗██████╗ \n" +
                "██║    ██║██║████╗  ██║████╗  ██║██╔════╝██╔══██╗\n" +
                "██║ █╗ ██║██║██╔██╗ ██║██╔██╗ ██║█████╗  ██████╔╝\n" +
                "██║███╗██║██║██║╚██╗██║██║╚██╗██║██╔══╝  ██╔══██╗\n" +
                "╚███╔███╔╝██║██║ ╚████║██║ ╚████║███████╗██║  ██║\n" +
                " ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝\n" +
                "                                                 ")
    }

    fun printBattleplans(ownBattleplan: BattlePlan, enemyBattleplan: BattlePlan) {
        val rowHeaders = ('A'..'Z').take(ownBattleplan.fields.firstOrNull()?.size ?: 0)
        val columnHeaders = (1..ownBattleplan.fields.size)


        println("Your sea:                   Enemy sea:")
        println("   ${columnHeaders.joinToString(" ")}   ${columnHeaders.joinToString(" ")}")

        ownBattleplan.fields.forEachIndexed { rowIndex, row ->
            val rowString1 = row.joinToString(" ") { field ->
                when (field.fieldType) {
                    FieldType.WATER -> " "
                    FieldType.MISS -> "o"
                    FieldType.HIT -> "X"
                    FieldType.SUNK -> "#"
                    FieldType.SHIP -> "H"
                }
            }
            val rowString2 = enemyBattleplan.fields[rowIndex].joinToString(" ") { field ->
                when (field.fieldType) {
                    FieldType.WATER -> " "
                    FieldType.MISS -> "o"
                    FieldType.HIT -> "X"
                    FieldType.SUNK -> "#"
                    FieldType.SHIP -> "H"
                }
            }
            println("${rowHeaders[rowIndex]} $rowString1         ${rowHeaders[rowIndex]} $rowString2")
        }
    }

    fun printShipAutoplacementOption() {
        println("Do you want to use auto placement feature? Type \"1\" for auto placement, \"2\" for manual placement")
    }

    fun printQuestionForShipPlacement(shipLength: Int) {
        println("Please insert the ship placement for ship length $shipLength as follows [ROW,COLUMN,DIRECTION].\n" +
                "Example: \"A,10,H\". Possible directions are: Horizontal (H) Vertical (V)")
    }

    fun onShipPlacementError(placement: PlacementResult) {
        when (placement) {
            PlacementResult.OUT_OF_BOUND -> println("Ship placement is out of map. Please change coordinates.")
            PlacementResult.PLACEMENT_CONFLICT -> println("Ship placement conflicts with already placed ship. Please change coordinates")
            else -> {}
        }
    }

    fun onInvalidShipPlacementInput() {
        println("Invalid ship placement coordinates. Please stick to the [ROW,COLUMN,DIRECTION].\n" +
                "\"Example: \"A,10,H\". Possible directions are: Horizontal (H) Vertical (V)")
    }

    fun printShipPlaced() {
        println("Ship placed!")
    }
}
