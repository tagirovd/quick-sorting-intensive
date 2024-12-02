package stage1.graduation.model;

import stage1.graduation.util.Console;

import java.util.Comparator;
import java.util.Objects;

public class User implements Comparable<User> {

    private final String name;
    private final String password;
    private final String email;

    private User(UserBuilder userBuilder) {
        name = userBuilder.name;
        password = userBuilder.password;
        email = userBuilder.email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static User inputUser() {
        User user;
        do {
            String name = askForUserName();
            String password = askForUserPassword();
            String email = askForUserEmail();
            try {
                user = new User.UserBuilder(name).setPassword(password).setEmail(email).build();
            } catch (RuntimeException e) {
                Console.println("");
                Console.println("Ошибка! " + e.getMessage());
                Console.println("Пожалуйста, повторите ввод:");
                continue;
            }
            break;
        } while (true);
        return user;
    }

    private static String askForUserName() {
        Console.print("Введите имя пользователя: ");
        return Console.readString();
    }

    private static String askForUserPassword() {
        Console.print("Введите пароль пользователя: ");
        return Console.readString();
    }

    private static String askForUserEmail() {
        Console.print("Введите email пользователя: ");
        return Console.readString();
    }

    public static User createFrom(String[] data, int index) {
        User user;
        try {
            String name = data[0];
            String password = data[1];
            String email = data[2];
            user = new User.UserBuilder(name).setPassword(password).setEmail(email).build();
        } catch (Exception e) {
            throw new RuntimeException("Ощибка при чтении " + index + " строки файла: " + e.getMessage());
        }
        return user;
    }

    public static Comparator<User> compareByName() {
        return Comparator.comparing(User::getName);
    }

    public static Comparator<User> compareByPassword() {
        return Comparator.comparing(User::getPassword);
    }

    public static Comparator<User> compareByEmail() {
        return Comparator.comparing(User::getEmail);
    }

    @Override
    public int compareTo(User other) {
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        int passwordComparison = this.password.compareTo(other.password);
        if (passwordComparison != 0) {
            return passwordComparison;
        }

        return this.email.compareTo(other.email);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User user = (User) object;
        return Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, email);
    }

    public static class UserBuilder {

        private final String name;
        private String password;
        private String email;

        public UserBuilder(String name) {
            if (name == null || name.isEmpty()) {
                throw new RuntimeException("Имя не может быть null или пустым");
            }
            this.name = name;
        }

        public UserBuilder setPassword(String password) {
            if (password == null || password.isEmpty() || password.length() > 30) {
                throw new RuntimeException("Пароль не может быть null или пустым, и не может быть больше 30 символов");
            }
            this.password = password;
            return this;
        }

        public UserBuilder setEmail(String email) {
            if (email == null || !email.matches("^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$")) {
                throw new RuntimeException("Неверный формат адреса электронной почты");
            }
            this.email = email;
            return this;
        }

        public User build() {
            if (password == null) {
                throw new RuntimeException("Необходимо указать пароль");
            }
            if (email == null) {
                throw new RuntimeException("Необходимо указать адрес электронной почты");
            }
            return new User(this);
        }
    }
}
