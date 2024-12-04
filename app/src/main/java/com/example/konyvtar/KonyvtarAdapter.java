package com.example.konyvtar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

public class KonyvtarAdapter extends BaseAdapter {

    private List<Konyvtar> konyvtarList;
    private Context context;

    public KonyvtarAdapter(List<Konyvtar> konyvtarList, Context context) {
        this.konyvtarList = konyvtarList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return konyvtarList.size();
    }

    @Override
    public Object getItem(int position) {
        return konyvtarList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.konyv_list_item, parent, false);
        TextView cimTextView = convertView.findViewById(R.id.cimTextView);
        TextView szerzoTextView = convertView.findViewById(R.id.szerzoTextView);
        TextView oldalszamTextView = convertView.findViewById(R.id.oldalszamTextView);
        TextView evTextView = convertView.findViewById(R.id.randomEvTextView);

        Konyvtar currentKonyv = konyvtarList.get(position);
        cimTextView.setText(currentKonyv.getCim());
        szerzoTextView.setText(currentKonyv.getSzerzo());
        oldalszamTextView.setText(String.valueOf(currentKonyv.getOldalszam()));
        Random rnd = new Random();
        evTextView.setText(String.valueOf(rnd.nextInt(2024-1800)+1800));

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Szeretné törölni?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);

                builder.setPositiveButton("Igen", (DialogInterface.OnClickListener) (dialog, which) -> {
                    konyvtarList.remove(v);
                });

                builder.setNegativeButton("Nem", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return true;
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Konyvtar konyvtar = konyvtarList.get(position);
                Intent intent = new Intent(context, KonyvDetails.class);
                intent.putExtra("cim", currentKonyv.getCim());
                intent.putExtra("szerzo", currentKonyv.getSzerzo());
                intent.putExtra("oldalszam", currentKonyv.getOldalszam());
                context.startActivity(intent);
            }
        });


        return convertView;
    }
}
