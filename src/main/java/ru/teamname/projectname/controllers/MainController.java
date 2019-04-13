package ru.teamname.projectname.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.teamname.projectname.entites.Answer;
import ru.teamname.projectname.entites.Category;
import ru.teamname.projectname.entites.Pack;
import ru.teamname.projectname.entites.Question;
import ru.teamname.projectname.repositories.answerRepository.AnswerRepository;
import ru.teamname.projectname.repositories.categoriesRepository.CategoryRepository;
import ru.teamname.projectname.repositories.packRepository.PackRepository;
import ru.teamname.projectname.repositories.questionRepository.QuestionRepository;

@RestController
@RequestMapping(path = "/questions")
public class MainController {

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @RequestMapping(path = "/add", method = RequestMethod.POST,  consumes = "application/json")
    public @ResponseBody Pack addQuestions(@RequestBody Pack pack) {
        if (pack != null) {
            try {
                pack = packRepository.save(pack);
                return pack;
            } catch (Exception e) {
                if (pack.getId() == null) {
                    return packRepository.getPackByName(pack.getName());
                } else {
                    for (int i = 0; i < pack.getCategories().size(); i++) {
                        Category category = pack.getCategories().get(i);
                        if (category.getId() != null) {
                            for (int j = 0; j < category.getQuestions().size(); j++) {
                                Question question = category.getQuestions().get(j);
                                Question tmp;
                                if (question.getId() == null){
                                    tmp = questionRepository.findQuestionByStringAndPrice(question.getQuestion(), question.getPrice());
                                    if (tmp != null){
                                        category.getQuestions().set(j, tmp);
                                        categoryRepository.addQuestion(category.getId(), tmp.getId());
                                        continue;
                                    } else {
                                        tmp = new Question();
                                        tmp.setPrice(question.getPrice());
                                        tmp.setQuestion(question.getQuestion());
                                        try {
                                            tmp = questionRepository.save(tmp);
                                            categoryRepository.addQuestion(category.getId(), tmp.getId());
                                            category.getQuestions().set(j, tmp);
                                        } finally {
                                            if (tmp.getId() == null) {
                                                category.getQuestions().remove(j--);
                                                continue;
                                            }
                                        }
                                    }
                                } else tmp = question;

                                for (int z = 0; z < question.getAnswers().size(); z++) {
                                    Answer answer = question.getAnswers().get(z);
                                    if (answer.getId() == null) {
                                        Answer tmp0 = answerRepository.findAnswerByString(answer.getAnswer());
                                        if (tmp0 == null || tmp0.getId() == null) {
                                            tmp0 = answerRepository.save(answer);
                                        }
                                        if (tmp0 != null && tmp0.getId() != null) {
                                            questionRepository.addAnswer(tmp.getId(), tmp0.getId());
                                            question.getAnswers().set(z, tmp0);
                                        } else {
                                            question.getAnswers().remove(z--);
                                        }
                                    } else
                                        questionRepository.addAnswer(tmp.getId(), answer.getId());
                                }
                                categoryRepository.addQuestion(category.getId(), question.getId());
                            }
                            packRepository.addCategory(pack.getId(), category.getId());
                        } else {
                            pack.getCategories().remove(i--);
                        }
                    }
                    return pack;
                }
            }
        }
        return null;




//        if (pack != null) {
//            try {
//                pack = packRepository.saveAndFlush(pack);
//                if (pack != null) return pack;
//            } catch (DataIntegrityViolationException e) {
//                if (pack.getId() != null) {
//                    for (int i = 0; i < pack.getCategories().size(); i++) {
//                        Category category = pack.getCategories().get(i);
//                        if (category.getId() != null) {
//                            for (int j = 0; j < category.getQuestions().size(); j++) {
//                                Question question = category.getQuestions().get(j);
//                                if (question.getId() != null) {
//                                    for (int z = 0; z < question.getAnswers().size(); z++) {
//                                        Answer answer = question.getAnswers().get(z);
//                                        if (answer.getId() == null) {
//                                            Answer tmp = answerRepository.findAnswerByString(answer.getAnswer());
//                                            if (tmp == null)
//                                                tmp = answerRepository.save(answer);
//
//                                            if (tmp != null) {
//                                                questionRepository.addAnswer(question.getId(), tmp.getId());
//                                                question.getAnswers().set(z, tmp);
//                                            } else {
//                                                question.getAnswers().remove(z--);
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    Question tmp = questionRepository.findQuestionByStringAndPrice(question.getQuestion(), question.getPrice());
//                                    if (tmp != null) {
//                                        category.getQuestions().set(j, tmp);
//                                        categoryRepository.addQuestion(category.getId(), tmp.getId());
//                                    } else {
//                                        category.getQuestions().remove(j--);
//                                    }
//                                }
//                            }
//
//                            packRepository.addCategory(pack.getId(), category.getId());
//                        } else {
//                            pack.getCategories().remove(i--);
//                        }
//                    }
//                } else {
//                    pack = packRepository.getPackByName(pack.getName());
//                }
//                return pack;
//            } catch (Exception e){
//                throw e;
//            }
//        }
//        return null;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Pack> getAllQuestion(){
        return packRepository.findAll();
    }
}
