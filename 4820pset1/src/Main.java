import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

	public static void main(String args[]) {

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			int n = Integer.parseInt(in.readLine());
			int[][] men = new int[n][n];
			int[][] women = new int[n][n];
			int[][] Ranking = new int[n][n];
			LinkedList<Integer> free = new LinkedList<Integer>();
			LinkedList<Integer> free2 = new LinkedList<Integer>();
			int[] Next = new int[n];
			int[] current = new int[n];
			int[] Next2 = new int[n];
			int[] current2 = new int[n];
			for (int i = 0; i < n; i++) {
				Next[i] = 0;
				current[i] = -1;
				Next2[i] = 0;
				current2[i] = -1;
			}
			Next2[0] = 1;

			for (int i = 0; i < 2 * n; i++) {
				String s = in.readLine();
				String[] empty = new String[1];
				empty[0] = "";
				String[] ints = s.split(" ");
				if (i < n && ints != empty) {
					for (int x = 0; x < n; x++) {
						int woman = Integer.parseInt(ints[x]);
						men[i][x] = woman;
					}
					free.add(i);
					free2.add(i);
				} else if (ints != empty) {
					for (int y = 0; y < n; y++) {
						women[i - n][y] = Integer.parseInt(ints[y]);
					}
				} else
					i -= 1;
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					Ranking[i][women[i][j]] = j;
				}
			}
			int wc = men[free.peek()][0];
			while (free.size() > 0) {
				int fm = free.peek();
				int w = men[fm][Next[fm]];
				Next[fm] += 1;
				int m1 = current[w];
				if (m1 == -1) {
					free.remove(0);
					current[w] = fm;
				} else if (Ranking[w][m1] > Ranking[w][fm]) {
					free.remove(0);
					free.add(0, m1);
					current[w] = fm;
				}
			}
			while (free2.size() > 0) {
				int fm = free2.peek();
				int w = men[fm][Next2[fm]];
				Next2[fm]++;
				int m1 = current2[w];
				if (m1 == -1) {
					free2.remove(0);
					current2[w] = fm;
				} else if (Ranking[w][m1] > Ranking[w][fm]) {
					free2.remove(0);
					free2.add(0, m1);
					current2[w] = fm;
				}
				if (Next2[fm]>=n) break;
			}
			System.out.println(current[0]);
			int r;
			int result = current2[wc];
			if (result == -1)
				r = 1;
			else if (result == current[wc])
				r = 3;
			else
				r = 2;
			System.out.println(r);
			// System.out.println("Men array is:");
			// printArr(men);
			// System.out.println("Women array is:");
			// printArr(women);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	private static void printArr(int[][] x) {
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				System.out.print(x[i][j] + " ");
			}
			System.out.println();
		}
	}
}