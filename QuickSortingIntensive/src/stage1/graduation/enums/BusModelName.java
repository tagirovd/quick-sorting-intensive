package stage1.graduation.enums;

public enum BusModelName {
    model1("ЗИЛ-325000"),
    model2("ЛАЗ-699П"),
    model3("Skoda 9Tr"),
    model4("КАвЗ-3275"),
    model5("ЛАЗ-699А"),
    model6("ПАЗ-659"),
    model7("ЗИУ-682Б"),
    model8("ЛАЗ-695Н");

    private final String model;

    BusModelName(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public static BusModelName getRandom() {
        BusModelName[] values = values();
        int length = values.length;
        int randomIndex = (int) (Math.random() * length);
        return values[randomIndex];
    }
}
