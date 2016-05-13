package example3;

import genetics.population.Creature;

/**
 * ����� BackpackCreature - ��������� ������ Creature, ����������� �������� ���
 * ������ � ������, ������� ������� ����������� � ���� ������ ��������� ��� 
 * ������� ������ � �����.
 * @author Tutynin Vladimir
 */
public abstract class BackpackCreature extends Creature {
	protected int num_items;
	protected int items[];
	protected int num_of_use;
	protected BackpackCrossFunction c;
	protected BackpackMutationFunction m;
	
	/**
	 * ����������� ������. ��� �������� ����� ����������� ������
	 * �������, ���������� ��������� ������ ����, ������� �����������
	 * ������������, � ����� �������, ����������� 
	 * ��������� ����������� � �������.
	 * @param n ������ �������.
	 * @param num_of_use ������������ ���������� ��������� ������ ����, �������
	 * ����� ����� � ������.
	 * @param c ������, ����������� ������� �����������.
	 * @param m ������, ����������� ������� �������.
	 */
	public BackpackCreature(int n, int num_of_use,
	BackpackCrossFunction c, BackpackMutationFunction m) {
		if (n > 100000) {
			n = 100000;
		}
		if (n < 2) {
			n = 2;
		}
		this.num_items = n;
		items = new int[num_items];
		for(int i = 0; i < num_items - 1; i++) {
			items[i] = 0;
		}
		if (num_of_use < 1) {
			num_of_use = 1;
		}
		if (num_of_use > 100000) {
			num_of_use = 100000;
		}
		this.num_of_use = num_of_use;
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
	public double fitness()	{
		return this.fit();
	}
	
	/**
	 * ������� ����������� �������� �������� - ������� - �� ����� ����� � ������.
	 * @param a �����, � ������� ���������� ���������.
	 */
	public void copyData(Creature a) {
		((BackpackCreature) a).num_items = this.num_items;
		((BackpackCreature) a).num_of_use = this.num_of_use;
		for (int i = 0; i < this.num_items; i++) {
			((BackpackCreature) a).items[i] = this.items[i];
		}
	}
	
	/**
	 * ������� ����������� ������� � ������������� ����������.
	 */
	public void regenerate() {
		for (int i=0; i<num_items; i++) {
			items[i] = items[i] % (num_of_use + 1);
		}
	}
	
	/**
	 * ������� ��������� ������������� ������ �������. 
	 */
	public void generate() {	
		items = new int[num_items];
		
		for (int i = 0; i < num_items; i++)
		{
			items[i] = (int) (Math.random() * 2 * num_of_use);
			items[i] = items[i] % (num_of_use + 1);
		}
	}
	
	/**
	 * ������� ����������� �����.
	 */
	public void cross(Creature cr) {
		c.cross(this, (BackpackCreature) cr);
	}
	
	/**
	 * ������� ������� �����.
	 */
	public void mutation() {
		m.mutate(this);
	}
	
	/**
	 * ������� ��������� ������ �������.
	 * @param item ��������������� �������� �����.
	 */
	public void setitems(int item[]) {
		for (int i = 0; i < num_items; i++) {
			this.items[i] = item[i];
		}
	}
	
	/**
	 * ������� ��������� ������ �������.
	 * @return ����� �������.
	 */
	public int[] getitems() {
		int[] tmp = new int[num_items];
		
		for (int i = 0; i < num_items; i++) {
			tmp[i] = items[i];
		}
		return tmp;
	}
	
	/**
	 * ������� ��������� ������� �������.
	 * @return ������ �������.
	 */
	public int getnum_items() {
		return this.num_items;
	}
	
	/**
	 * ������� ��������� ���������� ���������.
	 * @return ���������� ���������.
	 */
	public int getnum_of_use() {
		return this.num_of_use;
	}
}