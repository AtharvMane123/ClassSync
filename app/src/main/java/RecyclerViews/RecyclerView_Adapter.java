package RecyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.class_sync.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder> {
    ArrayList<RecyclerView_modelClass> arrayList;
    ArrayList<RecyclerView_Ebooks_ModelClass> EBookarrayList;
    int i = 0;

    String Layout;
    RecyclerViewItemClickListener recyclerViewItemClickListener;
    RecyclerViewItemLikedListener recyclerViewItemLikedListener;


    public RecyclerView_Adapter(ArrayList<RecyclerView_modelClass> arrayList, String Layout, RecyclerViewItemClickListener recyclerViewItemClickListener,RecyclerViewItemLikedListener recyclerViewItemLikedListener) {
        this.arrayList = arrayList;
        this.Layout = Layout;
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
        this.recyclerViewItemLikedListener=recyclerViewItemLikedListener;
    }

    public RecyclerView_Adapter(ArrayList<RecyclerView_Ebooks_ModelClass> arrayList, String Layout, RecyclerViewItemClickListener recyclerViewItemClickListener,RecyclerViewItemLikedListener recyclerViewItemLikedListener, int i) {
        this.EBookarrayList = arrayList;
        this.Layout = Layout;
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
        this.recyclerViewItemLikedListener=recyclerViewItemLikedListener;
        this.i = i;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return CreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            if (i == 1) {
                EbookHolder_List(holder, position);
                holder.like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.like.setVisibility(View.INVISIBLE);
                        holder.fav.setVisibility(View.VISIBLE);
                        recyclerViewItemLikedListener.ItemLiked(EBookarrayList.get(position).getName(), EBookarrayList.get(position).getAuthor(), EBookarrayList.get(position).getImg(),
                                EBookarrayList.get(position).getPdfUrl(), EBookarrayList.get(position).getDescription(), EBookarrayList.get(position).getRating(),position);
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerViewItemClickListener.ItemClick(EBookarrayList.get(position).getName(), EBookarrayList.get(position).getAuthor(), EBookarrayList.get(position).getImg(),
                                EBookarrayList.get(position).getPdfUrl(), EBookarrayList.get(position).getDescription());
                    }
                });
            } else {

                holder.like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.like.setVisibility(View.INVISIBLE);
                        holder.fav.setVisibility(View.VISIBLE);
                        recyclerViewItemLikedListener.ItemLiked(arrayList.get(position).getName(), arrayList.get(position).getAuthor(), arrayList.get(position).getBkImgurl(),
                                arrayList.get(position).getAudioUrl(), arrayList.get(position).getDescription(), arrayList.get(position).getRating(),position);
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerViewItemClickListener.ItemClick(arrayList.get(position).getName(), arrayList.get(position).getAuthor(), arrayList.get(position).getBkImgurl(),
                                arrayList.get(position).getAudioUrl(), arrayList.get(position).getDescription());
                    }
                });
                Holder_List(holder, position);
            }
        }


    @Override
    public int getItemCount() {
        if (i == 1) {
            return EBookarrayList.size();
        } else {
            return arrayList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Bk_Name, Bk_Author,like,fav;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Bk_Author = itemView.findViewById(R.id.Recycler_BookAuthor);
            Bk_Name = itemView.findViewById(R.id.Recycler_BookName);
            img = itemView.findViewById(R.id.Recycler_BookImg);
            like=itemView.findViewById(R.id.like);
            fav=itemView.findViewById(R.id.like1);
        }
    }


    public void Holder_List(ViewHolder holder, int position) {
        holder.Bk_Name.setText(arrayList.get(position).getName());
        holder.Bk_Author.setText(arrayList.get(position).getAuthor());
//        holder.img.setImageResource(arrayList.get(position).getBkImgurl());
        Picasso.get().load(arrayList.get(position).getBkImgurl()).error(R.drawable.f_bk2).into(holder.img);
    }


    public void EbookHolder_List(ViewHolder holder, int position) {
        holder.Bk_Name.setText(EBookarrayList.get(position).getName());
        holder.Bk_Author.setText(EBookarrayList.get(position).getAuthor());
//        holder.img.setImageResource(arrayList.get(position).getBkImgurl());
        Picasso.get().load(EBookarrayList.get(position).getImg()).error(R.drawable.f_bk2).into(holder.img);
    }





    public ViewHolder CreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }



    public interface RecyclerViewItemClickListener {
        public void ItemClick(String BkName, String BkAuthor, String BkImgUrl, String BkAudioUrl, String BkDescription);
    }
    public interface RecyclerViewItemLikedListener {
        public void ItemLiked(String BkName, String BkAuthor, String BkImgUrl, String BkAudioUrl, String BkDescription, String BkRating,int position);
    }
}
