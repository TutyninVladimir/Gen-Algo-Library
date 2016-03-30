package genetics.stopping;

import genetics.population.Creature;
import genetics.population.Stopping;

public class StoppingMaxNotChanged extends Stopping
{
	int turns;
	int turn;
	double lastMax;
	public boolean isEnding(int n, Creature[] cr)
	{
		this.turn++;
		double thisMax=cr[0].fitness();
		double tmp;
		for(int i=1;i<n;i++)
		{
			tmp=cr[i].fitness();
			if (tmp>thisMax)
				thisMax=tmp;
		}
		if (thisMax!=lastMax)
		{
			lastMax=thisMax;
			this.turn=0;
		}
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
	public StoppingMaxNotChanged(int turns)
	{
		if (turns<1)
			turns=1;
		this.turns=turns;
		this.turn=0;
		this.lastMax=0;
	}
}
