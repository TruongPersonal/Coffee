package com.project.DTO;

import java.util.HashMap;

public class PermissionAccount {
    private static PermissionAccount instance;

    private int accountId;
    private int roleId;
    private String roleName;
    private HashMap<String, Boolean> permissions;

    // Private constructor to prevent instantiation from outside
    private PermissionAccount() {
        // Initialize with default values or leave them null
    }

    // Method to get the singleton instance
    public static synchronized PermissionAccount getInstance() {
        if (instance == null) {
            instance = new PermissionAccount();
        }
        return instance;
    }

    // Method to set permission data after successful login
    public synchronized void setPermissionData(int accountId, int roleId, String roleName,
            HashMap<String, Boolean> permissions) {
        this.accountId = accountId;
        this.roleId = roleId;
        this.roleName = roleName;
        this.permissions = permissions;
    }

    public synchronized void reset() {
        // Đặt lại tất cả các giá trị về trạng thái ban đầu
        this.accountId = 0;
        this.roleId = 0;
        this.roleName = null;
        this.permissions = null;
    }

    public boolean hasPermission(String permission) {
        if (roleName.equals("Admin")) {
            return true;
        } else {
            return permissions != null && permissions.getOrDefault(permission, false);

        }
    }

    public boolean hasRole(String roleName) {
        return this.roleName != null && this.roleName.equals(roleName);
    }

    public HashMap<String, Boolean> getPermissions() {
        return permissions;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
