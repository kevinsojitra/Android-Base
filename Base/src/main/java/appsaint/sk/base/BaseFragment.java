package appsaint.sk.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.FontRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public abstract class BaseFragment<V extends ViewDataBinding, M extends ViewModel> extends Fragment {
    protected V binding;
    protected Context context;
    protected M viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, setLayout(), container, false);

        context = getActivity();
        viewModel = new ViewModelProvider(this).get(setViewModelClass());
        return setData(binding);
    }

    protected View setData(V binding) {
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUI(view);

        setListener();

        super.onViewCreated(view, savedInstanceState);
    }

    @LayoutRes
    protected abstract int setLayout();

    protected abstract Class<M> setViewModelClass();

    protected abstract void initUI(View view);

    protected abstract void setListener();

}
