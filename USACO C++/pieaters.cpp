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

typedef pair<int, int> ii;
typedef pair<int, ii> iii;

int m[301][301][301];
int dp[301][301];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("pieaters.in", "r", stdin);
  freopen("pieaters.out", "w", stdout);
  int N, M;
  cin >> N >> M;
  for (int i = 0; i <= 300; i++) {
    fill(dp[i], dp[i] + 301, 0);
    for (int j = 0; j <= 300; j++) {
      fill(m[i][j], m[i][j] + 301, 0);
    }
  }
  for (int i = 1; i <= M; i++) {
    int w, l, r;
    cin >> w >> l >> r;
    for (int j = l; j <= r; j++) {
      m[l][r][j] = max(m[l][r][j], w);
    }
  }
  for (int i = N; i >= 1; i--) {
    for (int j = i; j <= N; j++) {
      for (int k = i; k <= j; k++) {
        if (i > 1) {
          m[i - 1][j][k] = max(m[i - 1][j][k], m[i][j][k]);
        }
        if (j < N) {
          m[i][j + 1][k] = max(m[i][j + 1][k], m[i][j][k]);
        }
      }
    }
  }
  for (int i = N; i >= 1; i--) {
    for (int j = i; j <= N; j++) {
      for (int k = i; k <= j; k++) {
        if (k < j) dp[i][j] = max(dp[i][j], dp[i][k] + dp[k + 1][j]);
        dp[i][j] = max(dp[i][j], dp[i][k - 1] + m[i][j][k] + dp[k + 1][j]);
      }
    }
  }
  cout << dp[1][N] << "\n";
  return 0;
}
