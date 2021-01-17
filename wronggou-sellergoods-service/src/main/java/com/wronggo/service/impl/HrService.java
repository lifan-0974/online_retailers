package com.wronggo.service.impl;

import com.wronggo.mapper.TbSellerMapper;
import com.wronggo.model.TbSeller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;


@Service
public class HrService  implements UserDetailsService {
    @Resource
    TbSellerMapper tbSellerMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TbSeller selectbyname = tbSellerMapper.selectbyname(s);
        if (selectbyname==null){
                      throw new UsernameNotFoundException("用户不存在");
                 }
        return new User(selectbyname.getSellerId(),selectbyname.getPassword(),new ArrayList<>());

    }
}
