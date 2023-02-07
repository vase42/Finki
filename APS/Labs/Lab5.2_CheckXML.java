import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class CheckXML {
    public static boolean anskareka(String anska, String mjau){
        if(anska.length()!=mjau.length()-1) return false;
        for(int i=1;i<anska.length();i++){
            if(anska.charAt(i)!=mjau.charAt(i+1)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid = 1;

        Stack<String> stack = new Stack<>();
        for(int i=0;i<n;i++){
            if(redovi[i].charAt(0)=='['){
                if(redovi[i].charAt(1)!='/')
                    stack.push(redovi[i]);
                else {
                    if(stack.isEmpty()){
                        valid = 0;
                        break;
                    }
                    if(!anskareka(stack.pop(), redovi[i])){
                        valid = 0;
                    }
                }
            }
        }
        System.out.println(valid);
        br.close();
    }
}
