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

   private SessionFactory sessionFactory;
   public UserDaoImp(SessionFactory sessionFactory){
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }



   @Override
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> getUsersByCar(String model, int series) {
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery("from User as user where user.car.model =: model and user.car.series =: series");
      query.setParameter("series", series);
      query.setParameter("model", model);
      return query.getResultList();
   }

}
