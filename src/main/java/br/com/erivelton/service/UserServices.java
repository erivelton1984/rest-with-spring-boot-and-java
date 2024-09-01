package br.com.erivelton.service;


import br.com.erivelton.data.vo.v1.PersonVO;
import br.com.erivelton.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserServices implements UserDetailsService {

    private Logger logger = Logger.getLogger(PersonVO.class.getName());

    @Autowired
    UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Find one user by name " + username + "!");
        var user = userRepository.findByUsername(username);
        if(user != null){
            return user;
        }else{
            throw new UsernameNotFoundException("UserName " + username + "not found!");
        }
    }
}