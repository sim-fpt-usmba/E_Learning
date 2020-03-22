package ma.ac.usmba.fpt.e_learning;

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

    @FormUrlEncoded
    @POST("Grades.php")
    Call<ResponseBody> Grades(
            @Header("accept") String type,
            @Field("id") String StdID,
            @Field("year") String year,
            @Field("semester") String semester
    );

    @FormUrlEncoded
    @POST("Editeprofile.php")
    Call<ResponseBody> Editeprofile(
            @Header("accept") String type,
            @Field("id") String StdID,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("adresse") String adresse
    );

    @FormUrlEncoded
    @POST("Plan.php")
    Call<ResponseBody> Plan(
            @Header("accept") String type,
            @Field("id") String StdID
    );

    @FormUrlEncoded
    @POST("Plan_details.php")
    Call<ResponseBody> Plan_details(
            @Header("accept") String type,
            @Field("id") String StdID,
            @Field("school") String school,
            @Field("type") String label
    );

    @FormUrlEncoded
    @POST("Timetable.php")
    Call<ResponseBody> Timetable(
            @Header("accept") String type,
            @Field("id") String StdID,
            @Field("center") String center,
            @Field("code") String code
    );

    @FormUrlEncoded
    @POST("Registration.php")
    Call<ResponseBody> Registration(
            @Header("accept") String type,
            @Field("id") String StdID,
            @Field("cours_id") int program_id,
            @Field("year") String year,
            @Field("semester") String semester,
            @Field("filiere_id") int filiere_id,
            @Field("role") String role
            //,@Field("ids") String ids
            );

    @FormUrlEncoded
    @POST("Results.php")
    Call<ResponseBody> Results(@Header("accept") String type, @Field("id") String StdID, @Field("year") String Year);


    @FormUrlEncoded
    @POST("Fees.php")
    Call<ResponseBody> Fees(@Header("accept") String type, @Field("id") String StdID);


}