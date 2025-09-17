package pbl.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private LoggingAccessDeniedHandler access_denied_handler;

	@Autowired
	UserDetailsService user_details_service;

	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeHttpRequests()
				.antMatchers("/add_ticket/**", "/create_ticket/**", "/config_ticket/**", "/update_ticket/**",
						"/delete_ticket/**", "/user_list/**")
				.hasAnyRole("VENDER")
				.antMatchers("/ticket_list", "/display_ticket/**").hasAnyRole("GUEST", "VENDER")
				.antMatchers("/", "/registration", "/css/**", "/images/**", "/login", "/**").permitAll()
				.antMatchers("/h2/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/ticket_list", true).permitAll()
				.and()
				.logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").permitAll()
				.and()
				.exceptionHandling().accessDeniedHandler(access_denied_handler);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.userDetailsService(user_details_service).passwordEncoder(passwordEncoder());
	}

}
