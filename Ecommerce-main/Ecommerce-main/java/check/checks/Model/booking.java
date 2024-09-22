package check.checks.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class booking {
@GeneratedValue(strategy = GenerationType.AUTO)
@Column
@Id
Integer sno;
@Column
    String email_id;
@Column
    String username;
@Column
    String mobile;

@Column
    String address;
@CreationTimestamp
LocalDateTime bookingdate;

    public LocalDateTime getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(LocalDateTime bookingdate) {
        this.bookingdate = bookingdate;
    }

    @Column
    String State;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column
    String city;
}
