package jpql;

import gukjin.domain.Book;
import gukjin.domain.Item;
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
            member.setUsername("소국진");
            member.setMemberType(MemberType.ADMIN);
            member.setAge(10);
            em.persist(member);

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("소국진");
            em.persist(book);

            em.flush();
            em.clear();

            String query =
                    "select "+
                            "case when m.age <= 10 then '학생요금' "+
                                "when m.age >= 60 then '경로요금' "+
                                "else '일반요금' "+
                            "end "+
                    "from Member m";
            em.createQuery(query, String.class).getResultList().stream().forEach(e -> System.out.println(e));

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