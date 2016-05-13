package example3;

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

public class Example3 {

	public static void main(String arg[]) {
		int items = 0; 
		int size = 100;
		int iters = 100;
		int max_weight = 0;
		int costs[] = null;
		int weights[] = null;
		String str3 = "input3.txt";
		
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
		    	str3 = line.getOptionValue("f");
		    }
		    if (line.hasOption("i")) {
		    	iters = Integer.parseInt(line.getOptionValue("i"));
		    }
		}
		catch( ParseException exp ) {
		    System.out.println( "Unexpected exception:" + exp.getMessage() );
		}
		
		File file = new File(str3);
		try {
			Scanner scan = new Scanner(file);
			items = scan.nextInt();
			max_weight = scan.nextInt();
			costs = new int[items];
			weights = new int[items];
			for (int i = 0; i < items; i++) {
				costs[i] = scan.nextInt();
			}
			for (int i = 0; i < items; i++) {
				weights[i] = scan.nextInt();
			}
			scan.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BackpackCrossFunction cross3 = new BackpackCrossSimple();
		BackpackMutationFunction mutate3 = new BackpackMutationOneItem();
		BackpackCreature cr3 = new BackpackCreatureSimple(items, 1, cross3, mutate3);
		((BackpackCreatureSimple)cr3).init(max_weight, costs, weights);
		
		Choosing ch3 = new ChoosingRandom();
		Selecting sel3 = new SelectingMax();
		Stopping st3 = new StoppingIterations(iters);
		if (size<10) {
			size=10;
		}
		if (size>100000) {
			size=100000;
		}
		Population p3 = new Population(size, 0.4, 0.05, cr3, ch3, sel3, st3);
		p3.run();
		BackpackCreatureSimple ans3 = (BackpackCreatureSimple) p3.getAnswerCreature();
		System.out.print("Answer: "+ ans3.fit());
	}
}
