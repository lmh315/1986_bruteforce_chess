package q1986;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, m, cnt_safe;
	static int num_Q, num_K, num_P;
	static int[] r_Q, c_Q;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		int i, j;
		int r, c;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		st = new StringTokenizer(in.readLine());
		num_Q = Integer.parseInt(st.nextToken());
		r_Q = new int[num_Q];
		c_Q = new int[num_Q];
		for (i = 0; i < num_Q; i++) {
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			r_Q[i] = r;
			c_Q[i] = c;
		}

		st = new StringTokenizer(in.readLine());
		num_K = Integer.parseInt(st.nextToken());
		for (i = 0; i < num_K; i++) {
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = 1;
			move_knight(r, c);
		}

		st = new StringTokenizer(in.readLine());
		num_P = Integer.parseInt(st.nextToken());
		for (i = 0; i < num_P; i++) {
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = 1;
		}

		for (i = 0; i < num_Q; i++) {
			map[r_Q[i]][c_Q[i]] = 1;
		}

		for (i = 0; i < num_Q; i++) {
			move_queen(r_Q[i], c_Q[i]);
		}

		for (i = 0; i < n; i++)
			System.out.println(Arrays.toString(map[i]));

		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (map[i][j] == 0)
					cnt_safe++;
			}
		}

		out.write(String.valueOf(cnt_safe));
		out.flush();

		in.close();
		out.close();
	}

	public static void move_queen(int r, int c) {
		int i, j;
		int aR, aC;
		int[] dR = { -1, 1, 0, 0, -1, -1, 1, 1 };
		int[] dC = { 0, 0, -1, 1, 1, -1, 1, -1 };

		for (i = 0; i < 8; i++) {
			aR = r;
			aC = c;
			while (true) {
				aR += dR[i];
				aC += dC[i];
				if (!isRange(aR, aC))
					break;
				if (map[aR][aC] == 1)
					break;
				map[aR][aC] = 2;
			}
		}
	}

	public static void move_knight(int r, int c) {
		int i, j;
		int aR, aC;
		int[] dR = { -2, -2, -1, -1, 1, 1, 2, 2 };
		int[] dC = { -1, 1, -2, 2, -2, 2, -1, 1 };

		for (i = 0; i < 8; i++) {
			aR = r + dR[i];
			aC = c + dC[i];

			if (!isRange(aR, aC))
				continue;
			if (map[aR][aC] == 1)
				continue;
			map[aR][aC] = 2;
		}
	}

	public static boolean isRange(int x, int y) {
		if (x < 0 || x >= n || y < 0 || y >= m)
			return false;
		return true;
	}
}
