package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT DISTINCT u FROM User u JOIN FETCH u.car", User.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByCar(String model, int series) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("SELECT DISTINCT u FROM User u JOIN FETCH u.car c WHERE c.model = :model AND c.series = :series", User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultList();

        if (users.isEmpty()) {
            throw new EntityNotFoundException("пользователь с указанной моделью и серией автомобиля не найден: " + model + ", " + series);
        }

        return users.get(0);
    }
}
