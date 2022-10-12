import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'familyTree' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. 2D_STRING_ARRAY parent_child
     *  2. STRING request
     */

    

}

public class Solution {
	
	public static String familyTree(List<List<String>> parent_child, String request) {
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        int count = 0;
        for (int i = 0; i < parent_child.size(); i++) {
            for (int j = 0; j < 2; j++) {
                String str = parent_child.get(i).get(j);
                if (map.containsKey(str)) continue;
                map.put(str, count);
                count++;
            }
        }
        int[] p = new int[count];
        Arrays.fill(p, -1);
        boolean[][] isspouse = new boolean[count][count];
        boolean[][] ischild = new boolean[count][count];
        for (int i = 0; i < parent_child.size(); i++) {
            String parent = parent_child.get(i).get(0), child = parent_child.get(i).get(1);
            ischild[map.get(parent)][map.get(child)] = true;
            p[map.get(child)] = map.get(parent);
        }
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                for (int k = 0; k < count; k++) {
                    if (ischild[i][k] && ischild[j][k]) {
                        isspouse[i][j] = true;
                        isspouse[j][i] = true;
                        break;
                    }
                }
                if (!isspouse[i][j]) continue;
                if (p[i] == -1) p[i] = p[j];
                p[j] = -1;
                for (int k = 0; k < count; k++) {
                    if (p[k] == j) {
                        p[k] = i;
                    }
                }
            }
        }
        boolean[][] issibling = new boolean[count][count];
        for (int i = 0; i < count; i++) {
            if (p[i] == -1) continue;
            for (int j = i; j < count; j++) {
                if (p[j] == -1) continue;
                if (p[i] == p[j]) {
                    issibling[i][j] = true;
                    issibling[j][i] = true;
                }
            }
        }
        boolean[][] isgrandchild = new boolean[count][count];
        for (int i = 0; i < count; i++) {
            if (p[i] == -1) continue;
            if (p[p[i]] == -1) continue;
            isgrandchild[p[p[i]]][i] = true;
        }
        boolean[][] isgreatgrandchild = new boolean[count][count];
        for (int i = 0; i < count; i++) {
            if (p[i] == -1) continue;
            if (p[p[i]] == -1) continue;
            if (p[p[p[i]]] == -1) continue;
            isgreatgrandchild[p[p[p[i]]]][i] = true;
        }
        boolean[][] iscousin = new boolean[count][count];
        for (int i = 0; i < count; i++) {
            if (p[i] == -1) continue;
            for (int j = i; j < count; j++) {
                if (p[j] == -1) continue;
                if (issibling[p[i]][p[j]]) {
                    iscousin[i][j] = true;
                    iscousin[j][i] = true;
                }
            }
        }
        boolean[][] issecondcousin = new boolean[count][count];
        for (int i = 0; i < count; i++) {
            if (p[i] == -1) continue;
            for (int j = i; j < count; j++) {
                if (p[j] == -1) continue;
                if (iscousin[p[i]][p[j]]) {
                    issecondcousin[i][j] = true;
                    issecondcousin[j][i] = true;
                }
            }
        }
        boolean[][] ispibling = new boolean[count][count];
        for (int i = 0; i < count; i++) {
            if (p[i] == -1) continue;
            for (int j = 0; j < count; j++) {
                if (issibling[p[i]][j]) ispibling[i][j] = true;
            }
        }
        boolean[][] isgrandpibling = new boolean[count][count];
        for (int i = 0; i < count; i++) {
            if (p[i] == -1) continue;
            for (int j = 0; j < count; j++) {
                if (ispibling[p[i]][j]) isgrandpibling[i][j] = true;
            }
        }
        StringTokenizer st = new StringTokenizer(request);
        String str1 = st.nextToken(), str2 = st.nextToken();
        int one = map.get(str1), two = map.get(str2);
        if (isspouse[one][two]) return "spouse";
        for (int i = 0; i < count; i++) {
            if (isspouse[i][one] && i < one) one = i;
            if (isspouse[i][two] && i < two) two = i;
        }
        if (p[two] == one) return "child";
        if (issibling[one][two]) return "sibling";
        if (p[one] == two) return "parent";
        if (isgrandchild[one][two]) return "grandchild";
        if (isgrandchild[two][one]) return "grandparent";
        if (isgreatgrandchild[one][two]) return "great-grandchild";
        if (isgreatgrandchild[two][one]) return "great-grandparent";
        if (iscousin[one][two]) return "cousin";
        if (issecondcousin[one][two]) return "second cousin";
        if (ispibling[one][two]) return "pibling";
        if (ispibling[two][one]) return "nibling";
        if (isgrandpibling[one][two]) return "grandpibling";
        if (isgrandpibling[two][one]) return "grandnibling";
        return "grandnibling";
    }
	
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/data.in"));

        int numInputs = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> parent_child = new ArrayList<>();

        IntStream.range(0, numInputs).forEach(i -> {
            try {
                parent_child.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        String request = bufferedReader.readLine();

        String result = familyTree(parent_child, request);

        System.out.println(result);
    }
}
