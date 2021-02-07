package com.app.unofficial_nhl.helper_classes;

import android.view.Gravity;
import com.app.unofficial_nhl.helper_classes.data_models.Cell;
import com.app.unofficial_nhl.helper_classes.data_models.ColumnHeader;
import com.app.unofficial_nhl.helper_classes.data_models.RowHeader;

import java.util.ArrayList;
import java.util.List;

public class MyTableViewModel {
    // View Types
    public static final int GENDER_TYPE = 1;
    public static final int MONEY_TYPE = 2;

    private List<ColumnHeader> mColumnHeaderList;
    private List<RowHeader> mRowHeaderList;
    private List<List<Cell>> mCellList;

    public int getCellItemViewType(int column) {

        switch (column) {
            case 5:
                // 5. column header is gender.
                return GENDER_TYPE;
            case 8:
                // 8. column header is Salary.
                return MONEY_TYPE;
            default:
                return 0;
        }
    }

     /*
       - Each of Column Header -
            "Id"
            "Name"
            "Nickname"
            "Email"
            "Birthday"
            "Gender"
            "Age"
            "Job"
            "Salary"
            "CreatedAt"
            "UpdatedAt"
            "Address"
            "Zip Code"
            "Phone"
            "Fax"
     */

    public int getColumnTextAlign(int column) {
        switch (column) {
            // Id
            case 0:
                return Gravity.CENTER;
            // Name
            case 1:
                return Gravity.LEFT;
            // Nickname
            case 2:
                return Gravity.LEFT;
            // Email
            case 3:
                return Gravity.LEFT;
            // BirthDay
            case 4:
                return Gravity.CENTER;
            // Gender (Sex)
            case 5:
                return Gravity.CENTER;
            // Age
            case 6:
                return Gravity.CENTER;
            // Job
            case 7:
                return Gravity.LEFT;
            // Salary
            case 8:
                return Gravity.CENTER;
            // CreatedAt
            case 9:
                return Gravity.CENTER;
            // UpdatedAt
            case 10:
                return Gravity.CENTER;
            // Address
            case 11:
                return Gravity.LEFT;
            // Zip Code
            case 12:
                return Gravity.RIGHT;
            // Phone
            case 13:
                return Gravity.RIGHT;
            // Fax
            case 14:
                return Gravity.RIGHT;
            default:
                return Gravity.CENTER;
        }

    }

    private List<ColumnHeader> createColumnHeaderList() {
        List<ColumnHeader> list = new ArrayList<>();

        // Create Column Headers
        list.add(new ColumnHeader("Id"));
        list.add(new ColumnHeader("Name"));
        list.add(new ColumnHeader("Nickname"));
        list.add(new ColumnHeader("Email"));
        list.add(new ColumnHeader("Birthday"));


        return list;
    }

    private List<List<Cell>> createCellList() {
        List<List<Cell>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service

        for (int i = 0; i < 5; i++) {

            List<Cell> list = new ArrayList<>();

            // The order should be same with column header list;
            list.add(new Cell("1- + i, user.id"));          // "Id"
            list.add(new Cell("2- + i, user.name"));        // "Name"
            list.add(new Cell("3- + i, user.nickname"));    // "Nickname"
            list.add(new Cell("4- + i, user.email"));       // "Email"
            list.add(new Cell("5- + i, user.birthdate"));   // "BirthDay"


            // Add
            lists.add(list);
        }

        return lists;
    }

    private List<RowHeader> createRowHeaderList(int size) {
        List<RowHeader> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // In this example, Row headers just shows the index of the TableView List.
            list.add(new RowHeader(String.valueOf(i + 1)));
        }
        return list;
    }


    public List<ColumnHeader> getColumHeaderModeList() {
        return mColumnHeaderList;
    }

    public List<RowHeader> getRowHeaderList() {
        return mRowHeaderList;
    }

    public List<List<Cell>> getCellList() {
        return mCellList;
    }


    public void generateListForTableView() {
        mColumnHeaderList = createColumnHeaderList();
        mCellList = createCellList();
        mRowHeaderList = createRowHeaderList(5);
    }

}