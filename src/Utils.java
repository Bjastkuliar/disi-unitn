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

    public static long[] determinant(long[][][] matrix){
        int rowSwap = 0;
        for (int k=0; k < matrix.length-1; k++) {
            if(matrix[k][k][0]==0){
                double pivot= getPivot(matrix[k][k]);
                int r=k;
                for (int i=k+1; i < matrix.length; i++) {
                    double newPivot = getPivot(matrix[i][k]);
                    if ( newPivot > pivot) {
                        pivot= newPivot;
                        r=i;
                    }
                }

                if(pivot==0){
                    System.err.println("Matrice singolare");
                }

                //row swap
                if (r != k) {
                    long[][] tmp = copyRow(matrix[k]);
                    matrix[k]=matrix[r];
                    matrix[r]= tmp;
                    rowSwap++;
                }
            }
            //gauss elimination
            for (int i=k+1; i< matrix.length; i++){
                matrix[i][k]=divide(matrix[i][k],matrix[k][k]);
                for(int j=k+1;j<matrix.length;j++){
                    matrix[i][j] = subtract(matrix[i][j],multiply(matrix[i][k],matrix[k][j]));
                }
            }

            for(int i = 1; i< matrix.length;i++){
                for(int j = 0; j< k;j++){
                    if(i!=j){
                        matrix[i][j]=new long[]{0,1};
                    }
                }
            }
        }


        long[] determinant = matrix[0][0];

        for (int i = 1; i<matrix.length;i++){
            determinant = multiply(determinant,matrix[i][i]);
        }
        if(determinant[0] == 0||determinant[1]==0){
            System.err.println("La matrice è singolare");
        }
        determinant= multiply(determinant, new long[]{(long)Math.pow(-1,rowSwap),1});

        return determinant;
    }

    //Multiplication between matrix cells
    private static long [] multiply(long [] num1, long [] num2){
        long[] result = new long[2];
        result[0] = num1[0]*num2[0];
        result[1] = num1[1]*num2[1];
        return reduce(result);
    }

    //Division between matrix cells
    private static long [] divide(long [] num1, long [] num2){
        return multiply(num1,invert(num2));
    }

    //Subtraction between matrix cells
    private static long [] subtract(long [] num1, long [] num2){
        long numerator, numerator1,numerator2, lcm = lcm(num1[1],num2[1]);

        long tmp = lcm/num1[1];
        numerator1 = tmp*num1[0];

        tmp = lcm/num2[1];
        numerator2 = tmp*num2[0];

        numerator = numerator1-numerator2;

        long [] temp = new long[]{numerator,lcm};

        return reduce(temp);
    }

    /*Swaps numerator and denominator
    * If the numerator is 0, the whole fraction is set as 0/1*/
    private static long [] invert(long[] num){
        long[] invert = new long[2];
        if(num[0]!=0){
            invert[0] = num[1];
            invert[1] = num[0];
        } else {
            invert = null;
            System.err.println("Divide by 0");
        }
        return invert;
    }

    //Reduces the fraction to its least
    private static long[] reduce(long[] fraction){
        long numerator, denominator;
        if(fraction[0]<0&&fraction[1]<0){
            numerator = fraction[0]*-1; //-2
            denominator = fraction[1]*-1; //20
        } else {
            numerator = fraction[0]; //-2
            denominator = fraction[1]; //20
        }

        long gcd = gcd(numerator,denominator);

        numerator = numerator/gcd;
        denominator = denominator/gcd;

        return new long[]{numerator, denominator};
    }

    //greatest common divisor
    private static long gcd(long a, long b){
        a = Math.abs(a);
        b = Math.abs(b);
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    //least common multiple
    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }

    private static double getPivot (long[] number ){
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

    private static void insertNumber(long[][][] matrix, int i, int j, Scanner scanner){
        String number = scanner.next();
        String[] splitNumber = number.split("/");
        int q = 0;
        for (String num: splitNumber ) {
            matrix[i][j][q] = Integer.parseInt(num);
            q++;
        }
    }

    public static long[] getSampleSolution(int matrixNum){
        String filePath, fileFolder = "test/det/det_matrice-", fileExt = ".txt", solution;

        if(matrixNum<10){
            filePath = fileFolder+"00"+matrixNum+fileExt;
        } else if ( matrixNum<100){
            filePath = fileFolder+"0"+matrixNum+fileExt;
        } else {
            filePath = fileFolder+matrixNum+fileExt;
        }

        try {
            Scanner scanner = new Scanner(new File(filePath));
            solution = scanner.next();
            String[] split = solution.split("/");
            return new long[]{Long.parseLong(split[0]),Long.parseLong(split[1])};
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long[] runSampleMatrix(int matrixNum){
        String filePath, fileFolder = "test/mat/matrice-", fileExt = ".txt";

        if(matrixNum<10){
            filePath = fileFolder+"00"+matrixNum+fileExt;
        } else if ( matrixNum<100){
            filePath = fileFolder+"0"+matrixNum+fileExt;
        } else {
            filePath = fileFolder+matrixNum+fileExt;
        }

        long[][][] matrix = Utils.readMatrix(filePath);
        if(matrix!= null){
            return Utils.determinant(matrix);
        }
        return null;

    }

    /**Method for printing a solution from the given file-path
     * (simply prints the first token of the file)
     *
     * @param filePath the path to the solution file**/
    public static void printSampleSolution(String filePath){
        try {
            Scanner scanner = new Scanner(new File(filePath));
            System.out.println(scanner.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**Prints the matrix in a readable manner.
     * Makes use of the Arrays.deepToString() method for
     * converting each row in a string.
     *
     * @param matrix the matrix to be printed**/
    public static void printMatrix(long[][][] matrix){
        StringBuilder stringBuilder = new StringBuilder();
        for (long[][] longs : matrix) {
            stringBuilder.append(Arrays.deepToString(longs));
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }


}
