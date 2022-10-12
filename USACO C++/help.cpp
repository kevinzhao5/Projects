#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cmath>
#include <array>
#include <set>
#include <algorithm>
using namespace std;

#define mod 1000000007;

typedef pair<int, int> ii;
typedef unsigned long long ull;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("help.in", "r", stdin);
  freopen("help.out", "w", stdout);
  int N;
  cin >> N;
  ii intvs[100000];
  for (int i = 0; i < N; i++) {
    int a, b;
    cin >> a >> b;
    intvs[i] = ii(a, b);
  }
  sort(intvs, intvs + N);
  int pow2[100001];
  pow2[0] = 1;
  for (int i = 1; i <= N; i++) {
    pow2[i] = (2 * pow2[i - 1]) % mod;
  }
  multiset<int> ms;
  ull total = 1;
  ms.insert(intvs[0].second);
  for (int i = 1; i < N; i++) {
    total = (total * 2) % mod;
    ii curr = intvs[i];
    int a = curr.first, b = curr.second;
    while (*ms.begin() < a) {
      ms.erase(ms.begin());
      if (ms.size() == 0) break;
    }
    total = (total + pow2[i - ms.size()]) % mod;
    ms.insert(b);
  }
  cout << total;
  return 0;
}
