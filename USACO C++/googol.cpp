//code in java instead

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

struct bignum {
  int num[110];
};

bignum size, leaves;

void add(bignum b, int a) {
  b.num[0] += a;
  int index = 0;
  while (index < 109 && b.num[index] > 9) {
    b.num[index + 1] += b.num[index] / 10;
    b.num[index] %= 10;
    index++;
  }
}

void add(bignum b, int a) {
  b.num[0] += a;
  int index = 0;
  while (index < 109 && b.num[index] > 9) {
    b.num[index + 1] += b.num[index] / 10;
    b.num[index] %= 10;
    index++;
  }
}

void decrement(bignum b) {
  b.num[0]--;
  int index = 0;
  while (index < 109 && b.num[index] < 0) {
    b.num[index + 1]--;
    b.num[index] += 10;
    index++;
  }
}

void multtwo(bignum b) {
  int index = 0, add = 0;
  while (index < 109) {
    b.num[index] *= 2;
    b.num[index] += add;
    add = b.num[index] / 10;
    b.num[index] %= 10;
    index++;
  }
}

int divtwo(bignum b) {
  bool initzero = true;
  int ret = 0;
  for (int i = 109; i >= 0; i--) {
    if (initzero && b.num[i] != 0) {
      initzero = false;
    }
    if (!initzero) {
      int m = b.num[i] % 2;
      if (i > 0) {
        b.num[i - 1] += m * 10;
      } else ret = m;
      b.num[i] /= 2;
    }
  }
  return ret;
}

int main() {
  ios_base::sync_with_stdio(false);
  vector<string> vec;
  string zero = "0", curr = "1", temp = "";
  while (zero.compare(curr) != 0) {
    vec.push_back(curr);
    cout << curr << "\n";
    cin >> curr >> temp;
  }
  fill(num, num + 110, 0);
  for (int i = vec.size() - 1; i > 0; i--) {
    increment();
    multtwo();
    int depth = vec.size() - i;

  }
  increment();
  string ans = "";
  bool initzero = true;
  for (int i = 109; i >= 0; i--) {
    if (initzero && num[i] != 0) {
      initzero = false;
    }
    if (!initzero) {
      ans += to_string(num[i]);
    }
  }
  cout << "Answer " << ans << "\n";
  return 0;
}
