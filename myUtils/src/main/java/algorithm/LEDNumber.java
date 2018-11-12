package algorithm;

/**
 * 工业Led数字显示打印
 *
 */
public class LEDNumber {
    private static final int l=0;//代表竖线
    private static final int w=1;//代表横线
    private static final int s=2;//代表空格

    public static void main(String[] args) {
        ledNum("4562562");
    }
    public static void ledNum(String numStr){
        int[][] tar = new int[0][0];
        String[] numStrArr = numStr.split("");
        for(int i=0;i<numStrArr.length;i++){
            tar= contact(tar, NumEnum.getArr(Integer.parseInt(numStrArr[i])));
        }
        printArr(tar);
    }
    /*
    多个数组拼凑成3,3*n的二维数组
     */
    static int[][] contact(int[][] tar,int[][]... arr){
        int[][] tarArr = new int[arr[0].length][(tar.length>0?tar[0].length:0)+arr[0].length];
        for(int i = 0;i< tar.length;i++){
            System.arraycopy(tar[i], 0, tarArr[i], 0, tar[i].length);
        }
        for(int i = 0;i<arr.length;i++){
            for(int j = 0;j<arr[i].length;j++){
                System.arraycopy(arr[i][j], 0, tarArr[j], tar.length>0?tar[0].length:0, arr[i][j].length);
            }
        }
        tar=tarArr;
        return tar;
    }
    static void printArr(int[][] aa){
        for (int i = 0; i < aa.length; i++) {
            for (int j = 0; j < aa[0].length; j++) {
                System.out.print(aa[i][j]==l?"|":aa[i][j]==w?"_":" ");
                if(j>0&&j%3==2){System.out.print("  ");}

            }
            System.out.println();
        }
    }

    enum NumEnum{
        NUM_0(0,new int[][]{{2,1,2},{0,2,0},{0,1,0}}),
        NUM_1(1,new int[][]{{2,1,2},{2,0,2},{1,0,1}}),
        NUM_2(2,new int[][]{{2,1,2},{2,1,0},{0,1,2}}),
        NUM_3(3,new int[][]{{2,1,2},{2,1,0},{2,1,0}}),
        NUM_4(4,new int[][]{{2,2,2},{0,1,0},{2,2,0}}),
        NUM_5(5,new int[][]{{2,1,2},{0,1,2},{2,1,0}}),
        NUM_6(6,new int[][]{{2,1,2},{0,1,2},{0,1,0}}),
        NUM_7(7,new int[][]{{2,1,2},{2,2,0},{2,2,0}}),
        NUM_8(8,new int[][]{{2,1,2},{0,1,0},{0,1,0}}),
        NUM_9(9,new int[][]{{2,1,2},{0,1,0},{2,1,0}});

        private int num;
        private int[][] arr;
        private NumEnum(int num,int[][]arr){
            this.num = num;
            this.arr = arr;
        }
        public static int[][] getArr(int num){
            for(int i=0;i<NumEnum.values().length;i++){
                if(NumEnum.values()[i].getNum()==num){
                    return NumEnum.values()[i].getArr();
                }
            }
            return null;
        }
        public int getNum() {
            return num;
        }
        public int[][] getArr() {
            return arr;
        }
        public void setArr(int[][] arr) {
            this.arr = arr;
        }
        public void setNum(int num) {
            this.num = num;
        }

    }
}
