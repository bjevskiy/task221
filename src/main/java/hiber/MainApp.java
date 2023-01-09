package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.addCar(new Car("Audi", 11));
        userService.addCar(new Car("Buick", 22));
        userService.addCar(new Car("Bmw", 33));
        userService.addCar(new Car("Custom", 44));
        List<Car> carsList = userService.listCars();

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", carsList.get(0)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", carsList.get(1)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", carsList.get(2)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", carsList.get(3)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println(userService.getUserInfoByCar("Custom", 44));
        System.out.println(userService.getUserInfoByCar("Buick", 22));

        context.close();
    }
}
