package ru.liveproduction.victoria.api;

import javafx.util.Pair;

import java.util.List;

public class Game {



    String name;
    Thread gameThread;
    int round;
    int maxPlayers;
    List<User> players;
    List<Integer> points;

    int priority;
    List<Pair<String, List<Pair<Question, Integer>>>> questions;
    int timeRead;
    int timeWrite;

    Pair<Question, Integer> nowQuestion = null;

    public void sendAllCategories(){
        //TODO: with servers
    }

    public void waitClients(){
        //TODO: with servers
    }

    public void sendQuestion(){
        //TODO: with servers
    }

    public void waitRead(){
        //TODO
    }

    public void synh(){
        //TODO
    }

    public void waitWrite(){
        //TODO
    }

    public void checkAnswer(int id, String answer){
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == id) {
                if (nowQuestion.getKey().isRigthAnswer(answer)) {
                    endWait();
                    points.set(i, points.get(i) + nowQuestion.getValue());
                    priority = i;
                    updateState();
                } else {
                    points.set(i, points.get(i) + nowQuestion.getValue());
                    updateState();
                }
            }
        }
    }

    public void endWait() {
        //TODO
    }

    public void updateState(){
        //TODO
    }

}
