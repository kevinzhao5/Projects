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

int maximum[300001];
int extrasum[300001];

int rmq(int index, int lb, int rb, int l, int r) {
  if (lb <= l && r <= rb) {
    int ret = maximum[index] + extrasum[index];
    return ret;
  }
  if (l > rb || r < lb) return -1000000;
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  int lm = rmq(li, lb, rb, l, nr), rm = rmq(ri, lb, rb, nl, r), m = max(lm, rm) + extrasum[index];
  return m;
}

int setMaximum(int index, int pos, int val, int l, int r) {
  if (pos == l && pos == r) {
    maximum[index] = val;
    return val;
  }
  if (pos > r || pos < l) return maximum[index];
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  int lm = setMaximum(li, pos, val, l, nr), rm = setMaximum(ri, pos, val, nl, r), m = max(lm, rm);
  maximum[index] = m;
  return m;
}

void updateMax(int index) {
  int li = index * 2, ri = li + 1;
  int lm = maximum[li] + extrasum[li], rm = maximum[ri] + extrasum[ri], m = max(lm, rm);
  maximum[index] = m;
  if (index == 1) return;
  int nindex = index / 2;
  updateMax(nindex);
}

void updateExtraSum(int index, int lb, int rb, int val, int l, int r) {
  if (l > rb || r < lb) return;
  if (lb <= l && r <= rb) {
    extrasum[index] += val;
    if (index == 1) return;
    int nindex = index / 2;
    updateMax(nindex);
    return;
  }
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  updateExtraSum(li, lb, rb, val, l, nr);
  updateExtraSum(ri, lb, rb, val, nl, r);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("greedy.in", "r", stdin);
  freopen("greedy.out", "w", stdout);
  int N;
  cin >> N;
  int c[100000];
  for (int i = 0; i < N; i++) {
    cin >> c[i];
  }
  fill(maximum, maximum + 300001, 0);
  for (int i = 1; i <= N; i++) {
    int val = i - N - 1;
    setMaximum(1, i, val, 1, N);
  }
  fill(extrasum, extrasum + 300001, 0);
  int totalCount = 0;
  for (int i = 0; i < N; i++) {
    int curr = c[i] + 1, prevcurr = curr - 1;
    updateExtraSum(1, 1, curr, 1, 1, N);
    totalCount++;
    if (rmq(1, 1, N, 1, N) >= 0) break;
  }
  int ans = N - totalCount;
  cout << ans << "\n";
  return 0;
}
