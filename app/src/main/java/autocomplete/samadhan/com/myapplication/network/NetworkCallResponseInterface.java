package autocomplete.samadhan.com.myapplication.network;

public interface NetworkCallResponseInterface<T> {
    void onSuccess(T response);
    void onComplete();
    void onResponseError(Throwable t);
}
