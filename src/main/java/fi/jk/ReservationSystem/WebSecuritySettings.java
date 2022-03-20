package fi.jk.ReservationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.jk.ReservationSystem.web.UserDetailService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecuritySettings extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailService UDS;	
	
		// Determine which endpoints need to be authenticated
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .authorizeRequests()
	        .antMatchers("/css/**").permitAll()
	        .and()
	        .authorizeRequests()
	          .anyRequest().authenticated()
	          .and()
	      .formLogin()
	      	.loginPage("/login")
	          .defaultSuccessUrl("/events", true)
	          .permitAll()
	          .and()
	      .logout()
	          .permitAll();
	    }
	 
	 	//Use Bcrypt to encrypt password
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(UDS).passwordEncoder(new BCryptPasswordEncoder());
	    }

}
