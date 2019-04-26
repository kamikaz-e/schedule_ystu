package com.example.misha.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.fragments.BaseFragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.example.misha.myapplication.fragments.BaseFragment.handleBackPressed;

public abstract class BaseActivity extends AppCompatActivity {

    protected CharSequence currentTitle;

    protected Toolbar toolbar;

    protected ActionBar actionBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initToolbar();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(createDrawerClick());
        setupActionBar();
    }

    protected View.OnClickListener createDrawerClick() {
        return view -> onBackPressed();
    }

    protected void setupActionBar() {
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_right);
    }

    public void setCurrentTitle(String title) {
        toolbar.setTitle(title);
        actionBar.setTitle(title);
        currentTitle = title;
    }

    @Override
    public void onBackPressed() {
        if (!handleBackPressed(getSupportFragmentManager())) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                super.onBackPressed();
            } else {
                finish();
            }
        }
    }

    public boolean handleBackPressed(FragmentManager manager) {
        if (manager.getFragments() == null) return false;
        for (Fragment frag : manager.getFragments()) {
            if (frag == null) continue;
            if (frag.isVisible() && frag instanceof BaseFragment) {
                if (((BaseFragment) frag).onBackPressed()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setToolbarVisibility(boolean toolbarEnabled) {
        findViewById(R.id.toolbar).setVisibility(toolbarEnabled ? View.VISIBLE : View.GONE);
    }
    private void sendResultToTarget(Class target, Fragment root, int request,
                                    int result, Intent data) {
        if (root != null) {
            //   if (!root.isAdded()) return;
        }
        List<Fragment> activeFragments = getFragments(root == null
                ? getSupportFragmentManager()
                : root.getChildFragmentManager());
        if (activeFragments == null) return;
        for (Fragment fragment : activeFragments) {
            if (fragment == null) continue;
            Class fragmentClass = fragment.getClass();
            boolean instance = target.isInstance(fragment);
            if (fragmentClass.equals(target) || instance) {
                fragment.onActivityResult(request, result, data);
                return;
            }
            sendResultToTarget(target, fragment, request, result, data);
        }
    }

    public void sendResultToTarget(Class target, int request, int result, Intent data) {
        sendResultToTarget(target, null, request, result, data);
    }

    public List<Fragment> getFragments(FragmentManager fragmentManager) {
        List<Fragment> activeFragments = new ArrayList<>();
        try {
            Method method = fragmentManager.getClass()
                    .getDeclaredMethod("getActiveFragments");
            method.setAccessible(true);
            activeFragments = (List<Fragment>) method.invoke(fragmentManager);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return activeFragments;
    }


    public Toolbar getToolbar() {
        return toolbar;
    }

    public void replaceFragment(Fragment fragment, boolean toBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (toBackStack) {
            fragmentTransaction.addToBackStack(Fragment.class.getSimpleName());
        }

        fragmentTransaction.replace(R.id.content_frame, fragment, fragment.getClass().getName())
                .commitAllowingStateLoss();
    }

    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, true);
    }
}
