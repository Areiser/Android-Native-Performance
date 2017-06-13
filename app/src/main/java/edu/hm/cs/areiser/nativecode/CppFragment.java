package edu.hm.cs.areiser.nativecode;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by andreas on 5/1/17.
 */

public class CppFragment extends Fragment {

    static {
        System.loadLibrary("native-lib");
    }

    /**
     * Calculates primes.
     * @return Max prime
     */
    public native int calculatePrimes();


    /**
     * Native implementation of fibonacci numbers - recursive
     * @param n - what fibonacci number to calculate
     * @return the n'th fibonacci number
     */
    public static native int fibNative(int n);

    @Override
    public View onCreateView(LayoutInflater pLayoutInflater, ViewGroup pContainer, Bundle pSavedInstanceState) {
        View view = pLayoutInflater.inflate(R.layout.cpp_fragment, pContainer, false);
        final Button primeButton = (Button) view.findViewById(R.id.cppPrimeButton);
        final TextView primeText = (TextView) view.findViewById(R.id.cppPrimeView);
        primeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                int last = calculatePrimes();
                long duration = System.currentTimeMillis() - startTime;
                primeText.setText(getString(R.string.finished_prime) + last + getString(R.string.duration_ms) + duration);
            }
        });

        Button factorButton = (Button) view.findViewById(R.id.cppFactorButton);
        final TextView factorText = (TextView) view.findViewById(R.id.cppFactorView);
        factorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                int fib = fibNative(40);
                long duration = System.currentTimeMillis() - startTime;
                factorText.setText(getString(R.string.finished_fib) + fib + getString(R.string.duration_ms) + duration);
            }
        });
        return view;
    }

}
