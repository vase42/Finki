import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class LDS {
    private static int najdolgaOpagackaSekvenca(int[] a) {
        Integer [] ones = new Integer[a.length];
        Arrays.fill(ones, 1);
        for(int i=1;i<ones.length;i++){
            for(int j=0;j<i;j++){
                if(a[i]<a[j]){
                    if(ones[j]+1>ones[i])
                        ones[i]++;
                }
            }
        }
        return Collections.max(Arrays.asList(ones));
    }
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }
}
