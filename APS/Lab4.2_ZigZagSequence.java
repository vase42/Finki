import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        int sum = 0, succ, max = 0;
        for(int i=0;i<a.length-1;++i){
            succ = a[i+1];
            if(a[i]!=0&&sum==0) sum++;
            if((a[i]<0&&succ>0)||(a[i]>0&&succ<0)){
                sum++;
            } else{
                if(max<sum)
                    max=sum;
                sum=0; 
            }
        }
        if(max<sum)
            max=sum;
        return max;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}
