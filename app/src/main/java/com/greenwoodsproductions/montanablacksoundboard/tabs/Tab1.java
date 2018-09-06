package com.greenwoodsproductions.montanablacksoundboard.tabs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.greenwoodsproductions.montanablacksoundboard.MainActivity;
import com.greenwoodsproductions.montanablacksoundboard.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Ratan on 7/29/2015.
 */
public class Tab1 extends Fragment {
    GridView myGridView;

    int max;
    public String[] items1 ={
            "MOOOOIN...", "Bug oder Was ?", "Schnauze halten", "Dumme Sau", "Eieiei...", "Bullshit Scheiße", "Huansohn", "Ich glaubs nicht !", "Inzucht Camper", "Klarkommen",
            "Lache 1", "Lache 2", "Lache 3", "Lache 4", "Lache 5", "Lache 6", "Laufen nicht Sitzen", "Loch in der Wand", "Action", "Was soll das ?",
            "NOHOMO-aber...", "Wiederlicher", "Wehgatan", "Wie geil ist denn das 1", "Wie geil ist denn das 2"
    };

    public static int[] soundfiles= {
            R.raw.moinmeinepunktpunnkktpunkt,R.raw.bugioderwas,R.raw.dukannstauchmaldieschcnauzehalten,R.raw.dummesau,R.raw.eieieisiehtdiegeilaus,R.raw.groesstebullshitscheisse,R.raw.huansohn,R.raw.ichglaubsnich,R.raw.inzuchtcamper,R.raw.kommnichauflebenklar,
            R.raw.lache,R.raw.lache2,R.raw.lache3,R.raw.lache4,R.raw.lache5,R.raw.lache6,R.raw.laufenichsitzen,R.raw.lochinderwand,R.raw.alteristhieraction,R.raw.neinwassolldenndass,
            R.raw.nohomoaber,R.raw.soeinwidderlicher,R.raw.sowasvonwehgetan,R.raw.wiegeilisndas,R.raw.wiegeilistdas


    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.tab1,container,false);





        myGridView = (GridView)rootView.findViewById(R.id.tab1GridView);
        myGridView.setAdapter(new CustomGridAdapter(getActivity(), items1));
        myGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                File dest = Environment.getExternalStorageDirectory();

                InputStream in;

                Intent share = new Intent(Intent.ACTION_SEND);

                int name;

                name = soundfiles[pos];

                in = getResources().openRawResource(name);

                try
                {
                    OutputStream out = new FileOutputStream(new File(dest, "audio.mp3"));
                    byte[] buf = new byte[1024];
                    int len;
                    while ( (len = in.read(buf, 0, buf.length)) != -1)
                    {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().toString() + "/audio.mp3"));
                share.setType("audio/mp3");
                startActivity(Intent.createChooser(share, "Sound teilen über..."));


                return true;

            }
        });
        return rootView;
    }




    public class CustomGridAdapter extends BaseAdapter {

        private Context context;
        private String[] items;
        LayoutInflater inflater;

        public CustomGridAdapter(Context c, String[] items) {
            this.context = c;
            this.items = items;
            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }




        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.single_item, null);
            }
            Button button = (Button) convertView.findViewById(R.id.button);
            button.setText(items[position]);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (context instanceof MainActivity) {
                        ((MainActivity) context).tab1(position);
                    }
                }
            });

            return convertView;
        }


    }
}

