#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cmath>
#include <array>
using namespace std;

typedef pair<int, int> ii;

ii f[1001][1001];
int rr[] = { 1, -1, 0, 0 };
int rc[] = { 0, 0, 1, -1 };
int N;

void floodfill(int& a, int& b, int& c) {
  if (a < 0 || a >= N || b < 0 || b >= N || f[a][b].second != 0 || f[a][b].first == 0) return;
  f[a][b].second = c;
  for (int i = 0; i < 4; i++) {
    int na = a + rr[i], nb = b + rc[i];
    floodfill(na, nb, c);
  }
}

int main() {
    freopen("floodfill.in", "r", stdin);
    cin >> N;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int x;
        cin >> x;
        f[i][j] = ii(x, 0);
      }
    }
    int counter = 1;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (f[i][j].second == 0 && f[i][j].first == 1) {
          floodfill(i, j, counter);
          counter++;
        }
      }
    }
    freopen("floodfill.out", "w", stdout);
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        cout << f[i][j].second << " ";
      }
      cout << "\n";
    }
    return 0;
}
