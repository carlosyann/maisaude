package com.maissaude.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/login").permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers(HttpMethod.GET, "/cadastrarUsu").permitAll()
		.antMatchers(HttpMethod.POST, "/cadastrarUsu").permitAll()
		.antMatchers(HttpMethod.GET, "/solicitacaoList").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/solicitacaoList").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/cadastrarUbs").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/cadastrarUbs").hasRole("ADMIN")	
		.antMatchers(HttpMethod.GET, "/Ubs").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/Ubs").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/solicitacaoList").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/solicitacaoList").hasRole("ADMIN")
		.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
	auth.userDetailsService(userDetailsService)
	.passwordEncoder(new BCryptPasswordEncoder());
}

	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/style/**", "/img/**");
	}

}
