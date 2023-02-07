import java.util.Scanner;
import java.lang.*;

class RabotnaNedela{

    private int [] casovi;
    private int brNedela;

    public RabotnaNedela(int[] casovi, int brNedela) {
        super();
        this.casovi = casovi;
        this.brNedela = brNedela;
    }

    public int sumChas(){
        int sum = 0;
        for(int i=0;i<5;i++) {
            sum += casovi[i];
        }
        return sum;
    }

    @Override
    public String toString() {
        return sumChas() + "";
    }

}

class Rabotnik{
    private String ime;
    private RabotnaNedela [] nedeli;
    public String getIme() {
        return ime;
    }
    public Rabotnik(String ime, RabotnaNedela [] nedeli) {
        super();
        this.ime = ime;
        this.nedeli = nedeli;
    }
    public int sumAll(){
        int sum = 0;
        for (int i = 0; i < 4; i++){
            sum += nedeli[i].sumChas();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append(ime + "   ");
        for (int i = 0; i < 4; i++) {
            stb.append(nedeli[i].toString() + "   ");
        }
        stb.append(sumAll());
        return stb.toString();
    }

}

    public class Main {

    public static Rabotnik najvreden_rabotnik(Rabotnik [] niza){
        Rabotnik max = niza[0];
        for(int i=1;i<niza.length;i++){
            if(max.sumAll()<niza[i].sumAll())
                max=niza[i];
        }
        return max;
    }
    public static void table(Rabotnik [] niza){
        System.out.println("Rab   1   2   3   4   Vkupno");
        for(int i=0;i<niza.length;i++){
            System.out.println(niza[i].toString());
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        Rabotnik [] niza = new Rabotnik[n];
        for(int i=0;i<n;i++)
        {
            String name = input.next();
            RabotnaNedela [] neds = new RabotnaNedela[4];
            for(int j=0;j<4;j++){
                int [] hours = new int[5];
                for(int k=0;k<5;k++){
                    hours[k] = input.nextInt();
                }
                RabotnaNedela gom = new RabotnaNedela(hours,j);
                neds[j] = gom;
            }
            Rabotnik nov = new Rabotnik(name,neds);
            niza[i] = nov;
        }
        table(niza);
        System.out.println("NAJVREDEN RABOTNIK: " + najvreden_rabotnik(niza).getIme());
    }
}
