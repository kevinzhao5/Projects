#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cmath>
#include <array>
#include <set>
#include <map>
#include <algorithm>
#include <unordered_map>
#include <string>
using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("art.in", "r", stdin);
  freopen("art.out", "w", stdout);
  int N;
  cin >> N;
  int canvas[1000][1000];
  int amt[1000001];
  int u[1000001];
  fill(u, u + 1000001, N);
  int d[1000001];
  fill(d, d + 1000001, 0);
  int l[1000001];
  fill(l, l + 1000001, N);
  int r[1000001];
  fill(r, r + 1000001, 0);
  int numcolors = 0;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      int c;
      cin >> c;
      canvas[i][j] = c;
      amt[c]++;
      if (c > 0 && amt[c] == 1) numcolors++;
      u[c] = min(u[c], i);
      d[c] = max(d[c], i);
      l[c] = min(l[c], j);
      r[c] = max(r[c], j);
    }
  }
  if (numcolors == 1) {
    if (N == 1) cout << "1\n";
    else {
      int ans = N * N - 1;
      cout << ans << "\n";
    }
  } else {
    bool pos[1000001];
    fill(pos, pos + 1000001, true);
    int psums[1001][1001];
    for (int i = 0; i <= 1000; i++) {
      fill(psums[i], psums[i] + 1001, 0);
    }
    for (int i = 1; i <= N * N; i++) {
      if (amt[i] > 0) {
        psums[u[i]][l[i]]++;
        psums[u[i]][r[i] + 1]--;
        psums[d[i] + 1][l[i]]--;
        psums[d[i] + 1][r[i] + 1]++;
      }
    }
    for (int i = 0; i <= N; i++) {
      for (int j = 0; j <= N; j++) {
        if (i > 0) psums[i][j] += psums[i - 1][j];
        if (j > 0) psums[i][j] += psums[i][j - 1];
        if (i > 0 && j > 0) psums[i][j] -= psums[i - 1][j - 1];
        if (psums[i][j] > 1) {
          pos[canvas[i][j]] = false;
        }
      }
    }
    int ans = 0;
    for (int i = 1; i <= N * N; i++) {
      if (pos[i]) ans++;
    }
    cout << ans << "\n";
    return 0;
  }
}
