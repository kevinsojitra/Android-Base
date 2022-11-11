package appsaint.sk.base;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import java.util.Calendar;

public abstract class BaseDialog<V extends ViewDataBinding, M extends ViewModel> extends DialogFragment {
    protected V binding;
    protected Context context;
    protected M viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            Display display = dialog.getWindow().getWindowManager().getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            int width = metrics.widthPixels;
            dialog.getWindow().setLayout(
                    (int) (width*0.9),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        super.onStart();
    }



}
