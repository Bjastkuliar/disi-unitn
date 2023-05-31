import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static double[][] readMatrix(String fileName){
        double [][] matrix;
        Scanner scanner = null;
        int size = 0;
        try{
            scanner = new Scanner(new File(fileName));
            size = Integer.parseInt(scanner.next());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        matrix = new double[size][size];

        if(scanner!= null){
            for(int i = 0; i<size; i++){
                for (int j = 0; j<size; j++){
                    String number = scanner.next();
                    String[] splitNumber = number.split("/");
                    long[] num = new long[2];
                    for (int q = 0; q<2; q++) {
                        num[q] = Long.parseLong(splitNumber[q]);
                    }
                    matrix[i][j]= (double) num[0] /num[1];
                }
            }
        }

        if(size != 0){
            return matrix;
        } else { return null;}

    }

    public static double determinant(double[][] matrix){
        int rowSwap = 0;
        for (int k=0; k < matrix.length-1; k++) {
            if(matrix[k][k]==0){//pivot search
                double pivot= Math.abs(matrix[k][k]);
                int r=k;
                for (int i=k+1; i < matrix.length; i++) {
                    double newPivot = Math.abs(matrix[i][k]);
                    if ( newPivot > pivot) {
                        pivot= newPivot;
                        r=i;
                    }
                }
                if(pivot==0){
                    System.err.println("Matrice singolare");
                }
                if (r != k) {//swap row
                    double[] tmp = matrix[k];
                    matrix[k]=matrix[r];
                    matrix[r]= tmp;
                    rowSwap++;
                }
            }
            for (int i=k+1; i< matrix.length; i++){//gauss elimination
                matrix[i][k]=matrix[i][k]/matrix[k][k];
                for(int j= k+1; j<matrix.length;j++){
                    matrix[i][j]=matrix[i][j]-matrix[i][k]*matrix[k][j];
                }
            }
            for(int i = 1; i<matrix.length;i++){
                for(int j = 0;j<k+1;j++){
                    if(i!=j){
                        matrix[i][j]=0;
                    }
                }
            }

        }
        if(matrix[matrix.length-1][matrix.length-1]==0){
            System.err.println("Matrice Singolare");
        }

        printMatrix(matrix);

        double determinant = matrix[0][0];
        for (int i = 1; i<matrix.length;i++){
            determinant *= matrix[i][i];
        }
        System.out.println("swaps"+rowSwap);
        determinant*=Math.pow(-1,rowSwap);
        if(determinant == 0){
            System.err.println("La matrice è singolare");
        }
        return determinant;
    }

    public static void printMatrix(double[][] matrix){
        StringBuilder stringBuilder = new StringBuilder();
        for (double[] doubles : matrix) {
            stringBuilder.append(Arrays.toString(doubles));
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    public static void printSampleSolution(String filePath){
        try {
            Scanner scanner = new Scanner(new File(filePath));
            String[] nums = scanner.next().split("/");
            double solution = Double.parseDouble(nums[0])/Double.parseDouble(nums[1]);
            System.out.println("Solution: "+solution);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
