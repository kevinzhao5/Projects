#include <iostream>
#include <vector>
using namespace std;

vector< vector<long long> > at;

void rec(long long bmask, long long d) {

}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(0);
  int N, K, Q;
  cin >> N >> K >> Q;
  if (K > 20) {
    for (int i = 0; i < Q; i++) {
      
    }
  }
  vector< vector<long long> > v;
  v.assign(N, vector<long long>());
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      char c;
      cin >> c;
      if (c == '0') v[i].push_back(0);
      else v[i].push_back(1);
    }
  }
  long long m = 1000000007;
  for (int i = 0; i < Q; i++) {
    long long bs, s, bt, t;
    cin >> bs >> s >> bt >> t;
    long long a = 0;
    at.assign(pow())
  }
  return 0;
}
