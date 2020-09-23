package advanced;

/**
 * Your input is a compressed string of the format number[string] and the decompressed output form should be the string written number times. For example:

The input

3[abc]4[ab]c

Would be output as

abcabcabcababababc

One repetition can occur inside another. For example, 2[3[a]b] decompresses into aaabaaab
 */
public class Decompress {
    public String decomp(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int n = str.length();
        while(i < n) {
            if(Character.isDigit(str.charAt(i)) && i+1 < n && str.charAt(i+1) == '[') {
                int beg = i+1;
                int end = findEnd(str, beg+1);
                if(end>beg+1) {
                    sb.append(dup(decomp(str.substring(beg+1, end)), Character.digit(str.charAt(i), 10)));
                    i = end+1;
                } else {
                    sb.append(str.substring(i, Math.max(beg, end)+1));
                    i = Math.max(beg, end)+1;
                }
                continue;
            }
            sb.append(str.charAt(i++));
        }
        return sb.toString();
    }

    private int findEnd(String str, int i) {
        int count = 1;
        while(i < str.length()) {
            if(str.charAt(i) == ']') {
                if(count == 1) {
                    return i;
                }
                count--;
            } else if(str.charAt(i) == '[') {
                count++;
            }
            i++;
        }
        return -1;
    }
    private String dup(String str, int times) {
        StringBuilder sb = new StringBuilder(str.length()*times);
        while(times-- > 0) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Decompress().decomp("2[3[a]b]"));
    }
}
