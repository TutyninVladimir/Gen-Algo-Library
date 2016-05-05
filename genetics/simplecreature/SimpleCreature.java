package genetics.simplecreature;

import genetics.population.Creature;

/**
 * ����� SimpleCreature - ��������� ������ Creature, ����������� �������� ���
 * ������ � ������, ������� ������� ����������� � ���� ������ �����.
 * @author Tutynin Vladimir
 */
public abstract class SimpleCreature extends Creature {
	protected int bytes;
	protected int q;
	protected SimpleCrossFunction c;
	protected SimpleMutationFunction m;

	/**
	 * ����������� ������. ��� �������� ����� ����������� ����������� 
	 * ����� (�� 1 �� 31), � ����� �������, ����������� ���������
	 * ����������� � �������.
	 * @param bytes ����������� �����.
	 * @param c ������, ����������� ������� �����������.
	 * @param m ������, ����������� ������� �������.
	 */
	public SimpleCreature(int bytes, SimpleCrossFunction c, 
	SimpleMutationFunction m) {
		if (bytes > 31) {
			bytes = 31;
		}
		if (bytes < 1) {
			bytes = 1;
		}
		this.bytes = bytes;
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
	 * ������� ����������� �������� �������� - ����� - �� ����� ����� � ������.
	 * @param a �����, � ������� ���������� ���������.
	 */
	public void copyData(Creature a) {
		((SimpleCreature) a).bytes = this.bytes;
		((SimpleCreature) a).q = this.q;
	}

	/**
	 * ������� ��������� ������������� ����� � �������� ������������. 
	 */
	public void generate() {
		int tmp = 1;
		
		for (int i = 1; i <= this.bytes; i++) {
			tmp *= 2;
		}
		this.q = (int) (Math.random() * tmp);
	}

	/**
	 * ������� ����������� �����.
	 */
	public void cross(Creature cr) {
		c.cross(this, (SimpleCreature) cr);
	}

	/**
	 * ������� ������� �����.
	 */
	public void mutation() {
		m.mutate(this);
	}

	/**
	 * ������� ��������� ��������� ����� �����.
	 * @param q ��������������� �������� �����.
	 */
	public void set(int q) {
		int tmp = 1;
		
		for (int i = 1; i <= this.bytes; i++) {
			tmp *= 2;
		}
		if (q > tmp - 1) {
			q = tmp - 1;
		}
		if (q < 0) {
			q = 0;
		}
		this.q = q;
	}

	/**
	 * ������� ��������� ����� �����.
	 * @return ����� �����.
	 */
	public int get() {
		return this.q;
	}

	/**
	 * ������� ��������� ����� ��������.
	 * @return ����� ��������, ������������� ��� ������ �����.
	 */
	public int getbytes() {
		return this.bytes;
	}
}