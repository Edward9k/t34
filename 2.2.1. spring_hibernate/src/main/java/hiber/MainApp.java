package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Женёк", "Пригожин", "vagner@mail.ru", new Car("т34", 2)));
      userService.add(new User("Илья", "Варламов", "varlomov@yandex.ru", new Car("Lada", 1)));
      userService.add(new User("Клим", "Жуков", "jukov@gmail.com", new Car("Mercedes", 3)));
      userService.add(new User("Родион", "Раскольников", "ras@yandex.ru", new Car("BMX", 1)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());

         Car car = user.getCar();
         if (car != null) {
            System.out.println("Car series = " + car.getSeries());
            System.out.println("Car model = " + car.getModel());
         } else {
            System.out.println("Информация о машине недоступна.");
         }
         System.out.println();
      }

      List<User> list = userService.getUserByCarModelAndSeries("т34", 2);
      System.out.println(list.toString());

      userService.clearTable();

      context.close();

   }
}
