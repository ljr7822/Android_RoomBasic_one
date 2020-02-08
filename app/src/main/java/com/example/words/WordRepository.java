package com.example.words;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * 仓库类:用于获取数据等操作
 *
 * @author iwenLjr
 * Create to 2020/2/8 14:05
 */
class WordRepository {

    private LiveData<List<Word>>allWordsLive;
    private WordDao wordDao;

    WordRepository(Context context) {
        // 创建一个DataBase,把context传递进去
        WordDatabase wordDatabase = WordDatabase.getDatabase(context.getApplicationContext());
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();
    }

    void insertWords(Word... words){
        new InsertAsyncTask(wordDao).execute(words);
    }

    void updataWords(Word... words){
        new UpdateAsyncTask(wordDao).execute(words);
    }

    void deleteWords(Word... words){
        new DeleteAsyncTask(wordDao).execute(words);
    }

    void deleteAllWords(Word... words){
        new DeleteAllAsyncTask(wordDao).execute();
    }

    LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    // 生成内部类，进行异步处理插入
    static class InsertAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;

        // 使用构造函数传进来
        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            // 在后台需要做什么，相当于把数据库操作放到后台执行
            wordDao.insertWords(words);
            return null;
        }
//        // 任务完成的时候才呼叫，一般将结果带回主线程
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//        }
//        // 当进度发生更新时呼叫，进度条滚动等操作
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
    }

    // 生成内部类，进行异步处理更新
    static class UpdateAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;

        // 使用构造函数传进来
        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            // 在后台需要做什么，相当于把数据库操作放到后台执行
            wordDao.updateWords(words);
            return null;
        }
    }

    // 生成内部类，进行异步处理删除
    static class DeleteAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;

        // 使用构造函数传进来
        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            // 在后台需要做什么，相当于把数据库操作放到后台执行
            wordDao.deleteWords(words);
            return null;
        }
    }

    // 进行异步处理清空
    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private WordDao wordDao;

        // 使用构造函数传进来
        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // 在后台需要做什么，相当于把数据库操作放到后台执行
            wordDao.deleteAllWords();
            return null;
        }
    }
}
