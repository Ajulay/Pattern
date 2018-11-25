package com.patterns;

import java.util.HashMap;
import java.util.Map;

public class IdentityMap {

    private static Map<Integer, Organization> organizationMap = new HashMap();

    public static void addOrganization(Organization organization) {
        organizationMap.put(organization.getId(), organization);
    }

    public static Organization getOrganization(int key) {
        if (organizationMap.containsKey(key)) {
            return organizationMap.get(key);
        }
        return null;
    }

    public static void removeOrganization(Organization organization) {
        organizationMap.remove(organization.getId());
    }

    public static Map<Integer, Organization> getInstance() {
        return organizationMap;
    }
}
