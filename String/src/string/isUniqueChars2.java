package string;

public class isUniqueChars2 {
	public static boolean check(String str) {
		if (str.length() > 256)
			return false;
		boolean[] char_set = new boolean[256];
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i);
			if (char_set[val]) { // Already found this char in string
				return false;
			}
			char_set[val] = true;

		}
		return true;
	}
	public static boolean check2(String str) {
		int checker = 0;
		for (int i=0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0) {
				return false;
			}
			checker |= (1 << val);
		}
		return true;
	}
	public static void main(String[] args) { 
		String str = "abcd";
		System.out.println(check2(str));
				
	} 
}