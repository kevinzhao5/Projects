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
typedef unsigned long long ull;
typedef pair<ull, int> ui;

int N, M, K, Q;
vector< vector<ii> > adjList, adjList1;
ull dist[2][200][20000];
int hub[200];

void dijkstra(int first, int index) {
  int src = hub[index];
  fill(dist[first][index], dist[first][index] + 20000, 1000000000000000);
  priority_queue< ui, vector<ui>, greater<ui> > pq;
  pq.push(ui(0, src));
  dist[first][index][src] = 0;
  while (!pq.empty()) {
    ui p = pq.top();
    pq.pop();
    ull a = p.first;
    int b = p.second;
    if (a > dist[first][index][b]) continue;
    if (first == 0) {
      for (int i = 0; i < adjList[b].size(); i++) {
        ii p1 = adjList[b][i];
        int c = p1.first, d = p1.second;
        ull nd = a + d;
        if (dist[first][index][c] > nd) {
          dist[first][index][c] = nd;
          pq.push(ui(nd, c));
        }
      }
    } else {
      for (int i = 0; i < adjList1[b].size(); i++) {
        ii p1 = adjList1[b][i];
        int c = p1.first, d = p1.second;
        ull nd = a + d;
        if (dist[first][index][c] > nd) {
          dist[first][index][c] = nd;
          pq.push(ui(nd, c));
        }
      }
    }
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("vacationgold.in", "r", stdin);
  freopen("vacationgold.out", "w", stdout);
  cin >> N >> M >> K >> Q;
  adjList.assign(N, vector<ii>());
  for (int i = 0; i < M; i++) {
    int u, v, d;
    cin >> u >> v >> d;
    adjList[--u].push_back(ii(--v, d));
  }
  for (int i = 0; i < K; i++) {
    cin >> hub[i];
    hub[i]--;
  }
  for (int i = 0; i < K; i++) {
    dijkstra(0, i);
  }
  adjList1.assign(N, vector<ii>());
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < adjList[i].size(); j++) {
      adjList1[adjList[i][j].first].push_back(ii(i, adjList[i][j].second));
    }
  }
  for (int i = 0; i < K; i++) {
    dijkstra(1, i);
  }
  ull total = 0;
  int count = 0;
  for (int i = 0; i < Q; i++) {
    int a, b;
    ull cost = 1000000000000000;
    cin >> a >> b;
    --a;
    --b;
    for (int j = 0; j < K; j++) {
      ull ncost = dist[1][j][a] + dist[0][j][b];
      cost = min(cost, ncost);
    }
    if (cost < 1000000000000000) {
      total += cost;
      count++;
    }
  }
  cout << count << "\n" << total << "\n";
  return 0;
}
