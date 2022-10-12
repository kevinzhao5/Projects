/*
ID: awesome25
TASK: ditch
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
  freopen("ditch.in", "r", stdin);
  freopen("ditch.out", "w", stdout);
  int N, M;
  cin >> N >> M;
  int intpts[201][201];
  for (int i = 0; i <= N; i++) {
    fill(intpts[i], intpts[i] + 201, 0);
  }
  for (int i = 0; i < N; i++) {
    int Si, Ei, Ci;
    cin >> Si >> Ei >> Ci;
    intpts[Si][Ei] += Ci;
  }
  int ans = 0;
  int p[201];
  bool v[201];
  int f[201];
  while (true) {
    fill(p, p + 201, -1);
    fill(v, v + 201, false);
    fill(f, f + 201, 0);
    f[1] = 2000000000;
    bool b = false;
    while (true) {
      int mfw = 0, index = -1;
      for (int i = 1; i <= M; i++) {
        if (f[i] > mfw && !v[i]) {
          index = i;
          mfw = f[i];
        }
      }
      if (index == -1) {
        b = true;
        break;
      }
      if (index == M) break;
      v[index] = true;
      for (int i = 1; i <= M; i++) {
        int flow = min(mfw, intpts[index][i]);
        if (f[i] < flow) {
          f[i] = flow;
          p[i] = index;
        }
      }
    }
    if (b) break;
    int flow = f[M];
    ans += flow;
    int v = M, u = p[v];
    while (v != 1) {
      intpts[u][v] -= flow;
      intpts[v][u] += flow;
      v = u;
      u = p[v];
    }
  }
  cout << ans << "\n";
  return 0;
}
