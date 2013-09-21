/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.go.gregister.web.utils;

/**
 *
 * @author Totoland
 */
public enum PrivilegeTypeId {

    OWNER(1), SHARE(2);
    private final int id;

    PrivilegeTypeId(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}
