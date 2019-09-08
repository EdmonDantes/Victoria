package ru.teamname.projectname.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.teamname.projectname.entity.packs.Category;
import ru.teamname.projectname.entity.packs.Pack;
import ru.teamname.projectname.entity.packs.Question;
import ru.teamname.projectname.repository.packs.CategoryRepository;
import ru.teamname.projectname.repository.packs.PackRepository;
import ru.teamname.projectname.repository.packs.QuestionRepository;

import java.util.Optional;

@Service
public class PackService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Category getCategory(Integer id){
        if(id != null && id > 0 && categoryRepository != null){
            Optional<Category> optionalCategory = categoryRepository.findById(id);
            if(optionalCategory.isPresent())
                return optionalCategory.get();
        }
        return new Category();
    }

    public Category addCategory(Category category){
        if(category != null && (category.getid() == null || category.getid() < 0) && categoryRepository != null){
            return categoryRepository.save(category);
        }
        return category == null ? new Category() : category;
    }

    public Pack getPack(Integer id){
        if(id != null && id > 0 && packRepository != null){
            Optional<Pack> optionalPack = packRepository.findById(id);
            if(optionalPack.isPresent())
                return optionalPack.get();
        }
        return  new Pack();
    }

    public Pack addPack(Pack pack){
        if(pack != null && (pack.getid() == null || pack.getid() < 0) && packRepository != null){
            return packRepository.save(pack);
        }
        return pack == null ? new Pack() : pack;
    }

    public Question getQuestion(Integer id){
        if(id != null && id > 0 && questionRepository != null){
            Optional<Question> optionalQuestion = questionRepository.findById(id);
            if(optionalQuestion.isPresent())
                return optionalQuestion.get();
        }
        return new Question();
    }

    public Question addQuestion(Question question){
        if(question !=null && (question.getid() == null || question.getid() < 0) && questionRepository != null){
            return questionRepository.save(question);
        }
        return question == null ? new Question() : question;
    }
}