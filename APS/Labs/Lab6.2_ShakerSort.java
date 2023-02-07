import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {

    static void shakerSort(int a[], int n){
        boolean swapped = false;
        boolean flag = true;
        while(flag){
            flag = false;
            swapped = false;
            for(int i=n-1;i>0;i--){
                if(a[i]<a[i-1]){
                    int tmp = a[i];
                    a[i] = a[i-1];
                    a[i-1] = tmp;
                    swapped = true;
                }
            }

            for(int i=0;i<n;i++){
                System.out.print(a[i]+" ");
            }
            System.out.println();
            for(int i=0;i<n-1;i++){
                if(a[i]>a[i+1]){
                    int tmp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = tmp;
                    swapped = true;
                }
            }
            for(int i=0;i<n;i++){
                System.out.print(a[i]+" ");
            }
            System.out.println();
            if(swapped) flag = true;
        }
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }
}
