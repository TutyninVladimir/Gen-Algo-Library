package example3;

/**
 * ����� BackpackCreatureExample - ��������� ������ BackpackCreature, 
 * ��������������� ��� ������������ ������� ������������� ������ ��� 
 * ������� ����������� ������. 
 * @author Tutynin Vladimir
 */
public class BackpackCreatureExample extends BackpackCreature {
	/**
	 * ����������� ������, ����������� ����������� ������ SimpleCreature.
	 * @param n ������ �������.
	 * @param num_of_use ������������ ���������� ��������� ������ ����, �������
	 * ����� ����� � ������.
	 * @param c ������, ����������� ������� �����������.
	 * @param m ������, ����������� ������� �������.
	 */
	public BackpackCreatureExample(int n, int num_of_use, 
	BackpackCrossFunction c, BackpackMutationFunction m) {
		super(n, num_of_use, c, m);
	}
	
	/**
	 * ���������� ����������� ������� fit ������ BackpackCreature.
	 * @return �������� ������� �����������������.
	 */
	double fit() {
		//TO DO: write function.
		return 0;
	}
}