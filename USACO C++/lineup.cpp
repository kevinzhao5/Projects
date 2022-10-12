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

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("lineup.in", "r", stdin);
  freopen("lineup.out", "w", stdout);
  int N, K;
  cin >> N >> K;
  int B[100000];
  for (int i = 0; i < N; i++) {
    cin >> B[i];
  }
  int ans = 0, leftPointer = 0;
  vector<int> starts;
  multiset<int> ms, sizes;
  map<int, int> msize;
  for (int i = N - 1; i >= 0; i--) {
    int x = B[i];
    if (ms.count(x) == 0) {
      ms.insert(x);
      starts.push_back(i);
    }
  }
  for (int i = 0; i < N; i++) {
    int x = B[i];
    if (msize.count(x) == 0) {
      msize[x] = 1;
      sizes.insert(1);
    } else {
      sizes.erase(sizes.find(msize[x]));
      msize[x] = msize[x] + 1;
      sizes.insert(msize[x]);
    }
    if (msize.size() > K + 1) {
      int a = starts.back();
      bool b = true;
      while (leftPointer <= a) {
        int y = B[leftPointer];
        if (msize[y] == 1) {
          msize.erase(y);
          sizes.erase(sizes.find(1));
        } else {
          sizes.erase(sizes.find(msize[y]));
          msize[y] = msize[y] - 1;
          sizes.insert(msize[y]);
        }
        leftPointer++;
        if (msize.size() == K + 1 && leftPointer < a) {
          b = false;
          break;
        }
      }
      if (b) starts.pop_back();
    }
    ans = max(ans, *(--sizes.end()));
  }
  cout << ans;
  return 0;
}
