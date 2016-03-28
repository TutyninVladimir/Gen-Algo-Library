package backpackcreature;

public class OneItemMutation extends BackpackMutationFunction
{
	void mutate(BackpackCreature a)
	{
		int n=a.getnum_items();
		int u=a.getnum_of_use();
		int q=(int)(Math.random()*n);
		int[] olda=a.getitems();
		if (u!=1)
		{
			olda[q]=(int)(Math.random()*10*u);
			olda[q]=olda[q]%(u+1);
		}
		else
		{
			olda[q]=1-olda[q];
		}
		a.regenerate();
		a.setitems(olda);
	}
}