package ufrpe.behavior_tree_nodes.game_states_movements;

import easy_soccer_lib.utils.EFieldSide;
import easy_soccer_lib.utils.Vector2D;
import ufrpe.BehaviorTreePlayer;
import ufrpe.behavior_tree.BTNode;
import ufrpe.behavior_tree.BTStatus;

//Move o time para a posição de kick off
// se o time do agente atual tomo gol, entao os atacantes darao o kick off
// senao, o time fica na home position
public class MovePlayerToKickOff extends BTNode<BehaviorTreePlayer> {
    @Override
    public BTStatus tick(BehaviorTreePlayer agent) {
        EFieldSide side = agent.getSelfPerc().getSide();
        Vector2D homePosition = agent.getSelfPerc().getPosition();

        if(side == EFieldSide.RIGHT) { //tomou um gol
            posicionarTimeParaKickOff(agent);
        }
        else if(side == EFieldSide.LEFT) { //fez um gol
            posicionarJogadorEmPonto(agent, homePosition);
        }

        return BTStatus.SUCCESS;
    }

    // Se o time do agente atual tomou um gol, então preparamos ele para o kick off
    private void posicionarTimeParaKickOff(BehaviorTreePlayer agent) {
        // atacantes 8 e 7
        int numeroCamisa = agent.getSelfPerc().getUniformNumber();
        if(numeroCamisa == 7) {
            posicionarJogadorEmPonto(agent, new Vector2D(-4, 4));
        } 
        else if(numeroCamisa == 8) {
            posicionarJogadorEmPonto(agent, new Vector2D(-2, -1));
        }
        else {
            //posicionando jogador atual na sua home position
            posicionarJogadorEmPonto(agent, agent.getHomePosition());
        }
    }

    // posiciona o jogador atual numa posição indicada no parametro
    //esta funcao já trata o lado que o jogador pertence no campo
    private void posicionarJogadorEmPonto(BehaviorTreePlayer agent, Vector2D position) {
        EFieldSide side = agent.getSelfPerc().getSide();

        if(side == EFieldSide.RIGHT) {
            position.setX(-position.getX());
            position.setY(-position.getY());
        }

        Vector2D currentPos = agent.getSelfPerc().getPosition();
        if(!(currentPos.getX() == position.getX() && currentPos.getY() == position.getY())) {
            agent.getCommander().doMoveBlocking(position.getX(), position.getY());
        }
    }
}
