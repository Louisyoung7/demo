package org.example.service;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;
    public  UserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }
    //查全部
    public  List<User>listAll(){
        return userMapper.selectList(null);
    }

    //查单个
    public User getById(long id){
        return userMapper.selectById(id);
    }
    // 新增：insert 返回值是「影响的行数」，>0 表示成功
    public boolean add(User user){
        return userMapper.insert(user)>0;
    }

    //更新
    public boolean update(User user){
        return userMapper.updateById(user)>0;
    }

    //删除
    public boolean delete(long id){
        return userMapper.deleteById(id)>0;
    }

}



