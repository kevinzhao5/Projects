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

typedef pair<int, int> ii;

int N, K;
vector< vector<int> > adjList;
vector< vector<ii> > lcaPairs;
vector< vector<int> > c;
int parent[50001];
int par[50001];
int r[50001];
int anc[50001];
bool visited[50001];
int val[50001];
int flows[50001];

void process(int v, int p) {
  parent[v] = p;
  for (int i = 0; i < adjList[v].size(); i++) {
    int u = adjList[v][i];
    if (u == p) continue;
    c[v].push_back(u);
    process(u, v);
  }
}

int finds(int v) {
  if (par[v] != v) {
    par[v] = finds(par[v]);
  }
  return par[v];
}

void uni(int v, int u) {
  int vRank = r[finds(v)], uRank = r[finds(u)];
  if (vRank > uRank) {
    par[finds(u)] = finds(v);
  } else if (vRank < uRank) {
    par[finds(v)] = finds(u);
  } else {
    par[finds(u)] = finds(v);
    r[finds(v)]++;
  }
}

void tarjan(int v) {
  par[v] = v;
  r[v] = 1;
  anc[v] = v;
  for (int i = 0; i < c[v].size(); i++) {
    int u = c[v][i];
    tarjan(u);
    uni(v, u);
    anc[finds(v)] = v;
  }
  visited[v] = true;
  for (int i = 0; i < lcaPairs[v].size(); i++) {
    int u = lcaPairs[v][i].first;
    if (visited[u]) {
      lcaPairs[v][i].second = anc[finds(u)];
    }
  }
}

int calculate(int v) {
  int flow = val[v];
  for (int i = 0; i < c[v].size(); i++) {
    int u = c[v][i];
    flow += calculate(u);
  }
  flows[v] = flow;
  return flow;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("maxflow.in", "r", stdin);
  freopen("maxflow.out", "w", stdout);
  cin >> N >> K;
  adjList.assign(N + 1, vector<int>());
  lcaPairs.assign(N + 1, vector<ii>());
  c.assign(N + 1, vector<int>());
  fill(parent, parent + 50001, -1);
  fill(par, par + 50001, -1);
  fill(r, r + 50001, 0);
  fill(anc, anc + 50001, -1);
  fill(visited, visited + 50001, false);
  fill(val, val + 50001, 0);
  fill(flows, flows + 50001, 0);
  for (int i = 0; i < N - 1; i++) {
    int x, y;
    cin >> x >> y;
    adjList[x].push_back(y);
    adjList[y].push_back(x);
  }
  for (int i = 0; i < K; i++) {
    int s, t;
    cin >> s >> t;
    lcaPairs[s].push_back(ii(t, 0));
    lcaPairs[t].push_back(ii(s, 0));
  }
  int root = 1;
  process(root, 0);
  tarjan(root);
  for (int i = 1; i <= N; i++) {
    for (int j = 0; j < lcaPairs[i].size(); j++) {
      ii curr = lcaPairs[i][j];
      int f = curr.first, s = curr.second, ps = parent[s];
      if (s == 0) continue;
      val[i]++;
      val[f]++;
      val[s]--;
      val[ps]--;
    }
  }
  calculate(root);
  int ans = 0;
  for (int i = 1; i <= N; i++) {
    ans = max(ans, flows[i]);
  }
  cout << ans << "\n";
  return 0;
}
