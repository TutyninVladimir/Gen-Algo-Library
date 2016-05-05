package genetics.backpackcreature;

/**
 * ����� BackpackMutationFunction - ����������� ����� ��������� �������.
 * @author Tutynin Vladimir
 */
public abstract class BackpackMutationFunction {
	/**
	 * ����������� �������. ��� ���������� ��������� ������� ���������� 
	 * ������� �����-���������, ������� ����� ��������� 
	 * ���������� ������ �������.
	 * @param a ���������� �����.
	 */
	abstract void mutate(BackpackCreature a);
}