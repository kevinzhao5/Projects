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

int N;
int ft[100001];

void update(int i, int val) {
  while (i <= N) {
    ft[i] += val;
    i += (i & (-i));
  }
}

int rsq(int i) {
  int sum = 0;
  while (i > 0) {
    sum += ft[i];
    i -= (i & (-i));
  }
  return sum;
}

int findans(int left[100001], int right[100001]) {
  fill(ft, ft + 100001, 0);
  int revl[100001];
  fill(revl, revl + 100001, 0);
  for (int i = 1; i <= N; i++) {
    revl[left[i]] = i;
  }
  int curr = 0;
  for (int i = 1; i <= N; i++) {
    int index = revl[right[i]];
    curr += index - 1 - rsq(index);
    update(index, 1);
  }
  int revr[100001];
  fill(revr, revr + 100001, 0);
  for (int i = 1; i <= N; i++) {
    revr[right[i]] = i;
  }
  int ret = curr;
  for (int i = N; i > 1; i--) {
    curr -= N - revr[left[i]];
    curr += revr[left[i]] - 1;
    ret = min(ret, curr);
  }
  return ret;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("mincross.in", "r", stdin);
  freopen("mincross.out", "w", stdout);
  cin >> N;
  int left[100001];
  int right[100001];
  fill(left, left + 100001, 0);
  fill(right, right + 100001, 0);
  for (int i = 1; i <= N; i++) {
    cin >> left[i];
  }
  for (int i = 1; i <= N; i++) {
    cin >> right[i];
  }
  int ans1 = findans(left, right), ans2 = findans(right, left), ans = min(ans1, ans2);
  cout << ans << "\n";
  return 0;
}
