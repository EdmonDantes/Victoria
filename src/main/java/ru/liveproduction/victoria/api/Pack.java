package ru.liveproduction.victoria.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pack {
    int id_pack;
    Map <String, Map<Integer, List<Question>>> data;
    public String complex (int comp){
        ArrayList<String> str=new ArrayList();

        str.add("Films");
        str.add("IT");
        str.add("Math");


        for (int i=0; i<str.size(); i++){
            return (str.get(i));
        }

        return null;
    }

}
