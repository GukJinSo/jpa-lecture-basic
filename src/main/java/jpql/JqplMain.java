package jpql;

import jpql.domain.Address;
import jpql.domain.Member;
import jpql.domain.MemberDto;
import jpql.domain.Order;

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

            em.flush();
            em.clear();

            List<MemberDto> members = em.createQuery("SELECT new jpql.domain.MemberDto(m.id, m.username) FROM Member m", MemberDto.class).getResultList();
            System.out.println(members.get(0).getId());
            //em.createQuery("select o.address from Order o", Address.class).getResultList();

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