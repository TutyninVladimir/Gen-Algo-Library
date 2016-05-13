package genetics.simplecreature;

/**
 * ����� SimpleCrossFunction - ����������� ����� ��������� �����������.
 * @author Tutynin Vladimir
 */
public abstract class SimpleCrossFunction {
	/**
	 * ����������� �������. ��� ���������� ��������� ����������� ���������� 
	 * ������� �����-���������, ������� ����� ��������� 
	 * ���������� ������ �������.
	 * @param a ������ �����.
	 * @param b ������ �����.
	 */
	abstract void cross(SimpleCreature a, SimpleCreature b);
}
