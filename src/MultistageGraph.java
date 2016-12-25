public class MultistageGraph {
    private final static int Infinity = Integer.MAX_VALUE;
    private final static int maxsize = 100;
    public class multiStageGraph{
        //matrixΪ���ͼ�ı�ʾ,nΪ�����,matrix��1��ʼ�洢���Ժͽ��ı�Ŷ�Ӧ����
        int[][] matrix = new int[maxsize][maxsize];
        int n;
    }
    public multiStageGraph generateMultistageGraph(){
        int[][] matrix = {{0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,9,7,3,2,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity},{0,Infinity,0,Infinity,Infinity,Infinity,4,2,1,Infinity,Infinity,Infinity,Infinity},{0,Infinity,Infinity,0,Infinity,Infinity,2,7,Infinity,Infinity,Infinity,Infinity,Infinity},{0,Infinity,Infinity,Infinity,0,Infinity,Infinity,Infinity,11,Infinity,Infinity,Infinity,Infinity},{0,Infinity,Infinity,Infinity,Infinity,0,Infinity,11,8,Infinity,Infinity,Infinity,Infinity},{0,Infinity,Infinity,Infinity,Infinity,Infinity,0,Infinity,Infinity,6,5,Infinity,Infinity},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0,Infinity,4,3,Infinity,Infinity},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0,Infinity,5,6,Infinity},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0,Infinity,Infinity,4},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0,Infinity,2},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0,5},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0}};
        multiStageGraph g = new multiStageGraph();
        g.matrix = matrix;
        g.n = 12;
        return g;
    }
    public void FGRPATH(multiStageGraph g){
        //����һ��cost����洢ÿ����㵽�յ�����·�����ȣ�����һ��Des����洢ÿ����㵽�յ����ʱ��һ�����
        int[] cost = new int[g.n + 1];
        int[] des = new int[g.n + 1];
        cost[g.n] = 0;
        //����cost����
        for(int j = g.n - 1; j >= 1;j--){
            int min = Infinity,r = 0;
            //���������㷨���ҳ�r������һ����㣬ʹ��j��r�б��Ҽ���r���յ��·���������
            for(int i = j + 1; i <= g.n; i++){
                if((g.matrix[j][i] != Infinity) && (g.matrix[j][i] + cost[i] < min)){
                    min = g.matrix[j][i] + cost[i];
                    r = i;
                }
            }
            des[j] = r;
            cost[j] = min;
        }
        System.out.println("���·��Ϊ��" + cost[1]);
        System.out.println("���·��pathΪ��");
        System.out.print("1");
        int k = des[1];
        for(int i = 2; i <= g.n; i++) {
            System.out.print("->" + k);
            if(k == 12)
                break;
            k = des[k];
        }
    }

    public static void main(String[] args) {
        MultistageGraph msg =  new MultistageGraph();
        multiStageGraph g = msg.generateMultistageGraph();
        msg.FGRPATH(g);
    }
}