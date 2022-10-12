//doesn't work

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

int N;
int S[200001];
int T[200001];
int arr[400002];

int rmq(int index, int lb, int rb, int l, int r) {
  if (lb <= l && r <= rb) return arr[index];
  if (l > rb || r < lb) return -1;
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  int lm = rmq(li, lb, rb, l, nr), rm = rmq(ri, lb, rb, nl, r), maximum = max(lm, rm);
  return maximum;
}

int setVal(int index, int pos, int val, int l, int r) {
  if (pos == l && pos == r) {
    arr[index] = val;
    return val;
  }
  if (pos > r || pos < l) return arr[index];
  int nr = (l + r) / 2, nl = nr + 1, li = index * 2, ri = li + 1;
  int lm = setVal(li, pos, val, l, nr), rm = setVal(ri, pos, val, nl, r), maximum = max(lm, rm);
  arr[index] = maximum;
  return maximum;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("boarding.in", "r", stdin);
  freopen("boarding.out", "w", stdout);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> S[i] >> T[i];
  }
  fill(arr, arr + 400002, 0);
  int times[200001];
  int stopped = 0, stoppedCount = 0;
  for (int i = N; i >= 1; i--) {
    int bound = S[i];
    if (S[i] < stopped - stoppedCount) {
      stopped = 0;
      stoppedCount = 0;
    } else stoppedCount++;
    if (stopped > 0) bound = stopped;
    int time = rmq(1, 1, bound, 1, N);
    if (time > 0 && stopped == 0) {
      stopped = S[i + 1];
      stoppedCount = 1;
    }
    time += T[i];
    setVal(1, S[i], time, 1, N);
    times[i] = time;
  }
  int ans = 0;
  for (int i = 1; i <= N; i++) {
    int nans = N - i + S[i] + times[i];
    ans = max(ans, nans);
  }
  cout << ans << "\n";
  return 0;
}
