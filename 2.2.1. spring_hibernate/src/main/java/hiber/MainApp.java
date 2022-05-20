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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User user1 = new User("Ivan","Ivanov","emailAB@gmail.com");
      User user2 = new User("Sasha", "Petrov", "emailSP@gmail.com");
      User user3 = new User("Petya", "Alexandrov", "emailPA@gmail.com");
      User user4 = new User("Alexandr", "Nevsky", "emailAN@gmail.com");
      Car car1 = new Car("Ferrari", 54321);
      Car car2 = new Car("Subaru", 111);
      Car car3 = new Car("Nissan", 333);
      Car car4 = new Car("Ford", 12345);
      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);
      userService.addUserWithCar(user1,car1);
      userService.addUserWithCar(user2, car2);
      userService.addUserWithCar(user3, car3);
      userService.addUserWithCar(user4, car4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
         System.out.println();
      }
      List<User>usersByCar = userService.getUsersByCar("Ferrari",54321);
      for (User user : usersByCar){
         System.out.println(user);
         System.out.println();
      }

      context.close();
   }
}
