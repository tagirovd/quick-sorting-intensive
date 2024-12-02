package stage1.graduation.strategy;

import stage1.graduation.util.Console;
import stage1.graduation.model.Bus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;

public class FileInputStrategy<T> implements InputStrategy<T> {
    public FileInputStrategy() {
    }

    @Override
    public T[] input(Class<T> classType, int arrayLength) {
        Console.print("Введите название файла: ");
        String filePath = Console.readString();
        T[] array = (T[]) Array.newInstance(classType, arrayLength);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < arrayLength; i++) {
                String line = reader.readLine();
                if (line == null || line.isEmpty()) {
                    throw new RuntimeException("Недостаточно данных в файле!");
                }
                String[] data = line.split(",");

                if (classType == Bus.class) {
                    array[i] = (T) Bus.createFrom(data, i);
                } else if (classType == User.class) {
                    array[i] = (T) User.createFrom(data, i);
                } else if (classType == Student.class) {
                    array[i] = (T) Student.createStudentFromData(data, i);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения файла: " + e.getMessage());
        }
        return array;
    }
}
