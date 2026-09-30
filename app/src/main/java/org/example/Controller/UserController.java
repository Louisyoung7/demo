package org.example.controller;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// 表示这是一个 REST 控制器，返回值自动转成 JSON
@RestController
// 这个类里所有接口的路径都以 /users 开头
@RequestMapping("/users")
public class UserController{
    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    // GET /users —— 查全部
    @GetMapping
    public List<User>list(){
        return userService.listAll();
    }
    // GET /users/{id} —— 查单个，{id} 是路径参数，@PathVariable 取出来
    @GetMapping("/{id}")
    public User get(@PathVariable Long id){
        return userService.getById(id);
    }
    // POST /users —— 新增，@RequestBody 把请求体里的 JSON 自动转成 User 对象
    @PostMapping
    public User add(@RequestBody User user){
        userService.add(user);
        return user;
    }
    // PUT /users/{id} —— 按 id 更新
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id); // 以路径里的 id 为准，防止请求体里 id 和路径不一致
        userService.update(user);
        return user;
    }

    // DELETE /users/{id} —— 按 id 删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}




