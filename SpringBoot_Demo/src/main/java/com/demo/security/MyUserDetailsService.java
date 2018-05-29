package com.demo.security;
import com.common.model.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by lxg
 * on 2017/2/20.
 */
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    //private UserRoleService userRoleService;
    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = userService.findByUsername(username);
        User user =new User();
        if(user == null){
             throw new UsernameNotFoundException("用户名："+ username + "不存在！");
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        /*Iterator<Role> iterator =  user.getList().iterator();
        while (iterator.hasNext()){
            collection.add(new SimpleGrantedAuthority(iterator.next().getRole_name()));
        }*/

        return new org.springframework.security.core.userdetails.User(username,user.getPasswd(),collection);
    }
}
