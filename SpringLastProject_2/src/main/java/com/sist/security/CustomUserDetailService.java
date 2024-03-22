package com.sist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.sist.vo.*;

import lombok.Setter;

import com.sist.service.*;
public class CustomUserDetailService implements UserDetailsService{

	@Setter(onMethod_ = @Autowired)
	private MemberService mService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberVO vo=mService.memberInfo(username);
		CustomUserDetails user=new CustomUserDetails();
		user.setId(vo.getUserId());
		user.setName(vo.getUserName());
		user.setPassword(vo.getUserPwd());
		user.setAuthority(vo.getAuthority());
		user.setEnabled(vo.getEnabled()==1?true:false);
		user.setEmail(vo.getEmail());
		user.setPhone(vo.getPhone());
		user.setAddress(vo.getAddr1()+" "+vo.getAddr2());
		return user;
	}

}