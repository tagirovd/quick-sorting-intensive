package stage1.graduation.enums;

public enum UserNames {
    ALEXANDER("Alexander"),
    DIANA("Diana"),
    VIKTORIA("Viktoria"),
    SERGEY("Sergey"),
    DMITRIY("Dmitriy"),
    PAVEL("Pavel"),
    ALEXEY("Alexey"),
    NIKITA("Nikita"),
    MIHAIL("Mihail"),
    ALEXANDRA("Alexandra"),
    MARIYA("Mariya"),
    VALENTIN("Valentin"),
    ANNA("Anna"),
    MAXIM("Maxim"),
    YULIA("Yulia"),
    IVAN("Ivan"),
    OLGA("Olga"),
    DMITRI("Dmitri"),
    ARSENY("Arseny"),
    ALINA("Alina"),
    LARISA("Larisa"),
    VITALY("Vitaly"),
    ELENA("Elena"),
    GRIGORY("Grigory"),
    NATALIA("Natalia"),
    ANDREY("Andrey"),
    VERONIKA("Veronika"),
    STEPAN("Stepan"),
    IRINA("Irina"),
    VERA("Vera"),
    ALEXANDR("Alexandr"),
    SVIATOSLAV("Svyatoslav"),
    VIKTOR("Viktor");

    private final String name;

    UserNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static UserNames getRandom() {
        UserNames[] values = values();
        int length = values.length;
        int randomIndex = (int) (Math.random() * length);
        return values[randomIndex];
    }
}
