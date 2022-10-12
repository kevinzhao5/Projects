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

int N;
int p[100000];
int norm[100000];
vector< vector<int> > c;
int ft[100001];
int amt[100000];

int rsq(int index) {
  int sum = 0;
  while (index > 0) {
    sum += ft[index];
    index -= (index & (-index));
  }
  return sum;
}

void update(int index, int val) {
  while (index <= N) {
    ft[index] += val;
    index += (index & (-index));
  }
}

void process(int curr) {
  int init = rsq(N) - rsq(p[curr]);
  for (int i = 0; i < c[curr].size(); i++) {
    process(c[curr][i]);
  }
  int fin = rsq(N) - rsq(p[curr]);
  amt[curr] = fin - init;
  update(p[curr], 1);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  freopen("promote.in", "r", stdin);
  freopen("promote.out", "w", stdout);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> p[i];
    norm[i] = p[i];
  }
  sort(norm, norm + N);
  map<int, int> m;
  int counter = 1;
  m[norm[0]] = 1;
  for (int i = 1; i < N; i++) {
    if (norm[i] == norm[i - 1]) continue;
    counter++;
    m[norm[i]] = counter;
  }
  for (int i = 0; i < N; i++) {
    p[i] = m[p[i]];
  }
  c.assign(N, vector<int>());
  for (int i = 1; i < N; i++) {
    int t;
    cin >> t;
    t--;
    c[t].push_back(i);
  }
  fill(ft, ft + 100001, 0);
  process(0);
  for (int i = 0; i < N; i++) {
    cout << amt[i] << "\n";
  }
  return 0;
}
