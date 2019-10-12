package ufrpe;

import java.net.UnknownHostException;


public class Main {

	public static void main(String[] args) throws UnknownHostException {
		BehaviorTreeTeam team1 = new BehaviorTreeTeam("A");
		BehaviorTreeTeam team2 = new BehaviorTreeTeam("B");
		
		team1.launchTeamAndServer();
		team2.launchTeam();
	}
	
}

