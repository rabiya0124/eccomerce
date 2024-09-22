package check.checks.Model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Entity
public class pojo {

    @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
    Integer sno;
@Column
    private String username;
@Column
    private String password;
@Column
private String role;
@Column
private String emailid;

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    @Column
private Boolean userverfied;

    public Boolean isUserverfied() {

        if (userverfied== null || userverfied==false) return false ;
        return true;
    }
@Column
private Integer userotp;

    public Integer getUserotp() {
        return userotp;
    }

    public void setUserotp(Integer userotp) {
        this.userotp = userotp;
    }

    public void setUserverfied(boolean userverfied) {
        this.userverfied = userverfied;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }
}
