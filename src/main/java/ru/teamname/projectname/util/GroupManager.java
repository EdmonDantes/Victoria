/*
Copyright © 2019 Ilya Loginov. All rights reserved.
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository
*/

package ru.teamname.projectname.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;
import java.util.TreeMap;

public class GroupManager {

    protected Map<String, Group> groups = new TreeMap<>();

    public GroupManager(){}

    public GroupManager(JsonObject object) {
        try {
            JsonArray array = object.getAsJsonArray("groups");
            for (int i = 0; i < array.size(); i++) {
                try {
                    PermissionTree permissionTree = new PermissionTree();
                    JsonArray permissionsArray = array.get(i).getAsJsonObject().getAsJsonArray("permissions");
                    for (JsonElement element : permissionsArray) {
                        try {
                            String permission = element.getAsString();
                            if (permission.charAt(0) == '-')
                                permissionTree.addPermission(permission.substring(1), true);
                            else
                                permissionTree.addPermission(permission, false);
                        } catch (Exception ignore) {
                        }
                    }
                    JsonElement parent = array.get(i).getAsJsonObject().get("parent");
                    groups.put(array.get(i).getAsJsonObject().get("name").getAsString(), new Group(parent != null && i != 0 ? groups.get(parent.getAsString()) : null, permissionTree));
                } catch (Exception ignore) {}

            }
        } catch (Exception ignore) {}
    }

    public boolean addGroup(String name, String parent, String... permissions){
        if (name == null) return false;
        PermissionTree permissionTree = new PermissionTree();
        for (String permission : permissions) {
            boolean negate = permission.charAt(0) == '!';
            permissionTree.addPermission(negate ? permission.substring(1) : permission, negate);
        }
        groups.put(name.toLowerCase(), new Group(parent != null ? groups.get(parent) : null, permissionTree));
        return true;
    }

    public Group getGroup(String name) {
        return name != null ? groups.get(name.toLowerCase()) : null;
    }

    public boolean deleteGroup(String name) {
        if (name != null) {
            groups.remove(name);
            return true;
        }
        return false;
    }

    public boolean addPermissions(String name, String... permissions) {
        Group tmp = getGroup(name);
        if (tmp == null) return false;
        for (String permission : permissions) {
            if (!tmp.addPermission(permission)) return false;
        }
        return true;
    }

    public boolean havePermission(String name, String permission) {
        Group tmp = getGroup(name);
        if (tmp == null) return false;
        return tmp.havePermission(permission);
    }

    public boolean deletePermission(String name, String permission) {
        Group tmp = getGroup(name);
        if (tmp == null) return false;
        return tmp.deletePermission(permission);
    }
}
