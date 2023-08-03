import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**The class is responsible for handling most of the project logic. It is meant to act as a "helper-class", not a standalone one.
 *
 * @author Alberto Nicoletti (aka Bjastkuliar)
 * */
public class Utils {
    /** This method is responsible for reading the input file (containing the matrix in the specified format)
     * and for converting it into a matrix of long[]
     *
     * @param fileName the path-file where the input matrix is to be found*/
    public static long[][][] readMatrix(String fileName){
        long [][][] matrix;
        Scanner scanner = null;
        int size = 0;
        try{
            scanner = new Scanner(new File(fileName));
            size = Integer.parseInt(scanner.next()); //reading the size from the first line of the file
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

    /** This method performs the various transformations in order to calculate the determinant,
     * additionally it replaces all elements underneath the diagonal with [0,1]
     *
     * @param matrix the matrix outputted by readMatrix*/
    public static long[] determinant(long[][][] matrix) throws Exception {
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

                //row swap
                if (r != k) {
                    long[][] tmp = copyRow(matrix[k]);
                    matrix[k]=matrix[r];
                    matrix[r]= tmp;
                    rowSwap++;
                }

                /*if the pivot is 0 the program throws an exception because
                 *it is of no use keep evaluating the matrix*/
                if(pivot==0){
                    throw new Exception("Matrix is singular!");
                }
            }
            //gauss elimination
            for (int i=k+1; i< matrix.length; i++){
                matrix[i][k]=divide(matrix[i][k],matrix[k][k]);
                for(int j=k+1;j<matrix.length;j++){
                    matrix[i][j] = subtract(matrix[i][j],multiply(matrix[i][k],matrix[k][j]));
                }
            }

            //replace all cells below the diagonal with 0
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
            throw new Exception("Matrix is singular!");
        }

        /*correct the determinant sign according to the number of row swaps
        * if the number of row swaps is uneven, the sign has to be inverted
        * (i.e. multiplied by -1)*/
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

    /**Swaps numerator and denominator
    * If the numerator is 0, the whole fraction is set as 0/1
    * If the denominator is 0, null is returned (it is a divide by 0 error)
     *
     * @param num the fraction to be inverted*/
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

    //Reduces the fraction to its least components
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

    /**Retrieves the absolute value of the components of the fraction number first,
     * then it converts them to double and lastly performs the division. This is needed
     * in order to avoid the case in which the fraction gets cast to 0, due to the denominator
     * being larger of the numerator, leading to a "Divide by 0" error later on.
     *
     * @param number the matrix cell to be converted to a pivot*/
    private static double getPivot (long[] number ){
        double absNumerator = (double) Math.abs(number[0]), absDenominator = (double) Math.abs(number[1]);
        return absNumerator /absDenominator;
    }

    /**Copies a row of the matrix to a new one.
     *
     * @param row the row to be copied*/
    private static long[][] copyRow (long[][] row){
        long [][] tmp = new long [row.length][2];
        for(int i = 0; i<row.length;i++){
            tmp[i][0]=row[i][0];
            tmp[i][1]=row[i][1];
        }
        return tmp;
    }


    /**Performs the insertion of a fractional number in the long[][] matrix,
     * by splitting the input fractional long into numerator and denominator.
     *
     * @param matrix the matrix where the number has to be inserted
     * @param i the row index of the cell in which the number has to be inserted
     * @param j the column index of the cell in which the number has to be inserted
     * @param scanner the scanner reading the input file*/
    private static void insertNumber(long[][][] matrix, int i, int j, Scanner scanner){
        String number = scanner.next();
        String[] splitNumber = number.split("/");
        int q = 0;
        for (String num: splitNumber ) {
            matrix[i][j][q] = Integer.parseInt(num);
            q++;
        }
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
