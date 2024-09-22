package check.checks.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class timedate {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @Id
    Integer id;

    @CreationTimestamp
    LocalDateTime date;

    @CreatedDate
    LocalDateTime dateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
