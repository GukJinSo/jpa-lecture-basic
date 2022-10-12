package gukjin;

import gukjin.domain.*;
import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{

            Member member = new Member();
            member.setAddress(new Address("Daego", "Yulha Street", "1042-3"));
            member.setWorkAddress(new Address("Seoul", "Good Street", "1000-4"));
            member.setName("소국진");

            Team team = new Team("개발부");
            member.setTeam(team);
            em.persist(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("구단미");
            member2.setAddress(new Address(member.getAddress().getCity(), member.getAddress().getStreet(), member.getAddress().getZipcode()));
            em.persist(member2);

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