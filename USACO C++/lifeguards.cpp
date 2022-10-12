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
  freopen("lifeguards.in", "r", stdin);
  freopen("lifeguards.out", "w", stdout);
  int N;
  cin >> N;
  ii guards[200000];
  for (int i = 0; i < N; i++) {
    int a, b;
    cin >> a >> b;
    guards[i] = ii(a, i);
    guards[i + N] = ii(b, i);
  }
  sort(guards, guards + 2*N);
  int totalTime = 0, pTime = guards[0].first;
  int solo[100000];
  fill(solo, solo + 100000, 0);
  multiset<int> ms;
  ms.insert(guards[0].second);
  for (int i = 1; i < 2*N; i++) {
    ii curr = guards[i];
    int a = curr.first, b = curr.second;
    if (ms.size() > 0) totalTime += a - pTime;
    if (ms.size() == 1) solo[*ms.begin()] += a - pTime;
    if (ms.count(b) > 0) ms.erase(b);
    else ms.insert(b);
    pTime = a;
  }
  int ans = 0;
  for (int i = 0; i < N; i++) {
    ans = max(ans, totalTime - solo[i]);
  }
  cout << ans;
  return 0;
}
