package org.example.controller;
import org.example.entity.Score;
import org.example.service.ScoreService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
@RestController
@RequestMapping("/scores")
public class ScoreController{
    private  final ScoreService scoreService;
    public ScoreController(ScoreService scoreService){
        this.scoreService=scoreService;
    }

    @GetMapping
    public List<Score>list(){
        return scoreService.listAll();
    }

    @GetMapping("/{id}")
    public Score get(@PathVariable Long id){
        return scoreService.getById(id);
    }
    @PostMapping
    public Score add(@RequestBody Score score){
        scoreService.add(score);
        return score;
    }
    @PutMapping ("/{id}")
    public Score update(@PathVariable Long id,@RequestBody Score score){
        score.setId(id);
        scoreService.update(score);
        return score;
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return scoreService.delete(id);
    }

    @GetMapping("/search")
    public List<Score>search(@RequestParam(required=false) Integer minScore,@RequestParam(required=false) Integer maxScore,@RequestParam(required=false) String subject,@RequestParam(required=false) String name){
        return scoreService.search(minScore,maxScore,subject,name);
    }

    @GetMapping("/page")
    public Page<Score>page(@RequestParam(defaultValue="1")long page,@RequestParam(defaultValue="2")long size){
        return scoreService.page(page,size);
    }
}
