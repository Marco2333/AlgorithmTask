import java.util.Random;


public class Select {
	public static void swap(int[] A, int i, int j) {
		 int v = A[i];
	     A[i] = A[j];
	     A[j] = v;
	}
	
	public static int[] randomArray(int size, int minimun, int maximum) {
		Random random = new Random();
		
        if (maximum < minimun) {
            System.err.println("error: maximun < minumum");
            return null;
        }

        int[] A = new int[size];
        for (int i = 0; i < size; i++) {
            A[i] = random.nextInt(maximum - minimun) + minimun;
	    }
	    return A;
	}
	 
	public static int selectK(int[] a, int k) {
		int j, low = 0, high = a.length;
		
		while(true) {
			j = QuickSort.partition(a, low, high);
			if( k == j + 1) {
				return j;
			}
			else if(k < j + 1) {
				high = j;
			}
			else {
				low = j + 1; 
			}
		}
	}
	 
    public static int selectK2(int[] A, int k, int low, int high, int r) {
        if ((high - low + 1) <= r) { //直接找
            InsertionSort.insertionSort(A, low, high);
            return low + k - 1;
        }

        while (true) {
            int n = high - low + 1;//元素数
            int group = n / r;//分组数
            int j = 0;
            if (group != 0) {
                for (int i = 0; i < group; i++) {
                    InsertionSort.insertionSort(A, low + i * r, low + (i + 1) * r - 1); //对每一组进行排序
                    swap(A, low + i, low + i * r + r / 2 - 1); //将每一组 {i} 的中间元素放在 {low+i} 位置上，即收集到low到high的前部
                }
                j = selectK2(A, group / 2 + 1, low, low + group - 1, r);//求这些中间元素的中间值mm下标
            } else {//不可分组，直接调用
                j = selectK2(A, k, low, high, r);
            }
            swap(A, low, j);//将mm放到low位置上
            j = high + 1;
            j = QuickSort.partition(A, low, j);//用mm进行划分
            if (j - low + 1 == k) {
                return j;
            } else if (j - low + 1 > k) {
//            return selectK2(A, k, low, j - 1, r);
                high = j - 1;
            } else {
//            return selectK2(A, k - (j - low + 1), j + 1, high, r);
                k = k - (j - low + 1);
                low = j + 1;
            }
        }
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int a[] = {1, 3, 2, 11, 39, 20, 100, 555, 22, 11};
		int[] a = randomArray(100000, 0 ,100000);
		long start = System.currentTimeMillis();
		int index = selectK(a, 100);
		long time = System.currentTimeMillis() - start;
		System.out.println("用时 " + time + " ms");
		System.out.println(a[index]);
	}

}
