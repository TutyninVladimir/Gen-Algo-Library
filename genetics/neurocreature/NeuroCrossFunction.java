package genetics.neurocreature;

/**
 * ����� NeuroCrossFunction - ����������� ����� ��������� �����������.
 * @author Tutynin Vladimir
 */
public abstract class NeuroCrossFunction {
	/**
	 * ����������� �������. ��� ���������� ��������� ����������� ���������� 
	 * ������� �����-���������, ������� ����� ��������� 
	 * ���������� ������ �������.
	 * @param a ������ �����.
	 * @param b ������ �����.
	 */
	abstract void cross(NeuroCreature a, NeuroCreature b);
}