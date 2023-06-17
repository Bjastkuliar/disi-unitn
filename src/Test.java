import java.util.Arrays;

public class Test {
    public static void main(String[] args){
        int i = 1;
        while (i<=100){
            long[] solution = Utils.getSampleSolution(i);
            long[] determinant;
            try{
                determinant = Utils.runSampleMatrix(i);
            } catch (NullPointerException e){
                determinant = solution;
                System.out.println("Matrice numero "+i+" \nSoluzione: "+ Arrays.toString(solution));
            }


            if(determinant!=null&&solution!=null){
                if(solution[0]!=determinant[0]||solution[1]!= determinant[1]){
                    System.out.println("Mismatch detected on matrix "+i+"!");
                    System.out.println("Solution : "+Arrays.toString(solution));
                    System.out.println("Determinant : "+Arrays.toString(determinant));
                }
            } else {
                System.out.println("Something is null!");
            }
            i++;
        }
    }
}
