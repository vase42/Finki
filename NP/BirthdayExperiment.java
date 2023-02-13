import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Random;
class Experimet{
    static int TRIES = 100000000;
    int people;
    public Experimet(int people){
        this.people = people;
    }
    private double ran(){
        int count = 0;
        Random ran = new Random();
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<people;i++){
            int ren = ran.nextInt(365)+1;
            if(set.contains(ren)){
                count++;
            }
            else set.add(ren);
        }
        return count;
    }
    public double run(){
        int count = 0;
        for(int i=0;i<TRIES;i++){
            if(ran()>0) count++;
        }
        return (double) count / TRIES;
    }
}
public class MyExp{
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        double prev = 0;
//        for(;;){
//            String line = br.readLine();
//            if(line == null) break;
//            String [] spl = line.split("\\s+");
//            double now = Double.parseDouble(spl[3].substring(0,9));
//            System.out.print(spl[1]);
//            System.out.print(" ");
//            System.out.println(now-prev);
//            prev=now;
//        }

        Experimet ex = new Experimet(23);
        System.out.println(String.format("People: 23  Chance: %.5f%%",50,(ex.run()*100)));

//        for(int i=1;i<=50;i++){
//            Experimet ex = new Experimet(i);
//            System.out.println(String.format("People: %02d  Chance: %.10f%%",i,(ex.run()*100)));
//        }
    }
}
