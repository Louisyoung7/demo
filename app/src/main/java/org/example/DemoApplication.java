package org.example;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@org.mybatis.spring.annotation.MapperScan("org.example.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // 启动后通过 MyBatis-Plus 插入并查询数据，验证整合是否成功
    @Bean
    public CommandLineRunner verify(UserMapper userMapper) {
        return args -> {
            userMapper.insert(new User(1L, "Alice", 20));
            userMapper.insert(new User(2L, "Bob", 25));

            System.out.println(">>> 数据总条数: " + userMapper.selectCount(null));
            System.out.println(">>> 全部用户:");
            userMapper.selectList(null).forEach(System.out::println);
        };
    }
}
