package example1;

/**
 * ����� GraphCreatureExample - ��������� ������ GraphCreature, 
 * ��������������� ��� ������������ ������� ������������� ������ ��� 
 * ������� ����������� ������. 
 * @author Tutynin Vladimir
 */
public class GraphCreatureExample extends GraphCreature {
	/**
	 * ����������� ������, ����������� ����������� ������ GraphCreature.
	 * @param n ������ �������.
	 * @param c ������, ����������� ������� �����������.
	 * @param m ������, ����������� ������� �������.
	 */
	public GraphCreatureExample(int n, GraphCrossFunction c, 
	GraphMutationFunction m) {
		super(n, c, m);
	}
	
	/**
	 * ���������� ����������� ������� fit ������ GraphCreature.
	 * @return �������� ������� �����������������.
	 */
	double fit() {
		//TO DO: write function.
		return 0;
	}
}