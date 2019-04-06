package ru.liveproduction.victoria.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.liveproduction.victoria.api.Pack;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PackManager {
    List<Pack> packList;

    public PackManager(String filePath) throws FileNotFoundException {
        JsonArray array = new JsonParser().parse(new FileReader(filePath)).getAsJsonObject().get("packManager").getAsJsonArray();
        packList = new ArrayList<>(array.size());
        for (int i = 0; i < array.size(); i++) {
            packList.add(Pack.fromJson(array.get(i).getAsJsonObject()));
        }
    }

    public Pack getPackWithId(int id) {
        for (Pack pack : packList) {
            if (pack.getId() == id) {
                return pack;
            }
        }

        return null;
    }

    public List<Pack> getPackList() {
        return packList;
    }

    public JsonObject toJson(boolean answer){
        JsonArray array = new JsonArray();
        for (int i = 0; i < packList.size(); i++){
            array.add(packList.get(i).toJson(answer));
        }

        JsonObject result = new JsonObject();
        result.add("packManager", array);
        return result;
    }

    public JsonObject toJson(){
        return toJson(false);
    }


}
