package genetics.simplecreature;

public class SimpleCrossOnePoint extends SimpleCrossFunction {
	void cross(SimpleCreature a, SimpleCreature b) {
		int m;
		int q;
		int olda;
		int oldb; 
		int newa;
		int newb;
	
		olda = a.get();
		oldb = b.get();
		newa = 0;
		newb = 0;
		m = a.getbytes();
		q = 1 + ((int) Math.random() * (m - 1));
		int x = 1;
		for (int i = 1; i <= q; i++) {
			newa += (olda % 2) * x;
			olda = olda / 2;
			newb += (oldb % 2) * x;
			oldb = oldb / 2;
			x *= 2;
		}
		for (int i = q + 1; i <= m; i++) {
			newa += (oldb % 2) * x;
			oldb = oldb / 2;
			newb += (olda % 2) * x;
			olda = olda / 2;
			x *= 2;
		}
		a.set(newa);
		b.set(newb);
	}
}
