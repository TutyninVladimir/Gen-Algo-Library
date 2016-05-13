package genetics.population;

/**
 * ����� Population - �������� ����� ����������, ��������������� ��� ������
 * � ���������� � ����������� ���� ������ ������������� ���������.
 * @author Tutynin Vladimir
 */
public class Population {
	final double eps = 0.000001;

	private int cnt;
	private double chooses;
	private double mutates;
	private Creature[] cr;
	private CreatureMachine cm;
	private Choosing ch;
	private Selecting sel;
	private Stopping st;
	private int[] a;
	private int turns;
	
	/**
	 * ����������� ������. ��� �������� ��������� ������������ �����-�������� � 
	 * �������, ���������� �� ���������� ��������� ������, ������ � ��������. 
	 * @param n ����� ������ � ���������.
	 * @param chooses ���� ������������ ������.
	 * @param mutates ���� ���������� ������.
	 * @param mutates ���� ���������� ������. 
	 * @param c �����-��������, ������� �������� ����� ������ (�������), � �����
	 * �������� ������ � �������: ����������� � �������.
	 * @param ch ������-��������� ������ Choosing, � ������� ����������� �������
	 * ������ ������ ��� ����������� crossing.
	 * @param sel ������-��������� ������ Selecting, � ������� ����������� �������
	 * ������ ������ select.
	 * @param st ������-��������� ������ Stopping, � ������� ����������� �������
	 * �������� ������� ��������� ��������� isEnding.
	 */
	public Population(int n, double chooses, double mutates, Creature c, 
	Choosing ch, Selecting sel, Stopping st) {
		if (n < 0) {
			n = 0;
		}
		if (n > 1048576) {
			n = 1048576;
		}
		this.cnt = n;
		cm = new CreatureMachine(c);
		cr = new Creature[2 * n + 1];
		a = new int[2 * n + 1];
		for (int i = 0; i < 2 * n; i++) {
			cr[i] = cm.makeCreature();
			cr[i].generate();
		}
		this.ch = ch;
		this.sel = sel;
		this.st = st;
		if (chooses < 0.0) {
			chooses = 0.0;
		} else if (chooses > 1.0) {
			chooses = 1.0;
		}
		if (mutates < 0.0) {
			mutates = 0.0;
		} else if (mutates > 1.0) {
			mutates = 1.0;
		}
		this.chooses = chooses;
		this.mutates = mutates;
	}
	
	/**
	 * ������� ���������� ���������� �������� ���������.
	 * @return ����� �������� � ���������� ������ ���������.
	 */
	public int getTurns() {
		return turns;
	}
	
	private void copyTo(int a, int b) {
		// Copy data from a to b
		cr[a].copyData(cr[b]);
		// cm.setCreature(cr[a]);
		// cr[b] = cm.makeCreature();
	}

	private void mutations(int n1, int n2) {
		double f = 0.0;
		int[] a = new int[n2 - n1];
		int tmp;
		int tmp2;

		for (int i = 0; i < (n2 - n1); i++) {
			a[i] = i;
		}
		for (int i = (n2 - n1) - 1; i >= 0; i--) {
			tmp = ((int) (Math.random() * (i + 1)));
			tmp2 = a[i];
			a[i] = a[tmp];
			a[tmp] = tmp2;
		}
		tmp = 0;
		while ((f + eps) < mutates && tmp < (n2 - n1)) {
			f += 1.0 / (n2 - n1);
			cr[a[tmp] + n1].mutation();
			tmp++;
		}
	}

	/**
	 * ������ ���������. ��� ���������� ������������ �������� ������
	 * ������, �����������, ������� � ������. �������� ����������� �� ��� ���,
	 * ���� �� ����� ��������� ������� ��������.
	 */
	public void run() {
		int n1 = cnt;
		int n2 = cnt;
		boolean flag = false;
		
		turns = 0;
		while (flag != true) {
			for (int i = 0; i < n1; i++) {
				a[i] = -1;
			}
			ch.crossing(chooses, n1, a, cr);
			for (int i = 0; i < n1; i++) {
				if (a[i] != -1 && a[i] != i) {
					copyTo(i, n2);
					n2++;
					copyTo(a[i], n2);
					n2++;
					a[a[i]] = -1;
					a[i] = -1;
				}
			}
			for (int i = n1; i < n2; i += 2) {
				cr[i].cross(cr[i + 1]);
			}
			if (n2 > n1)
				mutations(n1, n2);
			for (int i = 0; i < n2; i++)
				a[i] = 0;
			sel.select(n2, n1, a, cr);
			for (int i = n2 - 1; i >= 0; i--) {
				if (a[i] == 0 && n2 > n1) {
					if (i != n2 - 1) {
						copyTo(n2 - 1, i);
					}
					n2--;
				}
			}
			n2 = n1;
			turns++;
			flag = st.isEnding(n1, cr);
		}
	}

	/**
	 * ������� ���������� �������� ������� ����������������� � �����
	 * ��������������� ����� ���������.
	 * @return �������� ������� ����� ��������������� �����.
	 */	
	public double getAnswer() {
		double x = cr[0].fitness();
		double tmp;
		
		for (int i = 0; i < cnt; i++) {
			tmp = cr[i].fitness();
			if (tmp > x)
				x = tmp;
		}
		return x;
	}

	/**
	 * ������� ���������� ����� � ���������� ��������� �����������������.
	 * @return ����� ��������������� ����� ���������.
	 */	
	public Creature getAnswerCreature() {
		double x = cr[0].fitness();
		int q = 0;
		double tmp;
		
		for (int i = 0; i < cnt; i++) {
			tmp = cr[i].fitness();
			if (tmp > x) {
				x = tmp;
				q = i;
			}
		}
		return cr[q];
	}

	/**
	 * ��������� ������������ ���������. ��� ������ ����� ��������� ������������
	 * ����� ���������� �������� ��������.
	 */	
	public void generateCreatures() {
		for (int i = 0; i < cnt; i++) {
			cr[i].generate();
		}
	}
}

/**
 * ����� CreatureMachine - ��������������� �����, ������������ ��� ��������
 * ��������� ���� ������������ �����-���������.
 * @author Tutynin Vladimir
 */
class CreatureMachine {
	private Creature cr;

	/**
	 * ����������� ������, ��� �������� ���������� ���������� �����-��������. 
	 * @param cr �����-��������.
	 */
	public CreatureMachine(Creature cr) {
		this.cr = cr;
	}

	/**
	 * ��������� �����-���������. 
	 * @param cr �����-��������.
	 */
	public void setCreature(Creature cr) {
		this.cr = cr;
	}

	/**
	 * ������� ������������ �����. 
	 * @return ����� �����.
	 */
	public Creature makeCreature() {
		return (Creature) this.cr.clone();
	}
}