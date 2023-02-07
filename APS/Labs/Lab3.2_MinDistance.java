import java.util.Scanner;

public class MinDistance {

    public static float minimalDistance(float points[][], int n) {
        double min=9999999.99;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                double distance = Math.sqrt(Math.pow(Math.abs(points[i][0]-points[j][0]),2)+Math.pow(Math.abs(points[i][1]-points[j][1]),2));
                if(min>distance)
                    min = distance;
            }
        }
        return (float)min;
    }

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();

        float points[][] = new float[N][2];

        for(int i=0;i<N;i++) {
            points[i][0] = input.nextFloat();
            points[i][1] = input.nextFloat();
        }

        System.out.printf("%.2f\n", minimalDistance(points,N));
    }
}
