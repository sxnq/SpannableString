package xunqaing.bwie.com.spannablestring.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 有的地方会用到Toast，这里附上单例Toast(不用等上一个消失，下一个才会出现，只用一个)
 *
 */

public class Utils {

    private static Toast toast;
    /**
     * 单例吐司
     */
    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }

        //toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.TOP,0,70);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(msg);
        toast.show();
    }
}