import java.util.Arrays;

public class Useless {
    public static void determinant(long[][][] matrix){
        for(int j = 0; j<matrix.length;j++){//select column
            for(int i = 1;i<matrix[j].length;i++){//select row

                //compute m = num/den
                double fl = (double) matrix[0][j][0] /matrix[0][j][1];
                double act = (double) matrix[i][j][0] /matrix[i][j][1];
                double m = act/fl;
                System.out.println("M: "+m);
                //apply m over row
                for(int k = j+1;k<matrix[i].length;k++){
                    //A[ij] -= A[i 0] * A[i-1 j]
                    long cellNum = matrix[i][k][0];
                    long cellDen = matrix[i][k][1];
                    System.out.println("Cell: "+Arrays.toString(matrix[i][k])+
                            "\nCell above: "+ Arrays.toString(matrix[i - 1][k]) +
                            "\nCell above times m: ["+m * matrix[i-1][k][0]+","+m * matrix[i-1][k][1]+"]"+
                            "\nResult: ["+(cellNum - (m * matrix[i-1][k][0]))+","+(cellDen - (m * matrix[i-1][k][1]))+"]");
                    matrix[i][k][0] = (long) (cellNum - (m * matrix[i-1][k][0]));
                    matrix[i][k][1] = (long) (cellDen - (m * matrix[i-1][k][1]));
                }
                //set the m-cell to 0
                matrix[i][0][0] = 0;
                matrix[i][0][1] = 1;
            }
            break;
        }
        Utils.printMatrix(matrix);
    }
    public static void determinant2(double[][] matrix){
        for(int j = 0; j<matrix.length;j++){
            for(int i = 1;i<matrix[j].length;i++){//select row

                double m = matrix[i][j] /matrix[j][j];
                System.out.println("M: "+m);
                //apply m over each cell in the row
                for(int k = 1;k<matrix[i].length;k++){
                    //A[ij] -= A[i 0] * A[i-1 j]
                    double cell = matrix[i][k];
                    matrix[i][k] = (cell - (m * matrix[i-1][k]));
                }
                //set the m-cell to 0
                //matrix[i][j] = 0;
            }
        }

        Test.printMatrix(matrix);
    }
}
