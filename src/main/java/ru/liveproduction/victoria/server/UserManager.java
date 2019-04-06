package ru.liveproduction.victoria.server;

import ru.liveproduction.victoria.api.User;
import ru.liveproduction.victoria.mysql.MysqlManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class UserManager {

    private static MysqlManager manager;
    private final static Random rand = new Random();

    public UserManager(String url, String user, String password){
        manager = new MysqlManager(15, url, user, password);
        manager.addTaskAsyncUpdate("create table if not exists `users`(`id` integer unsigned auto_increment, `name` varchar(17) binary, `token` varchar(128), `date` integer unsigned, primary key (`id`), unique key(`token`));");
        manager.addTaskAsyncUpdate("create table if not exists `market`(`id_user` integer unsigned, `id_pack` integer unsigned, primary key (`id_user`));");
    }

    private String createName(){
        return "player" + rand.nextInt();
    }

    private static String createToken() {
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < 128)
            stringBuilder.append(rand.nextInt());
        return stringBuilder.substring(0, 128).toString();
    }

    public User createUserForRequest(){
        String name = createName();
        String token = createToken();
        long date = System.currentTimeMillis();
        manager.addTaskAsyncUpdate("insert into `users`(`name`,`token`, `date`) values(\'" + name + "\',\'" + token + "\',\'" + date / 1000 + "\');");
        ResultSet rs = manager.addTaskAsyncGet("select `id` from `users` where `token` = \'" + token + "\';");
        try {
            rs.next();
            return new User(rs.getInt("id"), name, token, date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserFromId(int id) throws SQLException {
        ResultSet rs = manager.addTaskAsyncGet("select * from `users` where `id` = \'" + id + "\';");
        assert rs != null;
        if (rs.next())
            return new User(rs.getInt("id"), rs.getString("name"), rs.getString("token"), rs.getLong("date"));
        return null;
    }

    public boolean checkMarket(int id_user, int id_pack) {
        //TODO: create method to check buy thing in google play market
        return true;
    }
}
