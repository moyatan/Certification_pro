package Certification.app.config;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData implements Serializable {

	private static final long serialVersionUID = 1L;

	UserDetails account;

	public UserDetails getAccount() {
		return account;
	}

	public void setAccount(UserDetails account) {
		this.account = account;
	}

}
