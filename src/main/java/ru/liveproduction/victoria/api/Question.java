/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 06.04.19 2:30
*/

package ru.liveproduction.victoria.api;

import java.util.List;

public class Question {
    int id;
    String question;
    List<String> answers;

    public Question(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    boolean isRigthAnswer(String answer) { // 83%
        String ansClientTrim = answer.replace(" ", "");
        for (String ans : answers) {
            String ansTrim = ans.replace(" ", "");
            if (ansTrim.length() != ansClientTrim.length()) return false;
            int countWrongChar = 0;
            for (int i = 0; i < ansTrim.length(); i++) {
                if (ansTrim.charAt(i) != ansClientTrim.charAt(i)) countWrongChar++;
                if (ansTrim.length() - (ansTrim.length() * 0.83) < countWrongChar) return false;
            }
            return true;
        }
        return false;
    }
}
