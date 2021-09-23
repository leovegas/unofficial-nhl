package com.app.unofficial_nhl.ui.notifications;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.NetworkServiceNews;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.pojos.news.Doc;
import com.app.unofficial_nhl.pojos.news.News;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private News news;

    String copyright = "";
    List<Doc> docs = new ArrayList<Doc>();
    String headline = "";
    String article = "";
    String imageurl = "";
    String source = "";
    String date = "";
    RecyclerView recyclerView;
    CustomAdapterNews recyclerAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        ArrayList<ListRowNews> data = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<>();

        NetworkServiceNews.getInstance()
                .getJSONApi()
                .getNews2()
                .subscribeOn(Schedulers.io())
                .flatMapIterable(news -> news.getResponse().getDocs())
                .toList()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribe(new DisposableObserver<List<Doc>>() {

                    @Override
                    public void onNext(List<Doc> docs) {
                        for (Doc doc : docs) {

                            headline = doc.getAbstract();
                            article = doc.getLeadParagraph();
                            source = doc.getSource();
                            date = doc.getPubDate();
                            imageurl = doc.getMultimedia().get(0).getUrl();
                            String imageFull = "https://www.nytimes.com/" + imageurl;
                            System.out.println(imageFull);
                            ListRowNews listRowNews = new ListRowNews(headline, article, source, imageFull, date);
                            data.add(listRowNews);
                        }

                        recyclerView = root.findViewById(R.id.listviewnews);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerAdapter = new CustomAdapterNews(getActivity(), data);
                        recyclerView.setAdapter(recyclerAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }


}