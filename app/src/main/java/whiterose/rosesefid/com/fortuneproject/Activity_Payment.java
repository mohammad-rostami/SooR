package whiterose.rosesefid.com.fortuneproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import util.IabHelper;
import util.IabHelper.OnIabPurchaseFinishedListener;
import util.IabResult;
import util.Inventory;
import util.Purchase;

import static whiterose.rosesefid.com.fortuneproject.G.mHelper;


public class Activity_Payment extends Activity {

    public static Activity activity;
    IabHelper h;

    @Override
    protected void onResume() {
        G.currentActivity = this;
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity = this;
        setContentView(R.layout.payment);

        TextView btnAll = (TextView) findViewById(R.id.btn_all);
        h = new IabHelper(getBaseContext(), G.base64EncodedPublicKey);
        h.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {

                if (result.isSuccess()) {

                    ArrayList<String> moreSkus = new ArrayList<String>();
                    moreSkus.add("cmp.all");
                    h.queryInventoryAsync(true, moreSkus, new IabHelper.QueryInventoryFinishedListener() {
                        @Override
                        public void onQueryInventoryFinished(IabResult result, Inventory inv) {
                            if (result.isSuccess()) {

                                Log.i("Setup Helper", "SUCCESS");
                            } else {
                                Log.e("Setup Helper", "ERROR");

                            }

                        }


                    });
                }
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: do purchase for buying just this item
                // TODO: and if purchase completed add it to G.array in order to make user be able to use it
                if (h != null) {
                    h.flagEndAsync();
                }
                Log.i("pay","Start");
                h.launchPurchaseFlow(Activity_Payment.this, "cmp.all", 1001, new IabHelper.OnIabPurchaseFinishedListener() {
                    public void onIabPurchaseFinished(IabResult result, Purchase info) {
                        if (result.isSuccess()) {
                            G.array.add("all");
                            Toast.makeText(getBaseContext(),
                                    "پرداخت موفقیت آمیز بود ، در صورت بروز مشکل برنامه را مجددا اجرا کنید",
                                    Toast.LENGTH_LONG)
                                    .show();
                        } else {

                            Toast.makeText(getBaseContext(),
                                    "پرداخت موفقیت آمیز نبود ، دوباره امتحان کنید",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });

            }
        });


    }


    public boolean isPackageInstalled(String PackageName) {
        PackageManager manager = getPackageManager();
        boolean isAppInstalled = false;
        try {
            manager.getPackageInfo(PackageName, PackageManager.GET_ACTIVITIES);
            isAppInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return isAppInstalled;
    }

}