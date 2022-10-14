package jpql;

import jpql.domain.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

public class JqplMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Member member = new Member();
            Team team = new Team();
            team.setName("아스날");
            member.setUsername("아스날");
            member.addTeam(team);
            em.persist(team);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m from Member m, Team t Where t.name = m.username";
            System.out.println(em.createQuery(query, Member.class).getResultList().get(0));

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