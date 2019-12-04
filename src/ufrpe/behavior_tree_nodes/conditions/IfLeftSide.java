package ufrpe.behavior_tree_nodes.conditions;

import easy_soccer_lib.utils.EFieldSide;
import ufrpe.BehaviorTreePlayer;
import ufrpe.behavior_tree.BTNode;
import ufrpe.behavior_tree.BTStatus;

public class IfLeftSide extends BTNode<BehaviorTreePlayer> {
    @Override
    public BTStatus tick(BehaviorTreePlayer agent) {
        return agent.getSelfPerc().getSide() == EFieldSide.LEFT ?
                BTStatus.SUCCESS :
                BTStatus.FAILURE;
    }
}
