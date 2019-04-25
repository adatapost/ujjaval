package com.aim.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.aim.app.dao.UserDao;
import com.aim.app.model.User;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDao userDao;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("select email,pass,active from view_user where email=?")
				.authoritiesByUsernameQuery("select email,name from view_user where email=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**")
		.access("hasRole('ADMIN')")
		.antMatchers("/shop/**")
				.access("hasRole('SHOP')")
				.antMatchers("/customer/**")
				.access("hasRole('CUSTOMER')")
				.antMatchers("/**")
				.access("permitAll")
				.anyRequest().
				authenticated().and().formLogin()
				.successHandler(new AuthenticationSuccessHandler() {
					
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						// Obtain the username
						String email = authentication.getName();
					    User user = new User();
					    user.setEmail(email);
					    
					    user = userDao.get(user);
					    
					    
						//Obtain the session
						HttpSession session = request.getSession();
						session.setAttribute("user", user );
						session.setAttribute("role", user.getRole().getName());
						session.setAttribute("id", user.getId());
						System.out.println(user);
						
						Object []role = authentication.getAuthorities().toArray();
						if("ROLE_ADMIN".equals(role[0].toString()))
							response.sendRedirect("/admin");
						if("ROLE_SHOP".equals(role[0].toString()))
							response.sendRedirect("/shop");
						if("ROLE_CUSTOMER".equals(role[0].toString()))
							response.sendRedirect("/customer");
					}
				})
				.loginPage("/login").permitAll().and().logout().logoutUrl("/logout").permitAll().and().csrf().disable();
	}
}
