package genetics.simplecreature;

/**
 * ����� SimpleMutationFunction - ����������� ����� ��������� �������.
 * @author Tutynin Vladimir
 */
public abstract class SimpleMutationFunction {
	/**
	 * ����������� �������. ��� ���������� ��������� ������� ���������� 
	 * ������� �����-���������, ������� ����� ��������� 
	 * ���������� ������ �������.
	 * @param a ���������� �����.
	 */
	abstract void mutate(SimpleCreature a);
}