package com.app.unofficial_nhl.ui.favorite;

import android.graphics.Color;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FavoriteFragment extends Fragment {

    private FavoritesViewModel notificationsViewModel;
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
                ViewModelProviders.of(this).get(FavoritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        ProgressBar loadingBar = root.findViewById(R.id.progressBar2);
        loadingBar.setVisibility(View.VISIBLE);

        TableLayout table = (TableLayout) root.findViewById(R.id.imagetable);
        int i = 0;
        ArrayList<Integer> logos = (ArrayList<Integer>) new ArrayList<>(StaticData.logosMap.values());
        ArrayList<String> teams = (ArrayList<String>) new ArrayList<>(StaticData.logosMap.keySet());;

        for (int r=1; r<=32; r++){
            TableRow tr = new TableRow(getContext());
            for (int c=1; c<=1; c++){
                ImageView im = new ImageView (getContext());
                TextView team = new TextView((getContext()));
                team.setTextColor(Color.WHITE);
                team.setTextSize(20f);
                team.setText(teams.get(i));
                im.setImageDrawable(getResources().getDrawable(logos.get(i)));
                im.setPadding(0, 0, 0, 0); //padding in each image if needed
                //add here on click event etc for each image...
                //...
                tr.addView(im, 150,150);
                tr.addView(team);
                i++;
            }
            table.addView(tr);
        }




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

                        recyclerView = root.findViewById(R.id.listviewfavs);
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


}