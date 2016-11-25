
public class QuickSort {
	public static int partition(int[] a, int m, int p) {
		int v = a[m],
			i = m;
		while(i < p) {
			while(++i < p && a[i] < v);
			while(--p >= 0 && a[p] > v);
			if(i < p) {
				int temp = a[i];
				a[i] = a[p];
				a[p] = temp;
			}
		}
		a[m] = a[p];
		a[p] = v;
		
		return p;
	} 
	
	public static void quickSort(int[] a, int low, int high) {
		if(low < high) {
			int j = high + 1;
			j = partition(a, low, j);
			quickSort(a, low, j - 1);
			quickSort(a, j + 1, high);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = {1, 3, 2, 11, 39, 20, 100, 555, 22, 11};
		quickSort(a, 0, 9);
		for(int i = 0;i < 10;i++) {
			System.out.println(a[i]);
		}
		
	}

}
