import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

abstract class Customer{
    double minutes;
    int SMSs;
    double GBs;
    String id;

    public Customer(double minutes, int SMSs, double GBs, String id) {
        this.minutes = minutes;
        this.SMSs = SMSs;
        this.GBs = GBs;
        this.id = id;
    }

    abstract double totalPrice();
    abstract double commission();
}

class SCoustomer extends Customer{

    static double BASE_PRICE_S = 500.0;
    static double FREE_MINS_S = 100.0;
    static int FREE_SMSS_S = 50;
    static double FREE_GBS_S = 6.0;

    static double PRICE_PER_MIN_S = 5.0;
    static double PRICE_PER_SMS_S = 6.0;
    static double PRICE_PER_GB_S = 25.0;

    static double COMMISSION = 0.07;

    public SCoustomer(double minutes, int SMSs, double GBs, String id) {
        super(minutes, SMSs, GBs, id);
    }

    @Override
    double totalPrice() {
        double total = BASE_PRICE_S;
        if(minutes>FREE_MINS_S){
            total+=(minutes-FREE_MINS_S)*PRICE_PER_MIN_S;
        }
        if(SMSs>FREE_SMSS_S){
            total+=(SMSs-FREE_SMSS_S)*PRICE_PER_SMS_S;
        }
        if(GBs>FREE_GBS_S){
            total+=(GBs-FREE_GBS_S)*PRICE_PER_GB_S;
        }
        return total;
    }

    @Override
    double commission() {
        return totalPrice() * COMMISSION;
    }

}
class MCoustomer extends Customer{

    static double BASE_PRICE_M = 750.0;
    static double FREE_MINS_M = 150.0;
    static int FREE_SMSS_M = 60;
    static double FREE_GBS_M = 10.0;

    static double PRICE_PER_MIN_M = 4.0;
    static double PRICE_PER_SMS_M = 4.0;
    static double PRICE_PER_GB_M = 20.0;

    static double COMMISSION = 0.04;

    public MCoustomer(double minutes, int SMSs, double GBs, String id) {
        super(minutes, SMSs, GBs, id);
    }

    @Override
    double totalPrice() {
        double total = BASE_PRICE_M;
        if(minutes>FREE_MINS_M){
            total+=(minutes-FREE_MINS_M)*PRICE_PER_MIN_M;
        }
        if(SMSs>FREE_SMSS_M){
            total+=(SMSs-FREE_SMSS_M)*PRICE_PER_SMS_M;
        }
        if(GBs>FREE_GBS_M){
            total+=(GBs-FREE_GBS_M)*PRICE_PER_GB_M;
        }
        return total;
    }

    @Override
    double commission() {
        return totalPrice() * COMMISSION;
    }

}

class SalesRep implements Comparable<SalesRep>{
    String id;
    List<Customer> customers;

    public SalesRep(String id, List<Customer> customers) {
        this.id = id;
        this.customers = customers;
    }

    public static SalesRep createSales(String line){
        String [] parts = line.split("\\s+");
        String id = parts[0];
        List<Customer> list = new ArrayList<>();
        for(int i=1;i<parts.length;i+=5) {
            String custId = parts[i];
            String type = parts[i+1];
            double mins = Double.parseDouble(parts[i+2]);
            int sms = Integer.parseInt(parts[i+2]);
            double GBs = Double.parseDouble(parts[i+4]);
            if(type.equals("M")){
                MCoustomer tmp = new MCoustomer(mins,sms,GBs,type);
                list.add(tmp);
            } else {
                SCoustomer tmp = new SCoustomer(mins,sms,GBs,type);
                list.add(tmp);
            }
        }
        return new SalesRep(id, list);
    }

    private double totalCom(){
        return customers.stream().mapToDouble(Customer::commission).sum();
    }

    @Override
    public String toString() {
        DoubleSummaryStatistics sum = customers.stream().mapToDouble(Customer::totalPrice).summaryStatistics();
        return String.format("%s  Count: %d  Min: %.2f  Average: %.2f  Max: %.2f  Commision: %.2f",
                id,
                sum.getCount(),
                sum.getMin(),
                sum.getAverage(),
                sum.getMax(),
                totalCom()
        );
    }

    @Override
    public int compareTo(SalesRep o) {
        return Double.compare(this.totalCom(),o.totalCom());
    }
}
class MobileOperator{
    List<SalesRep> salesReps;
    void readSales(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        salesReps = br.lines().map(SalesRep::createSales).collect(Collectors.toList());
        br.close();
    }
    void printSales(OutputStream os){
        PrintWriter pw = new PrintWriter(os);
        salesReps.stream().sorted(Comparator.reverseOrder()).forEach(pw::println);
        pw.flush();
    }
}
public class Lines {
    public static void main(String[] args) throws IOException {
        MobileOperator mo = new MobileOperator();
        System.out.println("--READING--");
        mo.readSales(System.in);
        System.out.println("--PRINTING--");
        mo.printSales(System.out);
    }
}
