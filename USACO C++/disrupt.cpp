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

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("disrupt.in", "r", stdin);
  freopen("disrupt.out", "w", stdout);
  int N, M;
  cin >> N >> M;
  vector< vector<int> > op;
  vector< vector<ii> > np;
  vector< vector<int> > rp;
  op.asign(N, vector<int>());
  np.asign(N, vector<ii>());
  rp.asign(N, vector<int>());
  ii o[50000];
  for (int i = 0; i < N - 1; i++) {
    int p, q;
    cin >> p >> q;
    p--;
    q--;
    o[i] = ii(p, q);
    op[p].push_back(q);
    op[q].push_back(p);
  }
  for (int i = 0; i < M; i++) {
    int p, q, r;
    cin >> p >> q >> r;
    p--;
    q--;
    np[p].push_back(q, r);
    np[q].push_back(p, r);
  }
  
  return 0;
}
