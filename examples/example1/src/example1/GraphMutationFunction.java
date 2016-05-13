package example1;

/**
 * ����� GraphMutationFunction - ����������� ����� ��������� �������.
 * @author Tutynin Vladimir
 */
public abstract class GraphMutationFunction {
	/**
	 * ����������� �������. ��� ���������� ��������� ������� ���������� 
	 * ������� �����-���������, ������� ����� ��������� 
	 * ���������� ������ �������.
	 * @param a ���������� �����.
	 */
	abstract void mutate(GraphCreature a);
}