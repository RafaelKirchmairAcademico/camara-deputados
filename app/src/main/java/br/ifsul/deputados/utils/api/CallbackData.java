package br.ifsul.deputados.utils.api;

public interface CallbackData<T> {

    void onSuccess(T data);

    void onUnsuccess(String message);

    void onFailure(String message);

}
