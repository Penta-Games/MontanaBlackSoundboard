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
public class Tab3 extends Fragment {
    GridView myGridView;
    public String[] items3 = {
            "Alter Digga", "Das gibst doch nicht", "...Die letzte Scheiße", "Du kleiner DRECKIGER", "Ich hasse das", "Ich packs nichtmehr", "Is klar Alter!", "Jajajaaa", "Janhela...", "Mein Gott",
            "Nich euer Ernst", "...So lächerlich", "So spielt es sich gerne", "Spieß umdrehen", "Bullshit", "WAS ist das ???", "WAS soll das ???", "Wie findest du das ?"
    };

    public static int[] soundfiles= {
            R.raw.aalterdiggah,R.raw.dasgibtsdochnicht,R.raw.dieltztescheisse,R.raw.dukleinerdreckiger,R.raw.ichassedas,R.raw.ichpacksbnichmehr,R.raw.isklaralter,R.raw.jajajaaaaa,R.raw.janhelal,R.raw.meingott,
            R.raw.nicheuerernst,R.raw.solaecherlich,R.raw.sospieltsichsgerne,R.raw.spiessumdrehen,R.raw.wasfuernbullshit,R.raw.wasistdas,R.raw.wassollndas,R.raw.wiefindestdudashe


    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.tab3,container,false);



        myGridView = (GridView)rootView.findViewById(R.id.tab3GridView);
        myGridView.setAdapter(new CustomGridAdapter(getActivity(), items3));
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
                        ((MainActivity) context).tab3(position);
                    }
                }
            });

            return convertView;
        }


    }
}

