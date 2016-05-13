package genetics.population;

/**
 * ����� Creature - ����������� ����� �����.
 * @author Tutynin Vladimir
 */
public abstract class Creature implements Cloneable {
	
	/**
	 * ������� ������������, ������������ ������� CreatureMachine ��� 
	 * �������� �����.
	 * @return ������������� �����.
	 */
	@Override
	public Creature clone() {
		Creature copy = null;
		try {
			copy = (Creature) super.clone();
		} catch (CloneNotSupportedException e) {
			// e.printStackTrace();
		}
		return copy;
	}
	
	/**
	 * ����������� ������� ���������� �������� �����������������. ������� �� 
	 * �������� ������. 
	 * @return �������� ������� �����������������.
	 */
	public abstract double fitness();

	/**
	 * ����������� ������� ����������� �������� �������� �� ����� ����� � ������.
	 * @param cr �����, � ������� ���������� ���������.
	 */
	public abstract void copyData(Creature cr);

	/**
	 * ����������� ������� �����������, ������� �� ��������� �������� �����. 	
	 * @param cr �����, � ������� ����� ������������ ������� �����.
	 */
	public abstract void cross(Creature cr);

	/**
	 * ����������� ������� �������, ������� �� ��������� �������� �����.
	 */
	public abstract void mutation();
	
	/**
	 * ����������� ������� ��������� ������������� �������� ��������,
	 * ������� �� ��������� �������� �����.
	 */
	public abstract void generate();
}