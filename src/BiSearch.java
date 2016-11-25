public class BiSearch {
	
	public static int biSearch(int[] a, int low, int high, int found) {
		if(low > high) {
			return -1;
		}
		int m = (low + high) / 2;
		if(m >= a.length) {
			return -1;
		}
		if(a[m] == found) {
			return m;
		}
		if(a[m] > found) {
			return biSearch(a, low, m - 1, found);
		} 
		else {
			return biSearch(a, m + 1, high, found);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {20, 25, 31, 33, 38, 50, 55, 77, 100, 1111};
		System.out.println(biSearch(a, 0, 10, 55));
	}

}
