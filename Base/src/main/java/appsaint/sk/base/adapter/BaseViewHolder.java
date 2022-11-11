package appsaint.sk.base.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder<viewBinding extends ViewDataBinding> extends  RecyclerView.ViewHolder  {
    public viewBinding binding;
    public BaseViewHolder(@NonNull viewBinding itemView) {
        super(itemView.getRoot());
        binding = itemView;
    }
}
