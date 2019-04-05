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
    private volatile static Map<Long, ResultSet> results;
    private volatile int useConnection = 0;

    public MysqlManager(int countConnections, String url, String user, String password) throws SQLException {
        this.countConnections = countConnections;
        connections = new ArrayList<>(countConnections);
        for (int i = 0; i < countConnections; i++) {
            connections.add(i, new MysqlConnection(url, user, password));
        }
    }

    public long addTask(String query) {
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).canUseConnection()) {
                return connections.get(i).addTask(query, (result, id, thread) -> {
                    results.put(id, result);
                    return true;
                });
            }
        }

        if (useConnection > countConnections) useConnection = 0;

        return connections.get(useConnection++).addTask(query, (result, id, thread) -> {
            results.put(id, result);
            return true;
        });
    }

    public long addTask(String query, ResultRunnable resultRunnable) {
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).canUseConnection()) {
                return connections.get(i).addTask(query, (result, id, thread) -> {
                    if (!resultRunnable.exec(result, id, thread))
                        results.put(id, result);
                    return true;
                });
            }
        }

        if (useConnection > countConnections) useConnection = 0;

        return connections.get(useConnection++).addTask(query, (result, id, thread) -> {
            if (!resultRunnable.exec(result, id, thread))
                results.put(id, result);
            return true;
        });
    }

    public final static ResultRunnable printResult = new ResultRunnable() {
        @Override
        public boolean exec(ResultSet results, long id, Thread thread) throws SQLException {
            while (results.next()) {
                for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
                    System.out.print(results.getString(i) + ' ');
                }
                System.out.println();
            }
            return true;
        }
    };
}
