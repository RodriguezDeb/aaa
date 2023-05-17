package com.example.xtypee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.example.xtypee.Service.DbUsuarios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
//import com.example.xtypee.json.MyInfo;


public class login extends AppCompatActivity {


    public static final String KEY = "+4xij6jQRSBdCymMxweza/uMYo+o0EUg";
    private String testClaro = "Hola mundo";
    private String testDesCifrado;

    public String correo;
    public String mensaje;
    // public static List<MyInfo> list;
    public static String TAG = "mensaje";
    public static String TOG = "error";
    public static String json = null;
    public static String user, pswd;
    private Button button1, button2, button3, buttonfeis, buttonIG, buttonTT, buttonGIT;
    String _url = "https://www.instagram.com/technoswamp/";
    String _urlT = "https://twitter.com/Technoswam";
    String _urlfb = "https://www.facebook.com/profile.php?id=100086036944709&mibextid=ZbWKwL";
    String _urlgit = "https://github.com/Technoswamp";
   // public MyDesUtil myDesUtil = new MyDesUtil().addStringKeyBase64(KEY);

    EditText usernameusuario = findViewById(R.id.user);
    EditText contrasennas = findViewById(R.id.pswds);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        button2 = findViewById(R.id.buttonR);
        button1 = findViewById(R.id.buttonL);
        buttonfeis = findViewById(R.id.buttonfeis);
        buttonIG = findViewById(R.id.buttonIG);
        buttonTT = findViewById(R.id.buttonTT);
        buttonGIT = findViewById(R.id.buttonGIT);
      /*  EditText usuario = findViewById(R.id.user);
        EditText pswds = findViewById(R.id.pswds); */


        buttonGIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(_urlgit);
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                startActivity(i);
            }
        });

        buttonfeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(_urlfb);
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                startActivity(i);
            }
        });

        buttonTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(_urlT);
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                startActivity(i);
            }
        });

        buttonIG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(_url);
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                startActivity(i);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = String.valueOf(usernameusuario.getText());
                pswd = String.valueOf(contrasennas.getText());
                // acceso(usr , pswd);
                if(!validateUsername() | !validatePassword() ){

                }else{
                    checkUser();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, registro.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateUsername() {
        String val = usernameusuario.getText().toString();
        if(val.isEmpty()){
            usernameusuario.setError("Escriba el nombre de usuario");
            return false;
        }else{
            usernameusuario.setError(null);
            return true;
        }
    }
    public Boolean validatePassword() {
        String val = contrasennas.getText().toString();
        if(val.isEmpty()){
            contrasennas.setError("Escriba una contraseña");
            return false;
        }else{
            contrasennas.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userUsername = usernameusuario.getText().toString().trim();
        String userPassword = contrasennas.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("user").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    usernameusuario.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("contra").getValue(String.class);
                    if(!Objects.equals(passwordFromDB, contrasennas)){
                        usernameusuario.setError(null);
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);

                    }else{
                        contrasennas.setError("Contraseña Inválida");
                        contrasennas.requestFocus();
                    }
                } else{
                  usernameusuario.setError("Usuario Inexistente");
                  usernameusuario.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

   /* public void acceso(String usr , String pswd){
        int i=0;
        if(usr.equals("")||pswd.equals("")){
            Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
        }else{
            DbUsuarios dbUsuarios = new DbUsuarios(login.this);
            MyInfo myInfo = dbUsuarios.GetUsuario(usr);
            if(myInfo!=null){
                if(myInfo.getPassword().equals(pswd)){
                    Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(login.this, MainActivity.class);
                    intent.putExtra("Objeto", myInfo);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "No se ha encontrado el usuario", Toast.LENGTH_LONG).show();
            }
        }
    } */
