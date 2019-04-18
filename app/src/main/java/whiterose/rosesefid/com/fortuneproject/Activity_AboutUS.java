package whiterose.rosesefid.com.fortuneproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Mohammad on 5/14/2017.
 */

public class Activity_AboutUS extends AppCompatActivity {
    private ImageView btnBack;
    private ImageView btn_shares;
    private TextView btnGoToBazaar;
    private TextView btnGoToInstagram;
    private TextView btnGoToEmail;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        btnGoToBazaar = (TextView) findViewById(R.id.about_us_tv_go_to_bazaar);
        btnGoToInstagram = (TextView) findViewById(R.id.about_us_tv_instagram);
        btnGoToEmail = (TextView) findViewById(R.id.about_us_tv_email);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btn_shares = (ImageView) findViewById(R.id.btn_share);
        btn_shares.setVisibility(View.GONE);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity_AboutUS.super.onBackPressed();
            }
        });
        btnGoToBazaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafebazaar.ir/app/ir.ruberaah.app/?l=fa"));
                startActivity(browserIntent);
            }
        });
        btnGoToInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/soooor.app/"));
                startActivity(browserIntent);
            }
        });
        btnGoToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","info@rouberaah.ir", null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });
    }
}
