package ru.teamname.projectname.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.teamname.projectname.entity.localization.LocalizationString;
import ru.teamname.projectname.entity.packs.Category;
import ru.teamname.projectname.entity.packs.Pack;
import ru.teamname.projectname.entity.packs.Question;
import ru.teamname.projectname.repository.packs.CategoryRepository;
import ru.teamname.projectname.repository.packs.PackRepository;
import ru.teamname.projectname.repository.packs.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PackService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LocalizationService localizationService;

    public Category getCategory(Integer id){
        if(id != null && id > 0 && categoryRepository != null){
            Optional<Category> optionalCategory = categoryRepository.findById(id);
            if(optionalCategory.isPresent())
                return optionalCategory.get();
        }
        return new Category();
    }

    public List<Category> getCategories(){
        if (categoryRepository != null)
            return categoryRepository.findAll();
        return new ArrayList<>();
    }

    public Category addCategory(Category category){
        if(category != null && (category.getId() == null || category.getId() < 0) && categoryRepository != null){
            localizationService.addLStrings(category.getName().toArray(new LocalizationString[0]));
            return categoryRepository.save(category);
        }
        return category == null ? new Category() : category;
    }

    public boolean deleteCategory(Integer id) {
        if (id != null && id > 0 && categoryRepository != null) {
            try {
                categoryRepository.deleteById(id);
                return true;
            } catch (Exception ignore){}
        }
        return false;
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
        if(question !=null && (question.getId() == null || question.getId() < 0) && questionRepository != null){
            localizationService.addLStrings(question.getQuestionString().toArray(new LocalizationString[0]));
            localizationService.addLStrings(question.getAnswerString().toArray(new LocalizationString[0]));

            for (Category tmp : question.getCategory()) {
                if (tmp != null && (tmp.getId() == null || tmp.getId() < 1))
                    this.addCategory(tmp);
            }
            return questionRepository.save(question);
        }
        return question == null ? new Question() : question;
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
        if(pack != null && (pack.getId() == null || pack.getId() < 0) && packRepository != null){
            localizationService.addLStrings(pack.getName().toArray(new LocalizationString[0]));
            return packRepository.save(pack);
        }
        return pack == null ? new Pack() : pack;
    }
}