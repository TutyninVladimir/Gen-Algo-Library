package genetics.neurocreature;

/**
 * ����� GraphMutationFunction - ����������� ����� ��������� �������.
 * @author Tutynin Vladimir
 */
public abstract class NeuroMutationFunction {
	/**
	 * ����������� �������. ��� ���������� ��������� ������� ���������� 
	 * ������� �����-���������, ������� ����� ��������� 
	 * ���������� ������ �������.
	 * @param a ���������� �����.
	 */
	abstract void mutate(NeuroCreature a);
}