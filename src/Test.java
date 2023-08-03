import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**This class is a runnable class capable of running all 100 tests
 * stored in the repo at once, returning any mismatches in console.
 * It uses a very specific path-configuration (which is represented
 * in the repo on GitHub), therefore any matrix-solution couple that
 * one may want to add to the test suite, has to respect said configuration.
 *
 * @author Alberto Nicoletti (aka Bjastkuliar)*/
public class Test {
    public static void main(String[] args){
        int i = 1;
        while (i<=100){
            //read the solution from the solution file
            long[] solution = getSampleSolution(i);
            long[] determinant;

            //run the determinant function on the sample matrix
            try{
                determinant = runSampleMatrix(i);
            } catch (Exception e){
                determinant = solution;
                System.out.println("Matrice numero "+i+" \nSoluzione: "+ Arrays.toString(solution));
            }


            //check that the determinant produced and the original solution match
            if(determinant!=null&&solution!=null){
                long solutionFraction = solution[0]/solution[1], determinantFraction = determinant[0]/determinant[1];
                if(solutionFraction!=determinantFraction){
                    if(Math.abs(solution[0])!=Math.abs(determinant[0])||Math.abs(solution[1])!= Math.abs(determinant[1])){
                        System.out.println("Mismatch detected on matrix "+i+"!");
                        System.out.println("Solution : "+Arrays.toString(solution));
                        System.out.println("Determinant : "+Arrays.toString(determinant));
                    } else {
                        System.out.println("The solution fraction was mismatching, but the absolute values do not!\n"
                        +"Solution Fraction: "+solutionFraction+
                                "\n Determinant Fraction: "+ determinantFraction);
                    }
                }
            } else {
                System.out.println("Something is null!");
            }
            i++;
        }
    }

    /**Retrieves the solution for a given test
     *
     * @param matrixNum the file-number of the specified matrix*/
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

    /**Performs the determinant function stand-alone,
     * reading and running the operations on the matrix corresponding to a certain number.
     *
     * @param matrixNum the matrix number of the list*/
    public static long[] runSampleMatrix(int matrixNum) throws Exception {
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
}
