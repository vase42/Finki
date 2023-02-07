import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;
public class PostFixEvaluation {
    static int evaluatePostfix(char [] expr, int n){
        int sum = 0;
        Stack<String> stack = new Stack<String>();
        for(int i=0;i<n;i++){
            String alp = "";
            while(expr[i]!=' '&&i!=n){
                alp+=String.valueOf(expr[i]);
                i++;
                if(i>=n) break;
            }
            stack.push(alp);
            if("*".equals(stack.peek())){
                stack.pop();
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                sum=a*b;
                stack.push(String.valueOf(sum));
            }
            else if("/".equals(stack.peek())){
                stack.pop();
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                sum=b/a;
                stack.push(String.valueOf(sum));
            }
            else if("+".equals(stack.peek())){
                stack.pop();
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                sum=a+b;
                stack.push(String.valueOf(sum));
            }
            else if("-".equals(stack.peek())){
                stack.pop();
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                sum=b-a;
                stack.push(String.valueOf(sum));
            }
        }
        return sum;
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = evaluatePostfix(exp, exp.length);
        System.out.println(rez);
        Scanner input = new Scanner(System.in);
        String a;
        br.close();

    }
}
