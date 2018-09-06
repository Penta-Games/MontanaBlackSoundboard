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
 * Created by Oclemmy on 5/10/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class Tab2 extends Fragment{

    GridView myGridView;

    public String[] items2 ={
            "Alles klar", "Brathänchen", "Das weiß ich doch!", "EY ne", "Genauhuhu", "Hää", "Hallo, hör mal zu!", "Ja", "Jaeah", "Jahahaaa",
            "Jajaja", "Jawolski", "Kichern", "LABER", "Lecker SChmecker", "Neiiiin", "Nein", "Nenene", "Nice Baby!", "Oajaa",
            "Oh Fuck", "Oh Shit", "OMG", "Omg Omg", "OOOOOAH", "Spritzig oder?", "Uhhhh"
    };

    public static int[] soundfiles= {
            R.raw.allesklar,R.raw.brathaenchen,R.raw.dasweissichdoch,R.raw.eynehehe,R.raw.genauhuhu,R.raw.hae,R.raw.hallohoermalzu,R.raw.ja,R.raw.jaeah,R.raw.jahahahaaa,
            R.raw.jajaja,R.raw.jawoski,R.raw.kichern,R.raw.laber,R.raw.leckerschmecka,R.raw.neiiiiin,R.raw.nein,R.raw.nenene,R.raw.nicebaby,R.raw.oajaa,
            R.raw.ohfuck,R.raw.ohshit,R.raw.omg,R.raw.omgomg,R.raw.ooooooah,R.raw.spritzigoder,R.raw.uuuuh


    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.tab2,container,false);





        myGridView = (GridView)rootView.findViewById(R.id.tab2GridView);
        myGridView.setAdapter(new CustomGridAdapter(getActivity(), items2));
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
                        ((MainActivity) context).tab2(position);
                    }
                }
            });

            return convertView;
        }


    }
}
