package com.jose.herrera.todo1test.view.activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.jose.herrera.todo1test.R;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    //@VisibleForTesting
    private RelativeLayout notBlocking;
    private ProgressBar mProgressDialog;


    @Override
    public void setContentView(int layoutResID) {


        if (mProgressDialog == null) {

            mProgressDialog = new ProgressBar(this);
            mProgressDialog.setIndeterminate(true);
            notBlocking = new RelativeLayout(this);
            notBlocking.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            mProgressDialog.setLayoutParams(params);

            mProgressDialog.setVisibility(View.GONE);
            View.inflate(this, layoutResID, notBlocking);
            notBlocking.addView(mProgressDialog);

        }

        super.setContentView(notBlocking);

    }

    protected void showAlert(String message) {

        new AlertDialog.Builder(this).setTitle(getString(R.string.error))
                .setMessage(message)
                .setNeutralButton(getString(R.string.ok), null)
                .create().show();

    }

    public void showProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.setVisibility(View.VISIBLE);

        }

    }

    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

}