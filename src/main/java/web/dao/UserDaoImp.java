package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


   @Autowired
   private EntityManagerFactory entityManagerFactory;

   @Override
   public List<User> getUsersList() {
      EntityManager em = entityManagerFactory.createEntityManager();
      Query query = em.createQuery("SELECT u FROM User u");
      List<User> entities = query.getResultList();
      em.close();
      return entities;
   }

   @Override
   public void addUser(User user) {
      EntityManager em = entityManagerFactory.createEntityManager();
      em.getTransaction().begin();
      em.persist(user);
      em.getTransaction().commit();
      em.close();
   }

   @Override
   public void removeUser(Long id) {
      EntityManager em = entityManagerFactory.createEntityManager();
      em.getTransaction().begin();
      Query query = em.createQuery("delete from User u where u.id=:id")
              .setParameter("id", id);
      query.executeUpdate();
      em.getTransaction().commit();
      em.close();
   }

   @Override
   public User findUserById(Long id) {
      EntityManager em = entityManagerFactory.createEntityManager();
      User user = em.find(User.class, id);
      em.close();
      return user;
   }

   @Override
   public void updateUser(User user) {
      EntityManager em = entityManagerFactory.createEntityManager();
      em.getTransaction().begin();
      em.merge(user);
      em.getTransaction().commit();
      em.close();
   }
}
