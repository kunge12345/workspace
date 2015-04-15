package compressstring;

public class ComprssString{
	public static String compress(String s){
		StringBuffer mystr = new StringBuffer();
		char last = s.charAt(0);
		int count = 1;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i)==last) count++;
			else {
				mystr.append(last);
				mystr.append(count);
				last = s.charAt(i);
				count = 1;
			}
			
		}
		mystr.append(last);
		mystr.append(count);
		String str = mystr.toString();
		return str;
	}
	public static void main(String[] args) {

		String teststr = "aabbbccddddaa";
		System.out.println(compress(teststr));
	}
}