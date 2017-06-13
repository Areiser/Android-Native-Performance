#include <jni.h>
#include <string>
#include <vector>
#include <math.h>

using namespace std;

extern "C"
JNIEXPORT jint JNICALL
Java_edu_hm_cs_areiser_nativecode_CppFragment_calculatePrimes(JNIEnv *env,
                                                              jobject /* this */) {
    //Sieb des eratosthenes
    vector<int> *vec = new vector<int>();
    int max = 100000;
    //Select numbers from 2 to max
    for (int i = 2; i < max; i++) {
        vec->push_back(i);
    }

    //i till square root of max
    int root = (int) sqrt(max);
    for (int i = 2; i < root; i++) {
        for (vector<int>::iterator it = vec->begin() + i; it < vec->end();) {
            //CHeck if a bigger than i and if it is dividable
            if (*it % i == 0) {
                it = vec->erase(it);
            } else {
                it++;
            }
        }
    }

    int last = vec->at(vec->size() - 1);
    return last;
}


jint fibNative(jint n) {
    if(n<=0) return 0;
    if(n==1) return 1;
    return fibNative(n - 1) + fibNative(n - 2);
}

extern "C"
JNIEXPORT jint JNICALL Java_edu_hm_cs_areiser_nativecode_CppFragment_fibNative
        (JNIEnv *env, jclass obj, jint  n) {
    return fibNative(n);
}
