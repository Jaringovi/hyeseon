package week16;

import java.io.*;
import java.util.*;

/** 17178. 줄서기
 * 메모리 15224 kb
 * 시간 120 ms
 * 시뮬레이션
 *
 * [풀이]
 * 1. 맨 처음 대기열이 비어있으면 채운다.
 * 2. 정렬 순서와 지금 티켓 소유주가 같으면 입장한다.
 * 3. 대기열의 마지막 티켓 소유주가 정렬 순서와 같으면 입장한다.
 * 4. 아무 조건에도 해당하지 않으면 대기열로 간다.
 * 5. 대기열에만 사람들이 서있을 경우 pop한 순서가 정렬 순서와 같으면 GOOD 아니면 BAD를 출력한다.
 */
public class BJ_G5_17178_15224kb_120ms {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static PriorityQueue<Ticket> ticketSequence;
    static List<Stack<Ticket>> tickets;
    static Stack<Ticket> queue;
    static String result;

    static class Ticket implements Comparable<Ticket> {
        char alpha;
        int number;

        public Ticket(char alpha, int number) {
            this.alpha = alpha;
            this.number = number;
        }

        @Override
        public int compareTo(Ticket o) {
            if (this.alpha == o.alpha) {
                return Integer.compare(this.number, o.number);
            }
            return Character.compare(this.alpha, o.alpha);
        }

        @Override
        public boolean equals(Object o) {
            Ticket ticket = (Ticket) o;
            return alpha == ticket.alpha && number == ticket.number;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
        output();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        ticketSequence = new PriorityQueue<>();
        tickets = new ArrayList<>();
        queue = new Stack<>();

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            Stack<Ticket> newTickets = new Stack<>();

            for (int j = inputs.length - 1; j >= 0; j--) {
                char alpha = inputs[j].split("-")[0].charAt(0);
                int number = Integer.parseInt(inputs[j].split("-")[1]);

                newTickets.push(new Ticket(alpha, number));
                ticketSequence.add(new Ticket(alpha, number));
            }

            tickets.add(newTickets);
        }
    }

    private static void solve() {
        for (Stack<Ticket> ticket : tickets) {
            while (!ticket.isEmpty()) {
                // 맨 처음 대기열이 비어있으면 채운다.
                if (queue.isEmpty()) {
                    queue.add(ticket.pop());
                }
                // 정렬 순서와 지금 티켓 소유주가 같으면 입장한다.
                else if (ticket.peek().equals(ticketSequence.peek())) {
                    ticket.pop();
                    ticketSequence.poll();
                }
                // 대기열의 마지막 티켓 소유주가 정렬 순서와 같으면 입장한다.
                else if (queue.peek().equals(ticketSequence.peek())) {
                    queue.pop();
                    ticketSequence.poll();
                }
                // 아무 조건에도 해당하지 않으면 대기열로 간다.
                else {
                    queue.push(ticket.pop());
                }
            }
        }

        // 대기열에만 사람들이 서있을 경우 pop한 순서가 정렬 순서와 같으면 GOOD 아니면 BAD
        result = "GOOD";
        while (!queue.isEmpty()) {
            if (queue.peek().equals(ticketSequence.peek())) {
                queue.pop();
                ticketSequence.poll();
            }else {
                result = "BAD";
                return;
            }
        }
    }

    private static void output() {
        System.out.println(result);
    }
}
