package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	BCryptPasswordEncoder passEncode;
	
	@Autowired
	private DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
			
//		auth.jdbcAuthentication()
//		.dataSource(datasource)
//		.usersByUsernameQuery("SELECT user_name,user_email,user_pass,role from tbl_users where user_name=?")
//		.authoritiesByUsernameQuery("SELECT user_name FROM tbl_users where user_name=?")
//		.passwordEncoder(passEncode)
//		;
//		
			auth.inMemoryAuthentication().withUser("admin").password(passEncode.encode("admin")).roles("admin");
//			auth.inMemoryAuthentication().withUser("samyak").password("{noop}samyak").roles("admin");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		
		http
		.authorizeHttpRequests()
		.antMatchers("/","/bookappointment","/saveappointment","/viewappointments",
					 "/getallappointments","/searchappointment","/searchappointbyemail",
					 "/confotp","/confotprl","/viewappointmentbyemail","/getallappointmentsbyemail/**",
					 "/gettodaysappointmentsbyemail/**","/confappointment/**","/declineappointment/**",
					 "/getdeptbycompid/**","/getdeptbyempid/**","/getDeptByEmpId/**","/forgotpass")
		.permitAll()
		.anyRequest().hasRole("admin")
		
		.and()
		.formLogin()
		.permitAll()
		.defaultSuccessUrl("/adminbookappoint", true)
		
		.and()
		.logout()
		.logoutSuccessUrl("/")
		;
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/resources/static/**","/css/**","/js/**");
	}
}
