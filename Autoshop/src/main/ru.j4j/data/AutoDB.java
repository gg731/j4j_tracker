package data;

import model.Car;
import model.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.function.Function;

public class AutoDB implements Store {

    private final StandardServiceRegistry registry
            = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf
            = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new AutoDB();
    }

    public static Store getInst() {
        return Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> fu) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            T rsl = fu.apply(session);
            tx.commit();
            return rsl;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public int add(Object t) {
        return (int) tx(session -> session.save(t));
    }

    @Override
    public <T> List<T> getAll(Class<T> t) {
        return tx(session -> session.createQuery("from " + t.getName()).list());
    }

    @Override
    public <T> T findById(Class<T> c, int id) {
        return (T) tx(session ->
                session.createQuery("from " + c.getName() + " where id = :id")
                        .setParameter("id", id).list().get(0));
    }

    @Override
    public Driver findDriverByLogin(String login) {
        try {
            return (Driver) tx(session ->
                    session.createQuery("from Driver where login =:login")
                            .setParameter("login", login)
                            .list().get(0));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void update(Object e) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(e);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Car> sortByDate() {
        return tx(session ->
                session.createQuery("from Car where date >= current_date").list());
    }

    @Override
    public List<Car> sortByPhoto() {
        return tx(session ->
                session.createQuery("from Car where image != 0").list());
    }

    @Override
    public List<Car> sortByBrand(int id) {
        return tx(session ->
                session.createQuery("from Car where model.brand.id=" + id).list());
    }

}
