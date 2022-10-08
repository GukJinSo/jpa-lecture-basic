package gukjin.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private String city;
    private String street;
    private String zipcode;

    private DeliveryStatus status;

}
