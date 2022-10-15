package jpql;

import gukjin.domain.Book;
import jpql.domain.*;

import javax.persistence.*;
import java.util.List;

public class JqplMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{

            Team team = new Team();
            team.setName("아스날");

            Member member = new Member();
            member.setUsername("소국진");
            member.setAge(30);
            Member member2 = new Member();
            member2.setUsername("차차");
            member2.setAge(25);
            Member member3 = new Member();
            member3.setUsername("치치");
            member3.setAge(20);

            em.persist(member);
            em.persist(member2);
            em.persist(member3);

            String sql = "update Member m set m.age = 20";
            int resultCount = em.createQuery(sql).executeUpdate();
            System.out.println(resultCount);

            System.out.println(member.getAge());
            System.out.println(member2.getAge());
            System.out.println(member3.getAge());

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