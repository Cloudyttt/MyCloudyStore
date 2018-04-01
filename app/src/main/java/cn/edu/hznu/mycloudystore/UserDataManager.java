package cn.edu.hznu.mycloudystore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;
import static android.os.Build.ID;
import static cn.edu.hznu.mycloudystore.Constant.DATABASE_NAME;
import static cn.edu.hznu.mycloudystore.Constant.DB_VERSION;
import static cn.edu.hznu.mycloudystore.Constant.NAME;
import static cn.edu.hznu.mycloudystore.Constant.PASSWORD;
import static cn.edu.hznu.mycloudystore.Constant.PHONENUMBER;
import static cn.edu.hznu.mycloudystore.Constant.TABLE_NAME;
import static cn.edu.hznu.mycloudystore.Constant._ID;

/**
 * Created by Cloudy on 2017/12/8.
 */

public class UserDataManager {
    private SQLiteDatabase db = null;
    private UserDatabaseHelper dbHelper = null;
    public Context dbContext = null;


    public class UserDatabaseHelper extends SQLiteOpenHelper {
        public UserDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory
                factory, int version) {
            super(context, name, factory, version);
        }

        public UserDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DB_VERSION);
        }

        //创建表结构
        public void onCreate(SQLiteDatabase db) {
            final String sql = "create table " + TABLE_NAME + " ( "
                    + Constant._ID + " integer Primary key autoincrement not null, "
                    + Constant.NAME + " varchar, "
                    + Constant.PHONENUMBER + " varchar, "
                    + Constant.PASSWORD + " varchar) ";
            db.execSQL(sql);
            Toast.makeText(dbContext, "Create succeeded", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // 如果新的版本号高于旧的版本号
            if (newVersion > oldVersion) {
                // 删除表结构
                db.execSQL("drop table if exists " + TABLE_NAME);
                onCreate(db);
            }
        }
    }

    public UserDataManager(Context context) {
        dbContext = context;
        Log.i(TAG, "UserDataManager construction!");
    }

    //打开数据库
    public void openDataBase() throws SQLException {
        dbHelper = new UserDatabaseHelper(dbContext);
        db = dbHelper.getWritableDatabase();
    }

    //关闭数据库
    public void closeDataBase() throws SQLException {
        dbHelper.close();
    }

    //添加新用户，即注册
    public long insertUserData(User user) {
        String userNameIns = user.getUsername();
        String telephoneIns = user.getTelephone();
        String userPwdIns = user.getPassword();
        ContentValues values = new ContentValues();
        values.put(NAME, userNameIns);
        values.put(PHONENUMBER, telephoneIns);
        values.put(PASSWORD, userPwdIns);
        return db.insert(TABLE_NAME, null, values);
    }

    //更新用户信息，如修改密码
    public boolean updateUserData(User user) {
        //int id = userData.getUserId();
        String usernameUpdate = user.getUsername();
        String telephoneUpdate = user.getTelephone();
        String userPwdUpdate = user.getPassword();
        ContentValues values = new ContentValues();
        values.put(PHONENUMBER, telephoneUpdate);
        values.put(PASSWORD, userPwdUpdate);
        return db.update(TABLE_NAME, values, null, null) > 0;
        //return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
    }

    public Cursor fetchUserData(int id) throws SQLException {
        Cursor mCursor = db.query(false, TABLE_NAME, null, ID
                + "=" + id, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public String getStringByColumnName(String columnName, int id) {
        Cursor mCursor = fetchUserData(id);
        int columnIndex = mCursor.getColumnIndex(columnName);
        String columnValue = mCursor.getString(columnIndex);
        mCursor.close();
        return columnValue;
    }

    //查询表中所有数据
    public Cursor fetchAllUserDatas() {
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null,
                null);
        if (cursor.moveToFirst())
        {
            do
            {
                String name = cursor.getString(cursor.getColumnIndex(NAME));
                String telephone = cursor.getString(cursor.getColumnIndex(PHONENUMBER));
                String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
                Log.d(TAG, "fetchAllUserDatas: " + name);
                Log.d(TAG, "fetchAllUserDatas: " + telephone);
                Log.d(TAG, "fetchAllUserDatas: " + password);
            }
            while (cursor.moveToNext());
        }
        return cursor;
    }

    //根据id删除用户
    public boolean deleteUserData(int id) {
        return db.delete(TABLE_NAME, ID + "=" + id, null) > 0;
    }

    //根据用户名注销
    public boolean deleteUserDatabyname(String name) {
        return db.delete(TABLE_NAME, NAME + "=" + name, null) > 0;
    }

    //删除所有用户
    public boolean deleteAllUserDatas() {
        return db.delete(TABLE_NAME, null, null) > 0;
    }

    //根据手机号查询用户，可以判断注册时手机号是否已经存在，用于注册
    public int findUserByPhone(String phoneNumber) {
        Log.i(TAG, "findUserByPhone , phoneNumber=" + phoneNumber);
        int result = 0;
        Cursor mCursor = db.query(TABLE_NAME, null, PHONENUMBER + " = " + phoneNumber, null, null, null,
                null);
        if (mCursor != null) {
            result = mCursor.getCount();
            mCursor.close();
            Log.i(TAG, "findUserByPhone , result = " + result);
        }
        return result;
    }
    //根据手机号查询用户，用于获取已注册用户用户名
    public String getUernameByPhone(String phoneNumber)
    {
        Log.i(TAG, "getUernameByPhone , phoneNumber=" + phoneNumber);
        String result;
        Cursor cursor = db.query(TABLE_NAME,null,PHONENUMBER + " = " + phoneNumber,null,null,null,null);
        if(cursor.moveToFirst())
        {
            String name =  cursor.getString(cursor.getColumnIndex(NAME));
            Log.i(TAG, "getUernameByPhone , name=" + name);
            return name;
        }
        else
        {
            return null;
        }
    }
    //根据手机号和密码查询用户，用于登录
    public int findUserByPhoneAndPwd(String telephone, String pwd) {
        Log.i(TAG, "findUserByPhoneAndPwd");
        int result = 0;
        Cursor mCursor = db.query(TABLE_NAME, null, PHONENUMBER + "=" + telephone + " and " + PASSWORD +
                        "=" + pwd,
                null, null, null, null);
        Log.i(TAG, "findUserByPhoneAndPwd , query: " + NAME + "=" + telephone + " and " + PASSWORD +
                "=" + pwd);
        if (mCursor != null) {
            result = mCursor.getCount();
            mCursor.close();
            Log.i(TAG, "findUserByPhoneAndPwd , result = " + result);
        }
        return result;
    }
}
