package com.example.ge.soum_up.storage;

/**
 * Created by GE on 09/02/2017.
 */


import android.content.Context;
import android.content.SharedPreferences;


import com.example.ge.soum_up.model.User;

public class UserLocalStore {
    public static final String SP_Name="userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_Name,0);

    }

    public void storeUserData(User user)
    {try {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("user_ID",user.getUser_ID());
        spEditor.putString("nom",user.getNom());
        spEditor.putString("prenom",user.getPrenom());
        spEditor.putInt("age", user.getAge());
        spEditor.putString("username", user.getUsername());
        spEditor.putString("email", user.getEmail());
        spEditor.putString("adresse", user.getAdresse());
        spEditor.putString("mdp",user.getMdp());
        spEditor.putString("sexe",user.getSexe());
        spEditor.commit();
    }catch (Exception e){}


    }

    public User getLoggedInUser (){

        int user_ID = userLocalDatabase.getInt("user_ID", 1);
        String nom = userLocalDatabase.getString("nom", "");
        String prenom = userLocalDatabase.getString("prenom", "");
        int age = userLocalDatabase.getInt("age", 1);
        String username = userLocalDatabase.getString("username", "");
        String email = userLocalDatabase.getString("email","");
        String adresse = userLocalDatabase.getString("adresse","");
        String mdp = userLocalDatabase.getString("mdp","");
        String sexe = userLocalDatabase.getString("sexe","");

        User storedUser = new User(user_ID,nom,prenom,age,username,email,adresse,mdp,sexe);



        return storedUser;
    }
    public void setUserLoggedIn(boolean LoggedIn)
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("LoggedIn",LoggedIn);
        spEditor.commit();
    }
    public void clearUserData()
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
    public boolean getUserLoggedIn ()
    {
        if (userLocalDatabase.getBoolean("LoggedIn",false)== true)
            return true;
        else {
            return false;
        }
    }
}