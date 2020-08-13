package data;

import model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class TaskDB implements Task {
    private final StandardServiceRegistry registry
            = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf
            = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @Override
    public void addTask(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void doneTaskById(int id, int done) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("UPDATE Item SET done = " + done + " WHERE id = " + id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public int checkTaskById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("FROM Item WHERE id= " + id).list();
        session.getTransaction().commit();
        session.close();
        return items.get(0).getDone();
    }

    @Override
    public List<Item> findAllTask() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("FROM Item").list();
        session.getTransaction().commit();
        session.close();
        return items;
    }
}

