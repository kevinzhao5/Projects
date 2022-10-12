#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <cstdio>

using namespace std;

int main() {
  freopen("stampede.in", "r", stdin);
  freopen("stampede.out", "w", stdout);

  int N;
  cin >> N;

  vector<pair<int, int> > events;
  for (int i = 0; i < N; i++) {
    int x, y, r;
    cin >> x >> y >> r;
    x *= -r;

    /* We'll use a negative y value to indicate the end of an interval. */
    events.push_back(make_pair(x - r, y));
    events.push_back(make_pair(x, -y));
  }
  sort(events.begin(), events.end());

  set<int> seen;
  set<int> active;
  for (int i = 0; i < events.size(); ) {
    int j;
    for (j = i; j < events.size() && events[i].first == events[j].first; j++) {
      int y = events[j].second;
      if (y > 0) {
        active.insert(y);
      } else {
        active.erase(-y);
      }
    }
    if (!active.empty()) {
      seen.insert(*active.begin());
    }
    i = j;
  }
  cout << seen.size() << endl;

  return 0;
}