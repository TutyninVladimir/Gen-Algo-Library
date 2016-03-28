package simplecreature;

public class OneBitMutation extends SimpleMutationFunction
{
	void mutate(SimpleCreature a)
	{
		int m=a.getbytes();
		int q=(int)(Math.random()*m);
		int olda=a.get();
		int newa=0;
		int x=1;
		for(int i=0;i<q;i++)
			x=x*2;
		newa=olda^x;
		a.set(newa);
	}
}
