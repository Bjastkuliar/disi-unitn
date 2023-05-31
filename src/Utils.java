import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Utils {
    public static long[][][] readMatrix(String fileName){
        long [][][] matrix;
        Scanner scanner = null;
        int size = 0;
        try{
            scanner = new Scanner(new File(fileName));
            size = Integer.parseInt(scanner.next());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        matrix = new long[size][size][2];


        if(scanner!= null){
            for(int i = 0; i<size; i++){
                for (int j = 0; j<size; j++){
                    insertNumber(matrix, i, j, scanner);
                }
            }
        }

        if(size != 0){
            return matrix;
        } else { return null;}

    }

    public static void printMatrix(long[][][] matrix){
        StringBuilder stringBuilder = new StringBuilder();
        for (long[][] longs : matrix) {
            stringBuilder.append(Arrays.deepToString(longs));
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    public static long[] determinant(long[][][] matrix){
        for (int k=0; k < matrix.length-2; k++) {
            if(matrix[k][k][0]==0){
                printMatrix(matrix);
                long pivot= getPivot(matrix[k][k]);
                int r=k;
                for (int i=k+1; i < matrix.length; i++) {
                    long newPivot = getPivot(matrix[i][k]);
                    if ( newPivot > pivot) {
                        pivot= newPivot;
                        r=i;
                    }
                }

                //row swap
                if (r != k) {
                    long[][] tmp = copyRow(matrix[k]);
                    matrix[k]=matrix[r];
                    matrix[r]= tmp;
                }
            }
            //gauss elimination
            for (int i=k+1; i< matrix.length; i++){
                matrix[i][k][0]=matrix[i][k][0]/matrix[k][k][0];
                if(matrix[i][k][0]/matrix[k][k][0]!=0){
                    matrix[i][k][1]=matrix[i][k][1]/matrix[k][k][1];
                }
                for(int j=k+1;j<matrix.length;j++){
                    matrix[i][j][0]=matrix[i][j][0]-matrix[i][k][0]*matrix[k][j][0];
                    if(matrix[i][j][0]-matrix[i][k][0]*matrix[k][j][0]!=0){
                        matrix[i][j][1]=matrix[i][j][1]-matrix[i][k][1]*matrix[k][j][1];
                    }
                }
            }
            for(int i = 1; i< matrix.length;i++){
                for(int j = 0; j< k;j++){
                    if(i!=j){
                        matrix[i][j][0]=0;
                        matrix[i][j][1]=1;
                    }
                }
            }
        }


        long[] determinant = new long[2];
        determinant[0]=matrix[0][0][0];
        determinant[1]=matrix[0][0][1];

        for (int i = 1; i<matrix.length;i++){
            determinant[0]*=matrix[i][i][0];
            determinant[1]*=matrix[i][i][1];
        }
        if(determinant[0] == 0||determinant[1]==0){
            System.err.println("La matrice è singolare");
        }
        return determinant;
    }

    private static long getPivot (long[] number ){
        return Math.abs(number[0]/number[1]);
    }

    private static long[][] copyRow (long[][] row){
        long [][] tmp = new long [row.length][2];
        for(int i = 0; i<row.length;i++){
            tmp[i][0]=row[i][0];
            tmp[i][1]=row[i][1];
        }
        return tmp;
    }

    public static void printSampleSolution(String filePath){
        try {
            Scanner scanner = new Scanner(new File(filePath));
            System.out.println(scanner.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    private static void insertNumber(long[][][] matrix, int i, int j, Scanner scanner){
        String number = scanner.next();
        String[] splitNumber = number.split("/");
        int q = 0;
        for (String num: splitNumber ) {
            matrix[i][j][q] = Integer.parseInt(num);
            q++;
        }
    }
}
