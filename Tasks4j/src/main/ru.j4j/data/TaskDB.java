package data;

import model.Item;
import model.Role;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.function.Function;

public class TaskDB implements Task {

    private final StandardServiceRegistry registry
            = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf
            = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private TaskDB() {
    }

    private static final class Lazy {
        private static final Task INST = new TaskDB();
    }

    public static Task getInstance() {
        return Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void addTask(Item item) {
        tx(session ->
                session.save(item));
    }

    @Override
    public void doneTaskById(int id, int done) {
        tx(session ->
                session.createQuery("UPDATE Item SET done = :done WHERE id = :id")
                        .setParameter("done", done).setParameter("id", id)
                        .executeUpdate());
    }

    @Override
    public List<Item> findAllTask() {
        return tx(session -> session.createQuery("FROM Item").list());
    }

    @Override
    public User findUserByUsername(String username) {
        return tx(session -> {
            List<User> users = session.createQuery("FROM User WHERE username =:username")
                    .setParameter("username", username)
                    .list();
            if (!users.isEmpty()) {
                return users.get(0);
            }
            return null;
        });
    }

    @Override
    public List<Role> getAllRoles() {
        return tx(session -> session.createQuery("FROM Role").list());
    }

    @Override
    public void addUser(User user) {
        tx(session -> session.save(user));
    }

    @Override
    public Role getRoleById(String id) {
        return (Role) tx(session -> session.createQuery("FROM Role where id=" + id).list().get(0));
    }

    @Override
    public List<Item> findAllForUserId(int id) {
        return tx(session -> session.createQuery("FROM Item where user=" + id).list());
    }
}

