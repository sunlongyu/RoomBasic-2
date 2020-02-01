package com.example.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private LiveData<List<Word>> allWordsLiveData;
    private WordDao wordDao;

    public WordRepository(Context context) {
        WordDataBase wordDataBase = WordDataBase.getWordDataBase(context.getApplicationContext());
        wordDao = wordDataBase.getWordDao();
        allWordsLiveData = wordDao.findAllLiveData();
    }

    public LiveData<List<Word>> getAllWordsLiveData() {
        return allWordsLiveData;
    }

    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }


        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insert(words);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private WordDao wordDao;

        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAll();
            return null;
        }
    }

    public void insertWord(Word... words) {
        new InsertAsyncTask(wordDao).execute(words);
    }

    public void deleteAll() {
        new DeleteAllAsyncTask(wordDao).execute();
    }
}
