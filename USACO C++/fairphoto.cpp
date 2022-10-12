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
#include <unordered_set>
#include <stack>
using namespace std;

struct vs {
    int v[8], bm, x;
    bool operator <(const vs& n) const {
        for (int i = 0; i < 8; i++) {
            if (v[i] == n.v[i]) continue;
            if (v[i] < n.v[i]) return true;
            else return false;
        }
        if (bm < n.bm) return true;
        return false;
    }
};

typedef pair<int, int> ii;

int N, K;
ii c[100001];
set<vs> m;
map<int, int> mt;
int mr[8];
int ct[8];
vs p[8];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    freopen("fairphoto.in", "r", stdin);
    freopen("fairphoto.out", "w", stdout);
    cin >> N >> K;
    for (int i = 1; i <= N; i++) {
        int X = 0, B = 0;
        cin >> X >> B;
        B--;
        c[i] = ii(X, B);
    }
    for (int i = 0; i < 10; i++) {
        mt[1 << i] = i;
    }
    sort(c + 1, c + N + 1);
    fill(mr, mr + 8, -1);
    fill(ct, ct + 8, 0);
    int a = -1;
    for (int i = 0; i <= N; i++) {
        int x, b;
        if (i > 0) {
            x = c[i].first;
            b = c[i].second;
            ct[b]++;
            mr[b] = i;
        }
        ii tm[8];
        for (int j = 0; j < 8; j++) {
            tm[j] = ii(mr[j], j);
        }
        sort(tm, tm + 8);
        int ij = 0;
        for (int j = 7; j >= 0; j--) {
            if (tm[j].first == -1) {
                ij = j;
                break;
            }
        }
        int pm[9];
        pm[8] = 0;
        for (int j = 7; j >= 0; j--) {
            pm[j] = pm[j + 1] + (1 << tm[j].second);
            int ma = pm[j], su = ct[mt[(ma & (-ma))]];
            vs cm;
            cm.bm = ma;
            for (int k = 0; k < 8; k++) {
                cm.v[k] = ct[k];
            }
            while (ma > 0) {
                int lsb = (ma & (-ma));
                ma -= lsb;
                cm.v[mt[lsb]] = ct[mt[lsb]] - su;
            }
            if (i > 0) {
                if (m.count(cm) > 0) {
                    if (8 - j >= K) {
                        a = max(a, x - (*m.find(cm)).x);
                    }
                } else {
                    if (i < N) {
                        cm.x = c[i + 1].first;
                        m.insert(cm);
                    }
                }
            } else {
                cm.x = c[i + 1].first;
                m.insert(cm);
            }
        }
    }
    cout << a << "\n";
    return 0;
}
