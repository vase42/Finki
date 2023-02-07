import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {
    public static int calculateNumber(String expression, int n, int m){
        int num=0;
        for(int i=n;i<m;i++){
            num=(num*10)+Integer.parseInt(String.valueOf(expression.charAt(i)));
        }
        return num;
    }
    public static int evaluateExpression(String expression){
        Stack<String> stack = new Stack<>();
        boolean flag = false;
        int sum = 0;
        outerloop:
        for(int i=0;i<expression.length();++i) {
            String tmp = "";
            while (Character.isDigit(expression.charAt(i))) {
                tmp += expression.charAt(i);
                i++;
                if (i > expression.length()-1) {
                    if(flag){
                        int a = Integer.parseInt(stack.pop());
                        int b = Integer.parseInt(tmp);
                        sum = a * b;
                        tmp = String.valueOf(sum);
                        flag = false;
                    }
                    stack.push(tmp);
                    break outerloop;
                }
            }
            if(flag){
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(tmp);
                sum = a * b;
                tmp = String.valueOf(sum);
                flag = false;
            }
            stack.push(tmp);
            if (expression.charAt(i) == '*') {
                flag = true;
            }
        }
        sum = 0;
        for(int i=stack.size();i>0;i--){
            sum+=Integer.parseInt(stack.pop());
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}
