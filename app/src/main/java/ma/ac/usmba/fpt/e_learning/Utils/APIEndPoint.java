package ma.ac.usmba.fpt.e_learning.Utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIEndPoint {

    @GET("etudient/login")
    Call<ResponseBody> etudient_login(
            @Header("accept") String type,
            @Query("cne") String cne,
            @Query("cin") String cin
    );


    /*
    *
    * Prof
    *
    * */


    @GET("prof/login")
    Call<ResponseBody> prof_login(
            @Header("accept") String type,
            @Query("email") String matricule,
            @Query("password") String cin
    );

    /*@FormUrlEncoded
    @POST("Fees.php")
    Call<ResponseBody> Fees(@Header("accept") String type, @Field("id") String StdID);*/


}