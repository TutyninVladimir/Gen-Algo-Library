package genetics.graphcreature;

public class GraphCrossSimple extends GraphCrossFunction {
	void cross(GraphCreature a, GraphCreature b) {
		int tmp;
		int olda[];
		int oldb[];
		
		int q1 = 1 + ((int) Math.random() * (a.getlength() - 1));
		int q2 = 1 + ((int) Math.random() * (a.getlength() - 1));
		if (q1 == q2) {
			q2++;
		}
		if (q1 > q2) {
			tmp = q1;
			q1 = q2;
			q2 = tmp;
		}
		
		olda = a.get();
		oldb = b.get();
		for (int i = 0; i < q1; i++) {
			tmp = olda[i];
			olda[i] = oldb[i];
			oldb[i] = tmp;
		}
		for (int i = q2; i < a.getlength(); i++) {
			tmp = olda[i];
			olda[i] = oldb[i];
			oldb[i] = tmp;
		}
		a.set(olda);
		b.set(oldb);
		a.regenerate();
		b.regenerate();
	}
}