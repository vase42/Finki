import java.util.Scanner;

class Gragjanin{
    String ime;
    int licnak;
    int pasos;
    int vozacka;
    public Gragjanin(String ime, int licnak, int pasos, int vozacka){
        this.licnak=licnak;
        this.pasos=pasos;
        this.vozacka=vozacka;
        this.ime=ime;
    }

    public String getIme() {
        return ime;
    }
    public int getLicnak() {
        return licnak;
    }
    public int getPasos() {
        return pasos;
    }
    public int getVozacka() {
        return vozacka;
    }
    public boolean isFinished(){
        return licnak == 0 && pasos == 0 && vozacka == 0;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public void setLicnak(int licnak) {
        this.licnak = licnak;
    }
    public void setPasos(int pasos) {
        this.pasos = pasos;
    }
    public void setVozacka(int vozacka) {
        this.vozacka = vozacka;
    }
}
public class MVR {

    public static void main(String[] args) {
        Scanner br = new Scanner(System.in);
        int N = Integer.parseInt(br.nextLine());
        Gragjanin [] gragra = new Gragjanin[N+1];
        for (int i = 1; i <= N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
            gragra[i] = covek;
        }
        boolean flag = true;
        while(flag){
            flag = false;
            for(int i=1;i<gragra.length-1;i++){
                if(gragra[i].getLicnak()>gragra[i+1].getLicnak()){
                    Gragjanin tmp = gragra[i];
                    gragra[i] = gragra[i+1];
                    gragra[i+1] = tmp;
                    flag = true;
                }
            }
        }
        flag = true;
        while(flag){
            flag = false;
            for(int i=1;i<gragra.length-1;i++){
                if(gragra[i].getPasos()>gragra[i+1].getPasos()){
                    Gragjanin tmp = gragra[i];
                    gragra[i] = gragra[i+1];
                    gragra[i+1] = tmp;
                    flag = true;
                }
            }
        }
        flag = true;
        while(flag){
            flag = false;
            for(int i=1;i<gragra.length-1;i++){
                if(gragra[i].getVozacka()>gragra[i+1].getVozacka()){
                    Gragjanin tmp = gragra[i];
                    gragra[i] = gragra[i+1];
                    gragra[i+1] = tmp;
                    flag = true;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            System.out.println(gragra[i].ime);
        }
    }
}
