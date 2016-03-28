package Genetics;

abstract class BackpackCreature extends Creature
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

abstract class BackpackCrossFunction
{
	abstract void cross(BackpackCreature a, BackpackCreature b);
}

abstract class BackpackMutationFunction
{
	abstract void mutate(BackpackCreature a);
}

class SimpleBackpackCross extends BackpackCrossFunction
{
	void cross(BackpackCreature a, BackpackCreature b)
	{
		int n=a.getnum_items();
		int olda[], newa[], oldb[], newb[];
		olda=a.getitems();
		oldb=b.getitems();
		newa=new int[n];
		newb=new int[n];
		int i;
		int q=(int)(Math.random()*n);
		for(i=0;i<n;i++)
		{
			if (i<=q)
			{
				newa[i]=oldb[i];
				newb[i]=olda[i];
			}
			else
			{
				newa[i]=olda[i];
				newb[i]=oldb[i];
			}
		}
		a.regenerate();
		b.regenerate();
		a.setitems(newa);
		b.setitems(newb);
	}
}

class OneItemMutation extends BackpackMutationFunction
{
	void mutate(BackpackCreature a)
	{
		int n=a.getnum_items();
		int u=a.getnum_of_use();
		int q=(int)(Math.random()*n);
		int[] olda=a.getitems();
		if (u!=1)
		{
			olda[q]=(int)(Math.random()*10*u);
			olda[q]=olda[q]%(u+1);
		}
		else
		{
			olda[q]=1-olda[q];
		}
		a.regenerate();
		a.setitems(olda);
	}
}

class ExampleBackpackCreature extends BackpackCreature
{
	public ExampleBackpackCreature(int n, int num_of_use, BackpackCrossFunction c, BackpackMutationFunction m) 
	{
		super(n, num_of_use, c, m);
	}
	double fit()
	{
		//TO DO: write function.
		return 0;
	}
}

class SimpleBackpackCreature extends BackpackCreature
{
	int max_weight;
	int costs[];
	int weights[];
	void init(int max_weight, int cost[], int weight[])
	{
		int i;
		costs = new int[num_items];
		weights = new int[num_items];
		if (max_weight<0)
			max_weight=0;
		this.max_weight=max_weight;
		for(i=0;i<num_items;i++)
		{
			costs[i]=cost[i];
			weights[i]=weight[i];
		}
	}
	public SimpleBackpackCreature(int n, int num_of_use, BackpackCrossFunction c, BackpackMutationFunction m) 
	{
		super(n, num_of_use, c, m);
	}
	double fit()
	{
		int i, weight=0, t_items[];
		t_items = new int[num_items];
		for(i=0;i<num_items;i++)
		{
			t_items[i]=items[i];
			weight+=weights[i]*t_items[i];
		}
		int need_drop=weight-max_weight;
		i=0;
		while(need_drop>0&&i<num_items)
		{
			if (t_items[i]>0)
			{
				t_items[i]--;
				need_drop-=weights[i];
			}
			if (t_items[i]==0)
				i++;
		}
		int sum=0;
		weight=0;
		for(i=0;i<num_items;i++)
		{
			weight+=weights[i]*t_items[i];
			sum+=costs[i]*t_items[i];
		}
		if (weight<=max_weight) return sum;
		return 0;
	}
	int[] getanswer()
	{
		int i, weight=0, t_items[];
		t_items = new int[num_items];
		for(i=0;i<num_items;i++)
		{
			t_items[i]=items[i];
			weight+=weights[i]*t_items[i];
		}
		int need_drop=weight-max_weight;
		i=0;
		while(need_drop>0&&i<num_items)
		{
			if (t_items[i]>0)
			{
				t_items[i]--;
				need_drop-=weights[i];
			}
			if (t_items[i]==0)
				i++;
		}
		return t_items;
	}
}