package backpackcreature;

public class SimpleBackpackCreature extends BackpackCreature
{
	int max_weight;
	int costs[];
	int weights[];
	void init(int max_weight, int cost[], int weight[])
	{
		int i;
		costs = new int[num_items];
		weights = new int[num_items];
		if (max_weight<0)
			max_weight=0;
		this.max_weight=max_weight;
		for(i=0;i<num_items;i++)
		{
			costs[i]=cost[i];
			weights[i]=weight[i];
		}
	}
	public SimpleBackpackCreature(int n, int num_of_use, BackpackCrossFunction c, BackpackMutationFunction m) 
	{
		super(n, num_of_use, c, m);
	}
	double fit()
	{
		int i, weight=0, t_items[];
		t_items = new int[num_items];
		for(i=0;i<num_items;i++)
		{
			t_items[i]=items[i];
			weight+=weights[i]*t_items[i];
		}
		int need_drop=weight-max_weight;
		i=0;
		while(need_drop>0&&i<num_items)
		{
			if (t_items[i]>0)
			{
				t_items[i]--;
				need_drop-=weights[i];
			}
			if (t_items[i]==0)
				i++;
		}
		int sum=0;
		weight=0;
		for(i=0;i<num_items;i++)
		{
			weight+=weights[i]*t_items[i];
			sum+=costs[i]*t_items[i];
		}
		if (weight<=max_weight) return sum;
		return 0;
	}
	int[] getanswer()
	{
		int i, weight=0, t_items[];
		t_items = new int[num_items];
		for(i=0;i<num_items;i++)
		{
			t_items[i]=items[i];
			weight+=weights[i]*t_items[i];
		}
		int need_drop=weight-max_weight;
		i=0;
		while(need_drop>0&&i<num_items)
		{
			if (t_items[i]>0)
			{
				t_items[i]--;
				need_drop-=weights[i];
			}
			if (t_items[i]==0)
				i++;
		}
		return t_items;
	}
}