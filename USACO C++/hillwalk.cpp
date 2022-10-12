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

struct event {

  int a, b, c;
  bool first;

  bool operator < (const event& o) const {
      if (a != o.a) return a < o.a;
      if (first) return true;
      return b < o.b;
  }

};

struct hill {
  int a, b, c, d;

  bool operator < (const hill& o) const {
      if (b != o.b) return b < o.b;
      if (a != o.a) return a < o.a;
      if (c != o.c) return c < o.c;
      return d < o.d;
  }

  bool operator == (const hill& o) const {
      if (a == o.a && b == o.b && c == o.c && d == o.d) return true;
      return false;
  }

};

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("hillwalk.in", "r", stdin);
  freopen("hillwalk.out", "w", stdout);
  int N;
  cin >> N;
  hill hills[100000];
  event events[200000];
  for (int i = 0; i < N; i++) {
    int a, b, c, d;
    cin >> a >> b >> c >> d;
    hills[i].a = a;
    hills[i].b = b;
    hills[i].c = c;
    hills[i].d = d;
    events[i].a = a;
    events[i].b = b;
    events[i].c = i;
    events[i].first = true;
    events[i + N].a = c;
    events[i + N].b = d;
    events[i + N].c = i;
    events[i].first = false;
  }
  sort(events + 1, events + 2*N);
  multiset<hill> ms;
  int total = 0, first = 0;
  hill currHill;
  for (int i = 0; i < 2*N; i++) {
    event e = events[i];
    int a = e.a, b = e.b, c = e.c;
    hill h = hills[c];
    if (ms.count(h) > 0) {
      if (h == currHill) {
        while (i < 2 * N - 1 && events[i + 1].a == currHill.c) {
          i++;
          hill h1 = hills[events[i].c];
          if (ms.count(h1) > 0) ms.erase(h1);
          else ms.insert(h1);
        }
        while (true) {
          hill h1 = *(--ms.end());
          double y = ((1.0 * h1.d - h1.b) / (h1.c - h1.a)) * (h.c - h1.a) + h1.b;
          if (y < h.d && h1.c > h.c) {
            break;
          }
          ms.erase((--ms.end()));
          if (ms.size() == 0) break;
        }
        if (ms.size() == 0) break;
        currHill = *(--ms.end());
        total++;
      }
      ms.erase(h);
    } else {
      if (first == 0) {
        currHill = h;
        first = 1;
        total++;
      }
      ms.insert(h);
    }
  }
  cout << total;
  return 0;
}
