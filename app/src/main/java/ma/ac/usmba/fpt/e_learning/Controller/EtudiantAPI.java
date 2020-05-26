package ma.ac.usmba.fpt.e_learning.Controller;

import androidx.core.util.Consumer;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import ma.ac.usmba.fpt.e_learning.Model.Etudiant;
import ma.ac.usmba.fpt.e_learning.Model.Message;
import ma.ac.usmba.fpt.e_learning.Model.Seance;
import ma.ac.usmba.fpt.e_learning.Model.Semestre;
import ma.ac.usmba.fpt.e_learning.Utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EtudiantAPI extends NetworkUtils{

    public Etudiant Object_from_Json(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Etudiant.class);
    }
    public String Object_to_Json(Etudiant o){
        Gson gson = new Gson();
        return gson.toJson(o);
    }

    public void login(String cne, String cin, final Consumer<Etudiant> success, final Consumer<String> error){
        Call<Etudiant> call = apiEndPoint.etudient_login(
                "application/json",
                cne,
                cin
        );
        call.enqueue(new Callback<Etudiant>() {
            @Override
            public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                try {
                    if (response.code() == 200) {
                        //e.etudiant = response.body();
                        //e.openMain();
                        success.accept( response.body() );
                    } else {
                        error.accept(  "Failed to login, please check your ID or password" );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    error.accept( "Failed resp. code(" + response.code() + ") "+ ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Etudiant> call, Throwable t) {
                error.accept(  "Failed : " + t.getMessage() );
            }
        });
    }

    public void assister(String authorization, final Consumer<Seance> success, final Consumer<String> error){
        Calendar currentInstance = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String aujourdhui = df.format(currentInstance.getTime());

        HashMap<String,String> filter = new HashMap<>();
        filter.put("filter[start][value]", aujourdhui);
        filter.put("filter[end][value]", aujourdhui);
        filter.put("filter[now]", "yes");

        Call<Seance> call = apiEndPoint.etudient_course_now(
                "application/json",
                authorization,
                filter
        );
        call.enqueue(new Callback<Seance>() {
            @Override
            public void onResponse(Call<Seance> call, Response<Seance> response) {
                try {
                    if (response.code() == 200) {
                        success.accept( response.body() );
                    } else {
                        error.accept(  "no Seance now" );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    error.accept( "Failed resp. code(" + response.code() + ") "+ ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Seance> call, Throwable t) {
                error.accept(  "Failed : " + t.getMessage() );
            }
        });
    }

    public void seance(String authorization, int id, final Consumer<Seance> success, final Consumer<String> error){
        Call<Seance> call = apiEndPoint.etudient_course(
                "application/json",
                authorization,
                id
        );
        call.enqueue(new Callback<Seance>() {
            @Override
            public void onResponse(Call<Seance> call, Response<Seance> response) {
                try {
                    if (response.code() == 200) {
                        success.accept( response.body() );
                    } else {
                        error.accept(  "Seance not found" );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    error.accept( "Failed resp. code(" + response.code() + ") "+ ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Seance> call, Throwable t) {
                error.accept(  "Failed : " + t.getMessage() );
            }
        });
    }

    public void ressources(String authorization, final Consumer<ArrayList<Semestre>> success, final Consumer<String> error){
        Call<ArrayList<Semestre>> call = apiEndPoint.etudient_modules_semesters(
                "application/json",
                authorization
        );
        call.enqueue(new Callback<ArrayList<Semestre>>() {
            @Override
            public void onResponse(Call<ArrayList<Semestre>> call, Response<ArrayList<Semestre>> response) {
                try {
                    if (response.code() == 200) {
                        success.accept( response.body() );
                    } else {
                        error.accept(  "no ressources" );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    error.accept( "Failed resp. code(" + response.code() + ") "+ ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Semestre>> call, Throwable t) {
                error.accept(  "Failed : " + t.getMessage() );
            }
        });
    }

    public void seances(String authorization, HashMap filter, final Consumer<ArrayList<Seance>> success, final Consumer<String> error){
        Call<ArrayList<Seance>> call = apiEndPoint.etudient_courses(
                "application/json",
                authorization,
                filter
        );
        call.enqueue(new Callback<ArrayList<Seance>>() {
            @Override
            public void onResponse(Call<ArrayList<Seance>> call, Response<ArrayList<Seance>> response) {
                try {
                    if (response.code() == 200) {
                        success.accept( response.body() );
                    } else {
                        error.accept(  "no seances in this module" );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    error.accept( "Failed resp. code(" + response.code() + ") "+ ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Seance>> call, Throwable t) {
                error.accept(  "Failed : " + t.getMessage() );
            }
        });
    }

    public void messages(String authorization, int course_id, int id, final Consumer<ArrayList<Message>> success, final Consumer<String> error){
        Call<ArrayList<Message>> call = apiEndPoint.etudient_messages(
                "application/json",
                authorization,
                course_id,
                id
        );
        call.enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                try {
                    if (response.code() == 200) {
                        success.accept( response.body() );
                    } else {
                        error.accept(  "no seances in this module" );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    error.accept( "Failed resp. code(" + response.code() + ") "+ ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {
                error.accept(  "Failed : " + t.getMessage() );
            }
        });
    }

    public void send_message(String authorization, int course_id, String msg, final Consumer<Boolean> success, final Consumer<String> error){
        Call<Boolean> call = apiEndPoint.etudient_send_message(
                "application/json",
                authorization,
                course_id,
                msg
        );
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                try {
                    if (response.code() == 200) {
                        success.accept( response.body() );
                    } else {
                        error.accept(  "no seances in this module" );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    error.accept( "Failed resp. code(" + response.code() + ") "+ ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                error.accept(  "Failed : " + t.getMessage() );
            }
        });
    }

}
