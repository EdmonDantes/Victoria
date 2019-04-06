/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 05.04.19 22:59
*/

package ru.liveproduction.victoria.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public final class MysqlManager {
    private volatile int countConnections;
    private volatile List<MysqlConnection> connections;
    private volatile int useConnection = 0;

    public final static ResultRunnable printResult = (results, answer, thread) -> {
        System.out.println(answer);
        if (results != null) {
            while (results.next()) {
                for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
                    System.out.print(results.getString(i) + ' ');
                }
                System.out.println();
            }
        }
        return true;
    };

    public MysqlManager(int countConnections, String url, String user, String password) {
        this.countConnections = countConnections;
        connections = new ArrayList<>(countConnections);
        for (int i = 0; i < countConnections; i++) {
            try {
                connections.add(new MysqlConnection(url, user, password));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTask(String query, ResultRunnable resultRunnable, MysqlConnection.Type type) {

        if (connections.size() < 1) return;

        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).canUseConnection()) {
                connections.get(i).addTask(query, (result, answer, thread) -> {
                    resultRunnable.exec(result, answer, thread);
                    return true;
                }, type);
                return;
            }
        }

        if (useConnection > countConnections) useConnection = 0;

        connections.get(useConnection++).addTask(query, (result, answer, thread) -> {
            resultRunnable.exec(result, answer, thread);
            return true;
        }, type);
    }

    public void addTask(String query, ResultRunnable resultRunnable) {
        addTask(query, resultRunnable, MysqlConnection.Type.GET);
    }



    public ResultSet addTaskAsyncGet(String query) {
        boolean[] waiting = new boolean[]{true};
        ResultSet[] resultSets = new ResultSet[1];
        addTask(query, (result, answer, thread) -> {
            if (result != null) {
                resultSets[0] = result;
                waiting[0] = false;
            }
            return true;
        });
        while (waiting[0]) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return resultSets[0];
    }

    public int addTaskAsyncUpdate(String query) {
        boolean[] waiting = new boolean[]{true};
        int[] resultSets = new int[1];
        addTask(query, (result, answer, thread) -> {
                resultSets[0] = answer;
                waiting[0] = false;
            return true;
        }, MysqlConnection.Type.UPDATE);

        while (waiting[0]) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return resultSets[0];
    }

}
