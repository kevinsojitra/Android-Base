package appsaint.sk.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;


public abstract class BaseActivity <V extends ViewDataBinding, M extends AndroidViewModel> extends AppCompatActivity {
    protected V binding;
    protected Context context;
    protected M viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, setLayout());
        setContentView(binding.getRoot());
        context = this;
        viewModel = new ViewModelProvider(this).get(setViewModelClass());
        initUI();
        setListener();
    }

    @LayoutRes
    protected abstract int setLayout();

    protected abstract Class<M> setViewModelClass();

    protected abstract void initUI();

    protected abstract void setListener();

}
