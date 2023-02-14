import java.util.*;

class Car implements Comparable<Car>{
    private final String manuf;
    private final String model;
    private final int price;
    private final float power;

    public Car(String manuf, String model, int price, float power) {
        this.manuf = manuf;
        this.model = model;
        this.price = price;
        this.power = power;
    }

    public String getManuf() {
        return manuf;
    }


    public String getModel() {
        return model;
    }


    public int getPrice() {
        return price;
    }
    
    public float getPower() {
        return power;
    }



    @Override
    public int compareTo(Car o) {
        return getPrice() != o.getPrice() ? Integer.compare(getPrice(),o.getPrice()) : Float.compare(getPower(),o.getPower());

    }

    @Override
    public String toString() {
        return String.format("%s %s (%dKW) %d",manuf,model,Math.round(power),price);
    }
}
class CarCollection{
    List<Car> cars;

    public CarCollection() {
        cars = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void sortByPrice(boolean ascending){
        if(ascending) {
            cars.sort(Comparator.naturalOrder());

        }else {
            cars.sort(Comparator.reverseOrder());

        }
    }
    public List<Car> filterByManufacturer(String manufacturer){
        List<Car> temp = new ArrayList<>();
        for(Car car : cars){
            if(manufacturer.equalsIgnoreCase(car.getManuf())){
                temp.add(car);
            }
        }
        temp.sort(Comparator.comparing(Car::getModel));
        return temp;
    }
    public List<Car> getList(){
        return cars;
    }
}
public class CarTest {
    public static void main(String[] args) {
        CarCollection carCollection = new CarCollection();
        String manufacturer = fillCollection(carCollection);
        carCollection.sortByPrice(true);
        System.out.println("=== Sorted By Price ASC ===");
        print(carCollection.getList());
        carCollection.sortByPrice(false);
        System.out.println("=== Sorted By Price DESC ===");
        print(carCollection.getList());
        System.out.printf("=== Filtered By Manufacturer: %s ===\n", manufacturer);
        List<Car> result = carCollection.filterByManufacturer(manufacturer);
        print(result);
    }

    static void print(List<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    static String fillCollection(CarCollection cc) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if(parts.length < 4) return parts[0];
            Car car = new Car(parts[0], parts[1], Integer.parseInt(parts[2]),
                    Float.parseFloat(parts[3]));
            cc.addCar(car);
        }
        scanner.close();
        return "";
    }
}
          
