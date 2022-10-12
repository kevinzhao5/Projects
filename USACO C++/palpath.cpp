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
using namespace std;

typedef unsigned long long ull;

ull mod = 1000000007;
ull powers[4];

ull convert(int i, int j, int k, int l) {
  return powers[3] * i + powers[2] * j + powers[1] * k + l;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("palpath.in", "r", stdin);
  freopen("palpath.out", "w", stdout);
  int N;
  cin >> N;
  powers[0] = 1;
  for (int i = 1; i <= 3; i++) {
    powers[i] = N * powers[i - 1];
  }
  char grid[500][500];
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      cin >> grid[i][j];
    }
  }
  queue<ull> q;
  unordered_map<ull, ull> m;
  set<ull> s;
  for (int i = 0; i < N; i++) {
    ull index = convert(N - 1 - i, i, N - 1 - i, i);
    m[index] = 1;
    s.insert(index);
    q.push(index);
  }
  int ra[] = { -1, -1, 0, 0 };
  int rb[] = { 0, 0, -1, -1 };
  int rc[] = { 1, 0, 1, 0 };
  int rd[] = { 0, 1, 0, 1 };
  ull ansIndex = convert(0, 0, N - 1, N - 1), ans = 0;
  while (!q.empty()) {
    ull curr = q.front(), ind = curr;
    q.pop();
    int d = ind % N;
    ind -= d;
    ind /= N;
    int c = ind % N;
    ind -= c;
    ind /= N;
    int b = ind % N;
    ind -= b;
    ind /= N;
    int a = ind;
    for (int i = 0; i < 4; i++) {
      int na = a + ra[i], nb = b + rb[i], nc = c + rc[i], nd = d + rd[i];
      if (na < 0 || nb < 0 || nc >= N || nd >= N || grid[na][nb] != grid[nc][nd]) continue;
      ull nindex = convert(na, nb, nc, nd);
      if (s.count(nindex) == 0) {
        q.push(nindex);
        s.insert(nindex);
        m[nindex] = 0;
      }
      m[nindex] += m[curr];
      m[nindex] %= mod;
    }
    if (curr == ansIndex) ans = m[curr];
    m.erase(curr);
  }
  cout << ans << "\n";
  return 0;
}
