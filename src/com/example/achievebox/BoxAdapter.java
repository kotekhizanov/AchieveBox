package com.example.achievebox;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.achievebox.R;

public class BoxAdapter extends BaseAdapter {
	  Context ctx;
	  LayoutInflater lInflater;
	  ArrayList<Product> objects;

	  BoxAdapter(Context context, ArrayList<Product> products) {
	    ctx = context;
	    objects = products;
	    lInflater = (LayoutInflater) ctx
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }

	  // ���-�� ���������
	  @Override
	  public int getCount() {
	    return objects.size();
	  }

	  // ������� �� �������
	  @Override
	  public Object getItem(int position) {
	    return objects.get(position);
	  }

	  // id �� �������
	  @Override
	  public long getItemId(int position) {
	    return position;
	  }

	  // ����� ������
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    // ���������� ���������, �� �� ������������ view
	    View view = convertView;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.main_wall_block, parent, false);
	    }

	    Product p = getProduct(position);

	    // ��������� View � ������ ������ ������� �� �������: ������������, ����
	    // � ��������
	    ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
	    ((TextView) view.findViewById(R.id.tvPrice)).setText(p.price + "");
	    ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);

	    CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
	    // ����������� �������� ����������
	    cbBuy.setOnCheckedChangeListener(myCheckChangList);
	    // ����� �������
	    cbBuy.setTag(position);
	    // ��������� ������� �� �������: � ������� ��� ���
	    cbBuy.setChecked(p.box);
	    return view;
	  }

	  // ����� �� �������
	  Product getProduct(int position) {
	    return ((Product) getItem(position));
	  }

	  // ���������� �������
	  ArrayList<Product> getBox() {
	    ArrayList<Product> box = new ArrayList<Product>();
	    for (Product p : objects) {
	      // ���� � �������
	      if (p.box)
	        box.add(p);
	    }
	    return box;
	  }

	  // ���������� ��� ���������
	  OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
	    public void onCheckedChanged(CompoundButton buttonView,
	        boolean isChecked) {
	      // ������ ������ ������ (� ������� ��� ���)
	      getProduct((Integer) buttonView.getTag()).box = isChecked;
	    }
	  };
	}