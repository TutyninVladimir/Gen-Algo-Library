package genetics.population;

/**
 * ����� Selecting - ����������� ����� ��������� ��������.
 * @author Tutynin Vladimir
 */
public abstract class Stopping {
	/**
	 * ����������� �������. ��� ���������� ��������� �������� ���������� 
	 * ������� �����-���������, ������� ����� ��������� 
	 * ���������� ������ �������.
	 * @param n ����� ������ ���������.
	 * @param cr  ������ ������, �������������� �������� ���������.
	 * @return ���������� ���� boolean, �����������, ��������� �� ������� ��������.
	 */
	public abstract boolean isEnding(int n, Creature[] cr);
}