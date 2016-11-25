public class Max {
	
	public static double max(double[] arr, int i) {
		if(i < arr.length - 1) {
			double max = max(arr, i + 1);
			if(arr[i] > max) {
				return arr[i];
			}
			else {
				return max;
			}
		} else {
			return arr[i];
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] a = {1,3,4,5,2,-100,100,33,1000,55};
		System.out.println(max(a, 0));
	}

}
