/*
ID: awesome25
TASK: stall4
LANG: C++11
*/

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
  freopen("stall4.in", "r", stdin);
  freopen("stall4.out", "w", stdout);
  int N, M;
  cin >> N >> M;
  int s = 410;
  int g[s][s];
  for (int i = 0; i < N; i++) {
    fill(g[i], g[i] + s, 0);
  }
  for (int i = 0; i < N; i++) {
    int Si;
    cin >> Si;
    for (int j = 0; j < Si; j++) {
      int x;
      cin >> x;
      g[i + 1][N + x] = 1;
    }
    g[0][i + 1] = 1;
  }
  int sink = N + M + 1;
  for (int i = 0; i < M; i++) {
    g[N + i + 1][sink] = 1;
  }
  int ans = 0;
  int parent[s];
  bool visited[s];
  int f[s];
  while (true) {
    fill(parent, parent + s, -1);
    fill(visited, visited + s, false);
    fill(f, f + s, 0);
    f[0] = 1000000;
    bool b = true;
    while (true) {
      int mfw = 0, index = -1;
      for (int i = 0; i <= sink; i++) {
        if (visited[i] || f[i] <= mfw) continue;
        index = i;
        mfw = f[i];
      }
      if (index == -1) {
        b = false;
        break;
      }
      if (index == sink) break;
      visited[index] = true;
      for (int i = 0; i <= sink; i++) {
        if (g[index][i] <= 0) continue;
        int flow = min(mfw, g[index][i]);
        if (f[i] < flow) {
          f[i] = flow;
          parent[i] = index;
        }
      }
    }
    if (!b) break;
    int flow = f[sink];
    ans += flow;
    int v = sink, par;
    while (v != 0) {
      par = parent[v];
      g[par][v] -= flow;
      g[v][par] += flow;
      v = par;
    }
  }
  cout << ans << "\n";
  return 0;
}
