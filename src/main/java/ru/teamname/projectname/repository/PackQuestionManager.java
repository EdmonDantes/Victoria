package ru.teamname.projectname.repository;

import org.springframework.stereotype.Component;
import ru.teamname.projectname.entity.Answer;
import ru.teamname.projectname.entity.Category;
import ru.teamname.projectname.entity.Pack;
import ru.teamname.projectname.entity.Question;

@Component
public class PackQuestionManager {

    private final PackRepository packRepository;

    private final CategoryRepository categoryRepository;

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    public PackQuestionManager(PackRepository packRepository, CategoryRepository categoryRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.packRepository = packRepository;
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public Answer getAnswer(Answer answer) {
        if (answer != null) {
            if (answer.getId() == null)
                try {
                    answer = answerRepository.findAnswerByString(answer.getAnswer());
                } catch (Exception ignore) {
                    return null;
                }
        }
        return answer;
    }

    public Answer setAnswerInAnswer(Answer answer) {
        if (answer != null && answer.getAnswer() != null && answer.getId() != null)
            answerRepository.setAnswerString(answer.getId(), answer.getAnswer());
        return answer;
    }

    public Question getQuestion(Question question) {
        if (question != null) {
            if (question.getId() == null)
                try {
                    question = questionRepository.findQuestionByStringAndPrice(question.getQuestion(), question.getPrice());
                } catch (Exception ignore){
                    return null;
                }
        }
        return question;
    }

    public Question setQuestionInQuestion(Question question) {
        if (question != null && question.getId() != null && question.getQuestion() != null)
            questionRepository.setQuestion(question.getId(), question.getQuestion());
        return question;
    }

    public Question setPriceInQuestion(Question question) {
        if (question != null && question.getId() != null && question.getPrice() != null)
            questionRepository.setPrice(question.getId(), question.getPrice());
        return question;
    }

    public Question addAnswerToQuestion(Question question, Answer answer) {
        if (question != null && question.getId() != null && answer != null) {
            if (answer.getId() == null) {
                Answer tmp0 = answerRepository.findAnswerByString(answer.getAnswer());
                if (tmp0 == null || tmp0.getId() == null) {
                    tmp0 = answerRepository.save(answer);
                }
                if (tmp0 != null && tmp0.getId() != null) {
                    questionRepository.addAnswer(question.getId(), answer.getId());
                    question.getAnswers().add(answer);
                }
            } else
                questionRepository.addAnswer(question.getId(), answer.getId());
        }
        return question;
    }

    public Question deleteAnswerFromQuestion(Question question, Answer answer) {
        if (question != null && question.getId() != null && answer != null && answer.getId() != null) {
            questionRepository.deleteAnswerFromQuestion(question.getId(), answer.getId());
            question.getAnswers().remove(answer);
        }
        return question;
    }

    public Question deleteAllAnswerFromQuestion(Question question) {
        if (question != null && question.getId() != null) {
            questionRepository.deleteAnswersFromQuestion(question.getId());
        }
        return question;
    }

    public void deleteAnswerFromAllQuestion(Answer answer) {
        if (answer != null && answer.getId() != null) {
            questionRepository.deleteAnswerFromQuestions(answer.getId());
        }
    }

    public Category addQuestionToCategory(Category category, Question question) {
        if (category != null && category.getId() != null && question != null) {
            if (question.getId() == null) {
                Question tmp = questionRepository.findQuestionByStringAndPrice(question.getQuestion(), question.getPrice());
                if (tmp == null) {
                    tmp = new Question();
                    tmp.setQuestion(question.getQuestion());
                    tmp.setPrice(question.getPrice());
                    tmp = questionRepository.save(tmp);
                    for (Answer answer : question.getAnswers()) {
                        tmp = addAnswerToQuestion(tmp, answer);
                    }
                }
                question = tmp;
            }

            categoryRepository.addQuestion(category.getId(), question.getId());
            category.getQuestions().add(question);
        }

        return category;
    }

    public Pack addPack(Pack pack){
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
    }
}
