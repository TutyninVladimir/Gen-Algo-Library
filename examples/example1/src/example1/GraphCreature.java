package example1;

import genetics.population.Creature;

/**
 * ����� GraphCreature - ��������� ������ Creature, ����������� �������� ���
 * ������ � ������, ������� ������� ����������� � ���� 
 * ������� ��������������� ����� �� 1 �� n ��� ������� ����� �� ������.
 * @author Tutynin Vladimir
 */
public abstract class GraphCreature extends Creature {
	protected int n;
	protected int a[];
	protected GraphCrossFunction c;
	protected GraphMutationFunction m;
	
	/**
	 * ����������� ������. ��� �������� ����� ����������� ������
	 * �������, � ����� �������, ����������� ��������� ����������� � �������.
	 * @param n ������ �������.
	 * @param c ������, ����������� ������� �����������.
	 * @param m ������, ����������� ������� �������.
	 */
	public GraphCreature(int n, GraphCrossFunction c, 
	GraphMutationFunction m) {
		if (n > 100000) {
			n = 100000;
		}
		if (n < 2) {
			n = 2;
		}
		this.n = n;
		a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = i;
		}
		this.c = c;
		this.m = m;
	}
	
	/**
	 * ����������� ������� ���������� �������� �����������������. ������� �� 
	 * �������� ������. 
	 * @return �������� ������� �����������������.
	 */
	abstract double fit();
	
	/**
	 * ������� ���������� �������� �����������������, ������������ ��������� 
	 * ������ Population.
	 * @return �������� ������� �����������������.
	 */
	public double fitness() {
		return this.fit();
	}
	
	/**
	 * ������� ����������� �������� �������� - ������� - �� ����� ����� � ������.
	 * @param a �����, � ������� ���������� ���������.
	 */
	public void copyData(Creature a) {
		((GraphCreature) a).n = this.n;
		for (int i = 0; i < n; i++) {
			((GraphCreature) a).a[i] = this.a[i];
		}
	}
	
	/**
	 * ������� ����������� ������� � ������������� ����������.
	 */
	public void regenerate() {
		int i;
		int[] tmp = new int[n];
		int p2;
		
		for (i = 0; i < n; i++) {
			tmp[i] = 0;
		}
		for (i = 0; i < n; i++) {
			if (tmp[a[i]] == 0) { 
				tmp[a[i]] = 1;
			} else {
				a[i] = -1;
			}
		}
		p2 = 0;
		for (i = 0; i < n; i++) {
			if (a[i] == -1) {
				while (tmp[p2] != 0) {
					p2++;
				}
				tmp[p2] = 1;
				a[i] = p2;
			}
		}
	}
	
	/**
	 * ������� ��������� ������������ ������������ ��������� �� 1 �� n. 
	 */
	public void generate() {
		int tmp;
		int tmp2;
		
		a = new int[n];
		for (int i = 0; i < n; i++) {
			this.a[i] = i;
		}
		for (int i = n-1; i >= 0; i--) {
			tmp = ((int) (Math.random() * (i+1)));
			tmp2 = this.a[i];
			this.a[i] = this.a[tmp]; 
			this.a[tmp] = tmp2;
		}
	}
	
	/**
	 * ������� ����������� �����.
	 */
	public void cross(Creature cr) {
		c.cross(this, (GraphCreature) cr);
	}
	
	/**
	 * ������� ������� �����.
	 */
	public void mutation() {
		m.mutate(this);
	}
	
	/**
	 * ������� ��������� �������� �������.
	 * @param a ��������������� �������� �������.
	 */
	public void set(int a[]) {
		for (int i = 0; i < n; i++) {
			this.a[i] = a[i];
		}
		regenerate();
	}
	
	/**
	 * ������� ��������� �������� �����.
	 * @return ������� �����.
	 */
	public int[] get() {
		int[] tmp = new int[n];
		for (int i = 0; i < n; i++) {
			tmp[i] = this.a[i];
		}
		return tmp;
	}
	
	/**
	 * ������� ��������� ����� �������.
	 * @return ����� �������.
	 */
	public int getlength() {
		return this.n;
	}
}