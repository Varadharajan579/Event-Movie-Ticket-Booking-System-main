package pbl.project.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import pbl.project.database.DatabaseOperation;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	@Lazy
	private DatabaseOperation databaseOperation;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		pbl.project.beans.User user_info = databaseOperation.get_user_info(username);
		if (user_info == null) {
			throw new UsernameNotFoundException("username with " + username + "not found.");
		}

		ArrayList<String> role_list = new ArrayList<String>();
		role_list.add((String) user_info.getUser_role());

		List<GrantedAuthority> grant_list = new ArrayList<GrantedAuthority>();

		if (role_list != null) {
			for (String role : role_list) {
				grant_list.add(new SimpleGrantedAuthority(role));
			}
		}

		UserDetails login_user_info = (UserDetails) (new User(user_info.getUser_name(), user_info.getPassword(),
				grant_list));

		return login_user_info;
	}

}
