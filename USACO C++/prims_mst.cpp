#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cmath>
#include <array>
using namespace std;

typedef pair<int, int> ii;

int main() {
  freopen("prims_mst.in", "r", stdin);
  freopen("prims_mst.out", "w", stdout);
  int V, E;
  cin >> V >> E;
  vector< vector<ii> > adjList;
  adjList.assign(V, vector<ii>());
  for (int i = 0; i < E; i++) {
    int a, b, c;
    cin >> a >> b >> c;
    adjList[a].push_back(ii(c, b));
    adjList[b].push_back(ii(c, a));
  }
  priority_queue< ii, vector<ii>, greater<ii> > pq;
  for (int i = 0; i < adjList[0].size(); i++) {
    pq.push(adjList[0][i]);
  }
  bool v[100000];
  fill(v, v + 100000, false);
  int cost = 0;
  v[0] = true;
  while (!pq.empty()) {
    ii edge = pq.top();
    pq.pop();
    int a = edge.first, b = edge.second;
    if (v[b]) continue;
    v[b] = true;
    cost += a;
    for (int i = 0; i < adjList[b].size(); i++) {
      ii curr = adjList[b][i];
      if (v[curr.second]) continue;
      pq.push(curr);
    }
  }
  cout << cost;
  return 0;
}
