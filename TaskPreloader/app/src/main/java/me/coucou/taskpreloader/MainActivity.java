package me.coucou.taskpreloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressbar = (ProgressBar) this.findViewById(R.id.splash_progressbar);
        mTaskStatusText = (TextView)this.findViewById(R.id.task_status_text);
        mTaskStatusText.setText(R.string.preloader_init);

        LoadingTasksManager manager = new LoadingTasksManager(this,
                mTaskStatusText,
                findViewById(R.id.splash_container));
    }
}
