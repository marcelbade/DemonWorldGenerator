package marcel.demonworld.armygenerator.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class UserDetailsConfig {

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("johnDoe").password("123").authorities("read").build();
        return new InMemoryUserDetailsManager(user);
    }


}
