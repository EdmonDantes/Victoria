/*
Copyright © 2019 Ilya Loginov. All rights reserved.
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 05.04.19 22:45
*/

package ru.liveproduction.victoria;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.liveproduction.victoria.api.Question;
import ru.liveproduction.victoria.server.VictoriaServer;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StartTestingServer {

    public static void main(String ...args) throws ClassNotFoundException, SQLException, IOException {
        JsonObject pref = new JsonParser().parse(new FileReader(args[0])).getAsJsonObject();
        new VictoriaServer(pref.get("url").getAsString(), pref.get("user").getAsString(), pref.get("password").getAsString(), pref.get("pack").getAsString());



//        Scanner scanner = new Scanner(new File("test.txt"),"UTF-8");
//        List<Question> questionList = new LinkedList<>();
//
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            String[] tmp = line.split(Pattern.quote("?"));
//            if (tmp.length > 0) {
//                String quest = tmp[0].trim();
//                if (tmp.length > 1) {
//                    String[] tmp2 = tmp[1].split(Pattern.quote("."));
//                    if (tmp2.length > 0) {
//                        String answer = tmp2[0].trim();
//                        if (tmp2.length > 1) {
//                            String points = tmp2[1].trim();
//                            questionList.add(new Question(quest, Collections.singletonList(answer), Integer.valueOf(points)));
//                        }
//                    }
//                }
//            }
//        }
//        scanner.close();
//
//        JsonArray jsonArray = new JsonArray();
//
//        for (Question question : questionList) {
//            jsonArray.add(question.toJson(true));
//        }
//
//        PrintWriter printWriter = new PrintWriter("output.txt", "UTF-8");
//        printWriter.println(jsonArray.toString());
//        printWriter.flush();
//        printWriter.close();


    }
}
