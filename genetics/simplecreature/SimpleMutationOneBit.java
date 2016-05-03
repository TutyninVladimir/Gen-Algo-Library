package genetics.simplecreature;

public class SimpleMutationOneBit extends SimpleMutationFunction {
	void mutate(SimpleCreature a) {
		int m;
		int q;
		int olda;
		int newa;
		int x;
		
		m = a.getbytes();
		q = (int) (Math.random() * m);
		olda = a.get();
		newa = 0;
		x = 1;
		for (int i = 0; i < q; i++) {
			x = x * 2;
		}
		newa = olda ^ x;
		a.set(newa);
	}
}
