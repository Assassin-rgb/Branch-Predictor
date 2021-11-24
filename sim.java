import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class sim {
	public static void main(String[] args) throws FileNotFoundException {
		String trace;
		int n_bits = 0;
		String model = args[0];
		int m_bits = Integer.parseInt(args[1]);
		System.out.println("\nCOMMAND");
		if (model.equals("gshare")) {
			n_bits = Integer.parseInt(args[2]);
			trace = args[3];
			String[] tr = trace.split("/");
			System.out.println("./sim " + model + " " + m_bits + " " + n_bits + " " + tr[tr.length - 1]);
		}
		else{
			trace = args[2];
			String[] tr = trace.split("/");
			System.out.println("./sim " + model + " " + m_bits + " " + tr[tr.length - 1]);
		}

		// read trace file
		Scanner trace_file = null;
		File file = new File(trace);
		trace_file = new Scanner(file);

		// simulator
		if (model.equals("smith")) {
			smith simulator = new smith(m_bits, trace_file);
			simulator.run();
			simulator.printresults();
		}
		else if (model.equals("bimodal")) {
			bimodal simulator = new bimodal(m_bits, trace_file);
			simulator.run();
			simulator.printresults();
		} else {
			gshare simulator = new gshare(m_bits, n_bits, trace_file);
			simulator.run();
			simulator.printresults();
		}
	}
}
