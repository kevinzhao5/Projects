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

int maximum[400001];

int rmq(int index, int lb, int rb, int l, int r) {
  if (lb <= l && r <= rb) return maximum[index];
  if (l > rb || r < lb) return 0;
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  int lm = rmq(li, lb, rb, l, nr), rm = rmq(ri, lb, rb, nl, r), m = max(lm, rm);
  return m;
}

int update(int index, int pos, int val, int l, int r) {
  if (pos == l && pos == r) {
    maximum[index] = val;
    return val;
  }
  if (pos > r || pos < l) return maximum[index];
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  int lm = update(li, pos, val, l, nr), rm = update(ri, pos, val, nl, r), m = max(lm, rm);
  maximum[index] = m;
  return m;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("nocross.in", "r", stdin);
  freopen("nocross.out", "w", stdout);
  int N;
  cin >> N;
  int left[100001];
  int right[100001];
  fill(maximum, maximum + 400001, 0);
  fill(left, left + 100001, 0);
  fill(right, right + 100001, 0);
  for (int i = 1; i <= N; i++) {
    cin >> left[i];
  }
  for (int i = 1; i <= N; i++) {
    cin >> right[i];
  }
  int ans = 0;
  int rev[100001];
  fill(rev, rev + 100001, 0);
  for (int i = 1; i <= N; i++) {
    rev[right[i]] = i;
  }
  for (int i = 1; i <= N; i++) {
    int vals[9];
    int indices[9];
    fill(vals, vals + 9, 0);
    fill(indices, indices + 9, -1);
    for (int j = -4; j <= 4; j++) {
      if (left[i] + j <= 0) continue;
      int id = left[i] + j, index = rev[id] - 1;
      int nans = 1;
      if (index > 0) nans += rmq(1, 1, index, 1, N);
      vals[j + 4] = nans;
      indices[j + 4] = index + 1;
      ans = max(ans, nans);
    }
    for (int j = 0; j < 9; j++) {
      if (indices[j] > -1) {
        update(1, indices[j], vals[j], 1, N);
      }
    }
  }
  cout << ans << "\n";
  return 0;
}
