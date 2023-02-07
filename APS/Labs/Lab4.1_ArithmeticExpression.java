import java.io.BufferedReader;
import java.io.InputStreamReader;
class Stack {
    int n = 100;
    int top = 0;
    public Stack(int n){
        this.n = n;
    }
    String stack [] = new String[n];
    public void push(String data){
        if(top==n){
            System.out.println("Stack is full!");
        }
        else{
            stack[top] = data;
            top++;
        }
    }
    public String pop(){
        String data;

            top--;
            data = stack[top];
            stack[top] = null;

        return data;
    }
    public String peek(){
        if(top==0) return "Empty!";
        String data = stack[top-1];
        return data;
    }
    public int size(){
        return top;
    }
    public boolean isEmpty(){
        return top==0;
    }
    public void show(){
        for(int i=0;i<top;i++){
            System.out.print(stack[i] + " ");
        }
    }
}
public class ArithmeticExpression {
    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    //((5+6)-(2+2))
    static int presmetaj(char c[], int l, int r){
        int sum=0;
        Stack stack = new Stack(100);
        for(int i=l;i<=r+1;i++){
            if(")".compareTo(stack.peek()) == 0){
                stack.pop();
                int sec =  Integer.parseInt(stack.pop());
                String sign = stack.pop();
                int fir = Integer.parseInt(stack.pop());
                stack.pop();
                int sumAlt = 0;
                if(sign.equals("+")){
                    sumAlt+=fir+sec;
                }
                else{
                    sumAlt+=fir-sec;
                }
                stack.push(String.valueOf(sumAlt));
                sum = sumAlt;
                if(i==r+1) break;
                stack.push(Character.toString(c[i]));
                continue;
            }
            stack.push(Character.toString(c[i]));
        }
        return sum;
    }
    public static void main(String[] args) throws Exception {
        int i,j,k;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        char exp[] = expression.toCharArray();
        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);
        br.close();
    }

}
