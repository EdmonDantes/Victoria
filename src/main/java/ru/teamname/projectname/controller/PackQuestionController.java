package ru.teamname.projectname.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.teamname.projectname.entity.packs.Pack;
import ru.teamname.projectname.repository.packs.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(path = "api/packs")
public class PackQuestionController {

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    private PackQuestionManager manager;

    @PostConstruct
    public void postInitializationPackQuestionManager(){
        manager = new PackQuestionManager(packRepository, categoryRepository, questionRepository, answerRepository);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST,  consumes = "application/json")
    public @ResponseBody Pack addPack(@RequestBody Pack pack) {
        return  manager.addPack(pack);
    }

    @GetMapping(path = "/get/all")
    public @ResponseBody Iterable<Pack> getAllQuestion(){
        return packRepository.findAll();
    }
}
