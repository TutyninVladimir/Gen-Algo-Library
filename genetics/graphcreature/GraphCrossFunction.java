package genetics.graphcreature;

/**
 * ����� GraphCrossFunction - ����������� ����� ��������� �����������.
 * @author Tutynin Vladimir
 */
public abstract class GraphCrossFunction {
	/**
	 * ����������� �������. ��� ���������� ��������� ����������� ���������� 
	 * ������� �����-���������, ������� ����� ��������� 
	 * ���������� ������ �������.
	 * @param a ������ �����.
	 * @param b ������ �����.
	 */
	abstract void cross(GraphCreature a, GraphCreature b);
}