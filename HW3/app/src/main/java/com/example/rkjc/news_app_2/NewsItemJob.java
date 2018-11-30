package com.example.rkjc.news_app_2;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import android.os.AsyncTask;
import android.util.Log;

public class NewsItemJob extends JobService {
    static AsyncTask task;
    static Repository repo;

    @Override
    public boolean onStartJob(final JobParameters job) {
        task = new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                if(repo == null) {
                    repo = new Repository(getApplication());
                }
                repo.syncDataBase();
                Log.d(null,"Job executed.");
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job, false);
                NotificationUtils.sendNotification(getApplicationContext());
                super.onPostExecute(o);
            }
        };
        task.execute();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if(task != null) {task.cancel(false);}
        return true;
    }
}
