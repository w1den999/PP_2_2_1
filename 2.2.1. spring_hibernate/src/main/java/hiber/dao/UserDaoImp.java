package hiber.dao;

import hiber.model.Car;
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
   public void addUserWithCar(User user, Car car) {
      sessionFactory.getCurrentSession().save(user);
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public List<User> getUsersByCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("select t from User t where t.car.model = :model and t.car.series = :series",User.class);
      query.setParameter("model",model);
      query.setParameter("series",series);
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("select t from User t", User.class);
      return query.getResultList();
   }

}
