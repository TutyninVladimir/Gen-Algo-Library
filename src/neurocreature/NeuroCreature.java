package neurocreature;

import population.Creature;

public abstract class NeuroCreature extends Creature
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