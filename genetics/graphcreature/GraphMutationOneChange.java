package genetics.graphcreature;

/**
 * ����� GraphMutationOneChange - ��������� ������ GraphMutationFunction, 
 * ����������� ������� ������� ����� ������������ ��� ����� 
 * � ���� ������� ��������������� ����� �� 1 �� n.
 * @author Tutynin Vladimir
 */
public class GraphMutationOneChange extends GraphMutationFunction {
	/**
	 * ���������� ��������� ������� ������ �������� ����� ��� ����� � ��������� 
	 * � ���� ������� ��������������� ����� �� 1 �� n.
	 * @param a ���������� �����.
	 */
	void mutate(GraphCreature a) {
		int m;
		int q;
		int[] olda;
		int tmp;
		
		m = a.getlength();
		q = (int) (Math.random() * (m - 1));
		olda = a.get();
		tmp = olda[q];
		olda[q] = olda[q + 1];
		olda[q + 1] = tmp;
		a.set(olda);
	}
}