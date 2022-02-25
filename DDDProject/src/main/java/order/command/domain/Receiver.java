package order.command.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Receiver {
	
    @Column(name = "receiver_name")
    private String name;
    @Column(name = "receiver_phone")
    private String phone;

    //JPA 적용위해 기본 생성자 추가
    protected Receiver() {
    }

    public Receiver(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}

