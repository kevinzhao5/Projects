#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cmath>
#include <array>
#include <set>
#include <algorithm>
using namespace std;

int main() {
  freopen("time.in", "r", stdin);
  freopen("time.out", "w", stdout);
  int N, M, C;
  cin >> N >> M >> C;
  int m[100000];
  for (int i = 0; i < N; i++) {
    cin >> m[i];
  }
  vector< vector<int> > adjList;
  adjList.assign(N, vector<int>());
  for (int i = 0; i < M; i++) {
    int a, b;
    cin >> a >> b;
    --a;
    --b;
    adjList[a].push_back(b);
  }
  int ans = 0, counter = 0;
  int monies[100000][2];
  for (int i = 0; i < 2; i++) {
    fill(monies[i], monies[i] + 100000, -1);
  }
  monies[0][0] = 0;
  for (int i = 1; i <= 1000; i++) {
    for (int j = 0; j < N; j++) {
      if (monies[j][counter] == -1) continue;
      for (int k = 0; k < adjList[j].size(); k++) {
        int a = adjList[j][k];
        monies[a][1 - counter] = max(monies[a][1 - counter], monies[j][counter] + m[a]);
      }
    }
    counter = 1 - counter;
    ans = max(ans, monies[0][counter] - C * i * i);
  }
  cout << ans;
  return 0;
}
