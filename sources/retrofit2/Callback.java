package retrofit2;

/* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/Callback.class */
public interface Callback<T> {
    void onResponse(Call<T> call, Response<T> response);

    void onFailure(Call<T> call, Throwable th);
}
