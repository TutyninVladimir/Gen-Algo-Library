package neurocreature;

public class OneWeightMutation extends NeuroMutationFunction
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