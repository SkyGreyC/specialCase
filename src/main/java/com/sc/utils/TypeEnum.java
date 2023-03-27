package com.sc.utils;

public class TypeEnum {

    /**
     * 是否删除标识
     */
    public static enum IS_DEL{
        /**
         * 未删除
         */
        NO("00"),
        /**
         * 已删除
         */
        YES("01");

        private String flag;

        private IS_DEL(String flag) { this.flag=flag; }

        public String toString() { return flag; }
    }
}
