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

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("cardgame.in", "r", stdin);
  freopen("cardgame.out", "w", stdout);
  int N;
  cin >> N;
  int elsie[50000];
  set<int> b1;
  set<int> b2;
  for (int i = 1; i <= 2 * N; i++) {
    b1.insert(i);
    b2.insert(i);
  }
  for (int i = 0; i < N; i++) {
    cin >> elsie[i];
    b1.erase(elsie[i]);
    b2.erase(elsie[i]);
  }
  int hi[50001];
  int lo[50001];
  fill(hi, hi + 50001, 0);
  fill(lo, lo + 50001, 0);
  for (int i = 1; i <= N; i++) {
    hi[i] = hi[i - 1];
    int val = elsie[i - 1];
    set<int>::iterator it = b1.lower_bound(val);
    if (it == b1.end()) continue;
    b1.erase(it);
    hi[i]++;
  }
  for (int i = N - 1; i >= 0; i--) {
    lo[i] = lo[i + 1];
    int val = elsie[i];
    set<int>::iterator it = b2.lower_bound(val);
    if (it == b2.begin()) continue;
    b2.erase(--it);
    lo[i]++;
  }
  int ans = 0;
  for (int i = 0; i <= N; i++) {
    int nans = hi[i] + lo[i];
    ans = max(ans, nans);
  }
  cout << ans << "\n";
  return 0;
}
