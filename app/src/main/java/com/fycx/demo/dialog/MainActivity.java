package com.fycx.demo.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.fycx.dialog.TextListSelectionDialog;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_text_list_selection).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_text_list_selection:
                showTextListSelectionDialog();
                break;
            default:
        }
    }

    private void showTextListSelectionDialog() {
        String[] arrays = {"拍照","相册"};
        List<String> strings = Arrays.asList(arrays);
        new TextListSelectionDialog
                .Builder(this)
                .setStrings(strings)
                .setTextColor(Color.parseColor("#282828"))
                .setTextItemHeight(getResources().getDimensionPixelOffset(R.dimen.list_item_height))
                .setTextSizeInSp(18)
                .setOnTextItemClickListener(new TextListSelectionDialog.OnTextItemClickListener() {
                    @Override
                    public void onTextItemClick(DialogFragment dialog, String itemText, int position) {
                        Toast.makeText(MainActivity.this,"show TextListSelectionDialog-"+itemText+"-"+position,Toast.LENGTH_LONG).show();
                    }
                })
                .create().show();
    }
}
