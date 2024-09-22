package check.checks.Repositories;

import check.checks.Model.pojo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface crud extends JpaRepository<pojo,Integer> {

    pojo findByUsername(String username);
    pojo findByEmailid(String emailid);
}
