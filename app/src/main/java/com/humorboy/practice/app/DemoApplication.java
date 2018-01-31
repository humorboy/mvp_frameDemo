package com.humorboy.practice.app;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.humorboy.practice.BuildConfig;
import com.humorboy.practice.R;
import com.humorboy.practice.bridge.BridgeLifeCycleSetKeeper;
import com.humorboy.practice.constant.Constant;
import com.humorboy.practice.db.DaoMaster;
import com.humorboy.practice.db.DaoSession;
import com.humorboy.practice.utils.ToastUtil;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.oubowu.slideback.ActivityHelper;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zhy.changeskin.SkinManager;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 
 * <应用初始化> <功能详细描述>
 * 
 * @author humorboy
 * @version [版本号, 2017-3-24]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DemoApplication extends MultiDexApplication
{
    /**
     * app实例
     */
    public static DemoApplication demoApplication = null;
    
    /**
     * 本地activity栈
     */
    public static List<Activity> activitys = new ArrayList<Activity>();
    
    /**
     * 当前avtivity名称
     */
    public static String currentActivityName = "";
    private RefWatcher mRefWatcher;
    private ActivityHelper mActivityHelper;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        initSpeech();
        super.onCreate();
        init();
    }

    //语音识别初始化
    private void initSpeech() {
        StringBuffer param = new StringBuffer();
        param.append("appid="+getString(R.string.app_id));
        param.append(",");
        // 设置使用v5+
        param.append(SpeechConstant.ENGINE_MODE+"="+SpeechConstant.MODE_MSC);
        SpeechUtility.createUtility(DemoApplication.this, param.toString());
    }

    /**
     * 初始化数据
     */
    private void init() {
        demoApplication = this;
        SkinManager.getInstance().init(this);
        BridgeLifeCycleSetKeeper.getInstance().initOnApplicationCreate(this);
        // 如果检测到某个 activity 有内存泄露，LeakCanary 就是自动地显示一个通知
        mRefWatcher = LeakCanary.install(this);
        setupDatabase();
        KLog.init(BuildConfig.DEBUG);
        mActivityHelper = new ActivityHelper();
        registerActivityLifecycleCallbacks(mActivityHelper);
    }

    public static ActivityHelper getActivityHelper() {
        return ((DemoApplication)demoApplication).mActivityHelper;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        onDestory();
    }

    /**
     * 退出应用，清理内存
     */
    private void onDestory() {
        BridgeLifeCycleSetKeeper.getInstance().clearOnApplicationQuit();
        ToastUtil.destory();
    }


    /**
     * 
     * <添加> <功能详细描述>
     * 
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void addActivity(Activity activity)
    {
        activitys.add(activity);
    }
    
    /**
     * 
     * <删除>
     * <功能详细描述>
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void deleteActivity(Activity activity)
    {
        if (activity != null)
        {
            activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    private void setupDatabase() {
        // // 官方推荐将获取 DaoMaster 对象的方法放到 Application 层，这样将避免多次创建生成 Session 对象
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constant.DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        // 在 QueryBuilder 类中内置两个 Flag 用于方便输出执行的 SQL 语句与传递参数的值
        QueryBuilder.LOG_SQL = BuildConfig.DEBUG;
        QueryBuilder.LOG_VALUES = BuildConfig.DEBUG;
    }

    /**
     * 获取dao会话
     *
     * @return
     */
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    /**
     * 获取内存监控
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        DemoApplication application = (DemoApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

    // 获取ApplicationContext
    public static Context getContext() {
        return demoApplication;
    }
}
