package net.melove.demo.chat.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.easemob.util.HanziToPinyin;

import net.melove.demo.chat.info.MLUserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzan13 on 2015/7/21.
 */
public class MLUserDao {


    public MLUserDao(Context context) {
        MLDBManager.getInstance().onInit(context);
    }

    /**
     * 保存一个User
     *
     * @param userInfo
     */
    public synchronized void saveContacts(MLUserInfo userInfo) {
        ContentValues values = new ContentValues();
        values.put(MLDBConstants.COL_USER_NAME, userInfo.getUserName());
        values.put(MLDBConstants.COL_NICKNAME, userInfo.getNickName());
        values.put(MLDBConstants.COL_EMAIL, userInfo.getEmail());
        values.put(MLDBConstants.COL_AVATAR, userInfo.getAvatar());
        values.put(MLDBConstants.COL_COVER, userInfo.getCover());
        values.put(MLDBConstants.COL_GENDER, userInfo.getGender());
        values.put(MLDBConstants.COL_LOCATION, userInfo.getLocation());
        values.put(MLDBConstants.COL_SIGNATURE, userInfo.getSignature());
        values.put(MLDBConstants.COL_CREATE_AT, userInfo.getCreateAt());
        values.put(MLDBConstants.COL_UPDATE_AT, userInfo.getUpdateAt());
        MLDBManager.getInstance().insterData(MLDBConstants.TB_USER, values);
    }

    /**
     * 删除一个User
     *
     * @param username
     */
    public synchronized void deleteContacts(String username) {
        MLDBManager.getInstance().delete(
                MLDBConstants.TB_USER,
                MLDBConstants.COL_USER_NAME,
                new String[]{username});
    }

    /**
     * 更新一个User信息
     *
     * @param userInfo
     */
    public synchronized void updateContacts(MLUserInfo userInfo) {
        ContentValues values = new ContentValues();
        values.put(MLDBConstants.COL_USER_NAME, userInfo.getUserName());
        values.put(MLDBConstants.COL_NICKNAME, userInfo.getNickName());
        values.put(MLDBConstants.COL_EMAIL, userInfo.getEmail());
        values.put(MLDBConstants.COL_AVATAR, userInfo.getAvatar());
        values.put(MLDBConstants.COL_COVER, userInfo.getCover());
        values.put(MLDBConstants.COL_GENDER, userInfo.getGender());
        values.put(MLDBConstants.COL_LOCATION, userInfo.getLocation());
        values.put(MLDBConstants.COL_SIGNATURE, userInfo.getSignature());
        values.put(MLDBConstants.COL_CREATE_AT, userInfo.getCreateAt());
        values.put(MLDBConstants.COL_UPDATE_AT, userInfo.getUpdateAt());
        MLDBManager.getInstance().updateData(
                MLDBConstants.TB_USER, values,
                MLDBConstants.COL_USER_NAME,
                new String[]{userInfo.getUserName()});

    }

    /**
     * 保存联系人集合
     *
     * @param userInfoList
     */
    public synchronized void saveContactsList(List<MLUserInfo> userInfoList) {
        for (MLUserInfo userInfo : userInfoList) {
            saveContacts(userInfo);
        }
    }

    /**
     * 获取联系人列表
     *
     * @return
     */
    public synchronized Map<String, MLUserInfo> getContactList() {
        Cursor cursor = MLDBManager.getInstance().queryData(MLDBConstants.TB_USER,
                null, null, null, null, null, null, null);

        Map<String, MLUserInfo> users = new HashMap<String, MLUserInfo>();
        while (cursor.moveToNext()) {
            MLUserInfo userInfo = new MLUserInfo();
            String username = cursor.getString(cursor.getColumnIndex(MLDBConstants.COL_USER_NAME));
            String nickname = cursor.getString(cursor.getColumnIndex(MLDBConstants.COL_NICKNAME));
            String email = cursor.getString(cursor.getColumnIndex(MLDBConstants.COL_EMAIL));
            String avatar = cursor.getString(cursor.getColumnIndex(MLDBConstants.COL_AVATAR));
            String cover = cursor.getString(cursor.getColumnIndex(MLDBConstants.COL_COVER));
            int gender = cursor.getInt(cursor.getColumnIndex(MLDBConstants.COL_GENDER));
            String location = cursor.getString(cursor.getColumnIndex(MLDBConstants.COL_LOCATION));
            String signature = cursor.getString(cursor.getColumnIndex(MLDBConstants.COL_SIGNATURE));
            String createAt = cursor.getString(cursor.getColumnIndex(MLDBConstants.COL_CREATE_AT));
            String updateAt = cursor.getString(cursor.getColumnIndex(MLDBConstants.COL_UPDATE_AT));

            if (TextUtils.isEmpty(nickname)) {
                nickname = username;
            }
            userInfo.setUserName(username);
            userInfo.setNickName(nickname);
            userInfo.setEmail(email);
            userInfo.setAvatar(avatar);
            userInfo.setCover(cover);
            userInfo.setGender(gender);
            userInfo.setLocation(location);
            userInfo.setSignature(signature);
            userInfo.setCreateAt(createAt);
            userInfo.setUpdateAt(updateAt);

            // 判断是否为阿拉伯数字 设置联系人列表的header
            if (Character.isDigit(userInfo.getNickName().charAt(0))) {
                userInfo.setHeader("");
            } else {
                userInfo.setHeader(HanziToPinyin.getInstance()
                        .get(userInfo.getNickName().substring(0, 1))
                        .get(0).target.substring(0, 1).toUpperCase());
                char header = userInfo.getHeader().toLowerCase().charAt(0);
                if (header < 'a' || header > 'z') {
                    userInfo.setHeader("#");
                }
            }
            users.put(username, userInfo);
        }
        cursor.close();
        return users;
    }

}