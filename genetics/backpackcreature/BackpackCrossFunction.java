package genetics.backpackcreature;

/**
 * ����� BackpackCrossFunction - ����������� ����� ��������� �����������.
 * @author Tutynin Vladimir
 */
public abstract class BackpackCrossFunction {
	/**
	 * ����������� �������. ��� ���������� ��������� ����������� ���������� 
	 * ������� �����-���������, ������� ����� ��������� 
	 * ���������� ������ �������.
	 * @param a ������ �����.
	 * @param b ������ �����.
	 */
	abstract void cross(BackpackCreature a, BackpackCreature b);
}