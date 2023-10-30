package web.dao;

import web.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   @Transactional(readOnly = true)
   public List<User> getUsersList() {
      return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
   }

   @Override
   @Transactional(readOnly = true)
   public User getUserById(Long id) {
      return entityManager.find(User.class, id);
   }

   @Override
   @Transactional
   public void addUser(User user) {
      entityManager.persist(user);
   }

   @Override
   @Transactional
   public void updateUser(User user) {
      entityManager.merge(user);
   }

   @Override
   @Transactional
   public void deleteUserById(Long id) {
      User user = entityManager.find(User.class, id);
      if (user != null) {
         entityManager.remove(user);
      }
   }
}
