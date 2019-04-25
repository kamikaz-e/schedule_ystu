package com.example.misha.myapplication.activity;

import android.content.Intent;

import com.example.misha.myapplication.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseFragment extends Fragment {

    private void sendResultToTarget(Class target, Fragment root, int request,
                                    int result, Intent data) {
        if (root != null) {
            //   if (!root.isAdded()) return;
        }
        List<Fragment> activeFragments = getFragments(root == null
                ? getChildFragmentManager()
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

    public void replaceFragment(Fragment fragment, boolean toBackStack) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (toBackStack) {
            fragmentTransaction.addToBackStack(Fragment.class.getSimpleName());
        }
        fragmentTransaction.replace(R.id.contentFrame, fragment, fragment.getClass().getName())
                .commitAllowingStateLoss();
    }

    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, false);
    }
}
