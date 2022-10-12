import java.util.*;
import java.io.*;

public class csyllables {
	
	public static int syllables(String word) {
        String[] combos = { "ch", "ck", "ph", "sh", "th", "wh", "wr" };
        char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
        String[] prefixes = { "co", "de", "dis", "pre", "re", "un" };
        String[] suffixes = { "age", "ful", "ing", "less", "ment" };
        ArrayList<String> s = new ArrayList<String>();
        for (int i = 0; i < prefixes.length; i++) {
            if (word.length() >= prefixes[i].length() && word.substring(0, prefixes[i].length()).equals(prefixes[i])) {
                s.add(prefixes[i]);
                word = word.substring(prefixes[i].length());
                break;
            }
        }
        String suffix = "";
        for (int i = 0; i < suffixes.length; i++) {
            if (word.length() >= suffixes[i].length() && word.substring(word.length() - suffixes[i].length()).equals(suffixes[i])) {
                suffix = suffixes[i];
                word = word.substring(0, word.length() - suffixes[i].length());
                break;
            }
        }
        int initsize = s.size();
        for (int i = 0; i < combos.length; i++) {
            String rep = "" + i;
            word = word.replaceAll(combos[i], rep);
        }
        System.out.println(word);
        int totalvowelcount = 0;
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < vowels.length; j++) {
                if (word.charAt(i) == vowels[j]) {
                    totalvowelcount++;
                    break;
                }
            }
        }
        TreeSet<Integer> set = new TreeSet<Integer>();
        int currentvowelcount = 0;
        for (int i = 0; i < word.length(); i++) {
            boolean isvowel = false;
            for (int j = 0; j < vowels.length; j++) {
                if (word.charAt(i) == vowels[j]) {
                    isvowel = true;
                    break;
                }
            }
            if (isvowel) currentvowelcount++;
            else {
                if (currentvowelcount == 0 || currentvowelcount == totalvowelcount) continue;
                boolean issecondvowel = false;
                for (int j = 0; j < vowels.length; j++) {
                    if (word.charAt(i + 1) == vowels[j]) {
                        issecondvowel = true;
                        break;
                    }
                }
                if (issecondvowel) set.add(i);
                else set.add(i + 1);
            }
        }
        if (set.size() > 0) set.add(word.length());
        int previndex = 0;
        for (int i:set) {
            s.add(word.substring(previndex, i));
            previndex = i;
        }
        if (s.size() == initsize) {
            s.add(word);
        }
        if (!suffix.equals("")) s.add(suffix);
        String finalword = "";
        for (int i = 0; i < s.size(); i++) {
            finalword += s.get(i);
            if (i != s.size() - 1) finalword += "|";
        }
        for (int i = 0; i < combos.length; i++) {
            String rep = "" + i;
            finalword = finalword.replaceAll(rep, combos[i]);
        }
        System.out.println(finalword);
        int ret = 0;
        for (int i = 0; i < finalword.length(); i++) {
            if (finalword.charAt(i) == '|') ret += i;
        }
        return ret;
    }

	public static void main(String[] args) {
		syllables("seashell");
	}

}
