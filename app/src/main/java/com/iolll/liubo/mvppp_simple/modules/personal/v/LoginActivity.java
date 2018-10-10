package com.iolll.liubo.mvppp_simple.modules.personal.v;

import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iolll.liubo.mvppp_simple.R;
import com.iolll.liubo.mvppp_simple.base.iolll.MvpActivity;
import com.iolll.liubo.mvppp_simple.base.iolll.Presenter;
import com.iolll.liubo.mvppp_simple.model.net.User;
import com.iolll.liubo.mvppp_simple.modules.main.v.MainActivity;
import com.iolll.liubo.mvppp_simple.modules.personal.v_contrast.LoginContract;
import com.iolll.liubo.mvppp_simple.modules.personal.v_contrast.p_impl.LoginPresenterImpl;
import com.iolll.liubo.mvppp_simple.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.TextUtils.isEmpty;
import static me.jessyan.autosize.utils.AutoSizeUtils.mm2px;

public class LoginActivity extends MvpActivity implements LoginContract.V {


    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.userNameEdit)
    AppCompatEditText userNameEdit;
    @BindView(R.id.passWordEdit)
    AppCompatEditText passWordEdit;
    @BindView(R.id.submitBtn)
    MaterialButton submitBtn;



    private LoginPresenterImpl presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public ArrayList<? extends Presenter> createPresenter() {
        return new ArrayList<Presenter<LoginContract.V>>() {{
            presenter = new LoginPresenterImpl();
            add(presenter);
        }};
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initListener() {

        userNameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                userNameEdit.setWidth(b ? mm2px(Utils.getContext(), 500) : isEmpty(userNameEdit.getText()) ? 0 : view.getWidth());

            }
        });
        passWordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                passWordEdit.setWidth(b ? mm2px(Utils.getContext(), 500) : isEmpty(passWordEdit.getText()) ? 0 : view.getWidth());
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.doLoin(new User(userNameEdit.getText().toString(), passWordEdit.getText().toString()));
            }
        });

    }

    @Override
    protected void getData() {

    }


    @Override
    public void loginComplete() {
        Utils.startAct(MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
