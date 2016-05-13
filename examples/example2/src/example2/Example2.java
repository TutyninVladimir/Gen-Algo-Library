package example2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import genetics.choosing.ChoosingRandom;
import genetics.population.Choosing;
import genetics.population.Population;
import genetics.population.Selecting;
import genetics.population.Stopping;
import genetics.selecting.SelectingMax;
import genetics.stopping.StoppingIterations;

public class Example2 {

	public static void main(String arg[]) {
		int num_layers = 0; 
		int size = 100;
		int iters = 100;
		int layers[] = null;
		int examples = 0;
		double ins[][] = null;
		double outs[][] = null;
		double test_ex[] = null;
		double ans_ex[] = null;
		String str2 = "input2.txt";
		
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		Option opt1 = new Option("s", "size", true, "Size");
		opt1.setArgs(1);
		Option opt2 = new Option("f", "file", true, "File");
		opt2.setArgs(1);
		Option opt3 = new Option("i", "iter", true, "Iterations");
		opt3.setArgs(1);
		options.addOption(opt1);
		options.addOption(opt2);
		options.addOption(opt3);
		
		try {
		    CommandLine line = parser.parse( options, arg );
		    if (line.hasOption("s")) {
		    	size = Integer.parseInt(line.getOptionValue("s"));
		    }
		    if (line.hasOption("f")) {
		    	str2 = line.getOptionValue("f");
		    }
		    if (line.hasOption("i")) {
		    	iters = Integer.parseInt(line.getOptionValue("i"));
		    }
		}
		catch( ParseException exp ) {
		    System.out.println( "Unexpected exception:" + exp.getMessage() );
		}
		
		File file = new File(str2);
		try {
			Scanner scan = new Scanner(file);
			num_layers = scan.nextInt();
			layers = new int[num_layers];
			for (int i = 0; i < num_layers; i++) {
				layers[i] = scan.nextInt();
			}
			examples = scan.nextInt();
			ins = new double[examples][layers[0]];
			outs = new double[examples][layers[num_layers - 1]];
			for (int i = 0; i < examples; i++) {
				for (int j = 0; j < layers[0]; j++) {
					ins[i][j] = scan.nextDouble();
				}
				for (int j = 0; j < layers[num_layers - 1]; j++) {
					outs[i][j] = scan.nextDouble();
				}
			}
			test_ex = new double[layers[0]];
			for (int i = 0; i < layers[0]; i++) {
				test_ex[i] = scan.nextDouble();
			}
			scan.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		NeuroCrossFunction cross2 = new NeuroCrossSimple();
		NeuroMutationFunction mutate2 = new NeuroMutationOneWeight();
		NeuroCreature cr2 = new NeuroCreatureSimple(num_layers, layers, cross2, mutate2);
		((NeuroCreatureSimple)cr2).init(num_layers, ins, outs);
		
		Choosing ch2 = new ChoosingRandom();
		Selecting sel2 = new SelectingMax();
		Stopping st2 = new StoppingIterations(iters);
		if (size<10) {
			size=10;
		}
		if (size>100000) {
			size=100000;
		}
		Population p2 = new Population(size, 0.4, 0.05, cr2, ch2, sel2, st2);
		p2.run();
		NeuroCreatureSimple ans2 = (NeuroCreatureSimple) p2.getAnswerCreature();
		ans_ex = ans2.solve(test_ex);
		System.out.print("Answer: ");
		for (int i = 0; i < layers[num_layers - 1]; i++) {
			System.out.print(ans_ex[i] + " ");
		}
	}
}
