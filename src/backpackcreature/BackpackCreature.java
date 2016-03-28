package backpackcreature;

import population.Creature;

public abstract class BackpackCreature extends Creature
{
	protected int num_items;
	protected int items[];
	protected int num_of_use;
	protected BackpackCrossFunction c;
	protected BackpackMutationFunction m;
	abstract double fit();
	
	public double fitness()
	{
		return this.fit();
	}
	public void copyData(Creature a)
	{
		((BackpackCreature)a).num_items=this.num_items;
		((BackpackCreature)a).num_of_use=this.num_of_use;
		for (int i=0;i<this.num_items;i++)
			((BackpackCreature)a).items[i]=this.items[i];
	}
	public void regenerate()
	{
		int i;
		for(i=0;i<num_items;i++)
		{
			items[i]=items[i]%(num_of_use+1);
		}
	}
	public void generate()
	{
		int i;
		items = new int[num_items];
		for(i=0;i<num_items;i++)
		{
			items[i]=(int)(Math.random()*2*num_of_use);
			items[i]=items[i]%(num_of_use+1);
		}
	}
	public void cross(Creature cr)
	{
		c.cross(this, (BackpackCreature) cr);
	}
	public void mutation()
	{
		m.mutate(this);
	}
	public void setitems(int item[])
	{
		int i;
		for(i=0;i<num_items;i++)
		{
			this.items[i]=item[i];
		}
		
	}
	public int[] getitems()
	{
		int i;
		int[] tmp = new int[num_items];
		for(i=0;i<num_items;i++)
		{
			tmp[i]=items[i];
		}
		return tmp;
	}
	public int getnum_items()
	{
		return this.num_items;
	}
	public int getnum_of_use()
	{
		return this.num_of_use;
	}
	public BackpackCreature(int n, int num_of_use, BackpackCrossFunction c, BackpackMutationFunction m)
	{
		if (n>100000)
			n=100000;
		if (n<2)
			n=2;
		this.num_items=n;
		int i;
		items = new int[num_items];
		for(i=0;i<num_items-1;i++)
		{
			items[i]=0;
		}
		if (num_of_use<1)
			num_of_use=1;
		if (num_of_use>100000)
			num_of_use=100000;
		this.num_of_use=num_of_use;
		this.c = c;
		this.m = m;
	}
}