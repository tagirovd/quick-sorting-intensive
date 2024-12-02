package stage1.graduation.enums;

import stage1.graduation.model.Bus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

public enum EntityType {
    BUS,
    USER,
    STUDENT;

    public static EntityType getType(int item) {
        switch (item) {
            case 1: return BUS;
            case 2: return USER;
            case 3: return STUDENT;
            default: throw new RuntimeException("Ошибка! Нет типа, который соответствует пункту " + item);
        }
    }

    public static Class getClassType(EntityType type) {
        switch (type) {
            case BUS: return Bus.class;
            case USER: return User.class;
            case STUDENT: return Student.class;
        }
        return null;
    }
}
