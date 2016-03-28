package Genetics;

abstract class NeuroCreature extends Creature
{
	protected int num_layers;
	protected int layer[];
	protected double w[][][];
	protected NeuroCrossFunction c;
	protected NeuroMutationFunction m;
	abstract double fit();
	
	public double fitness()
	{
		return this.fit();
	}
	public void copyData(Creature a)
	{
		((NeuroCreature)a).num_layers=this.num_layers;
		for (int i=0;i<this.num_layers;i++)
			((NeuroCreature)a).layer[i]=this.layer[i];
		for(int i=0;i<num_layers-1;i++)
			for(int j=0;j<layer[i];j++)
				for(int k=0;k<layer[i+1];k++)
					((NeuroCreature)a).w[i][j][k]=this.w[i][j][k];
	}
	public void generate()
	{
		int i,j,k;
		int[] tmp = new int[num_layers];
		for(i=0;i<num_layers;i++)
		{
			tmp[i]=layer[i];
		}
		layer=tmp;
		w = new double [num_layers-1][][];
		for(i=0;i<num_layers-1;i++)
		{
			w[i]=new double[layer[i]][];
			for(j=0;j<layer[i];j++)
			{
				w[i][j]=new double[layer[i+1]];
				for(k=0;k<layer[i+1];k++)
					w[i][j][k]=(double)Math.random();
			}
		}
	}
	public void cross(Creature cr)
	{
		c.cross(this, (NeuroCreature) cr);
	}
	public void mutation()
	{
		m.mutate(this);
	}
	public void setweight(double weight[][][])
	{
		int i,j,k;
		for(i=0;i<num_layers-1;i++)
		{
			for(j=0;j<layer[i];j++)
			{
				for(k=0;k<layer[i+1];k++)
					this.w[i][j][k]=weight[i][j][k];
			}
		}
	}
	public double[][][] getweight()
	{
		int i,j,k;
		double[][][] tmp = new double[num_layers][][];
		for(i=0;i<num_layers-1;i++)
		{
			tmp[i]=new double[layer[i]][];
			for(j=0;j<layer[i];j++)
			{
				tmp[i][j]=new double[layer[i+1]];
				for(k=0;k<layer[i+1];k++)
					tmp[i][j][k]=w[i][j][k];
			}
		}
		return tmp;
	}
	public int getlength()
	{
		return this.num_layers;
	}
	public int[] getlayer()
	{
		int i;
		int[] tmp = new int[num_layers];
		for(i=0;i<num_layers;i++)
			tmp[i]=this.layer[i];
		return tmp;
	}
	public NeuroCreature(int n, int[] layers, NeuroCrossFunction c, NeuroMutationFunction m)
	{
		if (n>100000)
			n=100000;
		if (n<2)
			n=2;
		this.num_layers=n;
		this.layer = new int[n];
		for(int i=0;i<n;i++)
		{
			layer[i]=layers[i];
			if (layer[i]<1)
				layer[i]=1;
			if (layer[i]>100000)
				layer[i]=100000;
		}
		int i,j,k;
		w = new double [num_layers-1][][];
		for(i=0;i<num_layers-1;i++)
		{
			w[i]=new double[layer[i]][];
			for(j=0;j<layer[i];j++)
			{
				w[i][j]=new double[layer[i+1]];
				for(k=0;k<layer[i+1];k++)
					w[i][j][k]=0.0;
			}
		}
		this.c = c;
		this.m = m;
	}
}

abstract class NeuroCrossFunction
{
	abstract void cross(NeuroCreature a, NeuroCreature b);
}

abstract class NeuroMutationFunction
{
	abstract void mutate(NeuroCreature a);
}

class SimpleNeuroCross extends NeuroCrossFunction
{
	void cross(NeuroCreature a, NeuroCreature b)
	{
		double olda[][][], newa[][][], oldb[][][], newb[][][];
		olda=a.getweight();
		oldb=b.getweight();
		newa=a.getweight();
		newb=b.getweight();
		
		int i,j,k,n=0;
		for(i=0;i<a.num_layers-1;i++)
		{
			n+=a.layer[i]*a.layer[i+1];
		}
		int q=(int)(Math.random()*n);
		int tmp=0;
		for(i=0;i<a.num_layers-1;i++)
			for(j=0;j<a.layer[i];j++)
				for(k=0;k<a.layer[i+1];k++)
				{
					if (tmp<=q)
					{
						newa[i][j][k]=oldb[i][j][k];
						newb[i][j][k]=olda[i][j][k];
					}
					else
					{
						newa[i][j][k]=olda[i][j][k];
						newb[i][j][k]=oldb[i][j][k];
					}
					tmp++;
				}
		a.generate();
		b.generate();
		a.setweight(newa);
		b.setweight(newb);
	}
}

class OneWeightMutation extends NeuroMutationFunction
{
	void mutate(NeuroCreature a)
	{
		int i,j,k,l,n=0;
		for(i=0;i<a.num_layers-1;i++)
		{
			n+=a.layer[i]*a.layer[i+1];
		}
		int q=(int)(Math.random()*n);
		int tmp=0;
		double[][][] olda=a.getweight();
		for(i=0;i<a.num_layers-1;i++)
			for(j=0;j<a.layer[i];j++)
				for(k=0;k<a.layer[i+1];k++)
				{
					if (tmp==q)
					{
						double tmp2=olda[i][j][k];
						double tmp3=1.0;
						int rnd=(int)(Math.random()*20);
						for(l=1;l<=rnd;l++)
						{
							tmp2*=2;
							tmp3/=2;
						}
						if (((int)tmp2)%2==0)
						{
							olda[i][j][k]+=tmp3;
						}
						else
						{
							olda[i][j][k]-=tmp3;
						}
						if (olda[i][j][k]<0.0) olda[i][j][k]=0.0;
						if (olda[i][j][k]>1.0) olda[i][j][k]=1.0;
					}
					tmp++;
				}
		a.setweight(olda);
	}
}

class ExampleNeuroCreature extends NeuroCreature
{
	public ExampleNeuroCreature(int n, int layers[], NeuroCrossFunction c, NeuroMutationFunction m) 
	{
		super(n, layers, c, m);
	}
	double fit()
	{
		//TO DO: write function.
		return 0;
	}
}

class SimpleNeuroCreature extends NeuroCreature
{
	int examples;
	double in[][];
	double out[][];
	void init(int n, double inputs[][], double outputs[][])
	{
		examples=n;
		in = new double[n][this.layer[0]];
		out = new double[n][this.layer[num_layers-1]];
		//Число входов равняется числу нейронов первого слоя, число выходов - последнего
		int i,j;
		for(i=0;i<n;i++)
		{
			for(j=0;j<this.layer[0];j++)
			{
				in[i][j]=inputs[i][j];
			}
			for(j=0;j<this.layer[num_layers-1];j++)
			{
				out[i][j]=outputs[i][j];
			}
		}
	}
	public SimpleNeuroCreature(int n, int layers[], NeuroCrossFunction c, NeuroMutationFunction m) 
	{
		super(n, layers, c, m);
	}
	double fit()
	{
		int i,j,k,c;
		double neuro[][]= new double[this.num_layers][];
		for(i=0;i<num_layers;i++)
		{
			neuro[i]=new double[this.layer[i]];
		}
		double err=0.0;
		double l_err;
		for(c=0;c<examples;c++)
		{
			for(i=0;i<layer[0];i++)
				neuro[0][i]=in[c][i];
			for(i=1;i<num_layers;i++)
				for(j=0;j<layer[i];j++)
				{
					neuro[i][j]=0.0;
					for(k=0;k<layer[i-1];k++)
						neuro[i][j]+=neuro[i-1][k]*w[i-1][k][j];
					neuro[i][j]/=layer[i-1];
				}
			l_err=0.0;
			for(i=0;i<layer[num_layers-1];i++)
				l_err+=Math.abs(neuro[num_layers-1][i]-out[c][i]);
			err+=l_err;
		}
		return layer[num_layers-1]*examples-err;
	}
	double[] solve(double input[])
	{
		int i,j,k;
		double neuro[][]= new double[this.num_layers][];
		for(i=0;i<num_layers;i++)
		{
			neuro[i]=new double[this.layer[i]];
		}
		for(i=0;i<layer[0];i++)
			neuro[0][i]=input[i];
		for(i=1;i<num_layers;i++)
			for(j=0;j<layer[i];j++)
			{
				neuro[i][j]=0.0;
				for(k=0;k<layer[i-1];k++)
					neuro[i][j]+=neuro[i-1][k]*w[i-1][k][j];
				neuro[i][j]/=layer[i-1];
			}
		return neuro[num_layers-1];
	}
}