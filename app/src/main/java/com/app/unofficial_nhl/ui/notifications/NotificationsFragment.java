package com.app.unofficial_nhl.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.NetworkServiceNews;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.news.Doc;
import com.app.unofficial_nhl.pojos.news.News;
import com.app.unofficial_nhl.ui.home.RecyclerTouchListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

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
    String weburl = "";
    RecyclerView recyclerView;
    CustomAdapterNews recyclerAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        ProgressBar loadingBar = root.findViewById(R.id.progressBar2);
        loadingBar.setVisibility(View.VISIBLE);

        NotificationsViewModel notificationsViewModel =
                ViewModelProviders.of(getActivity()).get(NotificationsViewModel.class);

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
                            imageurl="https://static01.nyt.com/images/2022/08/14/world/14hockey-canada-print/14hockey-canada-mediumThreeByTwo440.jpg";
                            try {
                                imageurl = doc.getMultimedia().get(0).getUrl();
                            } catch (Exception e) {
                                imageurl="https://static01.nyt.com/images/2022/08/14/world/14hockey-canada-print/14hockey-canada-mediumThreeByTwo440.jpg";
                            }
                            weburl = doc.getWebUrl();
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
                        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
                        animation1.setDuration(1000);
                        recyclerView.setAlpha(1f);
                        recyclerView.startAnimation(animation1);
                        notificationsViewModel.block();

                        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                toggleView(view,getContext(),data.get(position).article);

                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }
                        }));

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        loadingBar.setVisibility(View.GONE);

                    }
                });


        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    public synchronized void toggleView(View v, Context context, String text) {

/*        v.animate().withLayer()
                .rotationX(90)
                .setDuration(400)
                .withEndAction(
                        new Runnable() {
                            @Override
                            public void run() {
                                float scale = context.getResources().getDisplayMetrics().density;
                                float distance = v.getCameraDistance() * (scale + (scale / 3));
                                v.setCameraDistance(distance * scale);
                                v.setRotationX(-90);

                                v.animate().withLayer()
                                        .rotationX(0)
                                        .setDuration(400)
                                        .start();
                            }
                        }

                ).start();*/


    }


}