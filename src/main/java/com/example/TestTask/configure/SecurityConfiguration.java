package com.example.TestTask.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

    private DataSource dataSource;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select name, password, active from usr where name=?")
                .authoritiesByUsernameQuery("select u.name, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.name=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/reg","/login2").permitAll()
                   // .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/login2").permitAll()
                .and()
                    .logout().permitAll();


//                .antMatchers("/adminPostList").hasAnyRole("ROLE_ADMIN","ADMIN")
//                .antMatchers("/adminPostList").hasAnyAuthority("ROLE_ADMIN","ADMIN");
//                .antMatchers("/posterPostList").hasAnyRole("POSTER")
//                .antMatchers("/visitorPostList").hasAnyRole("VISITOR");


    }
}
