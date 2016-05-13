package genetics.simplecreature;

/**
 * ����� SimpleCreatureExample - ��������� ������ SimpleCreature, 
 * ��������������� ��� ������������ ������� ������������� ������ ��� 
 * ������� ����������� ������. 
 * @author Tutynin Vladimir
 */
public class SimpleCreatureExample extends SimpleCreature {

	/**
	 * ����������� ������, ����������� ����������� ������ SimpleCreature.
	 * @param bytes ����������� �����.
	 * @param c ������, ����������� ������� �����������.
	 * @param m ������, ����������� ������� �������.
	 */
	public SimpleCreatureExample(int bytes, SimpleCrossFunction c, 
	SimpleMutationFunction m) {
		super(bytes, c, m);
	}
	
	/**
	 * ���������� ����������� ������� fit ������ SimpleCreature. � ������ ������
	 * ������������ ������� y=x^2+2*x+1, ��� ����� ���� �������� �� �������, 
	 * ����������� ������������.
	 * @return �������� ������� �����������������.
	 */
	double fit() {
		// TO DO: write function.
		double f = super.q * super.q + super.q * 2 + 1;
		return f;
	}
}