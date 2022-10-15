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
            member.setMemberType(MemberType.ADMIN);
            member.setAge(29);
            member.setTeam(team);

            em.persist(team);
            em.persist(member);

            em.flush();
            em.clear();

//            엔티티 직접 사용 - 외래키 값
            Team findTeam = em.find(Team.class, 1L);
            String query = "select m from Member m where m.team = :entity";
            Member findMember = em.createQuery(query, Member.class)
                    .setParameter("entity", findTeam)
                    .getSingleResult();


//            엔티티 직접 사용 - 기본 키 값
//            String query = "select m from Member m where m = :entity";
//            Member findMember = em.createQuery(query, Member.class)
//                    .setParameter("entity", member) // 엔티티를 직접 던지더라도 pk키로 동작
//                    .getSingleResult();

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