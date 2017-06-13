package edu.hm.cs.areiser.nativecode;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreas on 5/1/17.
 */

public class JavaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater pLayoutInflater, ViewGroup pContainer, Bundle pSavedInstanceState) {
        View view = pLayoutInflater.inflate(R.layout.java_fragment, pContainer, false);
        Button button = (Button) view.findViewById(R.id.javaPrimeButton);
        final TextView textView = (TextView) view.findViewById(R.id.javaPrimeView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                int last = calculatePrimes();
                long duration = System.currentTimeMillis() - startTime;
                textView.setText(getString(R.string.finished_prime) + last + getString(R.string.duration_ms) + duration);
            }
        });

        Button factorButton = (Button) view.findViewById(R.id.javaFactorButton);
        final TextView factorText = (TextView) view.findViewById(R.id.javaFactorView);
        factorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                int fib = fibJava(40);
                long duration = System.currentTimeMillis() - startTime;
                factorText.setText(getString(R.string.finished_fib) + fib + getString(R.string.duration_ms) + duration);
            }
        });
        return view;
    }

    private int calculatePrimes() {
        //Sieb des eratosthenes
        List<Integer> list = new ArrayList<>();
        int max = 100000;
        //Select numbers from 2 to max
        for (int i = 2; i < max; i++) {
            list.add(i);
        }

        //i till square root of max
        int root = (int) Math.sqrt(max);

        for (int i = 2; i < root; i++) {
            for (int current = i; current < list.size(); current++) {
                //Check if it is dividable
                if (list.get(current) % i == 0) {
                    list.remove(current);
                    //sub 1 because the index just shrinked
                    current--;
                }
            }
        }

        return list.get(list.size() - 1);

    }

    // Java implementation of fibonacci - recursive
    public static int fibJava(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        return fibJava(n - 1) + fibJava(n - 2);
    }

}
