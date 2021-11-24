import java.util.Scanner;


public class smith {
    Scanner trace_file;
    int counter;
    int high;
    int low = 0;
    int mid;
    int total = 0;
    int miss = 0;

    // constructor
    public smith(int m_bits, Scanner trace) {
        trace_file = trace;
        high = (int) (Math.pow(2,m_bits) - 1);
        counter = (high + 1) / 2;
        mid = counter;
    }

    // run simulator
    public void run() {
        while (trace_file.hasNextLine()) {
            total++;
            String[] line = trace_file.nextLine().split(" ");
            String actual = line[1];
            String predicted;

            // getting prediction
            if (counter < mid){
                predicted = "n";
            } else {
                predicted = "t";
            }

            // checking prediction
            if (!actual.equals(predicted)){
                miss++;
            }

            // updating counter
            if (actual.equals("t")) {
                if (counter != high) {
                    counter++;
                }
            } else {
                if (counter != low) {
                    counter--;
                }
            }
        }
    }

    // print final results
    public void printresults() {
        System.out.println("OUTPUT");
        System.out.println("number of predictions:\t\t" + total);
        System.out.println("number of mispredictions:\t" + miss);
        double missrate =  Math.round(((double)miss/(double)total) * 10000.0) / 100.0;
        System.out.println("misprediction rate:\t\t" + missrate + "%");
        System.out.println("FINAL COUNTER CONTENT    : " + counter);
    }
}
