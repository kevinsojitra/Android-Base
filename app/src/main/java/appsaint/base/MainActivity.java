package appsaint.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import appsaint.base.databinding.ActivityMainBinding;
import appsaint.sk.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewModel> implements View.OnClickListener {


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> setViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    protected void initUI() {
        binding.tvDesc.setText("Base is easy to use!");
    }

    @Override
    protected void setListener() {
        binding.tvDesc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == binding.tvDesc){
            Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
        }
    }
}