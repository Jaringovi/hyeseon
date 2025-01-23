package week23;

import java.io.*;
import java.util.*;

public class BJ_G3_1941 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int[][] dyx = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //상우하좌
  static char[][] students;
  static int result;
  static boolean[][] visits;
  static List<Character> comb;

  public static void main(String[] args) throws Exception {
    init();
    solve();
    output();
  }

  public static void init() throws Exception {
      students = new char[5][5];
      for(int i=0; i<5; i++) {
        students[i] = br.readLine().toCharArray();
      }

      visit = new boolean[5][5];
      comb = new ArrayList<>();
  }

  public static void solve() {
    for(int i=0; i<5; i++) {
      for(int j=0; j<5; j++) {
        visit[i][j] = true;
        comb.add(students[i][j]);
        findRoute(i, j, 1, comb, visit);
        visit[i][j] = false;
        comb.remove(comb.size()-1);
      }
    }
  }

  public static void findRoute(int y, int x, int cnt, List<Character> comb, boolean[][] visit) {
    if(cnt == 7) {
      int sCount = findSCount(comb);
      if(sCount >= 4) {
        result++;
      }
      return;
    }

    for(int i=0; i<4; i++) {
      int ny = dyx[i][0] + y;
      int nx = dyx[i][1] + x;

      if(!isRange(ny, nx) || visit[ny][nx]) {
        continue;
      }

      visit[ny][nx] = true;
      comb.add(students[ny][nx]);
      findRoute(ny, nx, cnt + 1, comb, visit);
      visit[ny][nx] = false;
      comb.remove(comb.size() -1);
    }
  }

  public static int findSCount(List<Character> comb) {
    int count = 0;
    for(char student : comb) {
      if(student == 'S') {
        count++;
      }
    }
    return count;
  }

  public static boolean isRange(int y, int x) {
    return y >= 0 && y < 5 && x >= 0 && x < 5;
  }

  public static void output(){
    System.out.println(result);
  }
}
