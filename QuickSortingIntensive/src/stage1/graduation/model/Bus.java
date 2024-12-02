package stage1.graduation.model;

import stage1.graduation.util.Console;

import java.util.Comparator;
import java.util.Objects;

public class Bus implements Comparable<Bus> {

    private final String model;
    private final int number;
    private final int mileage;

    private Bus(BusBuilder busBuilder) {
        model = busBuilder.model;
        number = busBuilder.number;
        mileage = busBuilder.mileage;
    }

    public String getModel() {
        return model;
    }

    public int getNumber() {
        return number;
    }

    public int getMileage() {
        return mileage;
    }

    public static Bus inputBus() {
        Bus bus;
        do {
            String model = askForBusModel();
            int number = askForBusNumber();
            int mileage = askForBusMileage();
            try {
                bus = new Bus.BusBuilder(model, number).setMileage(mileage).build();
            } catch (RuntimeException e) {
                Console.println("");
                Console.println("Ошибка! " + e.getMessage());
                Console.println("Пожалуйста, повторите ввод:");
                continue;
            }
            break;
        } while (true);
        return bus;
    }

    private static String askForBusModel() {
        Console.print("Введите модель автобуса: ");
        return Console.readString();
    }

    private static int askForBusNumber() {
        Console.print("Введите номер автобуса > 0 и < 1000: ");
        return Console.readInt();
    }

    private static int askForBusMileage() {
        Console.print("Введите пробег автобуса >= 0: ");
        return Console.readInt();
    }

    public static Bus createFrom(String[] data, int index) {
        Bus bus;
        try {
            int number = Integer.parseInt(data[0]);
            String model = data[1];
            int mileage = Integer.parseInt(data[2]);
            bus = new Bus.BusBuilder(model, number).setMileage(mileage).build();
        } catch (Exception e) {
            throw new RuntimeException("Ощибка при чтении " + index + " строки файла: " + e.getMessage());
        }
        return bus;
    }

    public static Comparator<Bus> compareByModel() {
        return Comparator.comparing(Bus::getModel);
    }

    public static Comparator<Bus> compareByNumber() {
        return Comparator.comparingInt(Bus::getNumber);
    }

    public static Comparator<Bus> compareByMileage() {
        return Comparator.comparingInt(Bus::getMileage);
    }

    public static Comparator<Bus> compareByEvenNumber() {
        return (o1, o2) -> {
            if (o1.number % 2 == 0 && o2.number % 2 == 0) {
                return Integer.compare(o1.number, o2.number);
            } else return 0;
        };
    }

    public static Comparator<Bus> compareByEvenMileage() {
        return (o1, o2) -> {
            if (o1.mileage % 2 == 0 && o2.mileage % 2 == 0) {
                return Integer.compare(o1.mileage, o2.mileage);
            } else return 0;
        };
    }

    @Override
    public int compareTo(Bus other) {
        int modelComparison = this.model.compareTo(other.model);
        if (modelComparison != 0) {
            return modelComparison;
        }

        int numberComparison = Integer.compare(this.number, other.number);
        if (numberComparison != 0) {
            return numberComparison;
        }

        return Integer.compare(this.mileage, other.mileage);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "model='" + model + '\'' +
                ", number=" + number +
                ", mileage=" + mileage +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus bus = (Bus) object;
        return Objects.equals(model, bus.model) &&
                number == bus.number &&
                mileage == bus.mileage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, number, mileage);
    }

    public static class BusBuilder {

        private final String model;
        private final int number;
        private int mileage;

        public BusBuilder(String model, int number) {
            if (model == null || model.isEmpty()) {
                throw new RuntimeException("Модель автобуса не может быть null или пустой");
            }
            if (number <= 0 || number > 999) {
                throw new RuntimeException("Номер автобуса должен быть больше 0 и меньше 1000");
            }
            this.model = model;
            this.number = number;
        }

        public BusBuilder setMileage(int mileage) {
            if (mileage < 0) {
                throw new RuntimeException("Пробег не может быть отрицательным");
            }
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }
}