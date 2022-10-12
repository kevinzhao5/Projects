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

int N;
vector<int> s;
vector<int> pos;
int pre[100000];
ii bales[100000];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("trapped.in", "r", stdin);
  freopen("trapped.out", "w", stdout);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int a, b;
    cin >> a >> b;
    bales[i] = ii(b, a);
  }
  sort(bales, bales + N);
  for (int i = 0; i < N; i++) {
    s.push_back(bales[i].second);
    pos.push_back(bales[i].first);
  }
  for (int i = 0; i < N; i++) {
    int bound = pos[i] + s[i];
    vector<int>::iterator it = lower_bound(pos.begin(), pos.end(), bound);
    if (it == pos.end() || *it > bound) --it;
    int index = it - pos.begin(), found = i;
    for (int j = index; j >= i; j--) {
      if (s[j] - (pos[j] - pos[i]) >= 0) {
        found = j;
        break;
      }
    }
    pre[i]++;
    pre[found]--;
  }
  int area = 0, presum = 0, prevloc = -1;
  for (int i = 0; i < N; i++) {
    if (pre[i] == 0) continue;
    if (pre[i] > 0) {
      if (presum == 0) {
        prevloc = i;
      }
      presum += pre[i];
    } else if (pre[i] < 0) {
      presum += pre[i];
      if (presum == 0) {
        area += pos[i] - pos[prevloc];
      }
    }
  }
  cout << area << "\n";
  return 0;
}
