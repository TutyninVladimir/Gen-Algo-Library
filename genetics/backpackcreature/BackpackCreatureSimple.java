package genetics.backpackcreature;

/**
 * Класс BackpackCreatureSimple - наследник класса SimpleCreature, 
 * предназначенный для решения задачи о ранце. 
 * @author Tutynin Vladimir
 */
public class BackpackCreatureSimple extends BackpackCreature {
	public int max_weight;
	public int costs[];
	public int weights[];
	
	/**
	 * Конструктор класса, идентичен конструктору класса BackpackCreature.
	 * @param n Размер массива.
	 * @param num_of_use Максимальное количество предметов одного типа, которые
	 * можно взять в рюкзак.
	 * @param c Объект, реализующий функцию скрещивания.
	 * @param m Объект, реализующий функцию мутации.
	 */
	public BackpackCreatureSimple(int n, int num_of_use, 
	BackpackCrossFunction c, BackpackMutationFunction m) {
		super(n, num_of_use, c, m);
	}
	
	/**
	 * Инициализация начальных данных задачи: максимальной вместимости рюкзака и
	 * характеристик предметов. 
	 * @param max_weight Максимальная вместимость рюкзака.
	 * @param cost Массив стоимостей предметов.
	 * @param weight Массив весов предметов.
	 */
	public void init(int max_weight, int cost[], int weight[]) {
		costs = new int[num_items];
		weights = new int[num_items];
		if (max_weight < 0) {
			max_weight = 0;
		}
		this.max_weight = max_weight;
		for (int i = 0; i < num_items; i++) {
			costs[i] = cost[i];
			weights[i] = weight[i];
		}
	}

	/**
	 * Функция вычисления приспособленности, которая выражается как суммарная
	 * стоимость набора.
	 * @return Значение приспособленности.
	 */
	double fit() {
		int weight = 0;
		int tmp = 0;
		int t_items[] = new int[num_items];
		int need_drop = 0;
		int sum=0;
		
		for (int i = 0; i < num_items; i++) {
			t_items[i] = items[i];
			weight += weights[i] * t_items[i];
		}
		need_drop = weight - max_weight;
		tmp = 0;
		while (need_drop > 0 && tmp < num_items) {
			if (t_items[tmp] > 0) {
				t_items[tmp]--;
				need_drop -= weights[tmp];
			}
			if (t_items[tmp] == 0) {
				tmp++;
			}
		}
		sum = 0;
		weight = 0;
		for (int i = 0; i < num_items; i++)
		{
			weight += weights[i] * t_items[i];
			sum += costs[i] * t_items[i];
		}
		if (weight <= max_weight) {
			return sum;
		}
		return 0;
	}
	
	/**
	 * Функция вычисления набора предметов, с исключением первых премдетов в 
	 * случае превышения максимума. 
	 * @return Набор предметов особи.
	 */
	int[] getanswer() {
		int weight = 0;
		int t_items[];
		int tmp;
		
		t_items = new int[num_items];
		for (int i = 0; i < num_items; i++) {
			t_items[i] = items[i];
			weight += weights[i] * t_items[i];
		}
		int need_drop = weight - max_weight;
		tmp = 0;
		while(need_drop > 0 && tmp < num_items)	{
			if (t_items[tmp] > 0) {
				t_items[tmp]--;
				need_drop -= weights[tmp];
			}
			if (t_items[tmp] == 0) {
				tmp++;
			}
		}
		return t_items;
	}
}