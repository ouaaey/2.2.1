package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series){

      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("SELECT FROM User u WHERE u.car.model = :model AND u.car.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);

      List<User> results=query.getResultList();
      if (results.isEmpty()){
         return null;
      } return results.get(0); //== результат предположительно будет один
   }

}
