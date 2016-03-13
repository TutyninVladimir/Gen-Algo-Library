package Genetics;

abstract class GraphCreature extends Creature
{
	private int n;
	private int a[];
	private GraphCrossFunction c;
	private GraphMutationFunction m;
	abstract double fit(int[] x);
	
	public double fitness()
	{
		return this.fit(a);
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
			else a[i]=0;
		}
		int p1=0;
		int p2=0;
		while(p1<n)
		{
			if(a[p1]==0)
			{
				while(tmp[p2]!=0&&p2<n)
					p2++;
				tmp[p2]=1;
				a[p1]=p2;
			}
			p1++;
		}
	}
	public void generate()
	{
		int i;
		long a=1+((int)Math.random()*25);
		long b=1+((int)Math.random()*1000);
		long c=1+((int)Math.random()*1000);
		for(i=0;i<n;i++)
		{
			this.a[i]=(int) (((a*i*i+b*i+c)%n)+1);
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
		return this.a;
	}
	public int getlenght()
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
			a[i]=i+1;
		this.c = c;
		this.m = m;
	}
}

abstract class GraphCrossFunction
{
	abstract void cross(GraphCreature a, GraphCreature b);
}

abstract class GraphMutationFunction
{
	abstract void mutate(GraphCreature a);
}

class GraphCross extends GraphCrossFunction
{
	void cross(GraphCreature a, GraphCreature b)
	{
		int tmp,i;
		int q1=1+((int)Math.random()*(a.getlenght()-1));
		int q2=1+((int)Math.random()*(a.getlenght()-1));
		if (q1==q2) q2++;
		if (q1>q2)
		{
			tmp=q1;
			q1=q2;
			q2=tmp;
		}
		int olda[],oldb[];
		olda=a.get();
		oldb=b.get();
		for(i=0;i<q1;i++)
		{
			tmp=olda[i];
			olda[i]=oldb[i];
			oldb[i]=tmp;			
		}
		for(i=q2;i<a.getlenght();i++)
		{
			tmp=olda[i];
			olda[i]=oldb[i];
			oldb[i]=tmp;			
		}
		a.set(olda);
		b.set(oldb);
		a.regenerate();
		b.regenerate();
	}
}

class OneChangeMutation extends GraphMutationFunction
{
	void mutate(GraphCreature a)
	{
		int m=a.getlenght();
		int q=(int)(Math.random()*(m-1));
		int[] olda=a.get();
		int tmp=olda[q];
		olda[q]=olda[q+1];
		olda[q+1]=tmp;
		a.set(olda);
	}
}

class ExampleGraphCreature extends GraphCreature
{
	public ExampleGraphCreature(int n, GraphCrossFunction c, GraphMutationFunction m) 
	{
		super(n, c, m);
	}
	double fit(int x[])
	{
		//TO DO: write function.
		return x[1];
	}
}