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

typedef unsigned long long ull;

int main() {
	string s = "AB";
	int x = 2;
	s = 'C' + s;
	cout << s.size() << "\n";
	/*int power = (int) (pow(2.0, 17)) - 1;
	for (int i = 1; i <= 131071; i++) {
		int mask = i;
		while (mask > 0) {
      int b = (mask & (-mask));
      mask -= b;
			cout << b << " ";
    }
		cout << "\n";
	}*/
	return 0;
}
