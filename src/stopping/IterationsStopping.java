package stopping;

import population.*;

public class IterationsStopping extends Stopping
{
	int turns;
	int turn;
	public boolean isEnding(int n, Creature[] cr)
	{
		this.turn++;
		if (this.turn>=this.turns)
			return true;
		else
			return false;
		
	}
	public void setTurns(int x)
	{
		if (x<1)
			x=1;
		this.turns=x;
		this.turn=0;
	}
	public int getTurns()
	{
		return this.turns;
	}
	public IterationsStopping(int turns)
	{
		if (turns<1)
			turns=1;
		this.turns=turns;
		this.turn=0;
	}
}
