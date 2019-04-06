package ru.liveproduction.victoria.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pack {
    int id;
    Map <String, Map<Integer, List<Question>>> data;

    public Pack(int id, Map<String, Map<Integer, List<Question>>> data) {
        this.id = id;
        this.data = data;
    }

    public Pack complex(boolean easy, boolean middle, boolean hard){
        Map<String, Map<Integer, List<Question>>> result = new HashMap<>();

        for (Map.Entry<String, Map<Integer, List<Question>>> tmp : data.entrySet()) {
            Map<Integer, List<Question>> result0 = new HashMap<>();
            for (Map.Entry<Integer, List<Question>> tmp0 : tmp.getValue().entrySet()) {
                if (tmp0.getKey() > 0 && tmp0.getKey() <= 15 && easy) result0.put(tmp0.getKey(), tmp0.getValue());
                else if (tmp0.getKey() > 15 && tmp0.getKey() <= 35 && middle) result0.put(tmp0.getKey(), tmp0.getValue());
                else if (hard && tmp0.getKey() > 35) result0.put(tmp0.getKey(), tmp0.getValue());
            }
            if (result0.size() > 0) {
                result.put(tmp.getKey(), result0);
            }
        }

        return new Pack(-1, result);
    }

}
