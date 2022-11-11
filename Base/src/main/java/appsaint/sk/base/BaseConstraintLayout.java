package appsaint.sk.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseConstraintLayout <V extends ViewDataBinding> extends ConstraintLayout {
    protected V binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),setLayout(),this,true);

    public BaseConstraintLayout(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public BaseConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public BaseConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public BaseConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    @LayoutRes
    protected abstract int setLayout();

    protected abstract void init(Context context, AttributeSet attrs) ;
}


