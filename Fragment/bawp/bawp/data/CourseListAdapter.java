package bawpapp.buildappswithpaulo.com.bawp.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import bawpapp.buildappswithpaulo.com.bawp.R;
import bawpapp.buildappswithpaulo.com.bawp.model.Course;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by paulodichone on 9/30/17.
 */

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder>  {
    private CourseData courseData = new CourseData();
    public OnItemClickListener itemClickListener;



    @Override
    public CourseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View courseRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_row, parent, false);
        return new ViewHolder(courseRow);
    }

    @Override
    public void onBindViewHolder(final CourseListAdapter.ViewHolder holder, int position) {

        final Context context = holder.courseTitle.getContext();


        Course course = courseData.courseList().get(position);
        holder.courseTitle.setText(course.getCourseName());
        Picasso.with(context)
                .load(course.getImageResourceId(context))
                .into(holder.courseImageView);
        Picasso.with(holder.courseTitle.getContext())
                .load(course.getImageResourceIdForAuthor(context))
                .into(holder.authorImageView);

         Bitmap photo = BitmapFactory.decodeResource(context.getResources(), course.getImageResourceId(context));
        Palette.from(photo).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                //Explore:
                //Vibrant, DarkVibrant, LightVibrant, Muted, DarkMuted, LightMuted
               int bgColor =  palette.getMutedColor(ContextCompat.getColor(context,
                        android.R.color.black));

                holder.courseTitle.setBackgroundColor(bgColor);
                holder.authorImageView.setBorderColor(bgColor);



            }
        });

    }

    @Override
    public int getItemCount() {
        //Log.d("TAG: ","Item count: " + courseData.courseList().size());
        return courseData.courseList().size();
    }

    public void setOnClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {


        public TextView courseTitle;
        public ImageView courseImageView;
        public CircleImageView authorImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            //Very important piece of code - register our view to receive click events
            itemView.setOnClickListener(this);

            courseTitle = itemView.findViewById(R.id.courseTitleId);
            courseImageView = itemView.findViewById(R.id.courseImageId);
            authorImageView = itemView.findViewById(R.id.authorImageID);
        }


        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
