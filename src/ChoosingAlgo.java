package Genetics;

class RandomChoosing extends Choosing
{
	private double eps=0.000001;
	void crossing(double fraction, int n, int[] use, Creature[] cr)
	{
		if (fraction>1.0)
			fraction=1.0;
		double f;
		int tries;
		int q1, q2;
		tries=0;
		f=0.0;
		for(int i=0;i<n;i++)
			use[i]=0;
		while ((f+eps)<fraction)
		{
			q1=(int)(Math.random()*n);
			q2=(int)(Math.random()*n);
			if (use[q1]==0&&use[q2]==0&&q1!=q2)
			{
				use[q1]=q2;
				use[q2]=q1;
				f+=2.0/n;
			}
			else
			{
				tries++;
				if (tries>10)
				{
					tries=0;
					q1=-1;
					q2=-1;
					for(int i=0;i<n;i++)
					{
						if (use[i]==0)
						{
							if (q1==-1)
								q1=i;
							else if (q2==-1)
								q2=i;
						}
					}
					if (q2==-1)
						return;
					else
					{
						use[q1]=q2;
						use[q2]=q1;
						f+=2.0/n;
					}
				}
			}
		}
	}
}

class ChoosingManager
{
	int n=1;
	Choosing a[];
	void init()
	{
		a = new Choosing[n+1];
		a[1] = new RandomChoosing();
	}
}