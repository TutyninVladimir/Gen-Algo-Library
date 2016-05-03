package genetics.backpackcreature;

public class BackpackCrossSimple extends BackpackCrossFunction
{
	void cross(BackpackCreature a, BackpackCreature b) {
		int n;
		int olda[];
		int newa[];
		int oldb[];
		int newb[];
		
		n = a.getnum_items();
		olda = a.getitems();
		oldb = b.getitems();
		newa = new int[n];
		newb = new int[n];
		int q = (int) (Math.random() * n);
		for (int i = 0; i < n; i++) {
			if (i <= q) {
				newa[i] = oldb[i];
				newb[i] = olda[i];
			} else {
				newa[i] = olda[i];
				newb[i] = oldb[i];
			}
		}
		a.regenerate();
		b.regenerate();
		a.setitems(newa);
		b.setitems(newb);
	}
}