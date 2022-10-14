package jpql;

import gukjin.domain.Book;
import jpql.domain.*;

import javax.persistence.*;

public class JqplMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("소국진");
            member.setMemberType(MemberType.ADMIN);
            member.setAge(29);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("구단미");
            member2.setMemberType(MemberType.USER);
            member2.setAge(24);
            em.persist(member2);

            em.flush();
            em.clear();

            String query = "select function('group_concat', m.username) from Member m";
            em.createQuery(query, String.class).getResultList().stream().forEach(System.out::println);

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