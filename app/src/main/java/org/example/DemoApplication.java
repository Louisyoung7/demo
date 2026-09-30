package org.example;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.example.mapper.ScoreMapper;
import org.example.entity.Score;
import org.example.entity.Book;
import org.example.entity.Borrow;
import org.example.mapper.BookMapper;
import org.example.mapper.BorrowMapper;

@SpringBootApplication
@org.mybatis.spring.annotation.MapperScan("org.example.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // 启动时初始化种子数据（幂等：表为空才插入，避免每次重启都重复插入报主键冲突）
@Bean
public CommandLineRunner init(UserMapper userMapper){
    return args->{
            if(userMapper.selectCount(null)==0){
                userMapper.insert(new User(null,"Alice",20));
                userMapper.insert(new User(null,"Bob",25));
                System.out.println(">>>已初始化种子数据：Alice,Bob(id=1,2)");
            }else{
                System.out.println(">>>表里已有"+userMapper.selectCount(null)+"条数据，跳过初始化");
            }
    };
}

@Bean
public CommandLineRunner initScore(ScoreMapper scoreMapper){
      return arg->{
          if(scoreMapper.selectCount(null)==0){
              scoreMapper.insert(new Score("张三","数学",95)); // 及格
              scoreMapper.insert(new Score("张三","英语",88)); // 及格
              scoreMapper.insert(new Score("李四","数学",45)); // 不及格
              scoreMapper.insert(new Score("王五","数学",78)); // 及格
              scoreMapper.insert(new Score("王五","英语",91)); // 及格
              scoreMapper.insert(new Score("赵六","数学",59)); // 不及格
              scoreMapper.insert(new Score("赵六","物理",70)); // 及格
              System.out.println(">>>已初始化成绩数据");
          }
      };
    }


    @Bean
    public CommandLineRunner initBook(BookMapper bookMapper){
        return arg->{
            if(bookMapper.selectCount(null)==0){
                bookMapper.insert(new Book("三体","刘慈欣"));
                bookMapper.insert(new Book("活着","余华"));
                bookMapper.insert(new Book("围城","钱钟书"));
                System.out.println("已初始化图书数据");
            }
        };
    }

    @Bean
    public CommandLineRunner initBorrow(BorrowMapper borrowMapper){
        return arg->{
            if(borrowMapper.selectCount(null)==0){
                borrowMapper.insert(new Borrow(1L,"张三","2026-09-20"));
                borrowMapper.insert(new Borrow(2L,"李四","2026-09-22"));
                borrowMapper.insert(new Borrow(1L,"王五","2026-09-25"));
                System.out.println("已初始化借阅数据");
            }
        };
    }
}



