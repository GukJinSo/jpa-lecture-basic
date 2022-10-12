package gukjin.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column=@Column(name = "work_city")),
            @AttributeOverride(name="street",
                    column=@Column(name = "work_street")),
            @AttributeOverride(name="zipcode",
                    column=@Column(name = "work_zipcode"))
    })
    private Address workAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
