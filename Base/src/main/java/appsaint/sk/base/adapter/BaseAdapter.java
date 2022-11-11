package appsaint.sk.base.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<viewBinding extends ViewDataBinding, T> extends RecyclerView.Adapter<BaseViewHolder<viewBinding>> implements Filterable {

    protected List<T> list;
    protected List<T> fullList;

    public BaseAdapter() {
        this.list = new ArrayList<>();
        this.fullList = new ArrayList<>();
    }

    @LayoutRes
    protected abstract int setItemLayout();

    protected abstract void bind(viewBinding binding, T item, int position);


    protected boolean isInFilter(T item, String search) {
        return false;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<T> updatedList) {
        this.list = updatedList;
        this.fullList = updatedList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BaseViewHolder<viewBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), setItemLayout(), parent, false);
        return new BaseViewHolder<viewBinding>(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<viewBinding> holder, int position) {
       bind(holder.binding,list.get(position),position);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<T> filteredList = new ArrayList<>();
                if (constraint.toString().isEmpty()) {
                    filteredList.addAll(fullList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (T item : fullList) {
                        if (isInFilter(item,filterPattern)) {
                            filteredList.add(item);
                        }
                    }

                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                list = (List<T>) results.values;
                notifyDataSetChanged();

            }
        };
    }
}
