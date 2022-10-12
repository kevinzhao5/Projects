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
  freopen("dec.in", "r", stdin);
  freopen("dec.out", "w", stdout);
  int N, B;
  cin >> N >> B;
  vector< vector<ii> > bonuses;
  bonuses.assign(21, vector<ii>());
  for (int i = 0; i < B; i++) {
    int K, P, A;
    cin >> K >> P >> A;
    bonuses[K].push_back(ii(P, A));
  }
  for (int i = 0; i < B; i++) {
    sort(bonuses[i].begin(), bonuses[i].end());
  }
  int cows[20][20];
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      cin >> cows[i][j];
    }
  }
  map<int, int> pow2;
  for (int i = 0; i <= 20; i++) {
    pow2[1 << i] = i;
  }
  int dp[1048576];
  fill(dp, dp + 1048576, 0);
  int bound = pow(2, N) - 1;
  for (int i = 1; i <= bound; i++) {
    int mask = i, count = 0;
    while (mask > 0) {
      mask -= (mask & -(mask));
      count++;
    }
    mask = i;
    while (mask > 0) {
      int l = (mask & -(mask)), nmask = i - l;
      mask -= l;
      int nscore = dp[nmask], index = pow2[l];
      nscore += cows[index][count - 1];
      for (int j = 0; j < bonuses[count].size(); j++) {
        ii curr = bonuses[count][j];
        if (nscore >= curr.first) {
          nscore += curr.second;
        }
      }
      dp[i] = max(dp[i], nscore);
    }
  }
  cout << dp[bound] << "\n";
  return 0;
}
