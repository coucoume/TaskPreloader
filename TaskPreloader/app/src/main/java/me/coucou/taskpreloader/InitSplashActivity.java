package me.coucou.taskpreloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.coucou.taskpreloader.task.LoadingTasksManager;

public class InitSplashActivity extends AppCompatActivity {

    public static String TAG="InitActivity";
    private ProgressBar mProgressbar;
    private TextView mTaskStatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        mProgressbar = (ProgressBar) this.findViewById(R.id.splash_progressbar);
        mTaskStatusText = (TextView)this.findViewById(R.id.task_status_text);
        mTaskStatusText.setText(R.string.preloader_init);

        mProgressbar.setVisibility(View.VISIBLE);

        LoadingTasksManager manager = new LoadingTasksManager(this,
                mTaskStatusText,
                findViewById(R.id.splash_container));

    }

}
