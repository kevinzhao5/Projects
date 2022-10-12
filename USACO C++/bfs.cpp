#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cmath>
#include <array>
using namespace std;

int main() {
  freopen("bfs.in", "r", stdin);
  freopen("bfs.out", "w", stdout);
  int V, E;
  cin >> V >> E;
  vector< vector<int> > adjList;
  adjList.assign(V, vector<int>());
  for (int i = 0; i < E; i++) {
    int a, b;
    cin >> a >> b;
    --a;
    --b;
    adjList[a].push_back(b);
    adjList[b].push_back(a);
  }
  int dist[100000];
  fill(dist, dist + 100000, 1000000000);
  dist[0] = 0;
  queue<int> q;
  q.push(0);
  while (!q.empty()) {
    int a = q.front();
    q.pop();
    for (int i = 0; i < adjList[a].size(); i++) {
      int b = adjList[a][i];
      if (dist[b] == 1000000000) {
        dist[b] = dist[a] + 1;
        q.push(b);
      }
    }
  }
  for (int i = 0; i < V; i++) {
    cout << dist[i] << " ";
  }
  cout << "\n";
  return 0;
}
