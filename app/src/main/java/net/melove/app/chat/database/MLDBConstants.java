package net.melove.app.chat.database;

/**
 * Created by lzan13 on 2015/7/24.
 * 数据库常量类
 */
public class MLDBConstants {

    public static final String TB_INVITED = "invited";
    public static final String TB_GROUP = "group";
    public static final String TB_CONTACTS = "contacts";

    // column name
    public static final String COL_USERNAME = "username";
    public static final String COL_NICKNAME = "nickname";
    public static final String COL_EMAIL = "email";
    public static final String COL_AVATAR = "avatar";
    public static final String COL_COVER = "cover";
    public static final String COL_GENDER = "gender";
    public static final String COL_LOCATION = "location";
    public static final String COL_SIGNATURE = "signature";
    public static final String COL_CREATE_AT = "create_at";
    public static final String COL_UPDATE_AT = "update_at";
    public static final String COL_GROUP_ID = "group_id";
    public static final String COL_GROUP_NAME = "group_name";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_OWNER = "owner";
    public static final String COL_MEMBERS = "members";
    public static final String COL_MAX_COUNT = "max_count";
    public static final String COL_COUNT = "count";
    public static final String COL_OPEN = "open";
    public static final String COL_REASON = "reason";
    public static final String COL_STATUS = "status";
    public static final String COL_TIME = "time";
    public static final String COL_TYPE = "type";


    /*创建联系人信息表*/
    public static final String SQL_CONTACTS = "create table if not exists '"
            + TB_CONTACTS + "' ("
            + COL_USERNAME + " varchar(128) primary key, "
            + COL_NICKNAME + " varchar(128), "
            + COL_EMAIL + " varchar(128), "
            + COL_AVATAR + " text, "
            + COL_COVER + " text, "
            + COL_GENDER + " integer, "
            + COL_LOCATION + " text, "
            + COL_SIGNATURE + " text, "
            + COL_CREATE_AT + " integer, "
            + COL_UPDATE_AT + " integer"
            + ")";

    /*创建群组信息*/
    public static final String SQL_GROUP = "create table if not exists '"
            + TB_GROUP + "' ("
            + COL_GROUP_ID + " varchar(128) primary key, "
            + COL_GROUP_NAME + " varchar(128), "
            + COL_DESCRIPTION + " text, "
            + COL_OWNER + " varchar(128), "
            + COL_MEMBERS + " text, "
            + COL_MAX_COUNT + " integer, "
            + COL_COUNT + " integer, "
            + COL_OPEN + " integer, "
            + COL_CREATE_AT + " integer, "
            + COL_UPDATE_AT + " integer"
            + ")";


}
