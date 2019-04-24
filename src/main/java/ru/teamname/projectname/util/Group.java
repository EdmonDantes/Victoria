package ru.teamname.projectname.util;

public class Group {
    protected Group parent;
    protected PermissionTree tree;

    public Group(Group parent, PermissionTree tree) {
        this.parent = parent;
        this.tree = tree;
    }

    public boolean addPermission(String permission) {
        boolean negate = permission.charAt(0) == '!';
        return this.tree.addPermission(negate ? permission.substring(1) : permission, negate);
    }

    public boolean havePermission(String permission) {
        return tree.havePermission(permission) || (parent != null && parent.havePermission(permission));
    }

    public boolean deletePermission(String permission) {
        return tree.deletePermisison(permission);
    }
}
