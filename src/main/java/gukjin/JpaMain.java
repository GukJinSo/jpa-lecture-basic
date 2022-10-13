package gukjin;

import gukjin.domain.*;
import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            member2.setAddress(new Address("Busan", member.getAddress().getStreet(), member.getAddress().getZipcode()));
            em.persist(member2);

            member.getFavoriteFoods().add("김치");
            member.getFavoriteFoods().add("국밥");
            member.getFavoriteFoods().add("깍두기");
            member.getFavoriteFoods().add("깍두기");

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println(favoriteFood);
            }

            String fullAddress = findMember.getAddress().getFullAddress();
            System.out.println(fullAddress);

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