package genetics.graphcreature;

public class GraphMutationOneChange extends GraphMutationFunction {
	void mutate(GraphCreature a) {
		int m;
		int q;
		int[] olda;
		int tmp;
		
		m = a.getlength();
		q = (int) (Math.random() * (m - 1));
		olda = a.get();
		tmp = olda[q];
		olda[q] = olda[q + 1];
		olda[q + 1] = tmp;
		a.set(olda);
	}
}