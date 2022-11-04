package com.genriking.chatapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genriking.chatapp.R;
import com.genriking.chatapp.pojo.Message;
import com.genriking.chatapp.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

    public static final int TYPE_MY_MESSAGE = 0;
    public static final int TYPE_OTHER_MESSAGE = 1;

    private List<Message> messages;
    private Context context;

    public MessagesAdapter(Context context) {
        messages = new ArrayList<>();
        this.context = context;
    }

    public List<Message> getMassages() {
        return messages;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_MY_MESSAGE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_my_message, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_other_message, parent, false);
        }
        return new MessagesViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        String author = message.getAuthor();

        if (author != null && author.equals(PreferenceManager.getDefaultSharedPreferences(context).getString("author", "anon"))) {
            return TYPE_MY_MESSAGE;
        } else {
            return TYPE_OTHER_MESSAGE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        Message message = messages.get(position);
        String author = message.getAuthor();
        String textOfMessage = message.getTextOfMessage();
        String urlToImage = message.getImageUrl();
        holder.textViewAuthor.setText(author);
        holder.textViewDate.setText(TimeUtils.convertTimestampToTime(message.getDate()));
        if (textOfMessage != null && !textOfMessage.isEmpty()) {
            holder.textViewTextOfMessage.setText(textOfMessage);
            holder.imageViewImage.setVisibility(View.GONE);
        }
        if (urlToImage != null && !urlToImage.isEmpty()) {
            Picasso.get().load(urlToImage).into(holder.imageViewImage);
            holder.imageViewImage.setVisibility(View.VISIBLE);
            holder.textViewTextOfMessage.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MessagesViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewAuthor;
        private TextView textViewTextOfMessage;
        private TextView textViewDate;
        private ImageView imageViewImage;

        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewTextOfMessage = itemView.findViewById(R.id.textViewTextOfMessage);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            imageViewImage = itemView.findViewById(R.id.imageViewImage);
        }
    }
}
