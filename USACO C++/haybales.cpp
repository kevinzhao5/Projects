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

ull minimum[800010];
ull sums[800010];
ull sumC[800010];
int lengths[800010];

ull rmq(int index, int lb, int rb, int l, int r) {
  if (lb <= l && r <= rb) {
    ull res = minimum[index] + sumC[index];
    return res;
  }
  if (l > rb || r < lb) return 100000000000;
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  ull lm = rmq(li, lb, rb, l, nr), rm = rmq(ri, lb, rb, nl, r), m = min(lm, rm) + sumC[index];
  return m;
}

ull rsq(int index, int lb, int rb, int l, int r) {
  if (lb <= l && r <= rb) {
    ull res = sums[index] + sumC[index] * (r - l + 1);
    return res;
  }
  if (l > rb || r < lb) return 0;
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  ull ls = rsq(li, lb, rb, l, nr), rs = rsq(ri, lb, rb, nl, r), s = ls + rs + sumC[index] * (min(r, rb) - max(l, lb) + 1);
  return s;
}

ull setValMin(int index, int pos, int val, int l, int r) {
  if (pos == l && pos == r) {
    minimum[index] = val;
    return val;
  }
  if (pos > r || pos < l) return minimum[index];
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  ull lm = setValMin(li, pos, val, l, nr), rm = setValMin(ri, pos, val, nl, r), m = min(lm, rm);
  minimum[index] = m;
  return m;
}

ull setValSum(int index, int pos, int val, int l, int r) {
  if (pos == l && pos == r) {
    sums[index] = val;
    return val;
  }
  if (pos > r || pos < l) return sums[index];
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  ull ls = setValSum(li, pos, val, l, nr), rs = setValSum(ri, pos, val, nl, r), s = ls + rs;
  sums[index] = s;
  return s;
}

void updateMin(int index) {
  int li = index * 2, ri = li + 1;
  ull lm = minimum[li] + sumC[li], rm = minimum[ri] + sumC[ri], m = min(lm, rm);
  minimum[index] = m;
  if (index == 1) return;
  int nindex = index / 2;
  updateMin(nindex);
}

void updateSum(int index) {
  int li = index * 2, ri = li + 1;
  ull ls = sums[li] + sumC[li] * lengths[li], rs = sums[ri] + sumC[ri] * lengths[ri], s = ls + rs;
  sums[index] = s;
  if (index == 1) return;
  int nindex = index / 2;
  updateSum(nindex);
}

void setSumC(int index, int lb, int rb, int val, int l, int r) {
  if (lb <= l && r <= rb) {
    sumC[index] += val;
    int nindex = index / 2;
    updateMin(nindex);
    updateSum(nindex);
    return;
  }
  if (l > rb || r < lb) return;
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  setSumC(li, lb, rb, val, l, nr);
  setSumC(ri, lb, rb, val, nl, r);
}

void build(int index, int l, int r) {
  lengths[index] = r - l + 1;
  if (l == r) return;
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  build(li, l, nr);
  build(ri, nl, r);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("haybales.in", "r", stdin);
  freopen("haybales.out", "w", stdout);
  fill(minimum, minimum + 800010, 1000000);
  fill(sums, sums + 800010, 0);
  fill(sumC, sumC + 800010, 0);
  int N, Q;
  cin >> N >> Q;
  build(1, 1, N);
  for (int i = 1; i <= N; i++) {
    int x;
    cin >> x;
    setValMin(1, i, x, 1, N);
    setValSum(1, i, x, 1, N);
  }
  for (int i = 0; i < Q; i++) {
    char c;
    cin >> c;
    int A, B;
    cin >> A >> B;
    if (c == 'M') {
      ull ans = rmq(1, A, B, 1, N);
      cout << ans << "\n";
    } else if (c == 'P') {
      int C;
      cin >> C;
      setSumC(1, A, B, C, 1, N);
    } else {
      ull ans = rsq(1, A, B, 1, N);
      cout << ans << "\n";
    }
  }
  return 0;
}
