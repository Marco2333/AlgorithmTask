public class InsertionSort {
	
	public static void insertionSort(int[] a, int low, int high) {
		for(int i = low + 1;i <= high;i++) {
			int temp = a[i];
			int position = i;
			for(int j = i - 1;j >= 0;j--) {
				if(a[j] > temp) {
					a[j + 1] = a[j];
					position -= 1;
				} else {
					break;
				}
			}
			a[position] = temp;
		}
	}
	
	public static void insertionSort(int[] a) {
		insertionSort(a, 0, a.length - 1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = {1, 3, 2, 11, 39, 20, 100, 555, 22, 11};
		insertionSort(a, 0, 9);
		
		for(int i = 0;i < 10;i++) {
			System.out.println(a[i]);
		}
	}

}
