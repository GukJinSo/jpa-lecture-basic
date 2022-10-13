package jpql;

import jpql.domain.Member;

import javax.persistence.*;
import java.util.List;

public class JqplMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{

            Member member = new Member();
            member.setUsername("소국진");
            em.persist(member);

            TypedQuery<Member> query1 = em.createQuery("SELECT m from Member m", Member.class);
            TypedQuery<String> query2 = em.createQuery("SELECT m.username from Member m", String.class);
            Query query3 = em.createQuery("SELECT m.username, m.age from Member m");

            List<Member> resultList = query1.getResultList();
            for (Member e : resultList) {
                System.out.println(e.getId());
            }

            // Method chaining
            System.out.println(
                    em.createQuery("SELECT m from Member m where m.id = :id", Member.class)
                            .setParameter("id", 1L)
                            .getSingleResult()
                            .getId()
            );

            tx.commit();
        }
        catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();

    }
}