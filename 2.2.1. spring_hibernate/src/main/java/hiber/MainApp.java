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

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      Car car = new Car("model1", 123);
      User user1 = new User("user1", "lasname1","user1@gmail.ru");
      user1.setCar(car);
      userService.add(user1);

      Car car2 = new Car("model2", 456);
      User user2 = new User("user2", "lasname2","user2@gmail.ru");
      user2.setCar(car2);
      userService.add(user2);

      Car car3 = new Car("model3", 789);
      User user3 = new User("user3", "lasname3","user3@gmail.ru");
      user3.setCar(car3);
      userService.add(user3);

      Car car4 = new Car("model4", 789);
      User user4 = new User("user4", "lasname4","user4@gmail.ru");
      user4.setCar(car4);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();

         if (user.getCar() != null) {
            System.out.println("car model = "+user.getCar().getModel());
            System.out.println("car series = "+user.getCar().getSeries());
         }
         System.out.println();
      }

      context.close();
   }
}
