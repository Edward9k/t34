package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);

   List<User> listUsers();

   public List<User> getUserByCarModelAndSeries(String model,int series);

   void clearTable();
}
