package com.example.words;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * 使用单例模式保证只有一个实例
 *
 * @author iwenLjr
 * Create to 2020/2/7 21:09
 */

// version:版本
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;

    static synchronized WordDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, "word_database")
                    //.addMigrations(MIGRATION_8_9)
                    //.fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public abstract WordDao getWordDao();

    // 创建数据库迁移的策略：添加列
    static final Migration MIGRATION_5_6 = new Migration(5,6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN bar_data INTEGER NOT NULL DEFAULT 1");
        }
    };

    // 创建数据库迁移的策略：添加列
    static final Migration MIGRATION_8_9 = new Migration(7,8) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN chineseinvisible INTEGER NOT NULL DEFAULT 0");
        }
    };

    // 创建数据库迁移的策略：删除列
    static final Migration MIGRATION_4_5 = new Migration(4,5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // 第一步：创建新的表
            database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL , english_word TEXT, " +
                    "chinese_word TEXT)");
            // 第二步：将旧表的数据迁移到新表
            database.execSQL("INSERT INTO wort_temp (id, english_word, chinese_word)" +
                    "SELECT id,english_word,chinese_word FROM word");
            // 第三步：删除旧表
            database.execSQL("DROP TABLE word");
            // 第四步：将新表重命名
            database.execSQL("ALTER TABLE word_temp RENAME to word");
        }
    };
}
