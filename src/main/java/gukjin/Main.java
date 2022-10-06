package gukjin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{



            // 모든 회원 조회
            // List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            // result.stream().forEach((member)-> System.out.println(member.getId()));

            // 수정
            // Member member = em.find(Member.class, 1L);
            // member.setName("Member1");

            // 삭제
            // Member member = em.find(Member.class, 2L);
            // em.remove(member);

            // 조회
            // Member member = em.find(Member.class, 1L);
            // System.out.println(member.getId());

            // 생성
            // Member member = new Member();
            // member.setId(2L);
            // member.setName("member1");
            // em.persist(member);
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