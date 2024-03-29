package ufrpe.behavior_tree_nodes.conditions;

import easy_soccer_lib.utils.EFieldSide;
import easy_soccer_lib.utils.Vector2D;
import ufrpe.BehaviorTreePlayer;
import ufrpe.behavior_tree.BTNode;
import ufrpe.behavior_tree.BTStatus;

import java.awt.*;

/**
 * Considerei o campo subdividido em 3 porções. A parte mais proxima do goleiro é
 * Campo de Defesa (FieldDefense) a porção do meio campo é o Campo do Meio (FieldMiddle)
 * e a porção que o time faz o gol é o Campo de Ataque (FieldAttack)
 *
 * Esta classe retorna sucesso caso o jogador atua esteja no FieldMiddle
 */
//TODO falta testar
public class IfPlayerIsInFieldMiddle extends BTNode<BehaviorTreePlayer> {
    @Override
    public BTStatus tick(BehaviorTreePlayer agent) {
        EFieldSide side = agent.getSelfPerc().getSide();
        Rectangle fieldAttack = new Rectangle(-18,-34, 34, 68);
        Vector2D currentPos = agent.getSelfPerc().getPosition();
        if(fieldAttack.contains(currentPos.getX(), currentPos.getY())) {
            return BTStatus.SUCCESS;
        }
        return BTStatus.FAILURE;
    }
}
