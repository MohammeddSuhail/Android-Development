package com.example.meetup;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.model.Posts;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class myAdapter extends FirebaseRecyclerAdapter<Posts,myAdapter.MyViewHolder> {

    public myAdapter(@NonNull FirebaseRecyclerOptions<Posts> options) {
        super(options);
    }

    @Override

    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NotNull Posts model) {
        holder.postDesc.setText(model.getPostDec());
        String timeAgo = getTimeAgo(model.getDatePost());
        holder.timeAgo.setText(timeAgo);
        holder.username.setText(model.getUsername());

        Picasso.get().load(model.getPostImageUrl()).into(holder.postImage);
        Picasso.get().load(model.getUserProfileImage()).into(holder.profileImage);
    }

    private String getTimeAgo(String datePost) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyy hh:mm:ss");
        try {
            long time = sdf.parse(datePost).getTime();
            long now = System.currentTimeMillis();
            CharSequence ago =
                    DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
            return ago+"";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row,parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        ImageView postImage,likeImage,commentImage;
        TextView username,timeAgo,postDesc,likeCounter,commentsCounter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profileImagePost);
            postImage = itemView.findViewById(R.id.postImage);
            username = itemView.findViewById(R.id.profileUsername);
            timeAgo = itemView.findViewById(R.id.timeAgo);
            postDesc = itemView.findViewById(R.id.postDesc);
            likeImage = itemView.findViewById(R.id.likeImage);
            commentImage = itemView.findViewById(R.id.commentsImage);
            likeCounter = itemView.findViewById(R.id.likeCounter);
            commentsCounter = itemView.findViewById(R.id.commentCounter);
        }
    }
}
