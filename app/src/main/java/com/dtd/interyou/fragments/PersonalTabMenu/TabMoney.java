package com.dtd.interyou.fragments.PersonalTabMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dtd.interyou.activities.DialogTest;
import com.dtd.interyou.R;
import com.dtd.interyou.activities.MoneyOutputActivity;
import com.dtd.interyou.adapters.DividerItemDecoration;
import com.dtd.interyou.adapters.OperationsListAdapter;
import com.dtd.interyou.fragments.BaseFragment;
import com.dtd.interyou.model.Operation;

import java.util.ArrayList;

/**
 * Created by Egor on 01.06.2015.
 */
public class TabMoney extends BaseFragment implements View.OnClickListener  {

    RecyclerView mList;
    Button btnOut;

    public static TabMoney newInstance() {
        return new TabMoney();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_money, container, false);
        mContext = view.getContext();

        btnOut = (Button) view.findViewById(R.id.btn_out);
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MoneyOutputActivity.class);
                startActivity(intent);
            }
        });

        mList = (RecyclerView) view.findViewById(R.id.operations_list);
        mList.setHasFixedSize(true);
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setLayoutManager(new LinearLayoutManager(mContext));
        mList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        ArrayList<Operation> operations = new ArrayList<>();


        operations.add(new Operation("11.02.15", Operation.Type.INPUT, "100"));
        operations.add(new Operation("12.02.15", Operation.Type.EXCHANGE, "180"));
        operations.add(new Operation("12.02.15", Operation.Type.OUTPUT, "75"));
        operations.add(new Operation("13.02.15", Operation.Type.OUTPUT, "50"));
        operations.add(new Operation("17.02.15", Operation.Type.INPUT, "150"));
        operations.add(new Operation("18.02.15", Operation.Type.EXCHANGE, "300"));

        mList.setAdapter(new OperationsListAdapter(mContext, operations, TabMoney.this));


        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.operation_cell:
                startActivity(new Intent(getActivity(), DialogTest.class));
                break;
        }
    }
}
