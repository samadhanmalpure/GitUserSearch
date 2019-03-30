package autocomplete.samadhan.com.myapplication.network;

public interface NetworkCallReponseInterface<T> {
    void onSuccess(T response);
    void onComplete();
    void onResponseError(Throwable t);
}
