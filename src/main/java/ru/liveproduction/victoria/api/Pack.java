package ru.liveproduction.victoria.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.util.Pair;

import java.util.*;

public class Pack {
    int id;
    Map <String,  List<Question>> data;

    public Pack(int id, Map<String, List<Question>> data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public Pack complex(boolean easy, boolean middle, boolean hard){
        Map<String, List<Question>> result = new HashMap<>();

        for (Map.Entry<String, List<Question>> tmp : data.entrySet()) {
            List<Question> result0 = new LinkedList<>();
            for (Question question : tmp.getValue()){
                if (easy && question.getPrice() > 0 && question.getPrice() <= 15) result0.add(question);
                else if (middle && question.getPrice() > 15 && question.getPrice() <= 40) result0.add(question);
                else if (hard && question.getPrice() > 40 && question.getPrice() <= 100) result0.add(question);
            }

            if (result0.size() > 0) {
                result.put(tmp.getKey(), result0);
            }
        }

        return new Pack(-1, result);
    }

    public List<String> getCategories(int count) {
        Map.Entry[] array = data.entrySet().toArray(new Map.Entry[]{});

        if (array.length < count) return null;

        Random random = new Random();
        List<String> result = new ArrayList<>();
        Set<Integer> numbers = new TreeSet<>();
        while (result.size() < count) {
            int number = random.nextInt(array.length - 1);
            while (numbers.contains(number)) {
                number = random.nextInt(array.length - 1);
            }

            result.add((String) array[number].getKey());
            numbers.add(number);
        }

        return result;
    }

    public List<Pair<String, List<Question>>> getQuestion(List<String> categories, int count) {
        Random random = new Random();
        List<Pair<String, List<Question>>> result = new ArrayList<>();
        for (String str : categories) {
            Set<Integer> numbers = new TreeSet<>();
            List<Question> tmp = new ArrayList<>();

            while (tmp.size() < count) {
                int number = random.nextInt(data.get(str).size());
                while (numbers.contains(number)) {
                    number = random.nextInt(data.get(str).size());
                }
                tmp.add(data.get(str).get(number));
                numbers.add(number);
            }

            if (tmp.size() > 0) {
                result.add(new Pair<>(str, tmp));
            }
        }

        return result;

    }

    public static Pack fromJson(JsonObject obj) {
        JsonArray array = obj.get("categories").getAsJsonArray();
        Map<String, List<Question>> data = new HashMap<>();

        for (int i = 0; i < array.size(); i++){
            JsonObject cat = array.get(i).getAsJsonObject();
            JsonArray question = cat.get("question").getAsJsonArray();
            List<Question> tmp0 = new LinkedList<>();

            for (int j = 0; j < question.size(); j++) {
                JsonObject object = question.get(i).getAsJsonObject();
                tmp0.add(Question.fromJson(object));
            }

            data.put(cat.get("name").getAsString(), tmp0);
        }
        return new Pack(obj.get("id").getAsInt(), data);
    }

    public JsonObject toJson(boolean answers){
        JsonArray result = new JsonArray();
        for (Map.Entry<String, List<Question>> tmp: data.entrySet()) {
            JsonArray array = new JsonArray();
            for (Question tmp0 : tmp.getValue()) {
                array.add(tmp0.toJson(answers));
            }

            JsonObject object = new JsonObject();
            object.addProperty("name", tmp.getKey());
            object.add("question", array);
            result.add(object);
        }

        JsonObject res = new JsonObject();
        res.addProperty("id", id);
        res.add("categories", result);
        return res;
    }

    public JsonObject toJson(){
        return toJson(false);
    }

}
