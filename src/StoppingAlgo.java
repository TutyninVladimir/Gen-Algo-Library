package Genetics;

class IterationsStopping extends Stopping
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

class MaxNotChangedStopping extends Stopping
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
	public MaxNotChangedStopping(int turns)
	{
		if (turns<1)
			turns=1;
		this.turns=turns;
		this.turn=0;
		this.lastMax=0;
	}
}

class StoppingManager
{
	int n=2;
	Stopping a[];
	void init()
	{
		a = new Stopping[n+1];
		a[1] = new IterationsStopping(100);
		a[2] = new MaxNotChangedStopping(5);
	}
}