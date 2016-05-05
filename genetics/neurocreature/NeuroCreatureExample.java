package genetics.neurocreature;

/**
 * ����� NeuroCreatureExample - ��������� ������ NeuroCreature, 
 * ��������������� ��� ������������ ������� ������������� ������ ��� 
 * ������� ����������� ������. 
 * @author Tutynin Vladimir
 */
public class NeuroCreatureExample extends NeuroCreature {
	/**
	 * ����������� ������, ����������� ����������� ������ NeuroCreature.
	 * @param n ����� ����.
	 * @param layers ����� �������� ������� ����.
	 * @param c ������, ����������� ������� �����������.
	 * @param m ������, ����������� ������� �������.
	 */
	public NeuroCreatureExample(int n, int layers[], 
	NeuroCrossFunction c, NeuroMutationFunction m) {
		super(n, layers, c, m);
	}

	/**
	 * ���������� ����������� ������� fit ������ NeuroCreature.
	 * @return �������� ������� �����������������.
	 */
	double fit() {
		// TO DO: write function.
		return 0;
	}
}