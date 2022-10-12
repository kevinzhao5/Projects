#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cmath>
#include <array>
using namespace std;

typedef pair<int, int> ii;

int main() {
	int V, E;
	cin >> V >> E;
	vector< vector<ii> > adjList;
	adjList.assign(V + 1, vector<ii>());
	for (int i = 0; i < E; i++) {
		int u, v, d;
		cin >> u >> v >> d;
		adjList[u].push_back(ii(v, d));
	}
	priority_queue<ii> pq;
	int dist[V + 1];
	fill(dist, dist + V + 1, 1000000000);
	dist[1] = 0;
	pq.push(ii(0, 1));
	while (!pq.empty()) {
		ii p = pq.top();
		pq.pop();
		int a = -p.first, b = p.second;
		if (a > dist[b]) continue;
		for (int i = 0; i < adjList[b].size(); i++) {
			int c = adjList[b][i].first, d = adjList[b][i].second;
			if (a + d < dist[c]) {
				dist[c] = a + d;
				pq.push(ii(-dist[c], c));
			}
		}
	}
	for (int i = 1; i <= V; i++) {
		cout << dist[i] << " ";
	}
  	return 0;
}


/*#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cmath>
#include <array>
using namespace std;

int main() {
	freopen("dijkstras.in", "r", stdin);
	int V, E, S;
	cin >> V >> E >> S;
	vector< vector < pair<int, int> > > edges;
	edges.assign(V, vector< pair<int, int> >());
	for (int i = 0; i < E; ++i) {
		int u, v, d;
		cin >> u >> v >> d;
		edges[u].push_back(pair<int, int>(v, d));
	}
	priority_queue< pair<int, int>, vector< pair<int, int> >, greater< pair<int, int> > > pq;
	int dist[V];
	fill(dist, dist + V, 1000000000);
	dist[S] = 0;
	pq.push(pair<int, int>(0, S));
	while (!pq.empty()) {
		pair<int, int> p = pq.top();
		pq.pop();
		int a = p.first, b = p.second;
		if (a > dist[b]) continue;
		for (int i = 0; i < (int)edges[b].size(); i++) {
			pair<int, int> p2 = edges[b][i];
			int c = p2.first, d = p2.second, e = a + d;
			if (e < dist[c]) {
				dist[c] = e;
				pq.push(pair<int, int>(e, c));
			}
		}
	}
	freopen("dijkstras.out", "w", stdout);
	for (int i = 0; i < V; i++) {
		cout << dist[i] << " ";
	}
  return 0;
}
*/
