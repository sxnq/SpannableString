package xunqaing.bwie.com.spannablestring;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import xunqaing.bwie.com.spannablestring.utils.Utils;

//http://www.cnblogs.com/upwgh/p/5897495.html
//http://blog.csdn.net/u012702547/article/details/49895157
//http://blog.csdn.net/u014620028/article/details/50977602

  /*
    * Spannable.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括，即在指定范围的前面和后面插入新字符都不会应用新样式
    * Spannable.SPAN_EXCLUSIVE_INCLUSIVE ：前面不包括，后面包括。即仅在范围字符的后面插入新字符时会应用新样式
    * Spannable.SPAN_INCLUSIVE_EXCLUSIVE ：前面包括，后面不包括。
    * Spannable.SPAN_INCLUSIVE_INCLUSIVE ：前后都包括。
    */

public class MainActivity extends Activity {

    private TextView textView;
    private TextView textView2;
    private Button bt;
    private EditText et;

    private Button bt2;
    private TextView tv3;

    public static int[] resIDs = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    public static String[] smileArray = {"[干嘛]", "[鼓掌]", "[握手]", "[发疯]"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.tv);
        textView2 = (TextView) findViewById(R.id.tv2);
        bt = (Button) findViewById(R.id.bt);
        et = (EditText) findViewById(R.id.et);

        bt2 = (Button) findViewById(R.id.bt2);
        tv3 = (TextView) findViewById(R.id.tv3);

        String str = "1234567890";
        SpannableString spannableString = new SpannableString(str);

        //        背景色 BackgroundColorSpan
        spannableString.setSpan(new BackgroundColorSpan(Color.YELLOW), 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        //        ForegroundColorSpan 前景色
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 5, 10, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        //下划线
        spannableString.setSpan(new UnderlineSpan(), 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);


        //显示图片
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, 20, 20);
        spannableString.setSpan(new ImageSpan(drawable), 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        // 加粗 倾斜
        spannableString.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);


        //下标
        spannableString.setSpan(new SubscriptSpan(), 1, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);


        //上标
        spannableString.setSpan(new SuperscriptSpan(), 3, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        //打开网页   不需要权限
        // 加上这句话，才有点击效果 (网页点击和点击事件)
        textView.setMovementMethod(new LinkMovementMethod());
        spannableString.setSpan(new URLSpan("http://www.baidu.com"), 5, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        //点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                Toast toast = Toast.makeText(getApplicationContext(),
                        "自定义位置Toast", Toast.LENGTH_LONG);
                //                toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.TOP,0,70);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                Log.e("xunxun widget", widget + "=====");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);

                Log.e("xunxun ds ", ds + "");

                //设置指定区域的文字变蓝色
                ds.setColor(Color.GREEN);
                //设置指定区域的文字有下划线
                ds.setUnderlineText(true);
            }
        };

        spannableString.setSpan(clickableSpan, 7, 10, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        //谁都要用这句话
        textView.setText(spannableString);


        //ClickableSpan 的第二种用法：给多个局部文字加颜色============================================================================
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> list = new ArrayList<String>();

                list.add("长江");
                list.add("青山");
                list.add("夕阳红");
                list.add("秋月春风");

                String content = "滚滚长江东逝水，浪花淘尽英雄。是非成败转头空，青山依旧在，几度夕阳红。白发渔樵（qiáo）江渚（zhǔ）上，惯看秋月春风。一壶浊酒喜相逢，古今多少事，都付笑谈中。";
                SpannableString spannableString = new SpannableString(content);

                for (int i = 0; i < list.size(); i++) {

                    final String temp = list.get(i);
                    String temp_content = content;

                    int start = 0;
                    while (temp_content.contains(temp)) {
                        spannableString.setSpan(new ClickableSpan() {

                                                    @Override
                                                    public void updateDrawState(TextPaint ds) {
                                                        super.updateDrawState(ds);
                                                        // 设置指定区域的文字变红色
                                                        ds.setColor(Color.BLUE);
                                                        //设置指定区域的文字有下划线
                                                        ds.setUnderlineText(false);
                                                    }

                                                    @Override
                                                    public void onClick(View widget) {
                                                        Utils.showToast(MainActivity.this, "点击了" + temp);
                                                    }

                                                }, start + temp_content.indexOf(temp), start + temp_content.indexOf(temp) + temp.length(),
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        textView2.setText(spannableString);
                        textView2.setMovementMethod(LinkMovementMethod.getInstance());
                        start = temp_content.indexOf(temp) + temp.length();
                        temp_content = temp_content.substring(start);
                    }
                }
            }

        });


        //Editext============================================================================

          /*
    * Spannable.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括，即在指定范围的前面和后面插入新字符都不会应用新样式
    * Spannable.SPAN_EXCLUSIVE_INCLUSIVE ：前面不包括，后面包括。即仅在范围字符的后面插入新字符时会应用新样式
    * Spannable.SPAN_INCLUSIVE_EXCLUSIVE ：前面包括，后面不包括。
    * Spannable.SPAN_INCLUSIVE_INCLUSIVE ：前后都包括。
    */
        SpannableString spannableStringet = new SpannableString("1234567890");
        spannableStringet.setSpan(new BackgroundColorSpan(Color.YELLOW), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        et.setText(spannableStringet);


        //=========================文字和表情===================================================

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = "寻寻，[干嘛]你咋这么帅气[握手][握手]";
                tv3.setText(toImageSpan(MainActivity.this, content, resIDs, smileArray));
            }
        });

    }

    public static SpannableString toImageSpan(Context context, String content, int[] emoImg, String[] emoText) {
        SpannableString ss = new SpannableString(content);
        for (int i = 0; i < emoText.length; i++) {
            int startPos = 0;
            String rep = emoText[i];
            int fromPos = 0;
            while ((startPos = content.indexOf(rep, fromPos)) != -1) {
                fromPos = startPos + rep.length();
                Bitmap bit = BitmapFactory.decodeResource(context.getResources(), emoImg[i]);

                BitmapDrawable bitmapDrawable = new BitmapDrawable(bit);
                bitmapDrawable.setBounds(0, 0, 50, 50);
                ImageSpan span = new ImageSpan(bitmapDrawable);
                ss.setSpan(span, startPos, fromPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return ss;
    }


}
