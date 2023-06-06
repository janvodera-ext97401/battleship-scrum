package gameplay

import battleplan.BattlePlanService
import model.Player

class GameplayServiceImpl(private val players: List<Player>,
                          private val battlePlanService: BattlePlanService) : GameplayService {
    private lateinit var playerOnTurn: Player
    override fun startGame() {
        playerOnTurn = players.first()
        battlePlanService.createBoard(10);
    }

    override fun endGame() {
        TODO("Not yet implemented")
    }

    override fun playerTurn(): Player {

        battlePlanService.shot(playerOnTurn.name, )
    }



    override fun whoIsOnTurn(): Player {
        return playerOnTurn
    }
}