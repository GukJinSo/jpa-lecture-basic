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

            Team team2 = new Team();
            team2.setName("첼시");

            Member member = new Member();
            member.setUsername("소국진");
            member.setMemberType(MemberType.ADMIN);
            member.setAge(29);
            member.setTeam(team);

            Member member2 = new Member();
            member2.setUsername("구단미");
            member2.setMemberType(MemberType.USER);
            member2.setAge(24);
            member2.setTeam(team);

            Member member3 = new Member();
            member3.setUsername("차차");
            member3.setMemberType(MemberType.USER);
            member3.setAge(11);
            member3.setTeam(team2);

            team.getMembers().add(member);
            team.getMembers().add(member2);
            team2.getMembers().add(member3);

            em.persist(team);
            em.persist(team2);
            em.persist(member);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();

            String query = "SELECT t from Team t";
            List<Team> teams = em.createQuery(query, Team.class).getResultList();

            for (Team t : teams) {
                System.out.println("team = "+t.getName());
                for (Member m : t.getMembers()) {
                    System.out.println(m.getUsername());
                }
            }

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