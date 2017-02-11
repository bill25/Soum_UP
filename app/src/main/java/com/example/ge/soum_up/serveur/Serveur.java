package com.example.ge.soum_up.serveur;

import android.app.ProgressDialog;
import android.content.Context;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;


import com.example.ge.soum_up.interfaces.GetUserCallback;
import com.example.ge.soum_up.model.User;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Serveur {

    ProgressDialog progressDialog;
    User returnedUser = null;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;




  //constructeur
    public Serveur(Context context) {

    }



    public void Login(User user, GetUserCallback userCallBack) {

        new Login(user, userCallBack).execute();
    }




    private class Login extends AsyncTask<Void, Void, User>
    {

        User user;
        GetUserCallback userCallBack;
        public Login(User user, GetUserCallback userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
        }



        HttpURLConnection conn;
        URL url = null;

// cette methode retourne utilisateur recuperer de la base de donnée et le renvoie a la methode onpostexecute qui s'execute dans UI thread en non pas dans doinbackgrond
        @Override
        protected User doInBackground(Void... params) {
            try {

                //  URL address where  php file resides
                url = new URL("http://www.hitech_25.netne.net/b1.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return returnedUser;
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");


                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Add parameters to URL

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username",user.getUsername() )
                        .appendQueryParameter("password",user.getMdp());

                String query = builder.build().getEncodedQuery();



                // Open connection for sending data
                OutputStream os = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));

                writer.write(query);

                writer.flush();
                writer.close();
                os.close();
                conn.connect();


            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return  returnedUser;
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                         Log.v("resulata",line);

                    }

                    try {
                        //construction de l'objet user depuis le resltat envoyer par le serveur

                        JSONObject user1 = new JSONObject(result.toString());
                        String nom = user1.getString("nom");
                        String prenom=user1.getString("prenom");

                        String email=user1.getString("email");
                        String adresse=user1.getString("adresse");

                        String sexe=user1.getString("sexe");
                        int user_id=user1.getInt("user_ID");
                        int age = user1.getInt("age");
                        Log.v("les detail ", nom+" " +prenom+" "+email+" "+adresse+" "+sexe+" "+user_id+" "+age );
                        returnedUser = new User(user_id,nom,prenom, age, user.getUsername(),email,adresse,
                                user.getMdp(),sexe);

                    } catch (JSONException e) {
                        return returnedUser;
                    }


                    // Pass data to onPostExecute method
                    return returnedUser;

                }else{

                    return returnedUser;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return returnedUser;
            }


        }

        @Override
        protected void onPostExecute(User returnedUser) {
            super.onPostExecute(returnedUser);
            userCallBack.done(returnedUser);
        }

    }


}