/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 06.04.19 1:30
*/

package ru.liveproduction.victoria.mysql;

import javafx.util.Pair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class MysqlConnection {

    public enum Type {
        UPDATE,
        GET
    }

    private volatile Connection mysqlConnection;
    private volatile Thread threadConnection;
    private volatile long sleepTimeInMs = 100;
    private volatile boolean startThread = true;
    private volatile boolean useConnection = false;
    private volatile Queue<Pair<Type, Pair<String, ResultRunnable>>> query;

    public MysqlConnection(String url, String user, String password) throws SQLException {
        query = new LinkedBlockingQueue<>();
        mysqlConnection = DriverManager.getConnection(url, user, password);
        threadConnection = new Thread(() -> {
            try {
                while (startThread) {
                    useConnection = true;
                    while (query.size() > 0) {
                        Pair<Type, Pair<String, ResultRunnable>> pair = query.poll();
                        assert pair != null;
                        if (pair.getKey() == Type.GET) {
                            ResultSet rs = mysqlConnection.prepareStatement(pair.getValue().getKey()).executeQuery();
                            pair.getValue().getValue().exec(rs, 0, threadConnection);
                        } else {
                            int answer = mysqlConnection.prepareStatement(pair.getValue().getKey()).executeUpdate();
                            pair.getValue().getValue().exec(null, answer, threadConnection);
                        }
                    }
                    useConnection = false;
                    try {
                        Thread.sleep(sleepTimeInMs);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                assert true;
            }
        });
        threadConnection.start();
    }

    public boolean canUseConnection() {
        return !useConnection && startThread;
    }

    public void setSleepTimeInMs(long sleepTimeInMs) {
        this.sleepTimeInMs = sleepTimeInMs;
    }

    public void addTask(String query, ResultRunnable runnable) {
        this.query.add(new Pair(Type.GET, new Pair<>(query, runnable)));
    }

    public void addTask(String query, ResultRunnable run, Type type) {
        this.query.add(new Pair(type, new Pair<>(query, run)));
    }

    public void closeConnection(){
        startThread = false;
    }
}
