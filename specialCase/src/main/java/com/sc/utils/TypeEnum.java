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

    /**
     * 用户权限
     */
    public static enum USER_TYPE{
        /**
         * 普通用户
         */
        NORMAL("00"),
        /**
         * 中级用户
         */
        MEDIUM("01"),
        /**
         * 高级用户
         */
        TOP("02");

        private String flag;

        private USER_TYPE(String flag) { this.flag=flag; }

        public String toString() { return flag; }
    }

    /**
     * 管理员权限
     */
    public static enum ADMIN_TYPE{
        /**
         * 初级管理员
         */
        NORMAL("00"),
        /**
         * 中级管理员
         */
        MEDIUM("01"),
        /**
         * 高级管理员
         */
        TOP("02"),
        /**
         * 不是管理员
         */
        NO("03");

        private String flag;

        private ADMIN_TYPE(String flag) { this.flag=flag; }

        public String toString() { return flag; }
    }

    /**
     * 图片标识
     */
    public static enum RESOURCE_TYPE{
        /**
         * 首页
         */
        HOME("00"),
        /**
         * 病例
         */
        CASE("01"),
        /**
         * 病灶
         */
        LESION("02");

        private String flag;

        private RESOURCE_TYPE(String flag) { this.flag=flag; }

        public String toString() { return flag; }
    }

    /**
     * 是否样例
     */
    public static enum IS_SAMPLE{
        /**
         * 不是
         */
        NO("00"),
        /**
         * 时是
         */
        YES("01");

        private String flag;

        private IS_SAMPLE(String flag) { this.flag=flag; }

        public String toString() { return flag; }
    }
}
