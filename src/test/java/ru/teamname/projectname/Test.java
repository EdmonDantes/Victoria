package ru.teamname.projectname;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.localization.LocalizationString;
import ru.teamname.projectname.repository.localization.LocalizationStringRepository;

import javax.annotation.Resource;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes = {Test.class})
public class Test {

    @Resource
    private LocalizationStringRepository lstring;

    @org.junit.Test
    @Transactional
    public void test(){
        LocalizationString tmp = new LocalizationString();
        tmp.locale = Locale.ENGLISH.toString();
        tmp.string = "Hello";
        tmp = lstring.save(tmp);
        Iterable<LocalizationString> a = lstring.findAll();
        for (LocalizationString b : a)
            System.out.println(b.id);
    }
}
