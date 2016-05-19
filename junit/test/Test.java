package test;

import org.junit.Assert;
import genetics.choosing.*;
import genetics.population.Creature;
import genetics.population.Population;
import genetics.selecting.SelectingMax;
import genetics.simplecreature.*;
import genetics.stopping.StoppingIterations;
import genetics.stopping.StoppingMaxNotChanged;

class SimpleCreatureMyFunction extends SimpleCreature {

	public SimpleCreatureMyFunction(int bytes, SimpleCrossFunction c, 
	SimpleMutationFunction m) {
		super(bytes, c, m);
	}
	
	public double fit() {
		double f = super.q * super.q + super.q * 2 + 1;
		return f;
	}
	
	/*public int get() {
		return super.q;
	}*/
}

class SimpleCreatureSecondFunction extends SimpleCreature {

	public SimpleCreatureSecondFunction(int bytes, SimpleCrossFunction c, 
	SimpleMutationFunction m) {
		super(bytes, c, m);
	}
	
	public double fit() {
		double f = Math.sqrt(super.q + 1);
		return f;
	}
	
	/*public int get() {
		return super.q;
	}*/
}

public class Test {

	@org.junit.Test
	public void crossing_test1() {
		//arrange
		int n = 100;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature[] cr = new Creature[2 * n + 1];
		for(int i = 0; i < 2 * n + 1; i++) {
			cr[i] = new SimpleCreatureMyFunction(20,cf,cm);
			cr[i].generate();
		}
		int[] use = new int[2 * n + 1];
		for(int i = 0; i < 2 * n + 1; i++) {
			use[i] = -1;
		}
		ChoosingRandom m = new ChoosingRandom(); 
		//act
		m.crossing(0.4, n, use, cr);
		//assert
		int[] use2 = new int[2 * n + 1];
		for(int i = 0; i < 2 * n + 1; i++) {
			use2[i] = 0;
		}
		int sum = 0;
		boolean true_test = true;
		for(int i = 0; i < 2 * n + 1; i++) {
			if (use[i] != -1) {
				sum++;
				use2[i]++;
				if (use2[i]>1) {
					true_test = false;
				}
			}
		}
		if (sum != n * 0.4) {
			true_test = false;
		}
		Assert.assertEquals(true_test, true);
	}

	@org.junit.Test
	public void crossing_test2() {
		//arrange
		int n = 250;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature[] cr = new Creature[2 * n + 1];
		for(int i = 0; i < 2 * n + 1; i++) {
			cr[i] = new SimpleCreatureMyFunction(20,cf,cm);
			cr[i].generate();
		}
		int[] use = new int[2 * n + 1];
		for(int i = 0; i < 2 * n + 1; i++) {
			use[i] = -1;
		}
		ChoosingRandom m = new ChoosingRandom(); 
		//act
		m.crossing(0.00, n, use, cr);
		//assert
		int[] use2 = new int[2 * n + 1];
		for(int i = 0; i < 2 * n + 1; i++) {
			use2[i] = 0;
		}
		int sum = 0;
		boolean true_test = true;
		for(int i = 0; i < 2 * n + 1; i++) {
			if (use[i] != -1) {
				sum++;
				use2[i]++;
				if (use2[i]>1) {
					true_test = false;
				}
			}
		}
		if (sum != 0) {
			true_test = false;
		}
		Assert.assertEquals(true_test, true);
	}
	
	@org.junit.Test
	public void crossing_test3() {
		//arrange
		int n = 10000;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature[] cr = new Creature[n];
		for(int i = 0; i < n; i++) {
			cr[i] = new SimpleCreatureMyFunction(20,cf,cm);
			cr[i].generate();
		}
		int[] use = new int[n];
		for(int i = 0; i < n; i++) {
			use[i] = -1;
		}
		ChoosingRandom m = new ChoosingRandom(); 
		//act
		m.crossing(1.00, n, use, cr);		
		//assert
		int[] use2 = new int[n];
		for(int i = 0; i < n; i++) {
			use2[i] = 0;
		}
		int sum = 0;
		boolean true_test = true;
		for(int i = 0; i < n; i++) {
			if (use[i] != -1) {
				sum++;
				use2[i]++;
				if (use2[i]>1) {
					true_test = false;
				}
			}
		}
		if (sum != n) {
			true_test = false;
		}
		Assert.assertEquals(true_test, true);
	}
	
	@org.junit.Test
	public void selecting_test1() {
		//arrange
		int n1 = 200;
		int n2 = 150;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature[] cr = new Creature[n1];
		for(int i = 0; i < n1; i++) {
			cr[i] = new SimpleCreatureMyFunction(10,cf,cm);
			cr[i].generate();
		}
		int[] use = new int[n1];
		for(int i = 0; i < n1; i++) {
			use[i] = 0;
		}
		SelectingMax m = new SelectingMax(); 
		//act
		m.select(n1, n2, use, cr);		
		//assert
		double min_alive = -3.1;
		double max_dead = -3.1;
		for(int i = 0; i < n1; i++) {
			if (use[i] == 1) {
				if (cr[i].fitness() < min_alive || min_alive <= -3) {
					min_alive = cr[i].fitness();
				}
			}
			if (use[i] == 0) {
				if (cr[i].fitness() > max_dead) {
					max_dead = cr[i].fitness();
				}
			}
		}
		Assert.assertEquals(min_alive >= max_dead - 0.01, true);
	}
	
	@org.junit.Test
	public void selecting_test2() {
		//arrange
		int n1 = 1000;
		int n2 = 500;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature[] cr = new Creature[n1];
		for(int i = 0; i < n1; i++) {
			cr[i] = new SimpleCreatureMyFunction(10,cf,cm);
			cr[i].generate();
		}
		int[] use = new int[n1];
		for(int i = 0; i < n1; i++) {
			use[i] = 0;
		}
		SelectingMax m = new SelectingMax(); 
		//act
		m.select(n1, n2, use, cr);		
		//assert
		double min_alive = -3.1;
		double max_dead = -3.1;
		for(int i = 0; i < n1; i++) {
			if (use[i] == 1) {
				if (cr[i].fitness() < min_alive || min_alive <= -3) {
					min_alive = cr[i].fitness();
				}
			}
			if (use[i] == 0) {
				if (cr[i].fitness() > max_dead) {
					max_dead = cr[i].fitness();
				}
			}
		}
		Assert.assertEquals(min_alive >= max_dead - 0.01, true);
	}
	
	@org.junit.Test
	public void selecting_test3() {
		//arrange
		int n1 = 10000;
		int n2 = 10000;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature[] cr = new Creature[n1];
		for(int i = 0; i < n1; i++) {
			cr[i] = new SimpleCreatureMyFunction(10,cf,cm);
			cr[i].generate();
		}
		int[] use = new int[n1];
		for(int i = 0; i < n1; i++) {
			use[i] = 0;
		}
		SelectingMax m = new SelectingMax(); 
		//act
		m.select(n1, n2, use, cr);		
		//assert
		double min_alive = -3.1;
		double max_dead = -3.1;
		for(int i = 0; i < n1; i++) {
			if (use[i] == 1) {
				if (cr[i].fitness() < min_alive || min_alive <= -3) {
					min_alive = cr[i].fitness();
				}
			}
			if (use[i] == 0) {
				if (cr[i].fitness() > max_dead) {
					max_dead = cr[i].fitness();
				}
			}
		}
		Assert.assertEquals((min_alive >= max_dead - 0.01), true);
	}
	
	@org.junit.Test
	public void stopping_test1() {
		//arrange
		int n = 100;
		int turns = 25;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature[] cr = new Creature[n];
		for(int i = 0; i < n; i++) {
			cr[i] = new SimpleCreatureMyFunction(20,cf,cm);
			cr[i].generate();
		}
		StoppingIterations m = new StoppingIterations(turns);
		boolean is_end = false;
		//act
		for(int i = 0;i < 20; i++) {
			is_end = m.isEnding(n, cr);
		}
		//assert
		Assert.assertEquals(!is_end, true);
	}
	
	@org.junit.Test
	public void stopping_test2() {
		//arrange
		int n = 100;
		int turns = 25;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature[] cr = new Creature[n];
		for(int i = 0; i < n; i++) {
			cr[i] = new SimpleCreatureMyFunction(20,cf,cm);
			cr[i].generate();
		}
		StoppingIterations m = new StoppingIterations(turns);
		boolean is_end = false;
		//act
		for(int i = 0;i < 25; i++) {
			is_end = m.isEnding(n, cr);
		}
		//assert
		Assert.assertEquals(is_end, true);
	}
	
	@org.junit.Test
	public void stopping_test3() {
		//arrange
		int n = 100;
		int turns = 5;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature[] cr = new Creature[n];
		for(int i = 0; i < n; i++) {
			cr[i] = new SimpleCreatureMyFunction(20,cf,cm);
			cr[i].generate();
		}
		StoppingMaxNotChanged m = new StoppingMaxNotChanged(turns);
		boolean is_end = false;
		//act
		for(int i = 0;i < 6; i++) {
			is_end = m.isEnding(n, cr);
		}
		//assert
		Assert.assertEquals(is_end, true);
	}
	
	@org.junit.Test
	public void mutate_test1() {
		//arrange
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature cr = new SimpleCreatureMyFunction(20,cf,cm);
		cr.generate();
		int x1, x2;
		//act
		x1 = ((SimpleCreatureMyFunction) cr).get();
		cr.mutation();
		x2 = ((SimpleCreatureMyFunction) cr).get();		
		//assert
		int diff = Math.abs(x1 - x2);
		boolean flag = false;
		int tmp = 1;
		for(int i = 0; i < 22; i++) {
			if (diff == tmp) {
				flag = true;
			}
			tmp = tmp * 2;
		}
		Assert.assertEquals(flag, true);
	}
	
	@org.junit.Test
	public void mutate_test2() {
		//arrange
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature cr = new SimpleCreatureMyFunction(20,cf,cm);
		cr.generate();
		int x1, x2;
		//act
		x1 = ((SimpleCreatureMyFunction) cr).get();
		cr.mutation();
		x2 = ((SimpleCreatureMyFunction) cr).get();
		for (int i = 0; i < 1000000; i++) {
			cr.mutation();
		}		
		//assert
		int diff = Math.abs(x1 - x2);
		boolean flag = false;
		int tmp = 1;
		for(int i = 0; i < 22; i++) {
			if (diff == tmp) {
				flag = true;
			}
			tmp = tmp * 2;
		}
		Assert.assertEquals(flag, true);
	}
	
	@org.junit.Test
	public void cross_test1() {
		//arrange
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature cr1 = new SimpleCreatureMyFunction(20,cf,cm);
		cr1.generate();
		Creature cr2 = new SimpleCreatureMyFunction(20,cf,cm);
		cr2.generate();
		int x1, x2, q1, q2;
		x1 = 0;
		q1 = ((SimpleCreatureMyFunction) cr1).get();
		q2 = ((SimpleCreatureMyFunction) cr2).get();
		for(int i = 0; i < 21; i++) {
			if (q1 % 2 == 1) {
				x1++;
			}
			if (q2 % 2 == 1) {
				x1++;
			}
			q1 = q1 / 2;
			q2 = q2 / 2;
		}
		//act
		cr1.cross(cr2);
		//assert
		q1 = ((SimpleCreatureMyFunction) cr1).get();
		q2 = ((SimpleCreatureMyFunction) cr2).get();
		x2 = 0;
		for(int i = 0; i < 21; i++) {
			if (q1 % 2 == 1) {
				x2++;
			}
			if (q2 % 2 == 1) {
				x2++;
			}
			q1 = q1 / 2;
			q2 = q2 / 2;
		}
		Assert.assertEquals(x1 == x2, true);
	}
	
	@org.junit.Test
	public void cross_test2() {
		//arrange
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature cr1 = new SimpleCreatureMyFunction(20,cf,cm);
		cr1.generate();
		Creature cr2 = new SimpleCreatureMyFunction(20,cf,cm);
		cr2.generate();
		int x1, x2, q1, q2;
		x1 = 0;
		q1 = ((SimpleCreatureMyFunction) cr1).get();
		q2 = ((SimpleCreatureMyFunction) cr2).get();
		for(int i = 0; i < 21; i++) {
			if (q1 % 2 == 1) {
				x1++;
			}
			if (q2 % 2 == 1) {
				x1++;
			}
			q1 = q1 / 2;
			q2 = q2 / 2;
		}
		//act
		for (int c = 1; c <= 100000; c++) {
			cr1.cross(cr2);
		}
		//assert
		q1 = ((SimpleCreatureMyFunction) cr1).get();
		q2 = ((SimpleCreatureMyFunction) cr2).get();
		x2 = 0;
		for(int i = 0; i < 21; i++) {
			if (q1 % 2 == 1) {
				x2++;
			}
			if (q2 % 2 == 1) {
				x2++;
			}
			q1 = q1 / 2;
			q2 = q2 / 2;
		}
		Assert.assertEquals(x1 == x2, true);
	}
	
	@org.junit.Test
	public void fit_test1() {
		//arrange
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature cr = new SimpleCreatureMyFunction(12,cf,cm);
		cr.generate();
		int x;
		double y;
		//act
		x = ((SimpleCreatureMyFunction) cr).get();
		y = ((SimpleCreatureMyFunction) cr).fit();		
		//assert
		Assert.assertEquals(y == ((double)(x * x + x * 2 + 1)) , true);
	}
	
	@org.junit.Test
	public void fit_test2() {
		//arrange
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature cr = new SimpleCreatureMyFunction(12,cf,cm);
		cr.generate();
		int x = 0;
		double y = 0.0;
		//act
		x = ((SimpleCreatureMyFunction) cr).get();
		for (int c = 1; c <= 10000000; c++) {
			y = ((SimpleCreatureMyFunction) cr).fit();
		}
		//assert
		Assert.assertEquals(y == ((double)(x * x + x * 2 + 1)) , true);
	}
	
	@org.junit.Test
	public void run_test1() {
		//arrange
		int n = 1000;
		double diff = 0.05;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature cr = new SimpleCreatureSecondFunction(20,cf,cm);
		ChoosingRandom r = new ChoosingRandom();
		SelectingMax m = new SelectingMax();
		StoppingMaxNotChanged s = new StoppingMaxNotChanged(20); 
		Population p = new Population(n, 0.4, 0.2, cr, r, m, s);
		//act
		p.run();
		//assert
		Assert.assertEquals((p.getAnswer() > 1024 * (1 - diff)) , true);
	}
	
	@org.junit.Test
	public void run_test2() {
		//arrange
		int n = 200000;
		double diff = 0.02;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature cr = new SimpleCreatureSecondFunction(20,cf,cm);
		ChoosingRandom r = new ChoosingRandom();
		SelectingMax m = new SelectingMax();
		StoppingMaxNotChanged s = new StoppingMaxNotChanged(20); 
		Population p = new Population(n, 0.4, 0.2, cr, r, m, s);
		//act
		p.run();
		//assert
		Assert.assertEquals((p.getAnswer() > 1024 * (1 - diff)) , true);
	}
	
	@org.junit.Test
	public void onestep_test1() {
		//arrange
		int n = 1000;
		double x1;
		double x2;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature cr = new SimpleCreatureSecondFunction(20,cf,cm);
		ChoosingRandom r = new ChoosingRandom();
		SelectingMax m = new SelectingMax();
		StoppingMaxNotChanged s = new StoppingMaxNotChanged(20); 
		Population p = new Population(n, 0.4, 0.2, cr, r, m, s);
		//act
		cr = p.getAnswerCreature();
		x1 = ((SimpleCreatureSecondFunction) cr).fit();
		p.oneStep();
		cr = p.getAnswerCreature();
		x2 = ((SimpleCreatureSecondFunction) cr).fit();
		//assert
		Assert.assertEquals((x2 >= x1) , true);
	}
	
	@org.junit.Test
	public void onestep_test2() {
		//arrange
		int n = 2500;
		double x1;
		double x2;
		SimpleCrossFunction cf = new SimpleCrossOnePoint();
		SimpleMutationFunction cm = new SimpleMutationOneBit();
		Creature cr = new SimpleCreatureSecondFunction(20,cf,cm);
		ChoosingRandom r = new ChoosingRandom();
		SelectingMax m = new SelectingMax();
		StoppingMaxNotChanged s = new StoppingMaxNotChanged(10); 
		Population p = new Population(n, 0.4, 0.2, cr, r, m, s);
		//act
		p.run();
		cr = p.getAnswerCreature();
		x1 = ((SimpleCreatureSecondFunction) cr).fit();
		p.oneStep();
		cr = p.getAnswerCreature();
		x2 = ((SimpleCreatureSecondFunction) cr).fit();
		//assert
		Assert.assertEquals((x2 >= x1) , true);
	}
}











