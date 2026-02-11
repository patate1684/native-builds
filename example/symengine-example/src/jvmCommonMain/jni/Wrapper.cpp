#include <symengine/expression.h>
#include <symengine/eval_double.h>
#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL
Java_com_ensody_nativebuilds_example_symengine_SymengineWrapper_getSymengineVersion(
        JNIEnv *env,
        jobject type
) {
    Expression expr = sin(Expression(5));

    Expression r = expr.evalf();   // default: double
    double v = SymEngine::eval_double(*r.get_basic());
    std::string s = v.get_str();
    return env->NewStringUTF(s);
}

#ifdef __cplusplus
}
#endif
