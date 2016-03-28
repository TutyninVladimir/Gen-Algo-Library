package graphcreature;

public class OneChangeMutation extends GraphMutationFunction
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