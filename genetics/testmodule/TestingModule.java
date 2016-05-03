package genetics.testmodule;

import genetics.choosing.ChoosingRandom;
import genetics.population.*;
import genetics.selecting.SelectingMax;
import genetics.stopping.StoppingIterations;
import genetics.stopping.StoppingMaxNotChanged;

public class TestingModule {
	public Population p;
	private double testanswer = 0;
	
	double getanswer() {
		return testanswer;
	}

	Population startTest(Creature cr, double eps) {
		ChoosingManager ch = new ChoosingManager();
		SelectingManager sel = new SelectingManager();
		StoppingManager stp = new StoppingManager();
		int size, newsize, anssize, globanssize = 10;
		double mark = 0, newmark, globmark = -1;
		double fraction, newfraction, ansfraction, globansfraction = 1.00;
		double mutate = 0.25;
		double answer, newanswer, globanswer = 0.0;
		double tmp;
		int c[] = new int[4];
		int a[] = new int[4];
		
		ch.init();
		sel.init();
		stp.init();
		
		for (c[1] = 1; c[1] <= ch.n; c[1]++)
			for (c[2] = 1; c[2] <= sel.n; c[2]++)
				for (c[3] = 1; c[3] <= stp.n; c[3]++) {
					size = 100;
					fraction = 1.0;
					mutate = 0.25;
					p = new Population(size, fraction, mutate, cr, ch.a[c[1]],
					sel.a[c[2]], stp.a[c[3]]);
					tmp = 0.0;
					for (int i = 0; i < 100; i++) {
						p.run();
						if (i == 0) {
							tmp = p.getAnswer();
						} else {
							tmp = Math.max(tmp, p.getAnswer());
						}
					}
					answer = tmp;
					mark = size * (1 + fraction) * p.getTurns();
					newanswer = answer + 2 * eps;
					while ((newanswer - answer > eps) && size < 100000) {
						answer = newanswer;
						size = size * 2;
						p = new Population(size, fraction, mutate, cr, 
						ch.a[c[1]], sel.a[c[2]], stp.a[c[3]]);
						tmp = 0.0;
						for (int i = 0; i < 1 + 10000 / size; i++) {
							p.run();
							if (i == 0) {
								tmp = p.getAnswer();
							} else {
								tmp = Math.max(tmp, p.getAnswer());
							}
						}
						newanswer = tmp;
						mark = size * (1 + fraction) * p.getTurns();
					}
					newsize = size;
					newfraction = fraction;
					newmark = mark;
					anssize = newsize;
					ansfraction = newfraction;
					while ((Math.abs(newanswer - answer) < eps) && 
						(newsize * newfraction > 10.0)) {
						if (newmark < mark) {
							mark = newmark;
							anssize = newsize;
							ansfraction = newfraction;
						}
						int q = (int) (Math.random() * 100) % 3;
						if (q == 0) {
							newsize = newsize - newsize / 10;
						}
						if (q == 1) {
							newfraction = newfraction / 1.1;
						}
						if (q == 2) {
							newsize = newsize - newsize / 20;
							newfraction = newfraction / 1.25;
						}
						p = new Population(newsize, newfraction, mutate, 
						cr, ch.a[c[1]], sel.a[c[2]], stp.a[c[3]]);
						tmp = 0.0;
						for (int i = 0; i < 1 + 10000 / size; i++) {
							p.run();
							if (i == 0) {
								tmp = p.getAnswer();
							} else {
								tmp = Math.max(tmp, p.getAnswer());
							}
						}
						newanswer = tmp;
						newmark = size * (1 + fraction) * p.getTurns();
					}
					if (globmark == -1 || (answer > globanswer)
					|| (Math.abs(answer - globanswer) < eps && mark < globmark)) {
						globanssize = anssize;
						globansfraction = ansfraction;
						globanswer = answer;
						a[1] = c[1];
						a[2] = c[2];
						a[3] = c[3];
					}
				}
		testanswer = globanswer;
		p = new Population(globanssize, globansfraction, mutate, cr, 
		ch.a[a[1]], sel.a[a[2]], stp.a[a[3]]);
		return p;
	}
}


class ChoosingManager {
	final int n = 1;
	Choosing a[];

	void init() {
		a = new Choosing[n + 1];
		a[1] = new ChoosingRandom();
	}
}


class SelectingManager {
	final int n = 1;
	Selecting a[];

	void init() {
		a = new Selecting[n + 1];
		a[1] = new SelectingMax();
	}
}


class StoppingManager {
	final int n = 2;
	Stopping a[];

	void init() {
		a = new Stopping[n + 1];
		a[1] = new StoppingIterations(100);
		a[2] = new StoppingMaxNotChanged(5);
	}
}