package genetics.graphcreature;

/**
 * ����� GraphCreaturePainting - ��������� ������ GraphCreature, 
 * ��������������� ��� ������� ������ ��������� �����. 
 * @author Tutynin Vladimir
 */
public class GraphCreaturePainting extends GraphCreature {
	private boolean map[][];
	
	/**
	 * ����������� ������, ��������� ������������ ������ GraphCreature.
	 * @param n ������ �������.
	 * @param c ������, ����������� ������� �����������.
	 * @param m ������, ����������� ������� �������.
	 */
	public GraphCreaturePainting(int n, GraphCrossFunction c, 
	GraphMutationFunction m) {
		super(n, c, m);
	}

	/**
	 * ������������� ����� � ���� ������� ���������.
	 * @param n ������ �������.
	 * @param map ������� ���������. 
	 * 1 - ���� ����� ����� ���������, 0 - ��� �����.
	 */
	public void init(int n, boolean map[][]) {
		this.map = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.map[i][j] = map[i][j];
			}
		}
	}

	/**
	 * ������� ���������� �����������������, ������� ���������� ��� ��������
	 * ����� �������� ������� � ������ ������.
	 * @return �������� �����������������.
	 */
	double fit() {
		int[] colors = new int[n + 1];
		boolean[] use = new boolean[n + 1];
		int num;

		for (int i = 0; i < n; i++) {
			colors[i] = 0;
		}
		num = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= num; j++) {
				use[i] = true;
			}
			for (int j = 0; j < i; j++) {
				if (map[a[i]][a[j]] == true) {
					use[colors[j]] = false;
				}
			}
			for (int j = 1; j <= num; j++) {
				if (colors[i] == 0 && use[j] == true) {
					colors[i] = j;
				}
			}
			if (colors[i] == 0) {
				num++;
				colors[i] = num;
			}
		}
		return n - num;
	}

	/**
	 * ������� ���������� ����� ������, �������� �������������� ���� ���
	 * ���������� ������� ��������� ��������� ��� �������� ������� ������.
	 * @return ����� ������.
	 */
	public int getAns() {
		int[] colors = new int[n + 1];
		boolean[] use = new boolean[n + 1];
		int num;

		for (int i = 0; i < n; i++)
			colors[i] = 0;
		num = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= num; j++) {
				use[i] = true;
			}
			for (int j = 0; j < i; j++) {
				if (map[a[i]][a[j]] == true) {
					use[colors[j]] = false;
				}
			}
			for (int j = 1; j <= num; j++) {
				if (colors[i] == 0 && use[j] == true) {
					colors[i] = j;
				}
			}
			if (colors[i] == 0) {
				num++;
				colors[i] = num;
			}
		}
		return num;
	}

	/**
	 * ������� ���������� ��������� ����� ���
	 * ���������� ������� ��������� ��������� ��� �������� ������� ������.
	 * @return ������ ���������.
	 */
	int[] doPaint() {
		int[] colors = new int[n];
		boolean[] use = new boolean[n];
		int num;
		
		for (int i = 0; i < n; i++) {
			colors[i] = 0;
		}
		num = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= num; j++) {
				use[i] = true;
			}
			for (int j = 0; j < i; j++) {
				if (map[a[i]][a[j]] == true) {
					use[colors[j]] = false;
				}
			}
			for (int j = 1; j <= num; j++) {
				if (colors[i] == 0 && use[j] == true) {
					colors[i] = j;
				}
			}
			if (colors[i] == 0) {
				num++;
				colors[i] = num;
			}
		}
		return colors;
	}
}