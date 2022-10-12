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

typedef long long ull;
typedef pair<ull, ull> uu;

uu trees[300];

int under(int i, int j, int k) {
  int res = 0;
  long long x1 = trees[i].first, y1 = trees[i].second;
  long long x2 = trees[j].first, y2 = trees[j].second;
  if (x1 == x2) return 0;
  long long x3 = trees[k].first, y3 = trees[k].second;
  double slope = 1.0 * (y2 - y1) / (x2 - x1);
  int maxx = max(x1, x2), minx = min(x1, x2);
  double y = slope * (x3 - x1) + y1;
  if (y > y3) {
    res++;
    if (x3 > minx && x3 <= maxx) {
      res++;
    }
  }
  return res;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("triangles.in", "r", stdin);
  freopen("triangles.out", "w", stdout);
  int N;
  cin >> N;
  for (int i = 0; i < N; i++) {
    ull a, b;
    cin >> a >> b;
    trees[i] = uu(a, b);
  }
  int count[301];
  fill(count, count + 301, 0);
  int below[300][300];
  for (int i = 0; i < 300; i++) {
    fill(below[i], below[i] + 300, 0);
  }
  for (int i = 0; i < N; i++) {
    long long x1 = trees[i].first;
    for (int j = i + 1; j < N; j++) {
      long long x2 = trees[j].first;
      if (x2 == x1) continue;
      for (int k = 0; k < N; k++) {
        if (k == i || k == j) continue;
        if (under(i, j, k) == 2) {
          below[i][j]++;
          below[j][i]++;
        }
      }
    }
  }
  for (int i = 0; i < N; i++) {
    for (int j = i + 1; j < N; j++) {
      for (int k = j + 1; k < N; k++) {
        int ans = 0;
        int ij = 1, jk = 1, ik = 1;
        int k_ = under(i, j, k), i_ = under(j, k, i), j_ = under(i, k, j);
        long long x1 = trees[i].first, y1 = trees[i].second;
        long long x2 = trees[j].first, y2 = trees[j].second;
        long long x3 = trees[k].first, y3 = trees[k].second;
        if (k_ == 2) {
          ans--;
          jk = -1;
          ik = -1;
        } else if (i_ == 2) {
          ans--;
          ij = -1;
          ik = -1;
        } else if (j_ == 2) {
          ans--;
          ij = -1;
          jk = -1;
        } else if (k_ == 1) {
          if (x3 > x2) {
            if (x2 < x1) {
              jk = -1;
            } else {
              ik = -1;
            }
          } else {
            if (x2 < x1) {
              ik = -1;
            } else {
              jk = -1;
            }
          }
        } else if (i_ == 1) {
          if (x1 > x2) {
            if (x2 < x3) {
              ij = -1;
            } else {
              ik = -1;
            }
          } else {
            if (x2 < x3) {
              ik = -1;
            } else {
              ij = -1;
            }
          }
        } else {
          if (x2 > x1) {
            if (x1 < x3) {
              ij = -1;
            } else {
              jk = -1;
            }
          } else {
            if (x1 < x3) {
              jk = -1;
            } else {
              ij = -1;
            }
          }
        }
        ans += below[i][j] * ij + below[j][k] * jk + below[i][k] * ik;
        count[ans]++;
      }
    }
  }
  for (int i = 0; i <= N - 3; i++) {
    cout << count[i] << "\n";
  }
  return 0;
}
