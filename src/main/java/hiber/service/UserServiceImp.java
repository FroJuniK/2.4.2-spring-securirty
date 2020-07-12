package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Role;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserDao dao;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return dao.getUserById(id);
    }

    @Transactional
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Transactional
    public void deleteUser(long id) {
        dao.deleteUser(id);
    }

    @Transactional
    public void editUser(User user) {
        dao.editUser(user);
    }

    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return dao.getAllRoles();
    }

    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return dao.getRoleByName(name);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dao.loadUserByUsername(s);
    }
}
