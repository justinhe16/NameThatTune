public class Main {

	public static void main(String args[]){
		System.out.println(harmonicSum(3));
	}

	static void mystery(String s){
		int l = s.length();
		if (l > 1){
			System.out.println(s);
			String k = s.substring(0, l-1);
			mystery(k);
			System.out.println(k);
		}
	}

	static float harmonicSum(int n){
		float x = (float) n;
		if (n <= 0){
			return 0;
		}
		else {
			return ((1/x) + harmonicSum(n-1));
		}
	}
}
