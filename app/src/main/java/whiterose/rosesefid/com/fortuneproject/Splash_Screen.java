package whiterose.rosesefid.com.fortuneproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import util.IabHelper;
import util.IabResult;
import util.Inventory;


//**************************SPLASH SCREEN ACTIVITY (SCREEN THAT APPEARS AT BEGINING OF RUN)

public class Splash_Screen extends AppCompatActivity {

    private IabHelper.QueryInventoryFinishedListener mGotInventoryListener;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        initializer();

        Intent intent = new Intent(Splash_Screen.this, Activity_Main.class);
        Splash_Screen.this.startActivity(intent);
        finish();
    }


    public void initializer() {
        Log.i("mohammad", "initializing");

        // CAFE BAZAAR IN APP PURCHASE
        mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {

            @Override
            public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                if (!result.isFailure()) {
                    // check all payment codes here
                    // here we have 4 types of payment (add any other here)
                    boolean state_cmp = inventory.hasPurchase("cmp.1");
                    boolean state_all = inventory.hasPurchase("cmp.all");
                    boolean state_tdy = inventory.hasPurchase("tdy.d");
                    boolean state_tmr = inventory.hasPurchase("tmr.d");

                    // if cafe bazaar returns true for each item then we have that item
                    if (state_cmp) {
                        Log.i("item state", "Bazaar say CMP isPremiumUser is : " + state_cmp);
                    }
                    if (state_all) {
                        Log.i("item state", "Bazaar say ALL isPremiumUser is : " + state_all);
                        //dont add item to array list if we had it
                        if (G.array.contains("all")) {

                        } else {
                            G.array.add("all");
                        }
                    }
                    if (state_tdy) {
                        Log.i("item state", "Bazaar say TDY isPremiumUser is : " + state_tdy);
                        if (G.array.contains("today")) {
                        } else {
                            G.array.add("today");
                        }
                    }
                    if (state_tmr) {
                        Log.i("item state", "Bazaar say TMR isPremiumUser is : " + state_tmr);
                        if (G.array.contains("tomorrow")) {

                        } else {
                            G.array.add("tomorrow");
                        }
                    }

                    for (int i = 0; i < G.array.size(); i++) {
                        Log.i("check items", G.array.get(i));
                    }
                }

                //start main activity after checking cafe bazaar
                Intent intent = new Intent(Splash_Screen.this, Activity_Main.class);
                Splash_Screen.this.startActivity(intent);
                finish();
            }
        };
        G.prepareBazaarInAppBilling(true, mGotInventoryListener);
    }
}
