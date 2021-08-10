package Certification.app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import Certification.app.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// これをちゃんとautowiredする
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	// パスワードをハッシュ化するためのもの
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	// プロジェクト全体にかけるセキュリティ
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**", "/css/**", "/javascript/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/login", "/signup",
						"/sortOrder", "/categorySearch", "/titleSearch", 
						"/contactus", "/success","/inputFavorite",
						"/articlesShow","/comment","/edit","/home")
				.permitAll() // どのユーザーからでもアクセス可能
				.anyRequest().authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/login") // フォーム送信のSubmitURL
				.usernameParameter("email") // name属性の'username'
				.passwordParameter("password") // name属性の'password'
				.defaultSuccessUrl("/", true) // 認証が成功した時のURL
				.failureUrl("/login?error") // 認証が失敗した時のページ
				.permitAll().and().sessionManagement().enableSessionUrlRewriting(true).and().csrf()
				.ignoringAntMatchers("/h2-console/**").and().logout().logoutUrl("/logout").logoutSuccessUrl("/") // ログアウト成功時のページ
				.permitAll();
	}

	// 認証時に自動的に呼ばれるクラス
	@Override
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}

@Configuration
class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}