#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cmath>
#include <array>
#include <set>
#include <algorithm>
using namespace std;

typedef pair<int, int> ii;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("timeline.in", "r", stdin);
  freopen("timeline.out", "w", stdout);
  int N, M, C;
  cin >> N >> M >> C;
  vector< vector<ii> > adjList;
  adjList.assign(N + 1, vector<ii>());
  for (int i = 1; i <= N; i++) {
    int S;
    cin >> S;
    adjList[0].push_back(ii(S, i));
  }
  for (int i = 0; i < C; i++) {
    int a, b, c;
    cin >> a >> b >> c;
    adjList[a].push_back(ii(c, b));
  }
  int dist[100001];
  fill(dist, dist + 100001, 0);
  priority_queue<ii> pq;
  pq.push(ii(0, 0));
  while (!pq.empty()) {
    ii curr = pq.top();
    pq.pop();
    int a = curr.first, b = curr.second;
    if (a < dist[b]) continue;
    for (int i = 0; i < adjList[b].size(); i++) {
      curr = adjList[b][i];
      int c = curr.first, d = curr.second;
      if (a + c > dist[d]) {
        dist[d] = a + c;
        pq.push(ii(dist[d], d));
      }
    }
  }
  for (int i = 1; i <= N; i++) {
    cout << dist[i] << "\n";
  }
  return 0;
}
