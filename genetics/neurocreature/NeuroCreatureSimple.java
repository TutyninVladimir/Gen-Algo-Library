package genetics.neurocreature;

public class NeuroCreatureSimple extends NeuroCreature
{
	int examples;
	double in[][];
	double out[][];
	public void init(int n, double inputs[][], double outputs[][])
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
	public NeuroCreatureSimple(int n, int layers[], NeuroCrossFunction c, NeuroMutationFunction m) 
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
	public double[] solve(double input[])
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