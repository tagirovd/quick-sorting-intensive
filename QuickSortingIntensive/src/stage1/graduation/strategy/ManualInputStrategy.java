package stage1.graduation.strategy;

import stage1.graduation.util.Console;
import stage1.graduation.model.Bus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.lang.reflect.Array;

public class ManualInputStrategy<T> implements InputStrategy<T> {
    @Override
    public T[] input(Class<T> classType, int arrayLength) {
        T[] array = (T[]) Array.newInstance(classType, arrayLength);

        for (int i = 0; i < arrayLength; i++) {
            Console.println("====================================");
            if (classType == Bus.class) {
                Console.printf("Введите данные для автобуса %d:\n", i + 1);
                array[i] = classType.cast(Bus.inputBus());
            } else if (classType == User.class) {
                Console.printf("Введите данные для пользователя %d:\n", i + 1);
                array[i] = classType.cast(User.inputUser());
            } else if (classType == Student.class) {
                Console.printf("Введите данные для студента %d:\n", i + 1);
                array[i] = classType.cast(Student.inputStudent());
            }
            Console.println("====================================\n");
        }
        return array;
    }
}
