package genetics.graphcreature;

/**
 *  ласс SimpleCrossOnePoint - наследник класса SimpleCrossFunction, реализующий
 * функцию изощрЄнного кроссинговера дл€ особи с генотипом 
 * в виде массива неповтор€ющихс€ чисел от 1 до n.
 * @author Tutynin Vladimir
 */
public class GraphCrossExtended extends GraphCrossFunction {
	/**
	 * –еализаци€ стратегии изощрЄнного кроссинговера дл€ особи с генотипом 
	 * в виде массива неповтор€ющихс€ чисел от 1 до n.
	 * @param a ѕерва€ скрещиваема€ особь.
	 * @param b ¬тора€ скрещиваема€ особь.
	 */
	void cross(GraphCreature a, GraphCreature b) {
		int tmp, n;
		int q1, q2;
		int olda[];
		int newa[];
		int oldb[];
		int newb[];
		int p1;
		int cnt[];
		
		q1 = 1 + ((int) Math.random() * (a.getlength() - 1));
		q2 = 1 + ((int) Math.random() * (a.getlength() - 1));
		n = a.getlength();
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
		newa = new int[n];
		newb = new int[n];
		p1 = q2;
		for (int i = 0; i < q1; i++) {
			newa[p1] = oldb[i];
			newb[p1] = olda[i];
			p1++;
			if (p1 >= n) {
				p1 -= n;
			}
			if (p1 == q1) {
				p1 = q2;
			}
		}
		for (int i = q1; i < q2; i++) {
			newa[i] = olda[i];
			newb[i] = oldb[i];
		}
		for (int i = q2; i < n; i++) {
			newa[p1] = oldb[i];
			newb[p1] = olda[i];
			p1++;
			if (p1 >= n) {
				p1 = 0;
			}
			if (p1 == q1) {
				p1 = q2;
			}
		}
		cnt = new int[n];
		for (int i = 0; i < n; i++) {
			cnt[i] = 0;
		}
		for (int i = q1; i < q2; i++) {
			cnt[newa[i]] = 1;
		}
		p1 = q1;
		for (int i = 0; i < q1; i++) {
			if (cnt[newa[i]] == 0) {
				cnt[newa[i]] = 1;
			} else {
				while (cnt[oldb[p1]] == 1 && p1 < q2) {
					p1++;
				}
				newa[i] = oldb[p1];
				cnt[oldb[p1]] = 1;
			}
		}
		for (int i = q2; i < n; i++) {
			if (cnt[newa[i]] == 0) {
				cnt[newa[i]] = 1;
			} else {
				while (cnt[oldb[p1]] == 1 && p1 < q2) {
					p1++;
				}
				newa[i] = oldb[p1];
				cnt[oldb[p1]] = 1;
			}
		}
		for (int i = 0; i < n; i++) {
			cnt[i] = 0;
		}
		for (int i = q1; i < q2; i++) {
			cnt[newa[i]] = 1;
		}
		p1 = q1;
		for (int i = 0; i < q1; i++) {
			if (cnt[newb[i]] == 0) {
				cnt[newb[i]] = 1;
			}
			else {
				while (cnt[olda[p1]] == 1 && p1 < q2) {
					p1++;
				}
				newb[i] = olda[p1];
				cnt[olda[p1]] = 1;
			}
		}
		for (int i = q2; i < n; i++) {
			if (cnt[newb[i]] == 0) {
				cnt[newb[i]] = 1;
			} else {
				while (cnt[olda[p1]] == 1 && p1 < q2) {
					p1++;
				}
				newb[i] = olda[p1];
				cnt[olda[p1]] = 1;
			}
		}
		a.set(newa);
		b.set(newb);
		a.regenerate();
		b.regenerate();
	}
}