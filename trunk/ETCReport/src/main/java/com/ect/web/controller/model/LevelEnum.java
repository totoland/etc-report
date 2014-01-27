/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.model;

/**
 *
 * @author Jirawat.l
 */
public enum LevelEnum {

    admin(10), center(20), header(30), auditor(40), recorder(50);
    private int value;

    private LevelEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.value == 10 ? "ผู้ดูแลระบบ" : this.value == 20 ? "ส่วนกลาง" : this.value == 30 ? "หน.จว" : this.value == 40 ? "หน.อก" : this.value == 50 ? "ผู้บันทึกข้อมูล" : "ไม่ระบุ";
    }

    public String getDesc(int value) {
        String res = "ยังไม่ได้เลือก";
        if (value == 10) {
            res = "ผู้ดูแลระบบ";
        } else if (value == 20) {
            res = "ส่วนกลาง";
        } else if (value == 30) {
            res = "หน.จว";
        } else if (value == 40) {
            res = "หน.อก";
        } else if (value == 50) {
            res = "ผู้บันทึกข้อมูล";
        }
        return "";
    }
}
