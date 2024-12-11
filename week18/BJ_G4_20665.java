package week18;

import java.io.*;
import java.util.*;

/** 20665. 독서실 거리두기
 * 구현
 *
 * 1%에서 틀렸습니다.
 */
public class BJ_G4_20665 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N, T, P;
	static int[][] reservations;
	static List<Person> persons;
	static int count;

	public static void main(String[] args) throws Exception {
		init();
		solve();
		output();
	}

	static class Person implements Comparable<Person> {
		int number;
		int startIndex;
		int endIndex;

		Person(int number, String start, String end) {
			this.number = number;
			this.startIndex = (Integer.parseInt(start.substring(0,2)) - 9) * 60 + Integer.parseInt(start.substring(2, 4));
			this.endIndex = (Integer.parseInt(end.substring(0, 2)) - 9) * 60 + Integer.parseInt(end.substring(2, 4));
		}

		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.startIndex, o.startIndex);
		}
	}

	public static void init() throws Exception {
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		T = Integer.parseInt(inputs[1]);
		P = Integer.parseInt(inputs[2]);

		reservations = new int[N][720];
		persons = new ArrayList<>();

		for (int i = 0; i < T; i++) {
			inputs = br.readLine().split(" ");
			persons.add(new Person(i, inputs[0], inputs[1]));
		}
	}

	private static void solve() {
		Collections.sort(persons);

		for (Person p : persons) {
			int index = findPriorityIndex(p.startIndex);

			for (int i = p.startIndex; i < p.endIndex; i++) {
				reservations[index][i] = p.number;
			}
		}

		for (int time : reservations[P - 1]) {
			if (time == 0) {
				count++;
			}
		}
	}

	private static int findPriorityIndex(int startIndex) {
		// 0번 자리에 배정되지 않았다면 0번을 반환한다.
		if (reservations[0][startIndex] == 0) {
			return 0;
		}

		// 끝자리에 배정되지 않았다면 끝번을 반환한다.
		if (reservations[reservations.length - 1][startIndex] == 0) {
			return reservations.length - 1;
		}

		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		for (int i = 1; i < N - 1; i++) {
			int downCount = 0;
			for (int j = i; j >= 0; j--) {
				if (reservations[j][startIndex] != 0) {
					break;
				}
				downCount++;
			}

			int upCount = 0;
			for (int j = i; j < N; j++) {
				if (reservations[j][startIndex] != 0) {
					break;
				}
				upCount++;
			}

			if (Math.abs(downCount - upCount) != 0 && Math.abs(downCount - upCount) != 1) {
				continue;
			}

			int mid = (downCount + upCount) / 2;

			if (max < mid) {
				max = mid;
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	private static void output() {
		System.out.println(count);
	}
}
