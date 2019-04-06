/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 06.04.19 2:30
*/

package ru.liveproduction.victoria.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;

public class Question {
    String question;
    List<String> answers;
    int price;

    public Question(String question, List<String> answers, int price) {
        this.question = question;
        this.answers = answers;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getQuestion() {
        return question;
    }

    boolean isRigthAnswer(String answer) { // 83%
        String ansClientTrim = answer.replace(" ", "");
        for (String ans : answers) {
            String ansTrim = ans.replace(" ", "");
            if (ansTrim.length() != ansClientTrim.length()) return false;
            int countWrongChar = 0;
            for (int i = 0; i < ansTrim.length(); i++) {
                if (ansTrim.charAt(i) != ansClientTrim.charAt(i)) countWrongChar++;
            }
            if (ansTrim.length() - (ansTrim.length() * 0.83) > countWrongChar)  return true;
        }
        return false;
    }

    public static Question fromJson(JsonObject obj) {
        List<String> ans = new LinkedList<>();
        if (obj.has("answers")) {
            JsonArray array = obj.get("answers").getAsJsonArray();

            for (int i = 0; i < array.size(); i++) {
                ans.add(array.get(i).getAsString());
            }
        }
        return new Question(obj.get("question").getAsString(), ans, obj.get("price").getAsInt());
    }

    public JsonObject toJson(boolean addAnswers){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("question", question);
        JsonArray array = new JsonArray();
        String[] str = answers.toArray(new String[]{});
        for (int i = 0; i < answers.size(); i++) {
            array.add(str[i]);
        }
        if (addAnswers)
            jsonObject.add("answers", array);
        jsonObject.addProperty("price", price);

        return jsonObject;
    }

    public JsonObject toJson(){
        return toJson(false);
    }

}
