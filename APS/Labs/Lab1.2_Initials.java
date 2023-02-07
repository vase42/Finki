import java.util.Scanner;
public class Initials {
    static void printInitials(String name){
        System.out.print(name.toUpperCase().charAt(0));
        for(int i=1;i<name.length();i++){
            if(name.charAt(i)==' ')
                System.out.print(name.toUpperCase().charAt(i+1));

        }
    }
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String name;
        scanner.nextLine();

        for(int i=0; i<n; i++){
            name = scanner.nextLine();
            printInitials(name);
            System.out.println();
        }
    }
}

