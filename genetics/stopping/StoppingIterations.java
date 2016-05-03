package genetics.stopping;

import genetics.population.*;

public class StoppingIterations extends Stopping {
	int turns;
	int turn;

	public StoppingIterations(int turns) {
		if (turns < 1) {
			turns = 1;
		}
		this.turns = turns;
		this.turn = 0;
	}
	
	public boolean isEnding(int n, Creature[] cr) {
		this.turn++;
		if (this.turn >= this.turns) {
			return true;
		}
		return false;
	}

	public void setTurns(int x) {
		if (x < 1) {
			x = 1;
		}
		this.turns = x;
		this.turn = 0;
	}

	public int getTurns() {
		return this.turns;
	}
}