import model.Candidate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HqlRun {
    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(ssr).buildMetadata().buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        session.save(new Candidate("Candidate 1", 1, 100));
        session.save(new Candidate("Candidate 2", 2, 200));
        session.save(new Candidate("Candidate 3", 3, 300));

        Candidate candidateById
                = (Candidate) session.createQuery("from Candidate where id=:id")
                .setParameter("id", 1).uniqueResult();

        Candidate candidateByName
                = (Candidate) session.createQuery("from Candidate where name=:name")
                .setParameter("name", "Candidate 1").uniqueResult();

        session.createQuery("insert into Candidate(name,expirience,salary)"
                + "select concat(name,' new'),expirience+1, salary+50"
                + " from Candidate where id=:id")
                .setParameter("id", 4).executeUpdate();

        session.createQuery("delete Candidate where id=:id")
                .setParameter("id", 2).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }
}
