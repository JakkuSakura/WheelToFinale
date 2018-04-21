package top.zhuoxinsocial;

import java.lang.reflect.Method;

public class Tools {

    static public Object invokeMethod(Object owner, String methodName,
                               Object[] args) throws Exception {
        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {

            argsClass[i] = args[i].getClass();
        }

        Method method = owner.getClass().getMethod(methodName, argsClass);
        return method.invoke(owner, args);
    }

}
