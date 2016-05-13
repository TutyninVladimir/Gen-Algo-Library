package genetics.stopping;

import genetics.population.*;

/**
 * ����� StoppingIterations - ��������� ������ Stopping, ����������� ������� 
 * �������� ����� ��������� ����� �������� ���������.
 * @author Tutynin Vladimir
 */
public class StoppingIterations extends Stopping {
	int turns;
	int turn;
	
	/**
	 * ����������� ������. ��� �������� ����������� ����� �������� ���������,
	 * ����� �������� �������� ��������� ������.
	 * @param turns ����� ��������.
	 */
	public StoppingIterations(int turns) {
		if (turns < 1) {
			turns = 1;
		}
		this.turns = turns;
		this.turn = 0;
	}
	
	/**
	 * ������� �������� ������� ��������.
	 * @param n ����� ������ ���������.
	 * @param cr ������ ������, �������������� �������� ���������.
	 * @return ���������� ���� boolean, �����������, ��������� �� ������� ��������.
	 */
	public boolean isEnding(int n, Creature[] cr) {
		this.turn++;
		if (this.turn >= this.turns) {
			return true;
		}
		return false;
	}

	/**
	 * ������������� ����� �������� ��� ��������� ��������.
	 * @param x ��������������� ����� ��������.
	 */
	public void setTurns(int x) {
		if (x < 1) {
			x = 1;
		}
		this.turns = x;
		this.turn = 0;
	}

	/**
	 * �������, ������������ �������� ����� �������� ���������.
	 * @return ������������� ����� ��������.
	 */
	public int getTurns() {
		return this.turns;
	}
}