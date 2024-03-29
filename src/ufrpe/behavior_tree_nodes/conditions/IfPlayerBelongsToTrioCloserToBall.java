package ufrpe.behavior_tree_nodes.conditions;


import easy_soccer_lib.perception.PlayerPerception;
import easy_soccer_lib.utils.EFieldSide;
import easy_soccer_lib.utils.Vector2D;
import ufrpe.BehaviorTreePlayer;
import ufrpe.behavior_tree.BTNode;
import ufrpe.behavior_tree.BTStatus;

import java.util.List;

/**
 * Uma das estratégias de defesa/ataque é realizar jogadas em trio com jogadores
 * do mesmo time.
 *
 * Esta classe checa se o jogador atual pertence a o trio de jogadores mais próximo
 * da bola no momento.
 */
//TODO falta testar
public class IfPlayerBelongsToTrioCloserToBall extends BTNode<BehaviorTreePlayer> {
    @Override
    public BTStatus tick(BehaviorTreePlayer agent) {
        Vector2D ballPos = agent.getFieldPerc().getBall().getPosition();
        EFieldSide side = agent.getSelfPerc().getSide();
        List<PlayerPerception> myTeam = agent.getFieldPerc().getTeamPlayers(side);

        //Excluindo o goleiro
        for (int i = 0; i < myTeam.size(); i++) {
            if(myTeam.get(i).getUniformNumber() == 1) {
                myTeam.remove(i);
            }
        }

        //Ordenando a lista pela distancia com a bola
        for (int i = 0; i < myTeam.size(); i++) {
            for (int j = i+1; j < myTeam.size(); j++) {
                double distA = myTeam.get(i).getPosition().distanceTo(ballPos);
                double distB = myTeam.get(j).getPosition().distanceTo(ballPos);
                if(distB < distA) {
                    PlayerPerception aux = myTeam.get(i);
                    myTeam.set(i, myTeam.get(j));
                    myTeam.set(j, aux);
                }
            }
        }

        if(myTeam.get(0).getUniformNumber() == agent.getSelfPerc().getUniformNumber())
            return BTStatus.SUCCESS;
        else if(myTeam.get(1).getUniformNumber() == agent.getSelfPerc().getUniformNumber())
            return BTStatus.SUCCESS;
        else if(myTeam.get(2).getUniformNumber() == agent.getSelfPerc().getUniformNumber())
            return BTStatus.SUCCESS;
        return BTStatus.FAILURE;
    }
}
