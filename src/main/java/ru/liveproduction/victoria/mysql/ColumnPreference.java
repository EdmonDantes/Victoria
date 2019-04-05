/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 06.04.19 2:18
*/

package ru.liveproduction.victoria.mysql;

import java.util.HashMap;
import java.util.Map;

public class ColumnPreference {
    enum TYPES {
        BIT,
        TINYINT,
        UTINYINT,
        SMALLINT,
        USMALLINT,
        MEDIUMINT,
        UMEDIUMINT,
        INT,
        UINT,
        BIGINT,
        UBIGINT,
        DECIMAL,
        FLOAT,
        DOUBLE,
        VARCHAR,
        CHAR,
        BINARY,
        VARBINARY,
        TINYBLOB,
        TINYTEXT,
        BLOB,
        TEXT,
    }

    private final static Map<TYPES, String> string = new HashMap<>();
    static {
        string.put(TYPES.BIT, "BIT");
        string.put(TYPES.TINYINT, "TINYINT");
        string.put(TYPES.UTINYINT, "UNSIGNED TINYINT");
        string.put(TYPES.SMALLINT, "SMALLINT");
        string.put(TYPES.USMALLINT, "UNSIGNED SMALLINT");
        string.put(TYPES.MEDIUMINT, "MEDIUMINT");
        string.put(TYPES.UMEDIUMINT, "UNSIGNED MEDIUMINT");
        string.put(TYPES.INT, "INT");
        string.put(TYPES.UINT, "UNSIGNED INT");
        string.put(TYPES.BIGINT, "MEDIUMINT");
        string.put(TYPES.UBIGINT, "UNSIGNED BIGINT");
        string.put(TYPES.DECIMAL, "DECIMAL");
        string.put(TYPES.FLOAT, "FLOAT");
        string.put(TYPES.DOUBLE, "DOUBLE");
        string.put(TYPES.VARCHAR, "VARCHAR");
        string.put(TYPES.CHAR, "CHAR");
        string.put(TYPES.BINARY, "BINARY");
        string.put(TYPES.VARBINARY, "VARBINARY");
        string.put(TYPES.TINYBLOB, "TINYBLOB");
        string.put(TYPES.TINYTEXT, "TINYTEXT");
        string.put(TYPES.BLOB, "BLOB");
        string.put(TYPES.TEXT, "TEXT");
    }

    private int countColumn;
}
