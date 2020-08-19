package data;

import model.Brand;
import model.Driver;
import model.Engine;
import model.Model;
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
    public <T> T add(T t) {
        return (T) tx(session -> session.save(t));
    }

    @Override
    public List<Brand> getAllBrands() {
        return tx(session -> session.createQuery("from Brand ").list());
    }

    @Override
    public Driver findDriverById(int id) {
        return (Driver) tx(session -> session.createQuery(
                "from Driver where id=:id").setParameter("id", id).list().get(0));
    }

    @Override
    public Engine findEngineById(int id) {
        return (Engine) tx(session -> session.createQuery(
                "from Engine where id=:id").setParameter("id", id).list().get(0));
    }

}
