import java.util.*;
import java.io.*;

public class BaseballStats {

	public static String round(double val) {
        String str = val + "";
        if (str.length() <= 5) return str.substring(1, str.length()) + "00000".substring(0, 5 - str.length());
        if ((int) (str.charAt(5)) - 48 >= 5) {
        	val += 0.001;
        	str = val + "";
        }
        return str.substring(1, 5);
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/stats"));
		in.useDelimiter("\\s");
		double[] out = new double[3];
		double[] single = new double[3];
		double[] doubles = new double[3];
		double[] triple = new double[3];
		double[] home = new double[3];
		double[] strike = new double[3];
		double[] walk = new double[3];
		double[] hit = new double[3];
		double[] sacrifice = new double[3];
		double highAvg = 0.0, slugPer = 0.0, onPer = 0.0;
		ArrayList<Integer> avgInd = new ArrayList<Integer>();
		ArrayList<Integer> batInd = new ArrayList<Integer>();
		ArrayList<Integer> slugInd = new ArrayList<Integer>();
		ArrayList<Integer> onInd = new ArrayList<Integer>();
		ArrayList<Integer> baseInd = new ArrayList<Integer>();
		avgInd.add(0);
		batInd.add(0);
		slugInd.add(0);
		onInd.add(0);
		baseInd.add(0);
		int leastBat = 100000, totBase = 100000;
		for (int i = 0; i < 3; i++) {
			out[i] = in.nextDouble();
			single[i] = in.nextDouble();
			doubles[i] = in.nextDouble();
			triple[i] = in.nextDouble();
			home[i] = in.nextDouble();
			strike[i] = in.nextDouble();
			walk[i] = in.nextDouble();
			hit[i] = in.nextDouble();
			sacrifice[i] = in.nextDouble();
			in.nextLine();
			if ((single[i] + doubles[i] + triple[i] + home[i]) / (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i]) >= highAvg) {
				if ((single[i] + doubles[i] + triple[i] + home[i]) / (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i]) > highAvg) {
					avgInd.remove(0);
				}
				highAvg = (single[i] + doubles[i] + triple[i] + home[i]) / (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i]);
				avgInd.add(i);
			}
			if (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i] <= leastBat) {
				if ((int) (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i]) < leastBat) {
					batInd.remove(0);
				}
				leastBat = (int) (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i]);
				batInd.add(i);
			}
			if ((single[i] + 2 * doubles[i] + 3 * triple[i] + 4 * home[i]) / (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i]) >= slugPer) {
				if ((single[i] + 2 * doubles[i] + 3 * triple[i] + 4 * home[i]) / (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i]) > slugPer) {
					slugInd.remove(0);
				}
				slugPer = (single[i] + 2 * doubles[i] + 3 * triple[i] + 4 * home[i]) / (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i]);
				slugInd.add(i);
			}
			if ((single[i] + doubles[i] + triple[i] + home[i] + walk[i] + hit[i]) / (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i] + walk[i] + hit[i] + sacrifice[i]) >= onPer) {
				if ((single[i] + doubles[i] + triple[i] + home[i] + walk[i] + hit[i]) / (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i] + walk[i] + hit[i] + sacrifice[i]) > onPer) {
					onInd.remove(0);
				}
				onPer = (single[i] + doubles[i] + triple[i] + home[i] + walk[i] + hit[i]) / (out[i] + single[i] + doubles[i] + triple[i] + home[i] + strike[i] + walk[i] + hit[i] + sacrifice[i]);
				onInd.add(i);
			}
			if (single[i] + 2 * doubles[i] + 3 * triple[i] + 4 * home[i] <= totBase) {
				if ((int) (single[i] + 2 * doubles[i] + 3 * triple[i] + 4 * home[i]) < totBase) {
					baseInd.remove(0);
				}
				totBase = (int) (single[i] + 2 * doubles[i] + 3 * triple[i] + 4 * home[i]);
				baseInd.add(i);
			}
		}
		for (int i = 0; i < avgInd.size(); i++) {
			System.out.print((char) (avgInd.get(i) + 65) + ", ");
		}
		System.out.println(round(highAvg));
		for (int i = 0; i < batInd.size(); i++) {
			System.out.print((char) (batInd.get(i) + 65) + ", ");
		}
		System.out.println(leastBat);
		for (int i = 0; i < slugInd.size(); i++) {
			System.out.print((char) (slugInd.get(i) + 65) + ", ");
		}
		System.out.println(round(slugPer));
		for (int i = 0; i < onInd.size(); i++) {
			System.out.print((char) (onInd.get(i) + 65) + ", ");
		}
		System.out.println(round(onPer));
		for (int i = 0; i < baseInd.size(); i++) {
			System.out.print((char) (baseInd.get(i) + 65) + ", ");
		}
		System.out.println(totBase);
		in.close();
	}

}