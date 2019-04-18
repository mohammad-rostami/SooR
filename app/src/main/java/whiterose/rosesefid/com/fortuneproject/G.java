package whiterose.rosesefid.com.fortuneproject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import util.IabHelper;
import util.IabResult;

public class G extends Application {

    public static Context context;
    public static String ID_DAILY;
    public static String ID;
    public static String ID_ALTERNATIVE;
    public static Integer MONTH_ID;
    public static Boolean IS_MALE = true;
    public static Boolean IS_YOURS = true;
    public static Boolean IS_PAYMENT = false;
    public static Boolean ACTIVE_REMOVER = false;
    public static Integer TAB_NO;
    public static Integer SIGN_ID = null;
    public static Integer COMPARE_ID = null;

    public static ArrayList<String> array = new ArrayList();
    public static String new_string;
    public static String pack_kind;

    public static LayoutInflater inflater;
    public static Activity currentActivity;
    public static final Handler HANDLER = new Handler();
    public static String id;


    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    public static final String TAG = "";
    public static final String SKU_PREMIUM = "cmp.1";//شناسه محصول
    public static final int RC_REQUEST = 0;
    public static boolean mIsPremium = false;
    public static IabHelper mHelper;
    public static String base64EncodedPublicKey = "MIHNMA0GCSqGSIb3DQEBAQUAA4G7ADCBtwKBrwCz4Z8KpN58VSJNl51t/AwetiO3Y2bse+TSVAtTaXouNa93f/QQzKn0P20jJ31rKN6tKf/LN1Td1Q4bjFQCsJVUtCYamvn8jZxGx8VB9U8vN2t6ZUj1JVa1ksSCRspgh8p1j+z1NLQUR5hswCFiP2TQn6wUUZWpsIKvWKDNO+TVuzY1wyFpoOoLhmmLMIFk5Z1dE/YxtiBsYrfF0F15Oh0PDQcWH0F16m7UD7sYtI0CAwEAAQ==";
    public static boolean setPremiumState = false;
    public static boolean premiumState = false;
    public static SharedPreferences dataList_pref;
    public static SharedPreferences initializer_pref;
    public static SharedPreferences alarm_pref;

    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("sans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        initializer_pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        dataList_pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        alarm_pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    public static boolean prepareBazaarInAppBilling(final boolean requireUpdatePrefrences,
                                                    final IabHelper.QueryInventoryFinishedListener mGotInventoryListener) {
        try {
            mHelper = new IabHelper(G.context, base64EncodedPublicKey);
            Log.d(TAG, "Starting setup.");
            IabHelper.OnIabSetupFinishedListener listener =
                    new IabHelper.OnIabSetupFinishedListener() {

                        @Override
                        public void onIabSetupFinished(IabResult result) {
                            Log.d(TAG, "Setup finished.");
                            if (!result.isSuccess()) {
                                Log.d(TAG, "Problem setting up In-app Billing: " + result);
                            }
                            if (requireUpdatePrefrences && mGotInventoryListener != null) {
                                mHelper.queryInventoryAsync(mGotInventoryListener);
                            }
                        }
                    };
            mHelper.startSetup(listener);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
