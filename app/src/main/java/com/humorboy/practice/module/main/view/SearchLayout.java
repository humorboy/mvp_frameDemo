package com.humorboy.practice.module.main.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.humorboy.practice.R;
import com.humorboy.practice.utils.KeybordUtil;
import com.humorboy.practice.utils.ToastUtil;

/**
 * Created by jhren on 2017/9/9.
 * custom define SearchView
 */
public class SearchLayout extends FrameLayout {
    private TextView search_close_btn;
    private EditText search_text;
    // 回调接口
    private ICallBack mCallBack;// 搜索按键回调接口

    public SearchLayout(Context context) {
        this(context,null);
    }

    public SearchLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_view_layout, this);
        search_close_btn = (TextView) view.findViewById(R.id.search_close_btn);
        search_close_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                search_text.setText("");
                KeybordUtil.closeKeybord(search_text,getContext());
                if(search_close_btn.getVisibility()==VISIBLE){
                    search_close_btn.setVisibility(GONE);
                }
            }
        });
        search_text = (EditText) view.findViewById(R.id.search_text);
        search_text.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        search_text.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEND ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
                    ToastUtil.makeText(getContext(),"搜索："+search_text.getText());
                    KeybordUtil.closeKeybord(search_text,getContext());
                    return true;
                }
                return false;
            }
        });

        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(search_text.getText())&&search_close_btn.getVisibility()==GONE){
                    search_close_btn.setVisibility(VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(mCallBack == null)){
                    mCallBack.SearchAciton(search_text.getText().toString());
                }
                if(TextUtils.isEmpty(search_text.getText())){
                    search_close_btn.setVisibility(GONE);
                }
            }
        });
    }

    public EditText getSearch_EditText(){
        return search_text;
    }

    /**
     * 点击键盘中搜索键后的操作，用于接口回调
     */
    public void setOnClickSearch(ICallBack mCallBack){
        this.mCallBack = mCallBack;
    }

    public interface ICallBack {
        void SearchAciton(String string);
    }
}
