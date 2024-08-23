package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.service.CarService;
import hiber.service.UserService;
import hiber.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car1 = new Car("Tesla", 11);
      Car car2 = new Car("BMV", 22);
      Car car3 = new Car("Audi", 33);
      Car car4 = new Car("Lada Sedan", 44);

      carService.add(car1);
      carService.add(car2);
      carService.add(car3);
      carService.add(car4);

      userService.add(new User("User5", "Lastname5", "user5@mail.ru", car1));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", car2));
      userService.add(new User("User7", "Lastname7", "user7@mail.ru", car3));
      userService.add(new User("User8", "Lastname8", "user8@mail.ru", car4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }
      User[] usersWithCar = {
              userService.getUserByCar("Tesla", 11),
              userService.getUserByCar("BMV", 22),
              userService.getUserByCar("Audi", 33),
              userService.getUserByCar("Lada Sedan", 44)
      };

      for(User userWithCar: usersWithCar) {
         System.out.println("Owner " + userWithCar.getCar() + " is " + userWithCar);
      }

      context.close();
   }
}
