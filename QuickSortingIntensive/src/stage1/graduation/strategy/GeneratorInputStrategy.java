package stage1.graduation.strategy;

import stage1.graduation.enums.BusModelName;
import stage1.graduation.enums.UserNames;
import stage1.graduation.enums.MailDomains;
import stage1.graduation.model.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GeneratorInputStrategy<T> implements InputStrategy<T> {
    private final Random random = new Random();
    private final Set<String> generatedEmails = new HashSet<>();
    private final Set<String> generatedUsernames = new HashSet<>();
    private final Set<Integer> generatedRecordBookNumbers = new HashSet<>();
    private final char[] symbols = {'-', '.', '_'};

    @Override
    public T[] input(Class<T> classType, int arrayLength) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) java.lang.reflect.Array.newInstance(classType, arrayLength);

        for (int i = 0; i < arrayLength; i++) {
            if (classType == Bus.class) {
                array[i] = classType.cast(createBus());
            } else if (classType == User.class) {
                array[i] = classType.cast(createUser());
            } else if (classType == Student.class) {
                array[i] = classType.cast(createStudent());
            }
        }
        return array;
    }

    private Bus createBus() {
        String model = BusModelName.getRandom().getModel();
        int number = random.nextInt(1000);
        int mileage = random.nextInt(200000);
        return new Bus.BusBuilder(model, number).setMileage(mileage).build();
    }

    private User createUser() {
        String username = generateUniqueUsername();  // Имя с заглавной буквы
        String email = generateUniqueEmail(username);  // Email с маленькой буквы
        String password = generateRandomPassword();
        return new User.UserBuilder(username)
                .setPassword(password)
                .setEmail(email)
                .build();
    }

    private Student createStudent() {
        int recordBookNumber = generateUniqueRecordBookNumber();
        int groupNumber = random.nextInt(9999) + 1;
        float averageScore = Math.round(random.nextFloat() * 5 * 100) / 100.0f;
        return new Student.StudentBuilder(recordBookNumber)
                .setGroupNumber(groupNumber)
                .setAverageMark(averageScore)
                .build();
    }

    private String generateUniqueUsername() {
        String username;
        do {
            String baseName = capitalizeFirstLetter(UserNames.getRandom().name().toLowerCase());  // Заглавная первая буква
            char symbol = symbols[random.nextInt(symbols.length)];
            int number = random.nextInt(100);
            username = baseName + symbol + number;
        } while (generatedUsernames.contains(username));
        generatedUsernames.add(username);
        return username;
    }

    private String generateUniqueEmail(String username) {
        String email;
        do {
            char symbol = symbols[random.nextInt(symbols.length)];
            int number = random.nextInt(100);
            String domain = MailDomains.getRandom().getDomain();
            email = username.toLowerCase() + symbol + number + "@" + domain;
        } while (generatedEmails.contains(email));
        generatedEmails.add(email);
        return email;
    }

    private int generateUniqueRecordBookNumber() {
        int recordBookNumber;
        do {
            recordBookNumber = random.nextInt(10000);
        } while (generatedRecordBookNumbers.contains(recordBookNumber));
        generatedRecordBookNumbers.add(recordBookNumber);
        return recordBookNumber;
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int passwordLength = 8 + random.nextInt(3);
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        return password.toString();
    }


    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
