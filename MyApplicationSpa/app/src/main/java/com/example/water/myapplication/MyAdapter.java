package com.example.water.myapplication;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyModel>{
    private List<Model> feedModelList;
    private Context mContext;
    private OnModelClickListener onModelClickListener;

    public MyAdapter(Context context, List<Model> feedModelList) {
        this.feedModelList = feedModelList;
        this.mContext = context;
    }

    @Override
    public MyModel onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show, null);
        MyModel viewHolder = new MyModel(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyModel mymodel, int i) {
        final Model model = feedModelList.get(i);


        mymodel.id.setText(model.getId_application());
        mymodel.name.setText(model.getName_cus());
        mymodel.lastname.setText(model.getLasname_cus());


        //customViewHolder.name.setText(course.getName());
        //customViewHolder.price.setText(course.getPrice());
        //customViewHolder.name.setText(Html.fromHtml(course.getName()));
        //customViewHolder.price.setText(Html.fromHtml(course.getPrice()));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModelClickListener.onModelClick(model);
            }
        };
        mymodel.id.setOnClickListener(listener);
        mymodel.name.setOnClickListener(listener);
        mymodel.lastname.setOnClickListener(listener);
    }

    public OnModelClickListener getOnItemClickListener() {
        return onModelClickListener;
    }

    public void setOnModelClickListener(OnModelClickListener onModelClickListener) {
        this.onModelClickListener = onModelClickListener;
    }

    @Override
    public int getItemCount() {
        return (null != feedModelList ? feedModelList.size() : 0);
    }

    class MyModel extends RecyclerView.ViewHolder {
        protected TextView id,name,lastname;

        public MyModel(View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.a);
            this.name = (TextView) view.findViewById(R.id.b);
            this.lastname = (TextView) view.findViewById(R.id.c);
        }
    }
}

    /*

    private Context MyContext;
    private List<Model> register ;
    private OnModelClickListener mListener;

    public  interface OnModelClickListener {
        void OnModelClick(Model item);
    }

    public MyAdapter (Context MyContext,List<Model> register ){
        this.MyContext = MyContext;
        this.register = register ;
    }


    @Override
    public MyModel onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(MyContext);
        //View view = inflater.inflate(R.layout.show, null);
        //return new MyAdapter.MyModel(view);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show, null);
        MyModel viewHolder = new MyModel(view);
        return viewHolder;
    }

    public void onBindViewHolder(MyModel holder, int i) {
        Model model = register.get(i);

        String id_cus = model.getId_cus();
        String name_cus = model.getName_cus();
        String lastname_cus = model.getLasname_cus();

        holder.a.setText(model.getId_cus());
        holder.b.setText(model.getName_cus());
        holder.c.setText(model.getLasname_cus());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mListener.OnModelClick(model);
            }
        };
        holder.a.setOnClickListener(listener);
        holder.b.setOnClickListener(listener);
        holder.c.setOnClickListener(listener);
    }


    public OnItemClickListener getOnModelClickListener() {
        return mListener;

    public void setOnModelClickListener(OnModelClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getItemCount()
        {
            return (null != register ? register.size() : 0);
        }

    class MyModel extends RecyclerView.ViewHolder{
        TextView a, b,c;

        public MyModel(View itemView){
            a = itemView.findViewById(R.id.a);
            b = itemView.findViewById(R.id.b);
            c = itemView.findViewById(R.id.c);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (register != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION);
                        //                  mListener.onItemClick(position);
                    }
                }
            });
        }
    }





}
*/