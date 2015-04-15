package ReplaceSpace;

public class ReplaceSpace {
	public static void Replace(char[] str, int length) {
		int spaceCount = 0, newLength, i;
		for (i = 0; i < length; i++) {
			if (str[i] == ' ')
				spaceCount++;

		}
		newLength = length + spaceCount * 2;
		str[newLength] = '\0';
		for (i = length - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				str[newLength - 1] = '0';
				str[newLength - 2] = '2';
				str[newLength - 3] = '%';
				newLength = newLength - 3;
			} else {
				str[newLength - 1] = str[i];
				newLength--;
			}

		}

	}
	public static void main(String[] args) {
		String s = "I Love U";
		char[] sc = s.toCharArray();
		int n = sc.length;	
		Replace(sc,n);
		System.out.println(sc);
	}
}
