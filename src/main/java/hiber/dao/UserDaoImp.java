package hiber.dao;

import hiber.model.Role;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory factory;

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        TypedQuery<User> query = factory.getCurrentSession().createQuery("FROM User");
        return query.getResultList();
    }

    public User getUserById(long id) {
        return factory.getCurrentSession().get(User.class, id);
    }

    public void addUser(User user) {
        factory.getCurrentSession().save(user);
    }

    public void deleteUser(long id) {
        Session session = factory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }

    public void editUser(User user) {
        factory.getCurrentSession().update(user);
    }

    @SuppressWarnings("unchecked")
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = factory.getCurrentSession().createQuery("FROM Role");
        return query.getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = factory.getCurrentSession().
                createQuery("FROM Role WHERE role = :name").
                setParameter("name", name);
        return query.getSingleResult();
    }
}
