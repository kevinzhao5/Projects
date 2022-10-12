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
#include <unordered_set>
#include <stack>
using namespace std;

long long N, Q, g;
vector< vector<long long> > v;
long long b[100005];
long long e[100005];
long long o[100005];
long long t[100005];
vector< vector<long long> > f;

long long so(long long j) {
  long long a = 0;
  while (j > 0) {
    a += o[j];
    j -= (j & (-j));
  }
  return a;
}

long long st(long long j) {
  long long a = 0;
  while (j > 0) {
    a += t[j];
    j -= (j & (-j));
  }
  return a;
}

void uo(long long j, long long n) {
  while (j <= N) {
    o[j] += n;
    j += (j & (-j));
  }
}

void ut(long long j, long long n) {
  while (j <= N) {
    t[j] += n;
    j += (j & (-j));
  }
}

long long dfs(long long u, long long p) {
  long long a = 1;
  b[u] = g;
  g++;
  for (int i = 0; i < v[u].size(); i++) {
    if (v[u][i] == p) continue;
    a += dfs(v[u][i], u);
  }
  e[u] = b[u] + a;
  return a;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("snowcow.in", "r", stdin);
  freopen("snowcow.out", "w", stdout);
  cin >> N >> Q;
  v.assign(N + 1, vector<long long>());
  for (int i = 0; i < N - 1; i++) {
    long long a, b;
    cin >> a >> b;
    v[a].push_back(b);
    v[b].push_back(a);
  }
  g = 1;
  fill(o, o + 100005, 0);
  fill(t, t + 100005, 0);
  dfs(1, -1);
  f.assign(N + 1, vector<long long>());
  for (int q = 0; q < Q; q++) {
    int y;
    cin >> y;
    if (y == 1) {
      int x, c;
      cin >> x >> c;
      bool z = false;
      for (int i = 0; i < f[c].size(); i++) {
        if (b[x] >= b[f[c][i]] && b[x] < e[f[c][i]]) {
          z = true;
          break;
        }
      }
      if (!z) {
        for (int i = f[c].size() - 1; i >= 0; i--) {
          if (b[x] < b[f[c][i]] && e[x] >= e[f[c][i]]) {
            uo(b[f[c][i]], -1);
            uo(e[f[c][i]], 1);
            ut(b[f[c][i]], b[f[c][i]] - e[f[c][i]]);
            f[c].erase(f[c].begin() + i);
          }
        }
        f[c].push_back(x);
        uo(b[x], 1);
        uo(e[x], -1);
        ut(b[x], e[x] - b[x]);
      }
    } else {
      int x;
      cin >> x;
      long long a = so(b[x]) * (e[x] - b[x]) + st(e[x] - 1) - st(b[x]);
      cout << a << "\n";
    }
  }
  return 0;
}
