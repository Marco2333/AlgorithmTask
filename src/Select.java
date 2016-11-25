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
        if ((high - low + 1) <= r) { //ֱ����
            InsertionSort.insertionSort(A, low, high);
            return low + k - 1;
        }

        while (true) {
            int n = high - low + 1;//Ԫ����
            int group = n / r;//������
            int j = 0;
            if (group != 0) {
                for (int i = 0; i < group; i++) {
                    InsertionSort.insertionSort(A, low + i * r, low + (i + 1) * r - 1); //��ÿһ���������
                    swap(A, low + i, low + i * r + r / 2 - 1); //��ÿһ�� {i} ���м�Ԫ�ط��� {low+i} λ���ϣ����ռ���low��high��ǰ��
                }
                j = selectK2(A, group / 2 + 1, low, low + group - 1, r);//����Щ�м�Ԫ�ص��м�ֵmm�±�
            } else {//���ɷ��飬ֱ�ӵ���
                j = selectK2(A, k, low, high, r);
            }
            swap(A, low, j);//��mm�ŵ�lowλ����
            j = high + 1;
            j = QuickSort.partition(A, low, j);//��mm���л���
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
		System.out.println("��ʱ " + time + " ms");
		System.out.println(a[index]);
	}

}
