/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 06.04.19 1:25
*/

package ru.liveproduction.victoria.mysql;

import java.sql.ResultSet;

public interface ResultRunnable{
    boolean exec(ResultSet results);
}
