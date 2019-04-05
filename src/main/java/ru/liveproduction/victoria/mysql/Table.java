/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 06.04.19 2:59
*/

package ru.liveproduction.victoria.mysql;

public class Table<T> {
    String name;
    ColumnPreference columns;
    Class<T> clazz;

    public Table(String name, ColumnPreference columns, Class<T> clazz){
        this.name = name;
        this.columns = columns;
    }

    public
}
