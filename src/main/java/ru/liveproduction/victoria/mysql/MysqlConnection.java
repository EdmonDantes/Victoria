/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 06.04.19 1:30
*/

package ru.liveproduction.victoria.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public final class MysqlConnection {
    private volatile Connection mysqlConnection;
    private volatile Thread threadConnection;
    private volatile long sleepTimeInMs = 100;
    private volatile boolean startThread = true;
    private volatile boolean useConnection = false;
    private volatile Queue<Map.Entry<String, Long>> query;
    private volatile Map<Long, ResultRunnable> runnables;
    private volatile Map<Long, ResultSet> results;

    private static volatile long endResultId = 0;

    public MysqlConnection(String url, String user, String password) throws SQLException {
        query = new LinkedBlockingQueue<>();
        runnables = new HashMap<>();
        results = new HashMap<>();
        mysqlConnection = DriverManager.getConnection(url, user, password);
        threadConnection = new Thread(() -> {
            try {
                while (startThread) {
                    useConnection = true;
                    while (query.size() > 0) {
                        Map.Entry<String, Long> pair = query.poll();
                        assert pair != null;
                        ResultSet rs = mysqlConnection.prepareStatement(pair.getKey()).executeQuery();
                        ResultRunnable run = runnables.get(pair.getValue());
                        if (run == null || !run.exec(rs, pair.getValue(), threadConnection)){
                            results.put(pair.getValue(), rs);
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

    public long addTask(String query){
        long tmp = endResultId++;
        this.query.add(new AbstractMap.SimpleEntry<>(query, tmp));
        return tmp;
    }

    public long addTask(String query, ResultRunnable runnable) {
        long tmp = endResultId++;
        this.runnables.put(tmp, runnable);
        this.query.add(new AbstractMap.SimpleEntry<>(query, tmp));
        return tmp;
    }

    public ResultSet getResult(long id) {
        return results.get(id);
    }

    public void closeConnection(){
        startThread = false;
    }
}
