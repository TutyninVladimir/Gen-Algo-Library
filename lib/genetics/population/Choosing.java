package genetics.population;

/**
 * ����� Choosing - ����������� ����� ��������� ������ ������ ��� �����������
 * @author Tutynin Vladimir
 */
public abstract class Choosing {
	/**
	 * ����������� �������. ��� ���������� ��������� ������ ���������� 
	 * ������� �����-���������, ������� ����� ��������� 
	 * ���������� ������ �������.
	 * @param f ���� ������������ ������.
	 * @param n ����� ������ � ���������.
	 * @param use ������, � ������� �����������, � ����� ������ ����� 
	 * ������������ i-� �����, -1 - ����� �� ��������� � �����������.
	 * @param cr ������ ������, �������������� �������� ���������.
	 */	
	public abstract void crossing(double f, int n, int[] use, Creature[] cr);
}
