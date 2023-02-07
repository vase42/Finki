import java.util.Scanner;

public class GreedyCoins {

    public static int minNumCoins(int coins[], int sum) {
        int count = 0;
        for(int i=0;i<coins.length;i++){
            for(int j=i+1;j<coins.length;j++){
                if(coins[j]>coins[i]){
                    int tmp = coins[i];
                    coins[i] = coins[j];
                    coins[j] = tmp;
                }
            }
        }
        int k=0;
        while(sum!=0){
            if(sum>=coins[count]){
                sum-=coins[count];
                k++;
            }
            else count++;
        }
        return k;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String coinsStringLine = input.nextLine();
        String coinsString[] = coinsStringLine.split(" ");
        int coins[] = new int[coinsString.length];
        for(int i=0;i<coinsString.length;i++) {
            coins[i] = Integer.parseInt(coinsString[i]);
        }

        int sum = input.nextInt();

        System.out.println(minNumCoins(coins, sum));
    }
}
