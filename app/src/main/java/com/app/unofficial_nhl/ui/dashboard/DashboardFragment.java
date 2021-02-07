package com.app.unofficial_nhl.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.MyTableAdapter;
import com.app.unofficial_nhl.helper_classes.data_models.Cell;
import com.app.unofficial_nhl.helper_classes.data_models.ColumnHeader;
import com.app.unofficial_nhl.helper_classes.data_models.RowHeader;
import com.evrencoskun.tableview.TableView;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private List<RowHeader> mRowHeaderList;
    private List<ColumnHeader> mColumnHeaderList;
    private List<List<Cell>> mCellList;
    private MyTableAdapter mTableAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

//        Intent myIntent = new Intent(getActivity(), MainActivity.class);
//        getActivity().startActivity(myIntent);
        Spinner spinner = (Spinner) root.findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) root.findViewById(R.id.spinner2);
        TableView mTableView = root.findViewById(R.id.content_container);

        mRowHeaderList = new ArrayList<RowHeader>();
        mColumnHeaderList = new ArrayList<ColumnHeader>();
        mCellList = new ArrayList<List<Cell>>();
        mRowHeaderList.add(new RowHeader("TEST1"));
        mRowHeaderList.add(new RowHeader("TEST2"));
        mRowHeaderList.add(new RowHeader("TEST3"));

        mColumnHeaderList.add(new ColumnHeader("TEST1"));
        mColumnHeaderList.add(new ColumnHeader("TEST2"));
        mColumnHeaderList.add(new ColumnHeader("TEST3"));

        List<Cell> list = new ArrayList<>();
        list.add(new Cell("CEll1"));
        list.add(new Cell("CEll2"));
        list.add(new Cell("CEll3"));

        mCellList.add(list);
        mTableAdapter = new MyTableAdapter();
        mTableView.setAdapter(mTableAdapter);
        mTableAdapter.setAllItems(mColumnHeaderList, mRowHeaderList, mCellList);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), R.layout.spinner_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }
        };

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(root.getContext(), R.layout.spinner_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Regular");
        adapter.add("Playoffs");
        adapter.add("Regular");

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.add("by Division");
        adapter2.add("by Conference");
        adapter2.add("by League");
        adapter2.add("by League");


        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount()); //set the hint the default selection so it appears on launch.

        spinner2.setAdapter(adapter2);
        spinner2.setSelection(adapter2.getCount()); //set the hint the default selection so it appears on launch.

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(root.getContext(), "Spinner item 1!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(root.getContext(), "Spinner item 2!", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        return root;
    }





}