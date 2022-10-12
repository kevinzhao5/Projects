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

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("nochange.in", "r", stdin);
  freopen("nochange.out", "w", stdout);
  int K, N;
  cin >> K >> N;
  int coins[16];
  int totalVal = 0;
  for (int i = 0; i < K; i++) {
    cin >> coins[i];
    totalVal += coins[i];
  }
  int power = (int) (pow(2.0, K + 1)) - 1;
  int costs[100001];
  costs[0] = 0;
  for (int i = 1; i <= N; i++) {
    cin >> costs[i];
    costs[i] += costs[i - 1];
  }
  map<int, int> m;
  for (int i = 0; i <= 17; i++) {
    m[(1 << i)] = i;
  }
  int dp[131072];
  fill(dp, dp + 131072, 0);
  for (int i = 1; i <= power; i++) {
    int mask = i;
    while (mask > 0) {
      int b = (mask & (-mask)), nmask = i - b, ncost = costs[dp[nmask]] + coins[m[b]];
      mask -= b;
      int* index = lower_bound(costs, costs + N + 1, ncost);
      if (*index > ncost) index--;
      int x = index - begin(costs);
      dp[i] = max(dp[i], x);
    }
  }
  int ans = -1;
  for (int i = 1; i <= power; i++) {
    if (dp[i] < N) continue;
    int mask = i, total = 0;
    while (mask > 0) {
      int b = (mask & (-mask));
      mask -= b;
      total += coins[m[b]];
    }
    total = totalVal - total;
    ans = max(ans, total);
  }
  cout << ans;
  return 0;
}
