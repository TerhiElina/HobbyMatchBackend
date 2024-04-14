package k24op1.hobbymatch.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Service annotaatio
@Service
public class UserDetailServiceImplem implements UserDetailsService {
    private final UserRepository uRepository;

    @Autowired
    public UserDetailServiceImplem(UserRepository userRepository){
        this.uRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
      User curruser = uRepository.findByUsername(username);
      UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
        AuthorityUtils.createAuthorityList(curruser.getStatus()));
        
        return user;
    }

}
