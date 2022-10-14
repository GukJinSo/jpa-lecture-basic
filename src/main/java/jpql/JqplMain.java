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
            em.persist(member);

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("소국진");
            em.persist(book);

            em.flush();
            em.clear();



//            em.createQuery("select i from Item i where type(i) = Book", Item.class) //@DiscriminatorValue와 연관
//                    .getResultList()
//                    .stream()
//                    .forEach(item -> System.out.println(item.getName()));

//            String query = "select m.username, TRUE, 'Hello' from Member m "
//                        + "where m.type = :userType";
//            List<Object[]> resultList = em.createQuery(query).setParameter("userType", MemberType.ADMIN).getResultList();
//            for (Object[] o : resultList) {
//                System.out.println(o[0]);
//                System.out.println(o[1]);
//                System.out.println(o[2]);
//            }



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