import java.util.Arrays;
import java.util.Scanner;

public class gshare {
    Scanner trace_file;
    int index_bits;
    int xor_bits;
    int[] gshare;
    String ghr = "";
    int high = 7;
    int low = 0;
    int mid = 4;
    int total;
    int miss = 0;

    // constructor
    public gshare(int m_bits, int n_bits, Scanner trace) {
        trace_file = trace;
        index_bits = m_bits;
        xor_bits = n_bits;
        gshare = new int[(int) Math.pow(2, m_bits)];
        Arrays.fill(gshare, mid);
        for (int i=0; i<n_bits; i++){
            ghr += "0";
        }
    }

    // perform xor
    public String xor(String a, String b) {
        String[] a1 = a.split("");
        String[] b1 = b.split("");
        int len = a.length();
        String out = "";
        int i = 0;
        while (i < len) {
            if (a1[i].equals(b1[i])) {
                out += "0";
            } else {
                out += "1";
            }
            i++;
        }
        return out;
    }

    // extract index
    public int getIndex(String address) {
        int dec = Integer.parseInt(address, 16);
        String bin = Integer.toBinaryString(dec);
        String ind1 = bin.substring(bin.length()-2-index_bits, bin.length()-2-xor_bits);
        String temp = bin.substring(bin.length()-2-xor_bits, bin.length()-2);
        String ind2 = xor(temp, ghr);
        String ind = ind1 + ind2;
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
            if (gshare[index] < mid){
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
                ghr = "1" + ghr;
                ghr = ghr.substring(0, ghr.length()-1);
                if (gshare[index] != high) {
                    gshare[index]++;
                }
            } else {
                ghr = "0" + ghr;
                ghr = ghr.substring(0, ghr.length()-1);
                if (gshare[index] != low) {
                    gshare[index]--;
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
        System.out.println("FINAL GSHARE CONTENTS");
        for (int i=0; i<gshare.length; i++) {
            System.out.println(i + "\t" + gshare[i]);
        }
    }
}
