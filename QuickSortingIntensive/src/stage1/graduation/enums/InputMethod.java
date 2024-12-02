package stage1.graduation.enums;

public enum InputMethod {
    MANUAL,
    GENERATOR,
    FILE;

    public static InputMethod getMethod(int item) {
        if (item == 1) {
            return MANUAL;
        }
        if (item == 3) {
            return FILE;
        }
        return GENERATOR;
    }

}

