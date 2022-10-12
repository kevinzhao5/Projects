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

int N, K, sum;
int a[100000];

bool pos(double time) {
  double totalTime = 0;
  int cowsLeft = K, ct = cowsLeft;
  double ratio = 1.0 * sum / ct;
  if (ratio > time + 0.000001) return false;
  for (int i = 0; i < N; i++) {
    long amt = (long)round(a[i] / ratio);
    cout << amt << "\n";
    cowsLeft -= amt;
    totalTime += 1.0 * a[i] / amt;
  }
  cout << "\n";
  if (totalTime > time + 0.000001) return false;
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("tallbarn.in", "r", stdin);
  freopen("tallbarn.out", "w", stdout);
  cin >> N >> K;
  sum = 0;
  for (int i = 0; i < N; i++) {
    cin >> a[i];
    sum += a[i];
  }
  double lo = 0, hi = 100000000000000000, mid = 0;
  while (lo + 0.1 < hi) {
    mid = (lo + hi) / 2;
    if (pos(mid)) hi = mid;
    else lo = mid + 0.1;
  }
  long x = (long)round(lo);
  cout << x << "\n";
  return 0;
}
