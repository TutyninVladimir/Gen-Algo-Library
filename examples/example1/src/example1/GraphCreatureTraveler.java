package example1;

/**
 * ����� GraphCreatureTraveler - ��������� ������ GraphCreature, 
 * ��������������� ��� ������� ������ �����������. 
 * @author Tutynin Vladimir
 */
public class GraphCreatureTraveler extends GraphCreature {
	private double map[][];
	private double mx;
	
	/**
	 * ����������� ������, ��������� ������������ ������ GraphCreature.
	 * @param n ������ �������.
	 * @param c ������, ����������� ������� �����������.
	 * @param m ������, ����������� ������� �������.
	 */
	public GraphCreatureTraveler(int n, GraphCrossFunction c, 
	GraphMutationFunction m) {
		super(n, c, m);
	}
	
	/**
	 * ������������� �����.
	 * @param n ������ �������.
	 * @param map ������� ���������� ����� ���������.
	 */
	public void init(int n, double map[][]) {
		double tmp;
		
		this.map = new double[n][n];
		mx = 0.0;
		for (int i = 0; i < n; i++) {
			tmp = 0.0;
			for (int j = 0; j < n; j++) {
				this.map[i][j] = map[i][j];
				if (map[i][j] > tmp) {
					tmp = map[i][j];
				}
			}
			mx += tmp;
		}
	}

	/**
	 * ������� ���������� �����������������, ������� ���������� ��� ��������
	 * ����� ������������� ���������� ����� � ����������� ������� ��������.
	 * @return �������� �����������������.
	 */
	double fit() {
		int i;
		double sum = 0.0;
		
		for (i = 0; i < n - 1; i++) {
			sum += map[a[i]][a[i + 1]];
		}
		sum += map[a[n - 1]][a[0]];
		return mx - sum;
	}

	/**
	 * ������� ���������� ���������� �� �������� ������ �����.
	 * @return ���������� �� ������� ��������.
	 */
	public double getAns() {
		int i;
		double sum = 0.0;
		
		for (i = 0; i < n - 1; i++) {
			sum += map[a[i]][a[i + 1]];
		}
		sum += map[a[n - 1]][a[0]];
		return sum;
	}

	/**
	 * ������� ��������� �������� �����.
	 * @return ������� �����.
	 */
	public int[] doPath() {
		return a;
	}
}