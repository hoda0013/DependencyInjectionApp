package io.senseai.dependencyinjectionapp;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

/**
 * Created by bigern on 12/26/15.
 */
public class PublicApi implements PublicAccess{

    public static final String LOG_TAG = PublicApi.class.getSimpleName();
    private static PublicApi mInstance;
    private Context mContext;
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    private PublicApi (Context context) {
        mContext = context;
        mHandlerThread = new HandlerThread(LOG_TAG);
        mHandler = new Handler(mHandlerThread.getLooper());
    }

    public static synchronized PublicApi getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PublicApi(context);
        }
        return mInstance;
    }

    @Override
    public void setMode(Mode mode) {

    }

    @Override
    public Mode getMode() {
        return null;
    }

}
