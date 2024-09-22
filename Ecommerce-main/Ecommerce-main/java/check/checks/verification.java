package check.checks;

import check.checks.Model.pojo;
import check.checks.Repositories.crud;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class verification implements UserDetailsService {

    @Autowired
    crud cx;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
String a,b;

List<GrantedAuthority> ls=new ArrayList<>();

        pojo dd= cx.findByEmailid(username);
        if(dd==null)
            throw new UsernameNotFoundException("invalid credentials");
        else
             a=dd.getEmailid();
        b=dd.getPassword();
        ls.add(new SimpleGrantedAuthority(dd.getRole()));

System.out.println("CHECKED"+"REACHED HERESSS");

        return new User(a,b,ls);

    }
}
