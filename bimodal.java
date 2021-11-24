import java.util.Scanner;
import java.util.Arrays;

public class bimodal {
    Scanner trace_file;
    int index_bits;
    int[] bimodal;
    int high = 7;
    int low = 0;
    int mid = 4;
    int total;
    int miss = 0;

    // constructor
    public bimodal(int m_bits, Scanner trace) {
        trace_file = trace;
        index_bits = m_bits;
        bimodal = new int[(int) Math.pow(2, m_bits)];
        Arrays.fill(bimodal, mid);
    }

    // extract index
    public int getIndex(String address) {
        int dec = Integer.parseInt(address, 16);
        String bin = Integer.toBinaryString(dec);
        String ind = bin.substring(bin.length()-2-index_bits, bin.length()-2);
        int index = Integer.parseInt(ind,2);
        return index;
    }

    // run simulator
    public void run() {
        while (trace_file.hasNextLine()) {
            total++;
            String predicted;
            String[] line = trace_file.nextLine().split(" ");
            int index = getIndex(line[0]);
            String actual = line[1];

            // getting prediction
            if (bimodal[index] < mid){
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
                if (bimodal[index] != high) {
                    bimodal[index]++;
                }
            } else {
                if (bimodal[index] != low) {
                    bimodal[index]--;
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
        System.out.println("FINAL BIMODAL CONTENTS");
        for (int i=0; i<bimodal.length; i++) {
            System.out.println(i + "\t" + bimodal[i]);
        }
    }
}
