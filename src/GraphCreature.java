package Genetics;

abstract class GraphCreature extends Creature
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

abstract class GraphCrossFunction
{
	abstract void cross(GraphCreature a, GraphCreature b);
}

abstract class GraphMutationFunction
{
	abstract void mutate(GraphCreature a);
}
class SimpleGraphCross extends GraphCrossFunction
{
	void cross(GraphCreature a, GraphCreature b)
	{
		int tmp,i;
		int q1=1+((int)Math.random()*(a.getlength()-1));
		int q2=1+((int)Math.random()*(a.getlength()-1));
		if (q1==q2) q2++;
		if (q1>q2)
		{
			tmp=q1;
			q1=q2;
			q2=tmp;
		}
		int[] olda;
		int[] oldb;
		olda=a.get();
		oldb=b.get();
		for(i=0;i<q1;i++)
		{
			tmp=olda[i];
			olda[i]=oldb[i];
			oldb[i]=tmp;			
		}
		for(i=q2;i<a.getlength();i++)
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

class ExtendedGraphCross extends GraphCrossFunction
{
	void cross(GraphCreature a, GraphCreature b)
	{
		int tmp,i,n;
		n=a.getlength();
		int q1=1+((int)Math.random()*(a.getlength()-1));
		int q2=1+((int)Math.random()*(a.getlength()-1));
		if (q1==q2) q2++;
		if (q1>q2)
		{
			tmp=q1;
			q1=q2;
			q2=tmp;
		}
		int olda[], newa[], oldb[], newb[];
		olda=a.get();
		oldb=b.get();
		newa = new int[n];
		newb = new int[n];
		int p1=q2;
		for(i=0;i<q1;i++)
		{
			newa[p1]=oldb[i];
			newb[p1]=olda[i];
			p1++;
			if (p1>=n) p1-=n;
			if (p1==q1) p1=q2;
		}
		for(i=q1;i<q2;i++)
		{
			newa[i]=olda[i];
			newb[i]=oldb[i];
		}
		for(i=q2;i<n;i++)
		{
			newa[p1]=oldb[i];
			newb[p1]=olda[i];
			p1++;
			if (p1>=n) p1=0;
			if (p1==q1) p1=q2;
		}
		int[] cnt=new int[n];
		for(i=0;i<n;i++)
		{
			cnt[i]=0;
		}
		p1=q1;
		for(i=0;i<q1;i++)
		{
			if (cnt[newa[i]]==0) cnt[newa[i]]=1;
			else 
			{
				newa[i]=oldb[p1];
				p1++;
			}
		}
		for(i=q2;i<n;i++)
		{
			if (cnt[newa[i]]==0) cnt[newa[i]]=1;
			else 
			{
				newa[i]=oldb[p1];
				p1++;
			}
		}
		for(i=0;i<n;i++)
		{
			cnt[i]=0;
		}
		p1=q1;
		for(i=0;i<q1;i++)
		{
			if (cnt[newb[i]]==0) cnt[newb[i]]=1;
			else 
			{
				newb[i]=olda[p1];
				p1++;
			}
		}
		for(i=q2;i<n;i++)
		{
			if (cnt[newb[i]]==0) cnt[newb[i]]=1;
			else 
			{
				newb[i]=olda[p1];
				p1++;
			}
		}
		a.set(newa);
		b.set(newb);
		a.regenerate();
		b.regenerate();
	}
}

class OneChangeMutation extends GraphMutationFunction
{
	void mutate(GraphCreature a)
	{
		int m=a.getlength();
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
	double fit()
	{
		//TO DO: write function.
		return 0;
	}
}

class TravelerGraphCreature extends GraphCreature
{
	private double map[][];
	public void init(int n, double map[][])
	{
		int i,j;
		this.map = new double[n][n];
		for(i=0;i<n;i++)
			for(j=0;j<n;j++)
			{
				this.map[i][j]=map[i][j];
			}
	}
	public TravelerGraphCreature(int n, GraphCrossFunction c, GraphMutationFunction m) 
	{
		super(n, c, m);
	}
	double fit() 
	{
		int i;
		double sum=0.0;
		for(i=0;i<n-1;i++)
		{
			sum+=map[a[i]][a[i+1]];
		}
		sum+=map[a[n-1]][a[0]];
		return 0-sum;
	}
}

class PaintingGraphCreature extends GraphCreature
{
	private boolean map[][];
	public void init(int n, boolean map[][])
	{
		int i,j;
		this.map = new boolean[n][n];
		for(i=0;i<n;i++)
			for(j=0;j<n;j++)
			{
				this.map[i][j]=map[i][j];
			}
	}
	public PaintingGraphCreature(int n, GraphCrossFunction c, GraphMutationFunction m) 
	{
		super(n, c, m);
	}
	double fit() 
	{
		int i,j;
		int[] colors=new int[n+1];
		boolean[] use=new boolean[n+1]; 
		for(i=0;i<n;i++)
			colors[i]=0;
		int num=0;		
		for(i=0;i<n;i++)
		{
			for(j=1;j<=num;j++)
				use[i]=true;
			for(j=0;j<i;j++)
				if (map[a[i]][a[j]]==true)
				{
					use[colors[j]]=false;
				}
			for(j=1;j<=num;j++)
			{
				if (colors[i]==0&&use[j]==true)
					colors[i]=j;
			}
			if (colors[i]==0)
			{
				num++;
				colors[i]=num;
			}
		}
		return n-num;
	}
	int[] doPaint()
	{
		int i,j;
		int[] colors=new int[n];
		boolean[] use=new boolean[n]; 
		for(i=0;i<n;i++)
			colors[i]=0;
		int num=0;		
		for(i=0;i<n;i++)
		{
			for(j=1;j<=num;j++)
				use[i]=true;
			for(j=0;j<i;j++)
				if (map[a[i]][a[j]]==true)
				{
					use[colors[j]]=false;
				}
			for(j=1;j<=num;j++)
			{
				if (colors[i]==0&&use[j]==true)
					colors[i]=j;
			}
			if (colors[j]==0)
			{
				num++;
				colors[j]=num;
			}
		}
		return colors;
	}
}
