package appsaint.sk.base;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public abstract class BaseBottomSheetDialog<V extends ViewDataBinding, M extends ViewModel> extends BottomSheetDialogFragment {
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

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    BottomSheetDialog d = (BottomSheetDialog) dialog;
                    View bottomSheetInternal = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                    if (bottomSheetInternal != null) {
                        BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }
            });
        }
        super.onStart();
    }

}
