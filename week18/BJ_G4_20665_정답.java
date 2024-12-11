package week18;

import java.io.*;
import java.util.*;

/** 20665. 독서실 거리두기
 * 메모리 15368 kb
 * 시간 156 ms
 * 구현
 *
 * */
public class BJ_G4_20665_정답 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, T, P;
	static List<Person> persons;
	static int[][] reservations;
	static int count;

	public static void main(String[] args) throws Exception {
		init();
		solve();
		output();
	}

	static class Person implements Comparable<Person> {
		int number;
		int startTime;
		int endTime;

		Person(int number, String start, String end) {
			this.number = number;
			this.startTime = (Integer.parseInt(start.substring(0, 2)) - 9) * 60 + Integer.parseInt(start.substring(2, 4));
			this.endTime = (Integer.parseInt(end.substring(0, 2)) - 9) * 60 + Integer.parseInt(end.substring(2, 4));
		}

		@Override
		public int compareTo(Person o) {
			if (this.startTime == o.startTime) {
				return Integer.compare(this.endTime, o.endTime); // 짧은 이용시간 우선
			}
			return Integer.compare(this.startTime, o.startTime);
		}
	}

	public static void init() throws Exception {
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		T = Integer.parseInt(inputs[1]);
		P = Integer.parseInt(inputs[2]) - 1; // 배열 인덱스를 위해 -1 처리

		reservations = new int[N][721]; // 720으로 하면 23%에서 ArrayIndexOutOfBoundsException 터진다.
		persons = new ArrayList<>();

		for (int i = 0; i < T; i++) {
			inputs = br.readLine().split(" ");
			persons.add(new Person(i + 1, inputs[0], inputs[1]));
		}

		Collections.sort(persons);
	}

	private static void solve() {
		for (Person person : persons) {
			int seatIndex = findPriorityIndex(person.startTime);

			for (int t = person.startTime; t < person.endTime; t++) {
				reservations[seatIndex][t] = person.number;
			}
		}

		// 민규가 선호하는 좌석(P번 좌석)의 빈 시간 계산, 향상된 for문을 사용하면 틀렸습니다 뜬다.
		for (int t = 0; t < 720; t++) {
			if (reservations[P][t] == 0) {
				count++;
			}
		}
	}

	private static int findPriorityIndex(int startTime) {
		int maxDistance = -1;
		int bestIndex = -1;

		for (int i = 0; i < N; i++) {
			if (reservations[i][startTime] == 0) { // 비어 있는 좌석만 검사
				int distance = calculateDistance(i, startTime);
				if (distance > maxDistance || (distance == maxDistance && i < bestIndex)) {
					maxDistance = distance;
					bestIndex = i;
				}
			}
		}

		return bestIndex;
	}

	private static int calculateDistance(int seatIndex, int startTime) {
		int leftDist = Integer.MAX_VALUE;
		int rightDist = Integer.MAX_VALUE;

		// 아래쪽 거리 계산
		for (int i = seatIndex - 1; i >= 0; i--) {
			if (reservations[i][startTime] != 0) {
				leftDist = seatIndex - i;
				break;
			}
		}

		// 위쪽 거리 계산
		for (int i = seatIndex + 1; i < N; i++) {
			if (reservations[i][startTime] != 0) {
				rightDist = i - seatIndex;
				break;
			}
		}

		// 양쪽 거리 중 최소값 반환
		int distance = Math.min(leftDist, rightDist);
		return distance == Integer.MAX_VALUE ? N : distance; // 양쪽이 비어 있으면 최대 거리 반환
	}

	private static void output() {
		System.out.println(count);
	}
}
