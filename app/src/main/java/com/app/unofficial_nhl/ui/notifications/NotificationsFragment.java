package com.app.unofficial_nhl.ui.notifications;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.NetworkServiceNews;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.ListRow;
import com.app.unofficial_nhl.helper_classes.MyCustomArrayAdapter;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.Teams;
import com.app.unofficial_nhl.pojos.news.Doc;
import com.app.unofficial_nhl.pojos.news.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private News news;

    String copyright =  "";
    List<Doc> docs = new ArrayList<Doc>();
    String headline = "";
    String article = "";
    String imageurl = "";
    String source = "";

    List<String> headlines = new ArrayList<String>();
    List<String> articles = new ArrayList<String>();
    List<String> images = new ArrayList<String>();
    List<String> sources = new ArrayList<String>();

    public static Drawable loadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);

        NetworkServiceNews.getInstance()
                .getJSONApi()
                .getNews()
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                        news = response.body();
                        if (news != null) {
                            docs = news.getResponse().getDocs();
                            copyright = news.getCopyright();
                        }

                        ArrayList<ListRowNews> alldata = new ArrayList<>();

                        for (Doc doc : docs) {

                            headline = doc.getAbstract();
                            article = doc.getLeadParagraph();
                            source = doc.getSource();
                            imageurl = doc.getMultimedia().get(0).getUrl();
                            String image = "https://www.nytimes.com/"+imageurl;

                            ListRowNews listRowNews = new ListRowNews(headline, article, source, image);
                            alldata.add(listRowNews);
                        }


                        MyCustomArrayAdapterNews adapter = new MyCustomArrayAdapterNews(getActivity(), alldata);
                        final ListView listviewnews = (ListView) root.findViewById(R.id.listviewnews);
                        listviewnews.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });



        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }


}