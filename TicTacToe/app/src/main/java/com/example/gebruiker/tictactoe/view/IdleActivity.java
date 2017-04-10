package com.example.gebruiker.tictactoe.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.gebruiker.tictactoe.R;

import static android.content.Intent.ACTION_VIEW;

/**
 * Created by Gebruiker on 2017-01-18.
 */

public class IdleActivity extends AppCompatActivity {
    // Attract mode bij te lange afwezigheid

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle);

        String videourl = "http://r5---sn-5hne6nlk.googlevideo.com/videoplayback?quality=medium&ratebypass=yes&itag=18&ipbits=0&gir=yes&key=cms1&upn=mx6kR4fG6cQ&sparams=clen,dur,ei,expire,gir,id,ip,ipbits,ipbypass,itag,lmt,mime,mm,mn,ms,mv,nh,pl,ratebypass,source,upn&expire=1484798878&source=youtube&ei=Pud_WNzmCqHn8gTNpI04&ip=83.82.2.146&lmt=1458195310714949&dur=134.536&pl=14&id=o-AMUFfFYeYWQZmxSHn70GSMnPea5EHdoO67JLPOFC9-P-&signature=69291D7B277E6F2BCBD774FEB068A19B4C08CDD6.261BD959993A39D78D0E96A7709D0CE52A4D792A&clen=3512088&mime=video/mp4&type=video%2Fmp4%3B+codecs%3D%22avc1.42001E%2C+mp4a.40.2%22&title=(HDVidz.in)_How-to-Win-Tic-Tac-Toe-Game&redirect_counter=1&req_id=e072370bdfe1a3ee&cms_redirect=yes&ipbypass=yes&mm=31&mn=sn-5hne6nlk&ms=au&mt=1484777156&mv=m&nh=IgpwZjAxLmFtczE1Kg4yMTMuNTEuMTU2LjIxMw";
        Uri uri = Uri.parse(videourl);
        Intent intent = new Intent(ACTION_VIEW, uri);
        intent.setDataAndType(uri, "video/mp4");
        startActivity(intent);

        LinearLayout rlayout = (LinearLayout) findViewById(R.id.mainlayout);
        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
