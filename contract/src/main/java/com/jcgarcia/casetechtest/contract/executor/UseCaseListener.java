package com.jcgarcia.casetechtest.contract.executor;

/**
 * Created by jcgarcia on 9/2/17.
 */

public interface UseCaseListener<T> {
    void onSuccess(T value);
    void onError(Object e);
}
