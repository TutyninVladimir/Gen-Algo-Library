package graphcreature;

import population.Creature;

public abstract class GraphCreature extends Creature
{
	protected int n;
	protected int a[];
	protected GraphCrossFunction c;
	protected GraphMutationFunction m;
	abstract double fit();
	
	public double fitness()
	{
		return this.fit();
	}
	public void copyData(Creature a)
	{
		((GraphCreature)a).n=this.n;
		for (int i=0;i<n;i++)
			((GraphCreature)a).a[i]=this.a[i];
	}
	public void regenerate()
	{
		int i;
		int[] tmp = new int[n];
		for(i=0;i<n;i++)
		{
			tmp[i]=0;
		}
		for(i=0;i<n;i++)
		{
			if (tmp[a[i]]==0) tmp[a[i]]=1;
			else a[i]=-1;
		}
		int p2=0;
		for(i=0;i<n;i++)
		{
			if(a[i]==-1)
			{
				while(tmp[p2]!=0)
					p2++;
				tmp[p2]=1;
				a[i]=p2;
			}
		}
	}
	public void generate()
	{
		a = new int[n];
		int i;
		long a=1+((int)Math.random()*25);
		long b=1+((int)Math.random()*1000);
		long c=1+((int)Math.random()*1000);
		for(i=0;i<n;i++)
		{
			this.a[i]=(int) (((a*i*i+b*i+c)%n));
		}
		regenerate();
	}
	public void cross(Creature cr)
	{
		c.cross(this, (GraphCreature) cr);
	}
	public void mutation()
	{
		m.mutate(this);
	}
	public void set(int a[])
	{
		for(int i=0;i<n;i++)
			this.a[i]=a[i];
	}
	public int[] get()
	{
		int i;
		int[] tmp = new int[n];
		for(i=0;i<n;i++)
			tmp[i]=this.a[i];
		return tmp;
	}
	public int getlength()
	{
		return this.n;
	}
	public GraphCreature(int n, GraphCrossFunction c, GraphMutationFunction m)
	{
		if (n>100000)
			n=100000;
		if (n<2)
			n=2;
		this.n=n;
		a = new int[n];
		for(int i=0;i<n;i++)
			a[i]=i;
		this.c = c;
		this.m = m;
	}
}
