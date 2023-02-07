import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();
        if(N>M)
            System.out.println(100*(N));
        else
            System.out.println(100*(N+(M-N)));
        if(M!=0)
            System.out.println(100*(N+M-1));
        else
            System.out.println(100*(N+M));
    }

}
