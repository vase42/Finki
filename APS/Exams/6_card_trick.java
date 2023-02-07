import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class card_trick {
    public static int count(int N){
        Queue<Integer> array = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= 51; i++) {
            array.add(i);
        }
        int k;
        for(k=0;k<2000;k++) {
            for (int i = 0; i < 7; i++) {
                stack.push(array.remove());
            }
            for (int i = 0; i < 7; i++) {
                array.add(stack.pop());
                array.add(array.remove());
            }
            if (array.peek() == N) break;
        }
        return k+1;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}
