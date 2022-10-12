//doesn't work

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

#define maxsize 150

typedef pair<int, int> ii;
typedef pair<int, ii> iii;

int N, M, Q, bessiei, bessiej, boxi, boxj, timecounter, rootc;
bool passable[maxsize][maxsize];
int timestart[maxsize][maxsize];
ii parent[maxsize][maxsize];
bool ispoint[maxsize][maxsize];
int timelow[maxsize][maxsize];
int color[maxsize][maxsize];
bool visited[maxsize][maxsize];
int rr[4] = { 1, 0, -1, 0 };
int rc[4] = { 0, -1, 0, 1 };
bool initstart[4];

void findpoints(int a, int b) {
  timelow[a][b] = timecounter;
  timestart[a][b] = timecounter;
  timecounter++;
  for (int i = 0; i < 4; i++) {
    int na = a + rr[i], nb = b + rc[i];
    if (na >= N || na < 0 || nb >= M || nb < 0 || !passable[na][nb]) continue;
    if (timestart[na][nb] != -1) {
      ii nv = ii(na, nb);
      if (nv != parent[a][b]) {
        timelow[a][b] = min(timelow[a][b], timelow[na][nb]);
      }
      continue;
    }
    if (a == boxi && b == boxj) rootc++;
    parent[na][nb] = ii(a, b);
    findpoints(na, nb);
    if (timelow[na][nb] >= timestart[a][b]) {
      ispoint[a][b] = true;
    }
    timelow[a][b] = min(timelow[a][b], timelow[na][nb]);
  }
}

void floodfill(int a, int b, int c) {
  color[a][b] = c;
  if (ispoint[a][b]) return;
  for (int i = 0; i < 4; i++) {
    int na = a + rr[i], nb = b + rc[i];
    if (na >= N || na < 0 || nb >= M || nb < 0 || !passable[na][nb] || color[na][nb] != -1) continue;
    floodfill(na, nb, c);
  }
}

void findstarts(int a, int b) {
  visited[a][b] = true;
  for (int i = 0; i < 4; i++) {
    int na = a + rr[i], nb = b + rc[i];
    if (na >= N || na < 0 || nb >= M || nb < 0 || !passable[na][nb] || visited[na][nb]) continue;
    if (na == boxi && nb == boxj) {
      initstart[i] = true;
      continue;
    }
    findstarts(na, nb);
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("pushabox.in", "r", stdin);
  freopen("pushabox.out", "w", stdout);
  cin >> N >> M >> Q;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      char c;
      cin >> c;
      if (c == '.') {
        passable[i][j] = true;
      } else if (c == '#') {
        passable[i][j] = false;
      } else if (c == 'A') {
        bessiei = i;
        bessiej = j;
        passable[i][j] = true;
      } else if (c == 'B') {
        boxi = i;
        boxj = j;
        passable[i][j] = true;
      }
    }
  }
  ii none = ii(-1, -1);
  for (int i = 0; i < N; i++) {
    fill(timestart[i], timestart[i] + M, -1);
    fill(ispoint[i], ispoint[i] + M, false);
    fill(parent[i], parent[i] + M, none);
    fill(timelow[i], timelow[i] + M, -1);
    fill(color[i], color[i] + M, -1);
    fill(visited[i], visited[i] + M, false);
  }
  timecounter = 0;
  rootc = 0;
  findpoints(boxi, boxj);
  if (rootc > 1) ispoint[boxi][boxj] = true;
  int counter = 0;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      if (passable[i][j] && color[i][j] == -1) {
        floodfill(i, j, counter);
        counter++;
      }
    }
  }
  fill(initstart, initstart + 4, false);
  findstarts(bessiei, bessiej);
  int visitable[maxsize][maxsize][4];
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      fill(visitable[i][j], visitable[i][j] + 4, -1);
    }
  }
  queue<iii> q;
  for (int i = 0; i < 4; i++) {
    if (initstart[i]) {
      visitable[boxi][boxj][i] = 1;
      q.push(iii(i, ii(boxi, boxj)));
    }
  }
  while (!q.empty()) {
    iii curr = q.front();
    q.pop();
    int a = curr.second.first, b = curr.second.second, c = curr.first;
    int na = a + rr[c], nb = b + rc[c];
    if (na >= N || na < 0 || nb >= M || nb < 0 || !passable[na][nb] || visitable[na][nb][c] != -1) continue;
    visitable[na][nb][c] = 1;
    q.push(iii(c, ii(na, nb)));
    for (int i = 0; i < 4; i++) {
      int nna = na - rr[i], nnb = nb - rc[i];
      if (nna >= N || nna < 0 || nnb >= M || nnb < 0 || !passable[nna][nnb] || visitable[na][nb][i] != -1) continue;
      if (ispoint[na][nb] && color[nna][nnb] != color[a][b]) continue;
      visitable[na][nb][i] = 1;
      q.push(iii(i, ii(na, nb)));
    }
  }
  for (int i = 0; i < Q; i++) {
    int R, C;
    cin >> R >> C;
    R--;
    C--;
    bool b = false;
    for (int j = 0; j < 4; j++) {
      if (visitable[R][C][j] == 1) {
        b = true;
      }
    }
    if (R == boxi && C == boxj) b = true;
    if (b) cout << "YES\n";
    else cout << "NO\n";
  }
  return 0;
}
