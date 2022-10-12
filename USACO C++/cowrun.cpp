#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cmath>
#include <array>
#include <set>
#include <map>
#include <algorithm>
using namespace std;

typedef unsigned long long ull;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("cowrun.in", "r", stdin);
  freopen("cowrun.out", "w", stdout);
  int N;
  cin >> N;
  int pos[1002];
  for (int i = 1; i <= N; i++) {
    cin >> pos[i];
  }
  sort(pos + 1, pos + 1 + N);
  int counter = 1;
  while (pos[counter] < 0 && counter <= N) counter++;
  if (counter == 1) {
    pos[0] = 0;
    ull total = 0;
    for (int i = 1; i <= N; i++) {
      total += (N - i + 1) * (pos[i] - pos[i - 1]);
    }
    cout << total << "\n";
  } else if (counter > N) {
    pos[counter] = 0;
    ull total = 0;
    for (int i = N; i >= 1; i--) {
      total += i * (pos[i + 1] - pos[i]);
    }
    cout << total << "\n";
  } else {
    ull dp[2][1002][1002];
    for (int i = 0; i < 2; i++) {
  		for (int j = 0; j < 1002; j++) {
        fill(dp[i][j], dp[i][j] + 1002, 1000000000000000);
      }
    }
    dp[0][counter][counter - 1] = 0;
    dp[1][counter][counter - 1] = 0;
    for (int i = counter; i <= N + 1; i++) {
      for (int j = counter - 1; j >= 0; j--) {
        if (i == counter && j == counter - 1) continue;
        for (int k = 0; k < 2; k++) {
          int num = N - (abs(i - counter) + abs(j - counter + 1)) + 1;
          ull c1, c2;
          if (k == 1) {
            if (i == counter) c1 = 1000000000000000;
            else if (i == counter + 1) c1 = dp[1][counter][j] + num * pos[counter];
            else c1 = dp[1][i - 1][j] + num * (pos[i - 1] - pos[i - 2]);
            if (j == counter - 1) c2 = dp[0][i - 1][counter - 1] + num * pos[i - 1];
            else c2 = dp[0][i - 1][j] + num * (pos[i - 1] - pos[j + 1]);
          } else {
            if (i == counter) c1 = dp[1][counter][j + 1] + num * abs(pos[j + 1]);
            else c1 = dp[1][i][j + 1] + num * (pos[i - 1] - pos[j + 1]);
            if (j == counter - 1) c2 = 1000000000000000;
            else if (j == counter - 2) c2 = dp[0][i][counter - 1] + num * abs(pos[counter - 1]);
            else c2 = dp[0][i][j + 1] + num * (pos[j + 2] - pos[j + 1]);
          }
          dp[k][i][j] = min(c1, c2);
        }
      }
    }
    cout << min(dp[0][N + 1][0], dp[1][N + 1][0]) << "\n";
  }
  return 0;
}
