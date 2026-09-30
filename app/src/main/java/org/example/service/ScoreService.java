package org.example.service;
import org.example.entity.Score;
import org.example.mapper.ScoreMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
@Service
public class ScoreService{
    private final ScoreMapper scoreMapper;
    public ScoreService(ScoreMapper scoreMapper){
        this.scoreMapper=scoreMapper;
    }

    public List<Score>listAll(){
        return scoreMapper.selectList(null);
    }

    public Score getById(long id){
        return scoreMapper.selectById(id);
    }

    public boolean add(Score score){
        return scoreMapper.insert(score)>0;
    }
    public boolean update(Score score){
        return scoreMapper.updateById(score)>0;
    }

    public boolean delete(long id){
        return scoreMapper.deleteById(id)>0;
    }
    // 条件查询：分数>=minScore 且 <maxScore、科目精确匹配、姓名模糊匹配，最后按分数降序
    public List<Score>search(Integer minScore,Integer maxScore,String subject,String nameLike) {
        QueryWrapper<Score> qw = new QueryWrapper<>();
        if (minScore != null) {
            qw.ge("score", minScore);
        }
        if (maxScore != null) {
            qw.lt("score", maxScore);
        }
        if (subject != null && !subject.isEmpty()) {
            qw.eq("subject", subject);
        }
        if (nameLike != null && !nameLike.isEmpty()) {
            qw.like("name", nameLike);
        }
        qw.orderByDesc("score");
        return scoreMapper.selectList(qw);
    }
    public Page<Score>page(long pageNum,long pageSize){
        Page<Score>page=new Page<>(pageNum,pageSize);
        return scoreMapper.selectPage(page,null);
    }
}

