import java.util.Scanner;

public class PushZero
{
    public static void pushZerosToEnd(int arr[], int n){
        int k=0;
        int [] aot = new int[n];
        System.out.println("Transformiranata niza e: ");
        for(int i=0;i<n;i++){
            if(arr[i]!=0) {
                System.out.print(arr[i] + " ");
                k++;
            }
        }
        for(int i=0;i<n-k;i++){
            System.out.print(0 + " ");
        }
    }
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        int arr[] = new int[100];
        int n = scanner.nextInt();
        for(int i=0,k=0;i<n;i++) {
            arr[i] = scanner.nextInt();
        }
        pushZerosToEnd(arr, n);
    }
}
