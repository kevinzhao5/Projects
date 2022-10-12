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

typedef pair<int, int> ii;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("route.in", "r", stdin);
  freopen("route.out", "w", stdout);
  int N, M, R;
  cin >> N >> M >> R;
  int l[40000];
  int r[40000];
  int dpl[40000];
  int dpr[40000];
  for (int i = 0; i < N; i++) {
    cin >> l[i];
    dpl[i] = l[i];
  }
  for (int i = 0; i < M; i++) {
    cin >> r[i];
    dpr[i] = r[i];
  }
  ii routes[100000];
  for (int i = 0; i < R; i++) {
    int a, b;
    cin >> a >> b;
    routes[i] = ii(--a, --b);
  }
  sort(routes, routes + R);
  for (int i = R - 1; i >= 0; i--) {
    ii curr = routes[i];
    int a = curr.first, b = curr.second, ld = dpr[b] + l[a], rd = dpl[a] + r[b];
    dpl[a] = max(dpl[a], ld);
    dpr[b] = max(dpr[b], rd);
  }
  int ans = 0;
  for (int i = 0; i < N; i++) {
    ans = max(ans, dpl[i]);
  }
  for (int i = 0; i < M; i++) {
    ans = max(ans, dpr[i]);
  }
  cout << ans << "\n";
  return 0;
}
