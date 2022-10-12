/*
ID: awesome25
TASK: fence6
LANG: C++11
*/

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
using namespace std;

typedef pair<int, int> ii;
typedef pair<int, ii> iii;

int N, vcounter;
int lens[101];
vector< vector<int> > eleft;
vector< vector<int> > eright;
int vnum[101][2];
vector< vector<ii> > adjlist;

void recprocess(int a, int p) {
  for (int i = 0; i < eleft[a].size(); i++) {
    if (eleft[a][i] == p) {
      vnum[a][0] = vcounter;
      return;
    }
  }
  vnum[a][1] = vcounter;
}

void process(int a) {
  if (vnum[a][0] == -1) {
    vnum[a][0] = vcounter;
    for (int i = 0; i < eleft[a].size(); i++) {
      recprocess(eleft[a][i], a);
    }
    vcounter++;
  }
  if (vnum[a][1] == -1) {
    vnum[a][1] = vcounter;
    for (int i = 0; i < eright[a].size(); i++) {
      recprocess(eright[a][i], a);
    }
    vcounter++;
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("fence6.in", "r", stdin);
  freopen("fence6.out", "w", stdout);
  cin >> N;
  fill(lens, lens + N + 1, -1);
  N++;
  eleft.assign(N, vector<int>());
  eright.assign(N, vector<int>());
  N--;
  N *= 2;
  adjlist.assign(N, vector<ii>());
  N /= 2;
  for (int i = 0; i < N; i++) {
    int s, Ls, N1s, N2s;
    cin >> s >> Ls >> N1s >> N2s;
    lens[s] = Ls;
    for (int j = 0; j < N1s; j++) {
      int x;
      cin >> x;
      eleft[s].push_back(x);
    }
    for (int j = 0; j < N2s; j++) {
      int x;
      cin >> x;
      eright[s].push_back(x);
    }
  }
  for (int i = 0; i <= 100; i++) {
    fill(vnum[i], vnum[i] + 2, -1);
  }
  vcounter = 0;
  for (int i = 1; i <= N; i++) {
    if (lens[i] == -1) continue;
    if (vnum[i][0] == -1 || vnum[i][1] == -1) {
      process(i);
    }
  }
  for (int i = 1; i <= N; i++) {
    int a = vnum[i][0], b = vnum[i][1];
    adjlist[a].push_back(ii(b, lens[i]));
    adjlist[b].push_back(ii(a, lens[i]));
  }
  int minperimeter = 1000000000;
  for (int i = 0; i < vcounter; i++) {
    priority_queue< iii, vector<iii>, greater<iii> > pq;
    int dist[201];
    int parent[201];
    int secondvertex[201];
    fill(dist, dist + 201, 1000000000);
    fill(parent, parent + 201, -1);
    fill(secondvertex, secondvertex + 201, -1);
    dist[i] = 0;
    pq.push(iii(0, ii(i, -1)));
    while (!pq.empty()) {
      iii curr = pq.top();
      pq.pop();
      int a = curr.first, b = curr.second.first, o = curr.second.second;
      if (b != i && secondvertex[b] == -1) secondvertex[b] = o;
      else if (secondvertex[b] != o) {
        int peri = a + dist[b];
        minperimeter = min(minperimeter, peri);
      }
      if (a > dist[b]) continue;
      for (int j = 0; j < adjlist[b].size(); j++) {
        ii p = adjlist[b][j];
        int c = p.first, d = p.second, ndist = d + a;
        if (b == i) o = c;
        if (c == parent[b]) continue;
        if (ndist < dist[c]) {
          parent[c] = b;
          dist[c] = ndist;
        }
        pq.push(iii(ndist, ii(c, o)));
      }
    }
  }
  cout << minperimeter << "\n";
  return 0;
}
