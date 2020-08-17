package data;

import model.Item;
import model.User;
import model.Role;

import java.util.List;

public interface Task {

    void addTask(Item item);

    void doneTaskById(int id, int done);

    List<Item> findAllTask();

    User findUserByUsername(String username);

    List<Role> getAllRoles();

    void addUser(User user);

    Role getRoleById(String id);

    List<Item> findAllForUserId(int id);

}
